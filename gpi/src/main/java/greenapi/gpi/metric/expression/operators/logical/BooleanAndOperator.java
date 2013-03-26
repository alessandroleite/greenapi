package greenapi.gpi.metric.expression.operators.logical;

public class BooleanAndOperator extends AbstractLogicalOperator
{
    /**
     * Create a {@link BooleanAndOperator}.
     */
    public BooleanAndOperator()
    {
        super("&&", 2, false);
    }

    @Override
    public boolean evaluate(boolean leftValue, boolean rightValue)
    {
        return leftValue && rightValue;
    }
}
