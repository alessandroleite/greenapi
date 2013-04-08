package greenapi.gpi.metric.expression.token;

import java.util.Objects;

import greenapi.gpi.metric.expression.Value;

public class Unary<T> extends Expr<T>
{
    /**
     * The expression of this {@link Unary} token.
     */
    private final Expr<T> expr;

    /**
     * 
     * @param unaryToken
     *            The unary's token.
     * @param expression
     *            The expression of this {@link Unary} token.
     */
    public Unary(Token unaryToken, Expr<T> expression)
    {
        super(unaryToken);
        this.expr = Objects.requireNonNull(expression);
    }

    @Override
    public Value<T> visit(ExpressionVisitor<T> visitor)
    {
        return new Value<T>(visitor.visit(this));
    }

    /**
     * Returns the expression of this {@link Unary} token.
     * 
     * @return The expression of this {@link Unary} token.
     */
    public Expr<T> getExpression()
    {
        return expr;
    }
}
