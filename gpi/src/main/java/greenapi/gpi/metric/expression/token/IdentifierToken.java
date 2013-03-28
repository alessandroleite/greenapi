package greenapi.gpi.metric.expression.token;

import java.util.Stack;

public class IdentifierToken extends Token
{
    /**
     * Create a new {@link IdentifierToken}.
     * 
     * @param tokenValue
     *            The value of the token.
     */
    public IdentifierToken(String tokenValue)
    {
        super(tokenValue);
    }

    @Override
    void translateForPrefixNotation(Stack<Token> operators, StringBuilder output)
    {
    }
}
