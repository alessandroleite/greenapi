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
package greenapi.gpi.metric.expression.operators.arithmetical;

import java.math.BigDecimal;

import greenapi.gpi.metric.expression.operators.AbstractOperator;
import greenapi.gpi.metric.expression.operators.ArithmeticalOperator;
import greenapi.gpi.metric.expression.operators.evaluators.ArithmeticalEvaluator;


public abstract class AbstractArithmeticalOperator extends AbstractOperator<BigDecimal> implements ArithmeticalOperator
{

    /**
     * The neutral value of this operator.
     */
    private final BigDecimal neutralValue;

    /**
     * 
     * @param symbol
     *            The symbol of the operator.
     * @param precedence
     *            The precedence of the operator.
     * @param unary
     *            Flag that indicates if the operator is a unary operator.
     * @param neutralElement
     *            The neutral element of the {@link greenapi.gpi.metric.expression.Operator.Operator}.
     */
    public AbstractArithmeticalOperator(String symbol, int precedence, boolean unary, BigDecimal neutralElement)
    {
        super(symbol, precedence, unary, new ArithmeticalEvaluator());
        this.neutralValue = neutralElement;
    }

    /**
     * 
     * @param symbol
     *            The symbol of the operator.
     * @param precedence
     *            The precedence of the operator.
     * @param neutralElement
     *            The neutral element of the {@link greenapi.gpi.metric.expression.Operator.Operator}.
     */
    public AbstractArithmeticalOperator(String symbol, int precedence, BigDecimal neutralElement)
    {
        this(symbol, precedence, false, neutralElement);
    }

    @Override
    public BigDecimal getNeutralValue()
    {
        return this.neutralValue;
    }
}
