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

import java.util.Map;
import java.util.Objects;
import java.util.Stack;

import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.Variable;
import greenapi.gpi.metric.expression.operators.Operator;

public final class OperatorToken<T> extends CalculationToken<T>
{
    /**
     * The operator that represents one {@link Token}.
     */
    private final Operator<Value<T>> operator;

    /**
     * Creates a new {@link Token} that represents an {@link Operator}. The representation of the {@link Token} ({@link String} value) is the symbol
     * of the {@link Operator}.
     * 
     * @param op
     *            The operator. Might not be <code>null</code>.
     */
    protected OperatorToken(final Operator<Value<T>> op)
    {
        super(Objects.requireNonNull(op.symbol()));
        this.operator = op;
    }

    @Override
    public void translateForPrefixNotation(Stack<Token> operators, StringBuilder output)
    {
    }
    
    @Override
    public Value<T> evaluate(Stack<Double> stack, Map<String, Variable<?>> variableValues)
    {
        return null;
    }

    /**
     * Returns the {@link Operator} that represents this {@link Token}.
     * 
     * @return The {@link Operator} that represents this {@link Token}.
     */
    public Operator<Value<T>> getOperator()
    {
        return operator;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (!(obj instanceof OperatorToken<?>))
        {
            return false;
        }

        return this.getValue().equals(((OperatorToken<?>) obj).getValue());
    }

    @Override
    public int hashCode()
    {
        return this.getValue().hashCode();
    }

    @Override
    public String toString()
    {
        return this.getValue().toString();
    }
}
