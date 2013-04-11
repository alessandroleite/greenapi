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
package greenapi.gpi.metric.expression.operators;

import greenapi.gpi.metric.expression.Computable;
import greenapi.gpi.metric.expression.EvaluationException;

/**
 * @param <R>
 *            The type of the return value after evaluate the operands.
 */
public interface Operator<R>
{

    /**
     * Return the character(s) that makes up the operator.
     * 
     * @return The operator symbol(s).
     */
    String symbol();

    /**
     * Returns the precedence of this operator.
     * 
     * @return The precedence of this operator.
     */
    int precedence();

    /**
     * Returns an indicator if this operator is unary or not.
     * 
     * @return An indicator if this operator is unary or not.
     */
    boolean isUnary();

    /**
     * Evaluate the operands and returns its value.
     * 
     * @param leftOperand
     *            The left operand to be evaluated.
     * @param rightOperand
     *            The right operand to be evaluated.
     * @param <T>
     *            The type of the operands.
     * @return The value of the evaluated operands.
     * @throws greenapi.gpi.metric.expression.EvaluationException
     *             If it's impossible to evaluate the operands.
     */
    <T> Computable<R> evaluate(Computable<T> leftOperand, Computable<T> rightOperand) throws EvaluationException;

    /**
     * Evaluate one operand and returns its value.
     * 
     * @param operand
     *            The operand to be evaluated.
     * @param <T>
     *            The type of the given operand.
     * @return The value of the evaluated operand.
     * @throws greenapi.gpi.metric.expression.EvaluationException
     *             If it's impossible to evaluate the given operand.
     */
    <T> Computable<R> evaluate(Computable<T> operand) throws EvaluationException;
}
