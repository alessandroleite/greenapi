package greenapi.gpi.metric.expression.operators.evaluators;

import java.math.BigDecimal;

import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.operators.ArithmeticalOperator;


public final class ArithmeticalEvaluator implements OperatorEvaluator<BigDecimal, ArithmeticalOperator>
{
    /**
     * Create an instance of the {@link ArithmeticalEvaluator}.
     */
    public ArithmeticalEvaluator()
    {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Value<BigDecimal> eval(T leftValue, T rightValue, ArithmeticalOperator operator)
    {
        BigDecimal nullValue = operator.getNeutralValue();
        return new Value<BigDecimal>(operator.evaluate(asBigDecimal(rightValue, nullValue), asBigDecimal(leftValue, nullValue)));
    }

    /**
     * Returns a value as a {@link BigDecimal}.
     * 
     * @param value
     *            The value to be returned as a {@link BigDecimal}.
     * @param defaultValue
     *            The default value in case of the value is <code>null</code>. In an addition operation this value must be zero, multiplication it
     *            must be one.
     * @param <T>
     *            The type of the value.
     * @return The value as a {@link BigDecimal} value.
     */
    private <T> BigDecimal asBigDecimal(T value, T defaultValue)
    {
        if (value instanceof BigDecimal)
        {
            return (BigDecimal) value;
        }
        else
        {
            return new BigDecimal(value == null ? defaultValue.toString() : value.toString());
        }
    }
}
