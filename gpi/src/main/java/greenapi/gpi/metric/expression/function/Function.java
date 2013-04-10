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

import greenapi.gpi.metric.Expression;
import greenapi.gpi.metric.expression.evaluator.Evaluator;

import java.util.List;

/**
 * This class is used to create a function.
 */
public interface Function<V>
{
    /**
     * Returns the name of the function. Every function has a name that starts with a letter [a-z,A-Z], or underscore (_) and cannot has space.
     * 
     * @return Returns the name of the function. May not be <code>null</code> or empty.
     */
    String name();

    /**
     * Evaluates and returns the value of a given {@link Expression}.
     * 
     * @param expression
     *            The expression of the function. In this case, an expression can have variable(s), constant(s) value(s), function(s) or a combination
     *            of them.
     * @param <T>
     *            The type of the value returned by the evaluate and execution of the given expression.
     * @return The value of the function after it had been executed.
     */
    <T> V evaluate(Expression<T> expression);

    /**
     * 
     * @param expression
     *            The expression that represents the function to be executed.
     * @param evaluator
     *            The evaluator to be used in the evaluation.
     * @param <T>
     *            The type of the value returned by the evaluate and execution of the given expression.
     * @return Return the function's value after its execution.
     */
    <T> V evaluate(Expression<T> expression, Evaluator<Expression<T>, V> evaluator);

    /**
     * Returns the value of the function execution.
     * 
     * @param values
     *            The function's arguments.
     * @return The value of the function execution.
     */
    V evaluate(V[] arguments);

    /**
     * Returns the value of the function execution.
     * 
     * @param arguments
     *            The function's arguments.
     * @return The result of the function execution.
     */
    V evaluate(List<V> arguments);
}
