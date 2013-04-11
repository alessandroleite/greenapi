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

public enum ExpressionTokens
{
    /**
     * Represents the end of a token.
     */
    EOT(Lexer.EOF_TYPE),

    /**
     * Represents an unary token.
     */
    UNARY(1),

    /**
     * Represents an operator token.
     */
    OP(2),

    /**
     * Represents a left parenthesis.
     */
    LPARENTHESIS(3),

    /**
     * Represents a right parenthesis.
     */
    RPARENTHESIS(4),

    /**
     * Represents a number. It can be INT or FLOAT.
     */
    ATOM(5),

    /**
     * Represents a identifier. It can be a variable or a function name.
     */
    IDENT(6),

    /**
     * Represents an assign.
     */
    EQUALS(7),

    /**
     * Represents a function call.
     */
    FUNCTION_CALL(8),

    /**
     * Represents an argument separator.
     */
    COMMA(9);

    /**
     * The id of the enum.
     */
    private final int id;

    /**
     * Creates a instance of this enum with the given value.
     * 
     * @param enumId
     *            The id of this enum.
     */
    private ExpressionTokens(int enumId)
    {
        this.id = enumId;
    }

    /**
     * Returns the enum's id.
     * 
     * @return The enum's id.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Factory method to get an enum's instance that has a given id.
     * 
     * @param enumId
     *            The id of the enum to be returned.
     * @return The enum that has the given id.
     * @throws IllegalArgumentException
     *             If the given value is unknown.
     */
    public ExpressionTokens get(int enumId)
    {
        for (ExpressionTokens type : ExpressionTokens.values())
        {
            if (type.getId() == id)
            {
                return type;
            }
        }
        throw new IllegalArgumentException();
    }
}
