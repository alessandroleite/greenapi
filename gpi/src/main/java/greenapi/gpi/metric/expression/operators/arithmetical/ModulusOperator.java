package greenapi.gpi.metric.expression.operators.arithmetical;


import java.math.BigDecimal;

public class ModulusOperator extends AbstractArithmeticalOperator
{

    /**
     * Creates a {@link ModulusOperator}.
     */
    public ModulusOperator()
    {
        super("%", 6, BigDecimal.ZERO);
    }

    @Override
    public BigDecimal evaluate(BigDecimal leftValue, BigDecimal rightValue)
    {
        return BigDecimal.valueOf(leftValue.doubleValue() % rightValue.doubleValue());
    }
}
