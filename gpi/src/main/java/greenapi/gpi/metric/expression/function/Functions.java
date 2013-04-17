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
package greenapi.gpi.metric.expression.function;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import greenapi.core.common.base.ClassUtils;
import greenapi.core.common.base.Strings;

@SuppressWarnings({ "rawtypes", "unchecked" })
public final class Functions
{
    /**
     * The {@link Map} with the {@link Function}s available in the system.
     */
    private static final Map<String, Function> FUNCTIONS = new ConcurrentHashMap<>();

    /**
     * Private constructor to avoid instance of this class.
     */
    private Functions()
    {
        throw new UnsupportedOperationException();
    }

    static
    {
        registerSystemFunctions();
    }

    /**
     * Find all classes on the classpath that implements the interface {@link Function} and register it in the system.
     */
    private static void registerSystemFunctions()
    {
        for (Class<?> clazz : ClassUtils.findSubclasses(Function.class))
        {
            register((Function<?>) ClassUtils.newInstanceForName(clazz));
        }

        assert !FUNCTIONS.isEmpty() : "System functions was not found!";
    }

    /**
     * Register a function.
     * 
     * @param function
     *            The function to be registered. Might not be <code>null</code> and it's required that every function has a non empty name.
     * @param <V>
     *            The return type of the function.
     * @return The previous function instance that had the same name of the newest one <code>null</code> if there was no function with the name.
     */
    public static <V> Function<V> register(Function<V> function)
    {
        return FUNCTIONS.put(Strings.checkArgumentIsNotNullOrEmpty(function.name()), function);
    }

    /**
     * Returns the function that has the given name.
     * 
     * @param functionName
     *            The function to be returned. Might not be <code>null</code>.
     * @param <V>
     *            The return type of the function.
     * @return The instance of {@link Function} that has the given name or <code>null</code> if there wasn't one.
     */
    public static <V> Function<V> getFunctionByName(String functionName)
    {
        return (Function<V>) FUNCTIONS.get(Objects.requireNonNull(functionName));
    }
}
