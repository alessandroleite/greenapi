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
package greenapi.gpi.metric;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import greenapi.gpi.metric.expression.EvaluationException;

public interface Metric<V, Q extends Quantity>
{
    /**
     * Returns the {@link Formulae} that indicate how to compute the {@link Metric}. Might not be <code>null</code>.
     * 
     * @return The {@link Formulae} that indicate how to compute the {@link Metric}. Might not be <code>null</code>.
     */
    Formulae<V, Q> formulae();

    /**
     * Return the value of this {@link Metric} computed using a given {@link Formulae}. Might not be <code>null</code>.
     * 
     * @return The value of this {@link Metric} computed using a given {@link Formulae}. Might not be <code>null</code>.
     * @throw MetricException If the expression could not be evaluated.
     * @see #formulae()
     */
    Measure<V, Q> value();

    /**
     * This method computes and returns the value of a {@link Metric} using its {@link Formulae}.
     * 
     * @param formulae
     *            The {@link Formulae} to compute the {@link Metric}.
     * @return The value of this {@link Metric} after evaluate it using the given {@link Formulae}.
     * @throws EvaluationException
     *             If the expression of the {@link Formulae} is invalid.
     * @see #formulae()
     * @see #value()
     */
    Measure<V, Q> value(Formulae<V, Q> formulae) throws EvaluationException;

    /**
     * Return the metric's name. Might not be <code>null</code> or empty.
     * 
     * @return The metric's name. Might not be <code>null</code> or empty.
     */
    String name();
}
