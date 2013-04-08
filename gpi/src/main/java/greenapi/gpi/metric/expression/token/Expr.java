package greenapi.gpi.metric.expression.token;

public abstract class Expr<T> extends MathNode<T>
{
    public Expr(Token t)
    {
        super(t);
    }
}
