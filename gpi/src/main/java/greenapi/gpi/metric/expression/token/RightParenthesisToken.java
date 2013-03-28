package greenapi.gpi.metric.expression.token;

import java.util.Stack;

public class RightParenthesisToken extends Token
{

    /**
     * Creates a open parenthesis token.
     */
    public RightParenthesisToken()
    {
        super(")");
    }

    @Override
    void translateForPrefixNotation(Stack<Token> operators, StringBuilder output)
    {
    }
}
