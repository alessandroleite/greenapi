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
package greenapi.gpi.metric.expression;

import greenapi.gpi.metric.MathExpression;

public class ExpressionBuilder
{
    /**
     * @param expression
     *            The expression to be evaluate and executed.
     * @param variablesValues
     *            The values of the variables that are used in the expression. The variables are resolved according with their position and their
     *            name. If a variable is used more than one time in the expression, we don't need to repeat its value. For instance, consider the
     *            expression: <code><pre>{@code}ExpressionBuilder.evaluate("x + y * z ^ x", 1,2,3)</pre></code>. In this expression we have three
     *            variables (x, y, z) and the variable x appears two times in the expression. As we can see, the method was called with just three
     *            values (1,2,3). In this case the analyzer knows that the value of x is 1, y is 2 and finally the of z is 3.
     * @param <T>
     *            The type of the value returned by the expression.
     * @return The result after analyze and execute the given expression.
     */
    public <T> T evaluate(String expression, Object... variablesValues)
    {
        return null;
    }

    /**
     * Returns an instance of one {@link MathExpression}.
     * 
     * @param <T>
     *            The type of the value returned by the expression.
     * @return An instance of a {@link MathExpression}.
     */
    public <T> MathExpression<T> newMathExpression()
    {
        return null;
    }

    /**
     * Returns a {@link MathExpression} of a given expression.
     * 
     * @param expression
     *            The expression that represents a math expression.
     * @param <T>
     *            The type of the value returned by the expression.
     * @return A {@link MathExpression} instance of the given expression.
     */
    public <T> MathExpression<T> newMathExpression(String expression)
    {
        return null;
    }
}
