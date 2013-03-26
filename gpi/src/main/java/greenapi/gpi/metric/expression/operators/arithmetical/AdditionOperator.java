package greenapi.gpi.metric.expression.operators.arithmetical;


import java.math.BigDecimal;

public class AdditionOperator extends AbstractArithmeticalOperator
{
    /**
     * Create a addition (+) operator.
     */
    public AdditionOperator()
    {
        super("+", 5, true, BigDecimal.ZERO);
    }

    @Override
    public BigDecimal evaluate(BigDecimal leftValue, BigDecimal rightValue)
    {
        return leftValue.add(rightValue);
    }
}
