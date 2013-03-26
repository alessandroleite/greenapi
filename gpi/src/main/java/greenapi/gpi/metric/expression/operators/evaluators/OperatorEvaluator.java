package greenapi.gpi.metric.expression.operators.evaluators;

import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.operators.Operator;

public interface OperatorEvaluator<R, Op extends Operator<R>>
{

    /**
     * Evaluate the values using a given {@link Operator}.
     * 
     * @param leftValue
     *            The value of the left operand.
     * @param rightValue
     *            The value of the right operand.
     * @param operator
     *            The {@link Operator} to be used in the evaluation of the values.
     * @param <E>
     *            The type of the operands.
     * @return The result of the {@link greenapi.gpi.metric.expression.Operator.Operator} execution.
     */
    <E> Value<R> eval(E leftValue, E rightValue, Op operator);
}
