/**
 * Copyright (c) 2012 GreenI2R
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package greenapi.core.common.base;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * This utility class was based originally on <a href="private.php?do=newpm&u=47838">Daniel Le Berre</a>'s <code>RTSI</code> class. This class can be
 * called in different modes, but the principal use is to determine what subclasses/implementations of a given class/interface exist in the current
 * runtime environment.
 * 
 * @author Daniel Le Berre, Elliott Wade
 */
public final class ClassFinder
{

    /**
     * Directory's {@link FileFilter}.
     */
    private static final FileFilter DIRECTORIES_ONLY = new FileFilter()
    {
        public boolean accept(File f)
        {
            return f.exists() && f.isDirectory();
        }
    };

    /**
     * {@link URL} {@link Comparator}.
     */
    private static final Comparator<URL> URL_COMPARATOR = new Comparator<URL>()
    {
        public int compare(URL u1, URL u2)
        {
            return String.valueOf(u1).compareTo(String.valueOf(u2));
        }
    };

    /**
     * {@link Class} {@link Comparator}.
     */
    private static final Comparator<Class<?>> CLASS_COMPARATOR = new Comparator<Class<?>>()
    {
        public int compare(Class<?> c1, Class<?> c2)
        {
            return String.valueOf(c1).compareTo(String.valueOf(c2));
        }
    };

    /**
     * The class to be search.
     */
    private Class<?> searchClass;

    /**
     * The classpath's locations.
     */
    private Map<URL, String> classpathLocations = new HashMap<URL, String>();

    /**
     * The classes founded.
     */
    private Map<Class<?>, URL> results = new HashMap<Class<?>, URL>();

    /**
     * The errors occurred during the execution.
     */
    private List<Throwable> errors = new ArrayList<Throwable>();

    /**
     * Default constructor.
     */
    public ClassFinder()
    {
        refreshLocations();
    }

    /**
     * Rescan the classpath, cacheing all possible file locations.
     */
    public void refreshLocations()
    {
        synchronized (classpathLocations)
        {
            classpathLocations = getClasspathLocations();
        }
    }

    /**
     * Returns the subclasses of a given class/interface.
     * 
     * @param superclass
     *            The superclass/interface of the classes to returned.
     * @return A read-only {@link Collection} with the subclasses of the given superclass.
     */
    public Collection<Class<?>> findSubclasses(Class<?> superclass)
    {
        return this.findSubclasses(superclass.getName());
    }

    /**
     * @param fqcn
     *            Name of superclass/interface on which to search.
     * @return The subclasses of the given superclass/interface.
     */
    public Collection<Class<?>> findSubclasses(String fqcn)
    {
        synchronized (classpathLocations)
        {
            synchronized (results)
            {
                searchClass = null;
                errors = new ArrayList<Throwable>();
                results = new TreeMap<Class<?>, URL>(CLASS_COMPARATOR);

                //
                // filter malformed FQCN
                //
                if (fqcn.startsWith(".") || fqcn.endsWith("."))
                {
                    return Collections.unmodifiableCollection(Collections.<Class<?>> emptyList());
                }

                //
                // Determine search class from fqcn
                //
                try
                {
                    searchClass = Class.forName(fqcn);
                }
                catch (ClassNotFoundException ex)
                {
                    errors.add(ex);
                    return Collections.unmodifiableCollection(Collections.<Class<?>> emptyList());
                }

                return Collections.unmodifiableCollection(findSubclasses(searchClass, classpathLocations));
            }
        }
    }

    /**
     * Returns a read-only {@link Collection} with the errors.
     * 
     * @return A read-only {@link Collection} with the errors.
     */
    public Collection<Throwable> getErrors()
    {
        return Collections.unmodifiableCollection(errors);
    }

    /**
     * The result of the last search is cached in this object, along with the URL that corresponds to each class returned. This method may be called
     * to query the cache for the location at which the given class was found.
     * 
     * @param cls
     *            The {@link Class} to be returned.
     * @return The {@link URL} of the given {@link Class}. The return can be <code>null</code> if the given class was not found during the last
     *         search, or if the result cache has been cleared.
     */
    public URL getLocationOf(Class<?> cls)
    {
        if (results != null)
        {
            return results.get(cls);
        }
        else
        {
            return null;
        }
    }

    /**
     * Determine every URL location defined by the current classpath, and it's associated package name.
     * 
     * @return A {@link Map} with the {@link URL} location defined by the current classpath.
     */
    public Map<URL, String> getClasspathLocations()
    {
        Map<URL, String> map = new TreeMap<URL, String>(URL_COMPARATOR);
        File file = null;

        String pathSep = System.getProperty("path.separator");
        String classpath = System.getProperty("java.class.path");

        StringTokenizer st = new StringTokenizer(classpath, pathSep);
        while (st.hasMoreTokens())
        {
            String path = st.nextToken();
            file = new File(path);
            include(null, file, map);
        }

        return map;
    }

    /**
     * Include a given package in the set of classes.
     * 
     * @param name
     *            The package.
     * @param file
     *            The file of the package
     * @param map
     *            The {@link Map} with the locations.
     */
    private void include(final String name, File file, final Map<URL, String> map)
    {
        if (!file.exists())
        {
            return;
        }
        if (!file.isDirectory())
        {
            // could be a JAR file
            includeJar(file, map);
            return;
        }

        String path = name == null ? "" : ".";

        // add subpackages
        File[] dirs = file.listFiles(DIRECTORIES_ONLY);
        for (int i = 0; i < dirs.length; i++)
        {
            try
            {
                // add the present package
                map.put(new URL("file://" + dirs[i].getCanonicalPath()), path + dirs[i].getName());
            }
            catch (IOException ioe)
            {
                return;
            }

            include(path + dirs[i].getName(), dirs[i], map);
        }
    }

    /**
     * Includes a given file (jar) in the {@link Map}.
     * 
     * @param file
     *            The file to be included.
     * @param map
     *            The {@link Map} with the jars.
     */
    private void includeJar(File file, Map<URL, String> map)
    {
        if (file.isDirectory())
            return;

        URL jarURL = null;
        JarFile jar = null;
        try
        {
            jarURL = new URL("file:/" + file.getCanonicalPath());
            jarURL = new URL("jar:" + jarURL.toExternalForm() + "!/");
            JarURLConnection conn = (JarURLConnection) jarURL.openConnection();
            jar = conn.getJarFile();
        }
        catch (Exception e)
        {
            // not a JAR or disk I/O error
            // either way, just skip
            return;
        }

        if (jar == null || jarURL == null)
        {
            return;
        }

        // include the jar's "default" package (i.e. jar's root)
        map.put(jarURL, "");

        Enumeration<JarEntry> e = jar.entries();
        while (e.hasMoreElements())
        {
            JarEntry entry = e.nextElement();

            if (entry.isDirectory())
            {
                if (entry.getName().toUpperCase().equals("META-INF/"))
                {
                    continue;
                }

                try
                {
                    map.put(new URL(jarURL.toExternalForm() + entry.getName()), packageNameFor(entry));
                }
                catch (MalformedURLException murl)
                {
                    // whacky entry?
                    continue;
                }
            }
        }
    }

    /**
     * Returns the package name for a given {@link JarEntry}.
     * 
     * @param entry
     *            The {@link JarEntry} to return the package name.
     * @return the package name for a given {@link JarEntry}.
     */
    private static String packageNameFor(JarEntry entry)
    {
        if (entry == null)
        {
            return "";
        }
        String s = entry.getName();
        if (s == null)
        {
            return "";
        }
        if (s.length() == 0)
        {
            return s;
        }
        if (s.startsWith("/"))
        {
            s = s.substring(1, s.length());
        }
        if (s.endsWith("/"))
        {
            s = s.substring(0, s.length() - 1);
        }
        return s.replace('/', '.');
    }

    // private final void includeResourceLocations(String packageName, Map<URL, String> map)
    // {
    // try
    // {
    // Enumeration<URL> resourceLocations = ClassFinder.class.getClassLoader().getResources(getPackagePath(packageName));
    //
    // while (resourceLocations.hasMoreElements())
    // {
    // map.put(resourceLocations.nextElement(), packageName);
    // }
    // }
    // catch (Exception e)
    // {
    // // well, we tried
    // errors.add(e);
    // return;
    // }
    // }

    /**
     * @param superClass
     *            The superclass to be used.
     * @param locations
     *            The location of the classes.
     * @return A {@link Collection} with all subclasses of a given super-class.
     */
    private Collection<Class<?>> findSubclasses(Class<?> superClass, Map<URL, String> locations)
    {
        Collection<Class<?>> v = new ArrayList<Class<?>>();

        Collection<Class<?>> w = null;

        Iterator<URL> it = locations.keySet().iterator();
        while (it.hasNext())
        {
            URL url = it.next();
            w = findSubclasses(url, locations.get(url), superClass);
            if (w != null && (w.size() > 0))
            {
                v.addAll(w);
            }
        }

        return v;
    }

    /**
     * Find the subclasses of a given super-class.
     * 
     * @param location
     *            The location to find the subclasses.
     * @param packageName
     *            The package name.
     * @param superClass
     *            The super-class.
     * @return A collection with the subclasses founded.
     */
    private Collection<Class<?>> findSubclasses(URL location, String packageName, Class<?> superClass)
    {
        synchronized (results)
        {
            // hash guarantees unique names...
            Map<Class<?>, URL> thisResult = new TreeMap<Class<?>, URL>(CLASS_COMPARATOR);
            Collection<Class<?>> v = new ArrayList<Class<?>>();

            String fqcn = searchClass.getName();

            List<URL> knownLocations = new ArrayList<URL>();
            knownLocations.add(location);

            // iterate matching package locations...
            for (int loc = 0; loc < knownLocations.size(); loc++)
            {
                URL url = knownLocations.get(loc);

                // Get a File object for the package
                File directory = new File(url.getFile());

                if (directory.exists())
                {
                    // Get the list of the files contained in the package
                    String[] files = directory.list();
                    for (int i = 0; i < files.length; i++)
                    {
                        // we are only interested in .class files
                        if (files[i].endsWith(".class"))
                        {
                            // removes the .class extension
                            String classname = files[i].substring(0, files[i].length() - 6);

                            try
                            {
                                Class<?> c = Class.forName(packageName + "." + classname);
                                if (superClass.isAssignableFrom(c) && !fqcn.equals(packageName + "." + classname))
                                {
                                    thisResult.put(c, url);
                                }
                            }
                            catch (Exception ex)
                            {
                                errors.add(ex);
                            }
                        }
                    }
                }
                else
                {
                    try
                    {
                        // It does not work with the filesystem: we must
                        // be in the case of a package contained in a jar file.
                        JarURLConnection conn = (JarURLConnection) url.openConnection();
                        // String starts = conn.getEntryName();
                        JarFile jarFile = conn.getJarFile();

                        Enumeration<JarEntry> e = jarFile.entries();
                        while (e.hasMoreElements())
                        {
                            JarEntry entry = e.nextElement();
                            String entryname = entry.getName();

                            if (!entry.isDirectory() && entryname.endsWith(".class"))
                            {
                                String classname = entryname.substring(0, entryname.length() - 6);
                                if (classname.startsWith("/"))
                                {
                                    classname = classname.substring(1);
                                }
                                classname = classname.replace('/', '.');

                                try
                                {
                                    Class<?> c = Class.forName(classname);

                                    if (superClass.isAssignableFrom(c) && !fqcn.equals(classname))
                                    {
                                        thisResult.put(c, url);
                                    }
                                }
                                catch (Exception | Error exception)
                                {
                                    // that's strange since we're scanning
                                    // the same classpath the classloader's
                                    // using... oh, well
                                    errors.add(exception);
                                }
                            }
                        }
                    }
                    catch (IOException ioex)
                    {
                        errors.add(ioex);
                    }
                }
            } // while

            results.putAll(thisResult);

            Iterator<Class<?>> it = thisResult.keySet().iterator();
            while (it.hasNext())
            {
                v.add(it.next());
            }
            return v;

        } // synch results
    }

    /**
     * Returns the path of a given package.
     * 
     * @param packageName
     *            The package to return the path.
     * @return the path of the given package.
     */
    protected static String getPackagePath(String packageName)
    {
        // Translate the package name into an "absolute" path
        String path = new String(packageName);
        if (!path.startsWith("/"))
        {
            path = "/" + path;
        }
        path = path.replace('.', '/');

        // ending with "/" indicates a directory to the classloader
        if (!path.endsWith("/"))
        {
            path += "/";
        }

        // for actual classloader interface (NOT Class.getResource() which
        // hacks up the request string!) a resource beginning with a "/"
        // will never be found!!! (unless it's at the root, maybe?)
        if (path.startsWith("/"))
        {
            path = path.substring(1, path.length());
        }
        return path;
    }
}
