package greenapi.gpi.metric.expression.token;

import greenapi.gpi.metric.expression.Value;

public class Variable<T> extends Expr<T>
{

    /**
     * Creates a new variable's token.
     * 
     * @param token
     *            The token that represents the variable.
     */
    public Variable(Token token)
    {
        super(token);
    }

    @Override
    public Value<T> visit(ExpressionVisitor<T> visitor)
    {
        return new Value<T>(visitor.visit(this));
    }
}
