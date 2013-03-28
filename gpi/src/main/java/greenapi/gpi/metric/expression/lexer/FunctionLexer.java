package greenapi.gpi.metric.expression.lexer;

import greenapi.gpi.metric.expression.token.CommaToken;
import greenapi.gpi.metric.expression.token.ExponentToken;
import greenapi.gpi.metric.expression.token.IdentifierToken;
import greenapi.gpi.metric.expression.token.LeftParenthesisToken;
import greenapi.gpi.metric.expression.token.NumberToken;
import greenapi.gpi.metric.expression.token.RightParenthesisToken;
import greenapi.gpi.metric.expression.token.Token;

public class FunctionLexer extends AbstractLexer
{
    /**
     * Creates a {@link FunctionLexer} with the given input.
     * 
     * @param input
     *            The input to be analyzed.
     */
    public FunctionLexer(String input)
    {
        super(input);
    }

    @Override
    public Token nextToken()
    {
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
            case ',':
                // A comma in the real world can be a thousands separator or an argument separator. But here It's always an argument separator.
                consume();
                return new CommaToken();
            case '(':
                consume();
                return new LeftParenthesisToken();
            case ')':
                consume();
                return new RightParenthesisToken();
            default:

                if (isLETTER())
                {
                    return name();
                }

                if (isNUMBER())
                {
                    return number();
                }
                throw new Error("Invalid character: " + current());
            }
        }
        return null;
    }

    /**
     * Consumes a name token. A name is sequence of >=1 letter. <br/>
     * name : LETTER+.
     * 
     * @return A {@link IdentifierToken} with the name of the identifier.
     */
    protected IdentifierToken name()
    {
        StringBuilder buf = new StringBuilder();
        do
        {
            buf.append(current());
            LETTER();
        } while (isLETTER());

        return new IdentifierToken(buf.toString());
    }

    /**
     * Consumes a number token. A number is a sequence of one or more digits, thousands separators, E or e and +-. Example: 5,559.25.
     * 
     * @return A {@link NumberToken} with the name of the identifier.
     */
    protected NumberToken number()
    {
        StringBuilder buf = new StringBuilder();
        do
        {
            buf.append(current());
            NUMBER();
        } while (isNUMBER());

        if (isEXPONENT())
        {
            Token exponent = exponent();
            buf.append(exponent.getValue());
        }

        return new NumberToken(buf.toString());
    }

    /**
     * Consumes a exponent token. A exponent is a 'E' or 'e' followed by a {+,-}? and by numbers [0..9]*
     * 
     * @return A exponent token.
     */
    protected Token exponent()
    {
        StringBuilder buf = new StringBuilder();
        do
        {
            buf.append(current());
            EXPONENT();
        } while (isEXPONENT());

        return new ExponentToken(buf.toString());
    }
}
