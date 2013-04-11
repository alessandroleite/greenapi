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
package greenapi.gpi.metric.expression.token;


import java.util.Objects;

import greenapi.gpi.metric.expression.Computable;
import greenapi.gpi.metric.expression.EvaluationException;
import greenapi.gpi.metric.expression.Value;

public class AssignToken<T> extends StatToken<T, Computable<T>>
{
    /**
     * The variable that the value is being assigned.
     */
    private final VarToken<T> id;

    /**
     * The value to be assigned.
     */
    private final ExpressionToken<T, Value<T>> value;

    /**
     * Creates an instance of the {@link AssignToken}.
     * 
     * @param var
     *            The variable to receive the value. Might not be <code>null</code>.
     * @param token
     *            The assign token.
     * @param assignedValue
     *            The expression that is been assigned to the variable. Might not be <code>null</code>.
     * @param <V>
     *            The return type of the expression that is been assigned to the variable.
     */
    public <V> AssignToken(VarToken<T> var, Token token, ExpressionToken<T, Value<T>> assignedValue)
    {
        super(token);
        this.id = Objects.requireNonNull(var);
        this.value = Objects.requireNonNull(assignedValue);
    }

    /**
     * Creates an instance of the {@link AssignToken}.
     * 
     * @param var
     *            The variable to receive the value. Might not be <code>null</code>.
     * @param assignedValue
     *            The expression that is been assigned to the variable. Might not be <code>null</code>.
     * @param <V>
     *            The return type of the expression that is been assigned to the variable.
     */
    public <V> AssignToken(VarToken<T> var, ExpressionToken<T, Value<T>> assignedValue)
    {
        this(var, new Token(7, '='), assignedValue);
    }

    /**
     * Returns the variable token.
     * 
     * @return The variable token.
     */
    public VarToken<T> getId()
    {
        return id;
    }

    /**
     * Returns The expression that is been assigned to a variable.
     * 
     * @param <V>
     *            The return type of the expression.
     * @return The expression that is been assigned to a variable.
     */
    public <V> ExpressionToken<T, Value<T>> getValue()
    {
        return value;
    }

    @Override
    public Computable<T> visit(ExpressionVisitor<T> visitor) throws EvaluationException
    {
        return visitor.visit(this);
    }
}
