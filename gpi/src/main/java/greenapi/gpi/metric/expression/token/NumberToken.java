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

import greenapi.gpi.metric.expression.Computable;

public class NumberToken<T> extends ExpressionToken<T, Computable<T>>
{
    /**
     * Creates a {@link NumberToken} node with a given {@link Token}.
     * 
     * @param token
     *            The token that represents a {@link NumberToken}. In this case, the token's text must be a {@link java.lang.Number}.
     */
    public NumberToken(Token token)
    {
        super(token);
    }

    @Override
    public Computable<T> visit(ExpressionVisitor<T> visitor)
    {
        return visitor.visit(this);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;

        int result = 1;
        result = prime * result + getToken().getText().hashCode();
        result = prime * result + getToken().hashCode();

        return result;
    }

    @Override
    public boolean equals(Object obj)
    {

        if (this == obj)
        {
            return true;
        }

        if (obj == null)
        {
            return false;
        }

        if (getClass() != obj.getClass())
        {
            return false;
        }

        NumberToken<?> other = (NumberToken<?>) obj;

        return this.getToken().equals(other.getToken());
    }

    @Override
    public String toString()
    {
        return this.getToken().getText().trim();
    }
}
