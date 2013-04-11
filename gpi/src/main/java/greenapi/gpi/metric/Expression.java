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

import java.util.Collection;

import greenapi.gpi.metric.expression.EvaluationException;
import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.Variable;
import greenapi.gpi.metric.expression.evaluator.Evaluator;


public interface Expression<T>
{

    /**
     * Returns the text that represents an expression. For instance, x * 2 + z % 4.
     * 
     * @return The text that represents an expression. For instance, x * 2 + z % 4.
     */
    String expression();

    /**
     * Returns a non <code>null</code> and read-only {@link Collection} with the variables found in the expression.
     * 
     * @return A non <code>null</code> and read-only {@link Collection} with the variables found in the expression.
     */
    Collection<Variable<?>> variables();

    /**
     * Evaluate and execute an expression using the given evaluator.
     * 
     * @param evaluator
     *            The {@link Evaluator} to be used in the evaluation process.
     * @return The value after the evaluation of the given {@link Expression}.
     * @throws EvaluationException
     *             Throws when the expression to be evaluated is not well formated. In other words, this method throws {@link EvaluationException}
     *             when the expression is wrong o missing something.
     */
    Value<T> evaluate(Evaluator<Expression<T>, Value<T>> evaluator) throws EvaluationException;
}
