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
