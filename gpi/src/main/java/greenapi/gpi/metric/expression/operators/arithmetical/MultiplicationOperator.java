package greenapi.gpi.metric.expression.operators.arithmetical;


import java.math.BigDecimal;

public class MultiplicationOperator extends AbstractArithmeticalOperator
{

    /**
     * Creates a {@link MultiplicationOperator}.
     */
    public MultiplicationOperator()
    {
        super("*", 6, BigDecimal.ONE);
    }

    @Override
    public BigDecimal evaluate(BigDecimal leftValue, BigDecimal rightValue)
    {
        return leftValue.multiply(rightValue);
    }
}
