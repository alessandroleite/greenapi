package greenapi.gpi.metric.expression.token;

import greenapi.gpi.metric.expression.Value;

public class Number<T> extends Expr<T>
{

    /**
     * 
     * @param t
     *            The token that represents a {@link Number}.
     */
    public Number(Token t)
    {
        super(t);
    }

    @Override
    public Value<T> visit(ExpressionVisitor<T> visitor)
    {
        return new Value<T>(visitor.visit(this));
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;

        int result = 1;
        result = prime * result + getToken().getText().hashCode();
        result = prime * result + getToken().hashCode();

        return result;
    }

    @Override
    public boolean equals(Object obj)
    {

        if (this == obj)
        {
            return true;
        }

        if (obj == null)
        {
            return false;
        }

        if (getClass() != obj.getClass())
        {
            return false;
        }

        Number<?> other = (Number<?>) obj;

        return this.getToken().equals(other.getToken());
    }

    @Override
    public String toString()
    {
        return this.getToken().getText().trim();
    }
}
