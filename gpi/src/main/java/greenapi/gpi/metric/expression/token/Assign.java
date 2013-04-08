package greenapi.gpi.metric.expression.token;

import greenapi.gpi.metric.expression.Value;

public class Assign<T> extends Stat<T>
{

    /**
     * The variable that the value is being assigned.
     */
    private final Variable<T> id;
    
    /**
     * The value to be assigned.
     */
    private final Expr<T> value;

    public Assign(Variable<T> var, Token token, Expr<T> assignedValue)
    {
        super(token);
        this.id = var;
        this.value = assignedValue;
    }

    public Assign(Variable<T> var, Expr<T> assignedValue)
    {
        this(var, null, assignedValue);
    }

    @Override
    public Value<T> visit(ExpressionVisitor<T> visitor)
    {
        return new Value<T>(visitor.visit(this));
    }

    public Variable<T> getId()
    {
        return id;
    }

    public Expr<T> getValue()
    {
        return value;
    }
}
