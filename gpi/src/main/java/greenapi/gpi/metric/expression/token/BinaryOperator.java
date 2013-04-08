package greenapi.gpi.metric.expression.token;

import greenapi.gpi.metric.expression.Value;

public class BinaryOperator<T> extends Expr<T>
{
    private final Expr<T> left;

    private final Expr<T> right;

    public BinaryOperator(Token token, Expr<T> left, Expr<T> right)
    {
        super(token);
        this.left = left;
        this.right = right;
    }

    public Expr<T> getLeft()
    {
        return left;
    }

    public Expr<T> getRight()
    {
        return right;
    }

    @Override
    public Value<T> visit(ExpressionVisitor<T> visitor)
    {
        return new Value<T>(visitor.visit(this));
    }

    /**
     * Returns the symbol of the operator.
     * 
     * @return the symbol of the operator.
     */
    public String symbol()
    {
        return this.getToken().getText();
    }
}
