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
import java.util.Stack;

public abstract class Token
{
    /**
     * The {@link Token} value.
     */
    private final String value;

    /**
     * Create a new {@link Token} with the given value.
     * 
     * @param tokenValue
     *            The character that represents the {@link Token}. Might not be <code>null</code>.
     */
    protected Token(String tokenValue)
    {
        this.value = Objects.requireNonNull(tokenValue);
    }

    /**
     * Returns a {@link String} that represents this {@link Token}.
     * 
     * @return A {@link String} that represents this {@link Token}.
     */
    public String getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return this.getValue();
    }

    /**
     * 
     * @param operators
     *            The {@link Stack} with the operators.
     * @param output
     *            The output to write the infix notation value.
     */
    abstract void translateForPrefixNotation(Stack<Token> operators, StringBuilder output);

}
