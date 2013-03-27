package greenapi.gpi.metric.expression;

import java.util.Map;
import java.util.Objects;
import java.util.Stack;

import greenapi.gpi.metric.expression.operators.Operator;

public final class OperatorToken<T> extends CalculationToken<T>
{
    /**
     * The operator that represents one {@link Token}.
     */
    private final Operator<Value<T>> operator;

    /**
     * Creates a new {@link Token} that represents an {@link Operator}. The representation of the {@link Token} ({@link String} value) is the symbol
     * of the {@link Operator}.
     * 
     * @param op
     *            The operator. Might not be <code>null</code>.
     */
    protected OperatorToken(final Operator<Value<T>> op)
    {
        super(Objects.requireNonNull(op.symbol()));
        this.operator = op;
    }

    @Override
    public void translateForPrefixNotation(Stack<Token> operators, StringBuilder output)
    {
    }
    
    @Override
    public Value<T> evaluate(Stack<Double> stack, Map<String, Variable<?>> variableValues)
    {
        return null;
    }

    /**
     * Returns the {@link Operator} that represents this {@link Token}.
     * 
     * @return The {@link Operator} that represents this {@link Token}.
     */
    public Operator<Value<T>> getOperator()
    {
        return operator;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (!(obj instanceof OperatorToken<?>))
        {
            return false;
        }

        return this.getValue().equals(((OperatorToken<?>) obj).getValue());
    }

    @Override
    public int hashCode()
    {
        return this.getValue().hashCode();
    }

    @Override
    public String toString()
    {
        return this.getValue().toString();
    }
}
