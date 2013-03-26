package greenapi.gpi.metric.expression.operators.logical;

/**
 * This class represents a boolean not operator. The symbol of this operator is ! (exclamation).
 */
public class BooleanNotOperator extends AbstractLogicalOperator
{
    /**
     * Creates a {@link BooleanNotOperator} operator.
     */
    public BooleanNotOperator()
    {
        super("!", 0, true);
    }

    @Override
    public boolean evaluate(boolean leftValue, boolean rightValue)
    {
        return !leftValue;
    }
}
