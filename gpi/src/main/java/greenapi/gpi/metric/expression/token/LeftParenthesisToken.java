package greenapi.gpi.metric.expression.token;

import java.util.Stack;

public class LeftParenthesisToken extends Token
{

    /**
     * Creates a open parenthesis token.
     */
    public LeftParenthesisToken()
    {
        super("(");
    }

    @Override
    void translateForPrefixNotation(Stack<Token> operators, StringBuilder output)
    {
    }
}
