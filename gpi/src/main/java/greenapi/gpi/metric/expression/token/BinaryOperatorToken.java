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

public class BinaryOperatorToken<T> extends ExpressionToken<T, Computable<T>>
{
    /**
     * The left operand of the operator.
     */
    private final ExpressionToken<T, Value<T>> left;

    /**
     * The right operand of the operator.
     */
    private final ExpressionToken<T, Value<T>> right;

    /**
     * Creates a {@link BinaryOperatorToken} with the operator and its operands.
     * 
     * @param operator
     *            The operator token. Might not be <code>null</code>.
     * @param leftOperand
     *            The left operand of the given operator. Might not be <code>null</code>.
     * @param rightOperand
     *            The right operand of the given operator. Might not be <code>null</code>.
     */
    public BinaryOperatorToken(Token operator, ExpressionToken<T, Value<T>> leftOperand, ExpressionToken<T, Value<T>> rightOperand)
    {
        super(operator);

        this.left = Objects.requireNonNull(leftOperand,
                String.format("The operator %s requires two operands. The left operand might not be null!", operator.getText()));

        this.right = Objects.requireNonNull(rightOperand,
                String.format("The operator %s requires two operands. The right operand might not be null!", operator.getText()));
    }

    /**
     * Returns the left operand. It's never <code>null</code>.
     * 
     * @return The left operands. It's never <code>null</code>.
     */
    public ExpressionToken<T, Value<T>> getLeft()
    {
        return left;
    }

    /**
     * Returns the right operand. It's never <code>null</code>.
     * 
     * @return The right operands. It's never <code>null</code>.
     */
    public ExpressionToken<T, Value<T>> getRight()
    {
        return right;
    }

    /**
     * Returns the symbol of the operator.
     * 
     * @return the symbol of the operator.
     */
    public String symbol()
    {
        return this.getToken().getText();
    }

    @Override
    public Computable<T> visit(ExpressionVisitor<T> visitor) throws EvaluationException
    {
        return visitor.visit(this);
    }
}
