package greenapi.gpi.metric.expression.token;

import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.parser.AST;

public abstract class MathNode<T> extends AST
{

    public MathNode(Token token)
    {
        super(token);
    }

    /**
     * 
     * @param visitor
     * @param <T>
     * @return
     */
    public abstract Value<T> visit(ExpressionVisitor<T> visitor);
}
