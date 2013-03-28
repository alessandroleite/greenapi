package greenapi.gpi.metric.expression.token;

import java.util.Stack;

public class CommaToken extends Token
{
    /**
     * Default constructor.
     */
    public CommaToken()
    {
        super(",");
    }

    @Override
    void translateForPrefixNotation(Stack<Token> operators, StringBuilder output)
    {
    }
}
