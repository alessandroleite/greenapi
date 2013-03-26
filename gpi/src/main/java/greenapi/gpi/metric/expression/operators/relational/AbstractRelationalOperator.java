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
