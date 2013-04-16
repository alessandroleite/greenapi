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
package greenapi.gpi.metric.expression.evaluator.impl;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import greenapi.core.common.base.ClassUtils;
import greenapi.gpi.metric.MathExpression;
import greenapi.gpi.metric.expression.evaluator.Evaluator;

@SuppressWarnings({ "unchecked", "rawtypes" })
public final class Evaluators
{

    /**
     * The {@link Map} with the available evaluators.
     */
    private static final Map<Class<?>, Class> REGISTERED_EVALUATORS = new ConcurrentHashMap<>();

    static
    {
        registerDefaultEvaluators();
    }

    /**
     * Default constructor (prevents this class from being instantiated).
     */
    private Evaluators()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Register the known evaluators of the system.
     */
    private static void registerDefaultEvaluators()
    {
        register(MathExpression.class, ExpressionEvaluator.class);
        register(String.class, ImplictVariableExpressionEvaluator.class);
    }

    /**
     * Returns the {@link Evaluator} registered for a given {@link Class}.
     * 
     * @param type
     *            The {@link Class} to get its {@link Evaluator}.
     * @param <T>
     *            The {@link Class} of the type to be evaluate.
     * @param <V>
     *            The {@link Class} of the returned type.
     * @return The {@link Evaluator} of the given {@link Class} or <code>null</code> if it's unknown.
     */
    public static <T, V> Evaluator<T, V> get(Class<?> type)
    {
        return (Evaluator<T, V>) ClassUtils.newInstanceForName(REGISTERED_EVALUATORS.get(type));
    }

    /**
     * Register a {@link Evaluator} for a given {@link Class}.
     * 
     * @param type
     *            The type to be evaluated by a given evaluator. Might not be <code>null</code>.
     * @param evaluator
     *            The evaluator of the given type. Might not be <code>null</code>.
     * @param <T>
     *            The {@link Class} of the type to be evaluate.
     * @param <V>
     *            The {@link Class} of the returned type.
     * 
     * @return The previous evaluator defined for the given type or <code>null</code> if there wasn't one.
     */
    public static <T, V> Class<Evaluator<T, V>> register(Class<?> type, Class evaluator)
    {
        return (Class<Evaluator<T, V>>) REGISTERED_EVALUATORS.put(Objects.requireNonNull(type), evaluator);
    }
}
