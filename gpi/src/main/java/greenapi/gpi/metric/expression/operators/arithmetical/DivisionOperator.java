package greenapi.gpi.metric.expression.operators.arithmetical;


import java.math.BigDecimal;

public class DivisionOperator extends AbstractArithmeticalOperator
{

    /**
     * Creates a {@link DivisionOperator}.
     */
    public DivisionOperator()
    {
        super("/", 6, false, BigDecimal.ONE);
    }

    @Override
    public BigDecimal evaluate(BigDecimal leftValue, BigDecimal rightValue)
    {
        return leftValue.divide(rightValue);
    }
}
