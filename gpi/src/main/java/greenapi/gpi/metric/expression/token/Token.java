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

public final class Token
{
    /**
     * The type of this token.
     */
    private final int type;

    /**
     * The text of the token. Might not be <code>null</code>.
     */
    private final String text;

    /**
     * 
     * @param tokenType
     *            The type of the token.
     * @param tokenText
     *            The text of the token. Might not be <code>null</code>.
     */
    public Token(int tokenType, String tokenText)
    {
        this.type = tokenType;
        this.text = tokenText;
    }

    /**
     * 
     * @param tokenType
     *            The type of the token.
     * @param c
     *            The value of this token.
     */
    public Token(int tokenType, char c)
    {
        this(tokenType, String.valueOf(c));
    }

    /**
     * @return the type
     */
    public int getType()
    {
        return type;
    }

    /**
     * @return the text
     */
    public String getText()
    {
        return text;
    }

    @Override
    public String toString()
    {
        return "type = " + getType() + " text = " + getText();
    }
}
