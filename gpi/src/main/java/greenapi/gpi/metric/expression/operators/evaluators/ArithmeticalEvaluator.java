/**
 * Copyright (c) 2012 GreenI2R
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
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