package greenapi.gpi.metric.expression.token;

public abstract class Stat<T> extends MathNode<T>
{

    public Stat(Token token)
    {
        super(token);
    }
}
