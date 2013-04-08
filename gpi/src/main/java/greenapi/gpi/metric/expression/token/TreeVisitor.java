package greenapi.gpi.metric.expression.token;

import greenapi.gpi.metric.expression.Value;

public class TreeVisitor<T> implements ExpressionVisitor<Value<T>>
{
    /**
     * Creates an instance of this visitor.
     */
    public TreeVisitor()
    {
    }

    @Override
    public Value<T> visit(Variable<Value<T>> variable)
    {
        return null;
    }

    @Override
    public Value<T> visit(Assign<Value<T>> assign)
    {
        return null;
    }

    @Override
    public Value<T> visit(Number<Value<T>> number)
    {
        return null;
    }

    @Override
    public Value<T> visit(FunctionToken<Value<T>> function)
    {
        return null;
    }

    @Override
    public Value<T> visit(BinaryOperator<Value<T>> bynaryOperator)
    {
        return null;
    }

    @Override
    public Value<T> visit(Unary<Value<T>> unary)
    {
        return null;
    }

}
