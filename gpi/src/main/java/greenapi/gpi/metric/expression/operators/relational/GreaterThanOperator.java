package greenapi.gpi.metric.expression.operators.relational;

public class GreaterThanOperator extends AbstractRelationalOperator
{

    /**
     * Creates a {@link GreaterThanOperator} operator.
     */
    public GreaterThanOperator()
    {
        super(">", 4);
    }

    @Override
    public <T extends Comparable<T>> boolean evaluate(T leftValue, T rightValue)
    {
        return leftValue != null && (rightValue == null || leftValue.compareTo(rightValue) > 0);
    }
}
