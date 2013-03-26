package greenapi.gpi.metric.expression.operators.arithmetical;


import java.math.BigDecimal;

public class SubtractionOperator extends AbstractArithmeticalOperator
{

    /**
     * Creates a {@link SubtractionOperator}.
     */
    public SubtractionOperator()
    {
        super("-", 5, true, BigDecimal.ZERO);
    }

    @Override
    public BigDecimal evaluate(BigDecimal leftValue, BigDecimal rightValue)
    {
        return leftValue.subtract(rightValue);
    }
}
