package greenapi.gpi.metric.expression.operators.evaluators;

import greenapi.core.common.primitives.Booleans;
import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.operators.LogicalOperator;

public final class LogicalEvaluator implements OperatorEvaluator<Boolean, LogicalOperator>
{
    /**
     * {@inheritDoc}
     */
    @Override
    public <E> Value<Boolean> eval(E leftValue, E rightValue, LogicalOperator operator)
    {
        return new Value<Boolean>(operator.evaluate(Booleans.valueOf(leftValue), Booleans.valueOf(rightValue)));
    }
}
