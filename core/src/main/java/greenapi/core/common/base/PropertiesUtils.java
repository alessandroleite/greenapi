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

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Properties;

import org.apache.log4j.Logger;

public final class PropertiesUtils
{

    /**
     * Default constructor that's never called.
     */
    private PropertiesUtils()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a {@link Properties} loaded by a given {@link InputStream}.
     * 
     * @param input
     *            The {@link InputStream} to load the {@link Properties}. Might not be <code>null</code>.
     * @return The {@link Properties} load of the {@link InputStream}.
     * @throws IOException
     *             If it's impossible to load the {@link Properties}.
     */
    public static Properties load(InputStream input) throws IOException
    {
        Properties properties = new Properties();
        properties.load(Objects.requireNonNull(input));
        return properties;
    }

    /**
     * Returns a {@link Properties} loaded by a given properties file.
     * 
     * @param name
     *            The path of the {@link Properties} file to be loaded. Might not be <code>null</code>.
     * @return The {@link Properties} load of the given file.
     * @throws IOException
     *             If the given file does not exist.
     */
    public static Properties load(String name) throws IOException
    {
        return load(ClassUtils.getDefaultClassLoader().getResourceAsStream(Objects.requireNonNull(name)));
    }

    /**
     * Returns a {@link Properties} loaded by a given properties file but only if it's available.
     * 
     * @param name
     *            The path of the {@link Properties} file to be loaded. Might not be <code>null</code>.
     * @return The {@link Properties} load of the given file or <code>null</code> if it's not available.
     */
    public static Properties loadIfAvailable(String name)
    {
        Properties properties = null;
        try
        {
            properties = load(name);
        }
        catch (IOException ignore)
        {
            Logger.getLogger(PropertiesUtils.class).debug(ignore.getMessage(), ignore);
        }
        return properties;
    }

    /**
     * Returns a {@link Properties} loaded by a given properties file. The given file name is searched in all the context.
     * 
     * @param name
     *            The path of the {@link Properties} file to be loaded. Might not be <code>null</code>.
     * @return The {@link Properties} load of the given file.
     * @throws IOException
     *             If the given file does not exist.
     */
    public static Properties loadPropertyFile(String name) throws IOException
    {
        InputStream is = ClassUtils.getDefaultClassLoader().getResourceAsStream("/properties/" + name);

        if (is == null)
        {
            is = ClassUtils.getDefaultClassLoader().getResourceAsStream(name);
        }

        if (is == null)
        {
            is = ClassUtils.getDefaultClassLoader().getResourceAsStream("/" + name);
        }

        if (is == null)
        {
            throw new IOException("Properties file " + name + " not found in any context. ");
        }

        return load(is);
    }

    /**
     * Write the {@link Properties} entries in a given {@link PrintStream} one per line. The format is key = value.
     * 
     * @param properties
     *            The properties do be printed.
     * @param output
     *            The {@link PrintStream} where the entries must be printed
     */
    public static void print(Properties properties, PrintStream output)
    {
        for (Enumeration<?> propertyNames = properties.propertyNames(); propertyNames.hasMoreElements();)
        {
            String propertyName = propertyNames.nextElement().toString();
            String propertyValue = properties.getProperty(propertyName);
            output.println(propertyName + "=" + propertyValue);
        }
    }

    /**
     * Copy all entries of a given properties to the system' property.
     * 
     * @param properties
     *            The {@link Properties} to be copied to system property.
     */
    public static void copyToSystemProperty(Properties properties)
    {
        if (properties != null)
        {
            copy(properties, System.getProperties());
        }
    }

    /**
     * Copy all entries of a given properties to another.
     * 
     * @param origin
     *            The properties to be copied.
     * @param dest
     *            The properties instance to receive the source entries.
     */
    public static void copy(Properties origin, Properties dest)
    {
        if (origin != null && dest != null)
        {
            for (Enumeration<?> propertyNames = origin.propertyNames(); propertyNames.hasMoreElements();)
            {
                String propertyName = propertyNames.nextElement().toString();
                String propertyValue = origin.getProperty(propertyName);
                dest.put(propertyName, propertyValue);
            }
        }
    }
}
