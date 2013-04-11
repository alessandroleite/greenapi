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
package greenapi.gpi.metric.expression.operators;

import greenapi.core.common.base.Strings;
import greenapi.gpi.metric.expression.Computable;
import greenapi.gpi.metric.expression.EvaluationException;
import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.operators.evaluators.OperatorEvaluator;

public abstract class AbstractOperator<R> implements Operator<R>
{
    /**
     * The symbol of the operator.
     */
    private final String symbol;

    /**
     * The precedence of the operator. See <a href="http://en.wikipedia.org/wiki/Order_of_operations">Order of operations</a> to get more information
     * about the order of common math operations.
     */
    private final int precedence;

    /**
     * Flag that indicates if operator is a unary operator.
     */
    private final boolean unary;

    /**
     * The length of the operator.
     */
    private final int length;

    /**
     * The evaluator of this {@link Operator}.
     */
    @SuppressWarnings("rawtypes")
    private OperatorEvaluator evaluator;

    /**
     * 
     * @param operatorSymbol
     *            The symbol of the operator.
     * @param operatorPrecedence
     *            The precedence of the operator.
     * @param unaryOperator
     *            Flag that indicates if the operator is a unary operator.
     * @param operationEvaluator
     *            The evaluator of this {@link Operator}.
     */
    public AbstractOperator(String operatorSymbol, int operatorPrecedence, boolean unaryOperator, OperatorEvaluator<R, ?> operationEvaluator)
    {
        this.symbol = Strings.checkArgumentIsNotNullOrEmpty(operatorSymbol).trim();
        this.precedence = operatorPrecedence;
        this.unary = unaryOperator;
        this.length = this.symbol.length();
        this.evaluator = operationEvaluator;
    }

    /**
     * Create a non-unary operator.
     * 
     * @param operatorSymbol
     *            The symbol of the operator.
     * @param operatorPrecedence
     *            The precedence of the operator.
     * @param operationEvaluator
     *            The evaluator of this {@link Operator}.
     */
    public AbstractOperator(String operatorSymbol, int operatorPrecedence, OperatorEvaluator<R, ?> operationEvaluator)
    {
        this(operatorSymbol, operatorPrecedence, false, operationEvaluator);
    }

    @Override
    public <T> Value<R> evaluate(Computable<T> operand) throws EvaluationException
    {
        if (!this.isUnary())
        {
            throw new EvaluationException(String.format("The operator %s requires the left and right operands!", this.symbol()));
        }
        return this.evaluate(operand, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Value<R> evaluate(Computable<T> leftOperand, Computable<T> rightOperand) throws EvaluationException
    {
        if (!this.isUnary() && (leftOperand == null || rightOperand == null))
        {
            throw new EvaluationException(String.format("This operator %s requires two not null operands !", this.symbol()));
        }

        return this.evaluator.eval(leftOperand.getValue(), rightOperand != null ? rightOperand.getValue() : null, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String symbol()
    {
        return this.symbol;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int precedence()
    {
        return this.precedence;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUnary()
    {
        return unary;
    }

    /**
     * Returns the length of the operator.
     * 
     * @return The length of the operator. In other words, the number of characters of the operator.
     */
    public int length()
    {
        return this.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        AbstractOperator<?> other = (AbstractOperator<?>) obj;
        if (symbol == null)
        {
            if (other.symbol != null)
            {
                return false;
            }
        }
        else if (!symbol.equals(other.symbol))
        {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return this.symbol;
    }
}
