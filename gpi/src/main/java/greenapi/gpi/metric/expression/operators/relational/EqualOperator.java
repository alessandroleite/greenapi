package greenapi.gpi.metric.expression.operators.relational;

public class EqualOperator extends AbstractRelationalOperator
{

    /**
     * Creates an {@link EqualOperator}.
     */
    public EqualOperator()
    {
        super("==", 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Comparable<T>> boolean evaluate(T leftValue, T rightValue)
    {
        return leftValue == null && rightValue == null || leftValue.compareTo(rightValue) == 0;
    }
}
