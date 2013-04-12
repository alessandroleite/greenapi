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
package greenapi.gpi.metric.expression.lexer;

import greenapi.core.common.base.Strings;
import greenapi.gpi.metric.expression.token.Token;

public abstract class Lexer
{

    /**
     * Constant that represents the end of the expression.
     */
    public static final char EOF = (char) -1;

    /**
     * Constant that represents the end of a token.
     */
    public static final int EOF_TYPE = -2;

    /**
     * The expression to be analyzed.
     */
    private String input;

    /**
     * The position of the cursor in the input.
     */
    private int p;

    /**
     * The value represents by the cursor. The value is input[p] or EOF if p > size[input].
     */
    private char c;

    /**
     * @param expression
     *            The expression to be analyzed and identified the tokens. Might not be <code>null</code> or empty.
     */
    public Lexer(String expression)
    {
        this.input = Strings.checkArgumentIsNotNullOrEmpty(expression, "The expression might not be null or empty!");
        c = input.charAt(p);
    }

    /**
     * Move to next non-whitespace character.
     */
    public void consume()
    {
        advance();
    }

    /**
     * Move one character if there is one available or detect "end of file".
     */
    protected void advance()
    {
        p++;
        c = p >= input.length() ? EOF : input.charAt(p);
    }

//    /**
//     * Check if the current value of the input is equals of a given value.
//     * 
//     * @param x
//     *            The value to be checked.
//     */
//    protected void match(char x)
//    {
//        if (c == x)
//        {
//            consume();
//        }
//        else
//        {
//            throw new Error("expecting " + x + ";found " + c);
//        }
//    }

    /**
     * Returns the character of the cursor.
     * 
     * @return The character of the cursor.
     */
    public char current()
    {
        return this.c;
    }

    /**
     * Returns the position of the cursor.
     * 
     * @return The index of the cursor in the input.
     */
    protected int index()
    {
        return p;
    }

    /**
     * Returns the size of the input.
     * 
     * @return The size of the input.
     */
    protected int length()
    {
        return this.input.length();
    }

    /**
     * Returns the character of a given position.
     * 
     * @param index
     *            The position to get the value.
     * @return The character of a given position.
     */
    protected char get(int index)
    {
        return this.input.charAt(index);
    }

    /**
     * Returns the next token or EOF if it's the end of the input.
     * 
     * @return The next token or EOF if it's the end of the input.
     */
    public abstract Token nextToken();

    /**
     * Returns the name of a given token type.
     * 
     * @param tokenType
     *            The type of the token.
     * @return The name of a given token type.
     */
    public abstract String getTokenName(int tokenType);
}
