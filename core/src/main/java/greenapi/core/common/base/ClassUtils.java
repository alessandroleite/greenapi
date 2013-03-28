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

import net.vidageek.mirror.dsl.Mirror;

import org.apache.log4j.Logger;

public final class ClassUtils
{
    /**
     * Private constructor. It's never invoked.
     */
    private ClassUtils()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the {@link ClassLoader} of this {@link Class}.
     * 
     * @return the {@link ClassLoader} of this {@link Class}.
     */
    public static ClassLoader getDefaultClassLoader()
    {
        ClassLoader cl = null;
        try
        {
            cl = Thread.currentThread().getContextClassLoader();
        }
        catch (Throwable ignore)
        {
            if (Logger.getLogger(ClassUtils.class).isDebugEnabled())
            {
                Logger.getLogger(ClassUtils.class).debug(ignore.getMessage(), ignore);
            }
        }

        if (cl == null)
        {
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
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
    @SuppressWarnings({"unchecked" })
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
