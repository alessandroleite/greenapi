package greenapi.gpi.metric.expression.token;

import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.operators.Operators;

import java.math.BigDecimal;

public class TreeVisitor<T> implements ExpressionVisitor<Value<T>>
{
    /**
     * Creates an instance of this visitor.
     */
    public TreeVisitor()
    {
    }

    public T visit(MathNode<T> node)
    {
        if (node instanceof BinaryOperator)
        {
            return this.visit((BinaryOperator<T>) node);
        }
        else if (node instanceof Assign)
        {
            return this.visit((Assign<T>) node);
        }
        else if (node instanceof Number)
        {
            return this.visit((Number<T>) node);
        }
        else if (node instanceof Variable)
        {
            return this.visit((Variable<T>) node);
        }
        else if (node instanceof FunctionToken)
        {
            return this.visit((FunctionToken<T>) node);
        }
        throw new IllegalArgumentException(node.getToken().getText());
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

    @SuppressWarnings("unchecked")
    @Override
    public Value<T> visit(Number<Value<T>> number)
    {
        return new Value<T>((T) new BigDecimal(number.getToken().getText()));
    }

    @Override
    public Value<T> visit(FunctionToken<Value<T>> function)
    {
        return null;
    }

    @Override
    public Value<T> visit(BinaryOperator<Value<T>> bynaryOperator)
    {
        Value<Value<T>> left = bynaryOperator.getLeft().visit(this);
        Value<Value<T>> right = bynaryOperator.getRight().visit(this);
        
        Value<T> result = Operators.<T>getOperatorBySymbol(bynaryOperator.symbol()).evaluate(left, right);
        
        return result;
    }

    @Override
    public Value<T> visit(Unary<Value<T>> unary)
    {
        return null;
    }
}
