package greenapi.gpi.metric.expression.operators.logical;

public class BooleanOrOperator extends AbstractLogicalOperator
{

    /**
     * Creates a {@link BooleanOrOperator}.
     */
    public BooleanOrOperator()
    {
        super("||", 1, false);
    }

    @Override
    public boolean evaluate(boolean leftValue, boolean rightValue)
    {
        return leftValue || rightValue;
    }
}
