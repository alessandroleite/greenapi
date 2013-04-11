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


public class UnaryToken<T> extends ExpressionToken<T, Computable<T>>
{
    /**
     * The expression of this {@link UnaryToken} token.
     */
    private final ExpressionToken<T, Value<T>> expr;

    /**
     * 
     * @param unaryToken
     *            The unary's token.
     * @param expression
     *            The expression of this {@link UnaryToken} token.
     */
    public UnaryToken(Token unaryToken, ExpressionToken<T, Value<T>> expression)
    {
        super(unaryToken);
        this.expr = Objects.requireNonNull(expression);
    }

    @Override
    public Computable<T> visit(ExpressionVisitor<T> visitor) throws EvaluationException
    {
        return visitor.visit(this);
    }

    /**
     * Returns the expression of this {@link UnaryToken} token.
     * 
     * @return The expression of this {@link UnaryToken} token.
     */
    public ExpressionToken<T, Value<T>> getExpression()
    {
        return expr;
    }

    /**
     * Returns the symbol of the unary operator.
     * 
     * @return The symbol of the unary operator.
     */
    public String symbol()
    {
        return this.getToken().getText();
    }
}
