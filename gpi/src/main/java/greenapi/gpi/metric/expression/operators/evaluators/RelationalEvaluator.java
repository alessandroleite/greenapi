package greenapi.gpi.metric.expression.operators.evaluators;

import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.operators.RelationalOperator;

public final class RelationalEvaluator implements OperatorEvaluator<Boolean, RelationalOperator>
{
    @Override
    public <E> Value<Boolean> eval(E leftValue, E rightValue, RelationalOperator operator)
    {
        // return new Value<Boolean>(operator.evaluate(comparable(leftValue), comparable(rightValue)));
        return null;
    }

    /**
     * Converts a given value to a {@link Comparable} type.
     * 
     * @param value
     *            The value to be converted to a {@link Comparable} value.
     * @param <E>
     *            The type of the value to be converted.
     * @param <T>
     *            The type of the {@link Comparable}.
     * @return The given value converted to a {@link Comparable} instance.
     */
    private <E, T extends Comparable<T>> T comparable(E value)
    {
        return null;
    }
}
