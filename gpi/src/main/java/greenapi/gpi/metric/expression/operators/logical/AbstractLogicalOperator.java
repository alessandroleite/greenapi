package greenapi.gpi.metric.expression.operators.logical;

import greenapi.core.common.primitives.Booleans;
import greenapi.gpi.metric.expression.operators.AbstractOperator;
import greenapi.gpi.metric.expression.operators.LogicalOperator;
import greenapi.gpi.metric.expression.operators.evaluators.LogicalEvaluator;

public abstract class AbstractLogicalOperator extends AbstractOperator<Boolean> implements LogicalOperator
{

    /**
     * Required constructor of a {@link LogicalOperator}.
     * 
     * @param symbol
     *            The symbol of the operator.
     * @param precedence
     *            The precedence of the operator.
     * @param unary
     *            Flag that indicates if the operator is a unary operator.
     */
    public AbstractLogicalOperator(String symbol, int precedence, boolean unary)
    {
        super(symbol, precedence, unary, new LogicalEvaluator());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean evaluate(Number leftValue, Number rightValue)
    {
        return this.evaluate(Booleans.valueOf(leftValue), Booleans.valueOf(rightValue));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean evaluate(String leftValue, String rightValue)
    {
        return this.evaluate(Booleans.valueOf(leftValue), Booleans.valueOf(rightValue));
    }
}
