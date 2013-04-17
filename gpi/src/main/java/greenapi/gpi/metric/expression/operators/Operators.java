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
package greenapi.gpi.metric.expression.operators;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.base.Preconditions;

import greenapi.core.common.base.ClassUtils;
import greenapi.core.common.base.Strings;

@SuppressWarnings("rawtypes")
public final class Operators
{
    /**
     * The {@link Map} with the {@link Operator}s available in the system.
     */
    private static final Map<String, Operator> OPERATORS = new ConcurrentHashMap<>();

    static
    {
        registerOperators();
    }

    /**
     * Default constructor (prevents this class from being instantiated).
     */
    private Operators()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Find all classes on the classpath that implements the interface {@link Operator}.
     */
    public static void registerOperators()
    {
        for (Class<?> clazz : ClassUtils.findSubclasses(Operator.class))
        {
            register((Operator<?>) ClassUtils.newInstanceForName(clazz));
        }
    }

    /**
     * Register a given {@link Operator}.
     * 
     * @param <R>
     *            The operator's return type.
     * @param operator
     *            The {@link Operator} to be registered.
     */
    public static <R> void register(Operator<R> operator)
    {
        OPERATORS.put(Strings.checkArgumentIsNotNullOrEmpty(operator.symbol()), operator);

    }

    /**
     * Returns the {@link Operator} that has the given symbol.
     * 
     * @param symbol
     *            The operator's symbol. Might not be <code>null</code>.
     * @param <R>
     *            The operator's return type.
     * @return The {@link Operator} that has the given symbol or <code>null</code>.
     */
    @SuppressWarnings("unchecked")
    public static <R> Operator<R> getOperatorBySymbol(String symbol)
    {
        return Preconditions.checkNotNull(OPERATORS.get(symbol), String.format("The operator %s does not exists!", symbol));
    }

    /**
     * Returns the available operators.
     * 
     * @return A read-only {@link Map} with the available operators.
     */
    public static Map<String, Operator> getOperators()
    {
        return Collections.unmodifiableMap(OPERATORS);
    }
}
