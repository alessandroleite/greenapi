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
package greenapi.gpi.metric.expression.operators.relational;

import greenapi.gpi.metric.expression.operators.AbstractOperator;
import greenapi.gpi.metric.expression.operators.RelationalOperator;
import greenapi.gpi.metric.expression.operators.evaluators.RelationalEvaluator;

public abstract class AbstractRelationalOperator extends AbstractOperator<Boolean> implements RelationalOperator
{

    /**
     * @param symbol
     *            The symbol of the operator.
     * @param precedence
     *            The precedence of the operator.
     * @param unary
     *            Flag that indicates if the operator is a unary operator.
     */
    public AbstractRelationalOperator(String symbol, int precedence, boolean unary)
    {
        super(symbol, precedence, unary, new RelationalEvaluator());
    }

    /**
     * @param symbol
     *            The symbol of the operator.
     * @param precedence
     *            The precedence of the operator.
     */
    public AbstractRelationalOperator(String symbol, int precedence)
    {
        this(symbol, precedence, false);
    }
}
