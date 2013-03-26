package greenapi.gpi.metric.expression.operators.evaluators;

import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.operators.RelationalOperator;

public final class RelationalEvaluator implements OperatorEvaluator<Boolean, RelationalOperator>
{
    @Override
    public <E> Value<Boolean> eval(E leftValue, E rightValue, RelationalOperator operator)
    {
        return new Value<Boolean>(operator.evaluate(comparable(leftValue), comparable(rightValue)));
    }

    /**
     * Converts a given value to a {@link Comparable} type.
     * 
     * @param value
     *            The value to be converted to a {@link Comparable} value.
     * @param <E>
     *            The type of the value to be converted.
     * @return The given value converted to a {@link Comparable} instance.
     */
    private <E> ComparableType<E> comparable(E value)
    {
        return new ComparableType<E>(value);
    }

    private static final class ComparableType<E> implements Comparable<ComparableType<E>>
    {

        /**
         * The value to be converted to a {@link Comparable} type.
         */
        private final E value;

        /**
         * Create a instance of this class with a given value that must be encapsulated as a {@link Comparable} type.
         * 
         * @param val
         *            The value to be converted to a {@link Comparable} type.
         */
        private ComparableType(E val)
        {
            this.value = val;
        }

        @SuppressWarnings({ "unchecked", "rawtypes" })
        @Override
        public int compareTo(ComparableType<E> other)
        {

            if (this.value == null && (other == null || other.value == null))
            {
                return 0;
            }

            if (value == null && other != null && other.value != null)
            {
                return -1;
            }

            if (this.value instanceof Comparable<?> && other.value instanceof Comparable<?>)
            {
                return ((Comparable) value).compareTo((Comparable) other.value);
            }

            if (this.value != null && other != null && other.value != null)
            {
                return this.value.toString().compareTo(other.value.toString());
            }
            return -1;
        }
    }
}
