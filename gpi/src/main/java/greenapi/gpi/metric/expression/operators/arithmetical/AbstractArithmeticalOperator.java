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
