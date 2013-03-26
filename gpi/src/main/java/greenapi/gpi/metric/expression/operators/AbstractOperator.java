package greenapi.gpi.metric.expression.operators;

import greenapi.core.common.base.Strings;
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
    public <T> Value<R> evaluate(Value<T> operand)
    {
        if (!this.isUnary())
        {
            throw new EvaluationException(String.format("The operator %s requires the left and right operands!", this.symbol()));
        }
        return this.evaluate(operand, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Value<R> evaluate(Value<T> leftOperand, Value<T> rightOperand)
    {
        if (leftOperand == null && rightOperand == null)
        {
            throw new EvaluationException("The left and right operands are null!");
        }

        return this.evaluator.eval(leftOperand.getValue(), rightOperand.getValue(), this);
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
