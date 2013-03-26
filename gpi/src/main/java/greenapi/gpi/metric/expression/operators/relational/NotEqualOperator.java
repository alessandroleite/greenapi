package greenapi.gpi.metric.expression.operators.relational;

public class NotEqualOperator extends AbstractRelationalOperator
{

    /**
     * Creates a {@link NotEqualOperator}.
     */
    public NotEqualOperator()
    {
        super("<>", 3);
    }

    @Override
    public <T extends Comparable<T>> boolean evaluate(T leftValue, T rightValue)
    {
        return (leftValue == null && rightValue != null) || (leftValue != null && leftValue.compareTo(rightValue) != 0);
    }
}
