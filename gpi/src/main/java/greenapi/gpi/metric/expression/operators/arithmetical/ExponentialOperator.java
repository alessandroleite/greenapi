package greenapi.gpi.metric.expression.operators.arithmetical;

import java.math.BigDecimal;

public class ExponentialOperator extends AbstractArithmeticalOperator
{

    /**
     * Create an instance of the {@link ExponentialOperator}.
     */
    public ExponentialOperator()
    {
        super("^", 6, false, BigDecimal.ONE);
    }

    @Override
    public BigDecimal evaluate(BigDecimal leftValue, BigDecimal rightValue)
    {
        return leftValue.pow(rightValue.intValue());
    }
}
