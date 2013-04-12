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

import greenapi.gpi.metric.expression.token.Token;

public class ExpressionLexer extends AbstractLexer
{

    /**
     * Creates an instance of the {@link ExpressionLexer}.
     * 
     * @param input
     *            The input with the expression to be analyzed.
     */
    public ExpressionLexer(String input)
    {
        super(input);
    }

    @Override
    public Token nextToken()
    {
        Token t;
        while (current() != EOF)
        {
            switch (current())
            {
            case ' ':
            case '\n':
            case '\t':
            case '\r':
            case '\f':
                WS();
                continue;
            case '+':
            case '-':
                t = new Token(ExpressionTokens.UNARY.getId(), current());
                consume();
                return t;
            case '*':
            case '/':
            case '%':
            case '^':
                t = new Token(ExpressionTokens.OP.getId(), current());
                consume();
                return t;
            case '(':
                consume();
                return new Token(ExpressionTokens.LPARENTHESIS.getId(), '(');
            case ')':
                consume();
                return new Token(ExpressionTokens.RPARENTHESIS.getId(), ')');
            case '=':
                consume();
                return new Token(ExpressionTokens.EQUALS.getId(), '=');
            case ',':
                consume();
                return new Token(ExpressionTokens.COMMA.getId(), ",");
            default:
                if (isLETTER())
                {
                    return name();
                }
                else if (isNUMBER())
                {
                    return number();
                }
                throw new Error("invalid character: " + current());
            }
        }
        return new Token(EOF_TYPE, ExpressionTokens.EOT.name());
    }

    @Override
    public String getTokenName(int tokenType)
    {
        return ExpressionTokens.get(tokenType).name();
    }
}
