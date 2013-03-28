package greenapi.gpi.metric.expression.token;

import java.util.Stack;

public class ExponentToken extends Token
{

    /**
     * Creates an exponent token.
     * 
     * @param value
     *            The value of the token. Might not be <code>null</code>.
     */
    public ExponentToken(String value)
    {
        super(value);
    }

    @Override
    void translateForPrefixNotation(Stack<Token> operators, StringBuilder output)
    {
    }
}
