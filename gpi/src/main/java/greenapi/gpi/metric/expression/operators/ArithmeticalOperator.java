package greenapi.gpi.metric.expression.operators;

import java.math.BigDecimal;


public interface ArithmeticalOperator extends Operator<BigDecimal>
{

    /**
     * Returns the neutral to be used if one of the operands is <code>null</code>.
     * 
     * @return The neutral to be used if one of the operands is <code>null</code>.
     */
    BigDecimal getNeutralValue();

    /**
     * Returns the value after the evaluation of the operands.
     * 
     * @param leftValue
     *            The value of the left operand.
     * @param rightValue
     *            The value of the right operand.
     * @return The value of the evaluation. Might not be <code>null</code>.
     */
    BigDecimal evaluate(BigDecimal leftValue, BigDecimal rightValue);

}
