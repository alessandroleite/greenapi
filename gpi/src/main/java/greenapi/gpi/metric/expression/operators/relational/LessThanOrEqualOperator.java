package greenapi.gpi.metric.expression.operators.relational;

public class LessThanOrEqualOperator extends AbstractRelationalOperator
{

    /**
     * Creates a {@link LessThanOrEqualOperator}.
     */
    public LessThanOrEqualOperator()
    {
        super("<=", 4);
    }

    @Override
    public <T extends Comparable<T>> boolean evaluate(T leftValue, T rightValue)
    {
        return (leftValue == null && rightValue == null) || (leftValue != null && leftValue.compareTo(rightValue) <= 0);
    }
}
