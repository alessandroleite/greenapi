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

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javassist.bytecode.ClassFile;
import net.vidageek.mirror.dsl.Mirror;

import org.apache.log4j.Logger;

@SuppressWarnings("rawtypes")
public final class ClassUtils
{

    private static final String CLASS_SUFFIX = ".class";

    private static final String REGEX_DOT = "\\.";

    private static final String FILE_SEPARATOR = File.separatorChar + "";

    /**
     * Private constructor to avoid instance of this class. It's never invoked.
     */
    private ClassUtils()
    {
        throw new UnsupportedOperationException();
    }

    public static class ClassFinderException extends RuntimeException
    {

        private static final long serialVersionUID = 5309532665686994276L;

        public ClassFinderException(String msg, Throwable cause)
        {
            super(msg, cause);
        }
    }

    public static boolean isApplicationJar()
    {
        try
        {
            File f = new File(ClassUtils.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            return f.isFile() && f.getAbsolutePath().endsWith(".jar");
        }
        catch (URISyntaxException e)
        {
            throw new ClassFinderException(e.getMessage(), e);
        }
    }

    /**
     * Return the default ClassLoader to use: typically the thread context ClassLoader, if available; the ClassLoader that loaded the ClassUtils class
     * will be used as fallback.
     * 
     * @return the default ClassLoader (never <code>null</code>)
     * @see java.lang.Thread#getContextClassLoader()
     */
    public static ClassLoader getDefaultClassLoader()
    {
        ClassLoader cl = null;
        try
        {
            cl = Thread.currentThread().getContextClassLoader();
        }
        catch (Throwable ex)
        {
            if (Logger.getLogger(ClassUtils.class).isDebugEnabled())
            {
                Logger.getLogger(ClassUtils.class).debug(ex.getMessage(), ex);
            }
        }
        if (cl == null)
        {
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

    /**
     * Returns the current application root path.
     * 
     * @return the current application root path.
     */
    public static String getAppPath()
    {
        return getAppPath(ClassUtils.class);
    }

    /**
     * Returns the current application root path.
     * 
     * @param base
     *            The class to be consider as the code source.
     * @return the current application root path.
     */
    public static String getAppPath(Class<?> base)
    {
        try
        {
            File f = new File(base.getProtectionDomain().getCodeSource().getLocation().toURI());
            return f.getAbsolutePath();
        }
        catch (URISyntaxException e)
        {
            throw new ClassFinderException(e.getMessage(), e);
        }
    }

    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     * 
     * @param packageName
     *            The base package. Might not be <code>null</code>.
     * @return A read only {@link List} with the classes found in the given package.
     * @throws ClassFinderException
     *             If the classes could not be read.
     */
    public static List<Class> getApplicationClasses(String packageName)
    {
        return getApplicationClasses(ClassUtils.class, packageName);
    }

    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     * 
     * @param base
     *            The base class to be used as reference of location.
     * @param packageName
     *            The base package. Might not be <code>null</code>.
     * @return A read only {@link List} with the classes found in the given package.
     * @throws ClassFinderException
     *             If the classes could not be read.
     */
    public static List<Class> getApplicationClasses(Class<?> base, String packageName)
    {
        try
        {
            File f = new File(base.getProtectionDomain().getCodeSource().getLocation().toURI());

            List<Class> classes = null;

            if (f.isDirectory())
            {
                classes = findClassesInsideDir(f, packageName);
            }
            else if (f.isFile())
            {
                classes = findClassesInsideJar(f, packageName);
            }
            else
            {
                throw new ClassFinderException("Cannot find classes", null);
            }
            return Collections.unmodifiableList(classes);
        }
        catch (ClassNotFoundException | URISyntaxException | IOException e)
        {
            throw new ClassFinderException(e.getMessage(), e);
        }
    }

    public static List<Class> filterConcreteClasses(List<Class> classes)
    {
        List<Class> filtered = new ArrayList<>(classes);

        for (Iterator<Class> iter = filtered.iterator(); iter.hasNext();)
        {
            Class<?> clazz = iter.next();
            if (clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers()))
            {
                iter.remove();
            }
        }
        return Collections.unmodifiableList(filtered);
    }

    public static List<Class> findSubclasses(Class<?> clazz)
    {
        return findSubclasses(clazz, clazz, clazz.getPackage().getName());
    }

    //

    /**
     * 
     * @param superClassOrInterface
     *            Classes of superclass/interface on which to search.
     * @param codeSource
     *            The class to be used as the domain.
     * @param packageName
     *            The package name.
     * @return A read only {@link List} with the subclasses of the given {@link Class} or empyt if none was found.
     */
    public static List<Class> findSubclasses(Class<?> superClassOrInterface, Class<?> codeSource, String packageName)
    {
        List<Class> classes = new LinkedList<>(filterConcreteClasses(getApplicationClasses(codeSource, packageName)));

        for (Iterator<Class> iter = classes.iterator(); iter.hasNext();)
        {
            Class<?> sub = iter.next();
            if (!superClassOrInterface.isAssignableFrom(sub))
            {
                iter.remove();
            }
        }
        return Collections.unmodifiableList(classes);
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     * 
     * @param directory
     *            The base directory
     * @param packageName
     *            The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private static List<Class> findClassesInsideDir(File directory, String packageName) throws ClassNotFoundException
    {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists())
        {
            return classes;
        }

        List<File> files = new ArrayList<File>(Arrays.asList(directory.listFiles()));
        while (!files.isEmpty())
        {
            File file = files.get(0);
            files.remove(file);
            if (file.isDirectory())
            {
                files.addAll(Arrays.asList(file.listFiles()));
            }
            else if (file.isFile())
            {
                Class clazz = fileNameToClass(file.getAbsolutePath(), packageName);
                if (clazz != null)
                {
                    classes.add(clazz);
                }
            }
        }
        return classes;
    }

    public static List<Class> findClassesInsideJar(File jar, String packageName) throws URISyntaxException, ZipException, IOException,
            ClassNotFoundException
    {
        ZipFile zf = new ZipFile(jar.getAbsoluteFile());
        Enumeration e = zf.entries();

        List<Class> classes = new ArrayList<Class>();
        while (e.hasMoreElements())
        {
            ZipEntry ze = (ZipEntry) e.nextElement();
            Class clazz = null;
            try
            {
                clazz = fileNameToClass(ze.getName(), packageName);
            }
            catch (ClassNotFoundException | NoClassDefFoundError ignore)
            {
                // do nothing
            }

            if (clazz != null)
            {
                classes.add(clazz);
            }
        }
        zf.close();
        return classes;
    }

    private static Class fileNameToClass(String fileName, String packageName) throws ClassNotFoundException
    {
        String path = packageName.replaceAll(REGEX_DOT, FILE_SEPARATOR);
        if (fileName.contains(path) && fileName.endsWith(CLASS_SUFFIX))
        {
            return Class.forName(fileName.substring(fileName.indexOf(path), fileName.length() - CLASS_SUFFIX.length()).replaceAll(FILE_SEPARATOR,
                    REGEX_DOT));
        }
        else
        {
            return null;
        }
    }

    /**
     * Return the path(jar o directory) where the given class was found.
     * 
     * @param clazz
     *            The class to be verified
     * @return A not <code>null</code> {@link String} with the path of the given class.
     */
    public static String getClassLocation(Class<?> clazz)
    {
        String className = "/" + clazz.getName().replace('.', '/') + ".class";
        URL u = clazz.getResource(className);
        String s = u.toString();
        if (s.startsWith("jar:file:/"))
        {
            int pos = s.indexOf(".jar!/");
            if (pos != -1)
            {
                if (File.separator.equals("\\"))
                    s = s.substring("jar:file:/".length(), pos + ".jar".length());
                else
                    s = s.substring("jar:file:".length(), pos + ".jar".length());
                s = s.replaceAll("%20", " ");
            }
            else
            {
                s = "?";
            }
        }
        else if (s.startsWith("file:/"))
        {
            if (File.separator.equals("\\"))
                s = s.substring("file:/".length());
            else
                s = s.substring("file:".length());
            s = s.substring(0, s.lastIndexOf(className)).replaceAll("%20", " ");
        }
        else
        {
            s = "?";
        }
        return s;
    }

    public static Class<?>[] getClassesWith(String packageName, Class<? extends Annotation> annotation) throws IOException, ClassNotFoundException
    {

        if (annotation == null)
        {
            throw new NullPointerException();
        }

        final List<Class<?>> result = new ArrayList<Class<?>>();
        Class<?>[] classes = getClasses(getDefaultClassLoader(), packageName);

        if (classes != null)
        {
            for (Class<?> clazz : classes)
            {
                if (clazz.isAnnotationPresent(annotation))
                {
                    result.add(clazz);
                }
            }
        }
        return result.toArray(new Class<?>[result.size()]);

    }

    public static ClassFile getClassFile(Class<?> clazz) throws IOException
    {
        String className = clazz.getSimpleName() + ".class";

        if (clazz.getPackage() != null)
        {
            String tPackageName = clazz.getPackage().getName();
            String tClassName = clazz.getSimpleName();
            tPackageName = tPackageName.replace('.', '/');
            className = tPackageName + '/' + tClassName + ".class";
        }

        try (InputStream input = clazz.getClassLoader().getResourceAsStream(className); BufferedInputStream bis = new BufferedInputStream(input))
        {
            ClassFile cf = new ClassFile(new DataInputStream(bis));
            return cf;
        }
    }

    public static Class<?>[] getClasses(ClassLoader classLoader, String packageName) throws IOException, ClassNotFoundException
    {

        if (classLoader != null)
        {

            String path = packageName.replace('.', '/');
            Enumeration<URL> resources = classLoader.getResources(path);

            List<File> dirs = new ArrayList<File>();

            while (resources.hasMoreElements())
            {
                URL resource = resources.nextElement();
                dirs.add(new File(resource.getFile()));
            }

            ArrayList<Class<?>> classes = new ArrayList<Class<?>>();

            for (File directory : dirs)
            {
                classes.addAll(findClasses(directory, packageName));
            }

            return classes.toArray(new Class[classes.size()]);
        }
        return null;
    }

    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException
    {
        List<Class<?>> classes = new ArrayList<Class<?>>();

        if (!directory.exists())
        {
            return classes;
        }

        File[] files = directory.listFiles();
        for (File file : files)
        {
            if (file.isDirectory())
            {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            }
            else if (file.getName().endsWith(".class"))
            {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    public static Class<?>[] getClasses(String packageName) throws IOException, ClassNotFoundException
    {
        return getClasses(getDefaultClassLoader(), packageName);
    }

    /**
     * Create a instance of a given class.
     * 
     * @param fullClassName
     *            The name of the class to be instantiate.
     * @param args
     *            Arguments to use when invoking this constructor
     * @param <T>
     *            The type of the given class.
     * @return An instance of the given class. Can be <code>null</code> if the class wasn't found.
     */
    @SuppressWarnings({ "unchecked" })
    public static <T> T newInstanceForName(String fullClassName, Object... args)
    {
        try
        {
            return (T) newInstanceForName(Class.forName(fullClassName), args);
        }
        catch (ClassNotFoundException exception)
        {
            return null;
        }
    }

    /**
     * Create a instance of a given class.
     * 
     * @param clazz
     *            The class to be instantiate.
     * @param args
     *            Arguments to use when invoking this constructor
     * @param <T>
     *            The type of the given class.
     * @return An instance of the given class.
     */
    public static <T> T newInstanceForName(Class<T> clazz, Object... args)
    {
        if (args == null || args.length == 0)
        {
            return new Mirror().on(clazz).invoke().constructor().withoutArgs();
        }
        else
        {
            return new Mirror().on(clazz).invoke().constructor().withArgs(args);
        }
    }
}
