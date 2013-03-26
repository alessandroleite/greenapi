package greenapi.gpi.metric.expression.operators.relational;

public class GreaterThanOrEqualOperator extends AbstractRelationalOperator
{
    /**
     * Creates a {@link GreaterThanOrEqualOperator}.
     */
    public GreaterThanOrEqualOperator()
    {
        super(">=", 4);
    }

    @Override
    public <T extends Comparable<T>> boolean evaluate(T leftValue, T rightValue)
    {
        return (leftValue == null && rightValue == null) || (leftValue != null && leftValue.compareTo(rightValue) >= 0);
    }
}
