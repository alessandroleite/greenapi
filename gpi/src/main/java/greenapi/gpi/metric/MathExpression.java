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

import greenapi.gpi.metric.expression.EvaluationException;
import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.Variable;
import greenapi.gpi.metric.expression.function.Function;

public interface MathExpression<T> extends Expression<T>
{

    /**
     * Returns an {@link Expression} whose value is (this / divisor); if the exact quotient cannot be represented (because it has a non-terminated
     * decimal expansion) an {@link ArithmeticException} if thrown.
     * 
     * @param divisor
     *            value by which this {@link Expression} is to be divided.
     * @return An {@link Expression} whose value is (this / divisor).
     * @throws ArithmeticException
     *             if the exact quotient does not have a terminating decimal expansion.
     */
    MathExpression<T> divide(MathExpression<T> divisor);

    /**
     * Returns an {@link Expression} whose value is (this &times multiplicand).
     * 
     * @param multiplicand
     *            The value to be multiplied by this {@link Expression}.
     * @return An {@link Expression} whose value is (this &times multiplicand).
     */
    MathExpression<T> multiply(MathExpression<T> multiplicand);

    /**
     * Returns an {@link Expression} whose value is (this ^ n), The power is computed exactly, to unlimited precision. The given {@link Expression}
     * must returns a value in the range 0 through 999999999, inclusive. ZERO.pow(0) returns ONE.
     * 
     * @param expression
     *            power to raise this {@link Expression} to.
     * @return An {@link Expression} whose value is (this ^ n).
     * @throws ArithmeticException
     *             if the value of the given {@link Expression} is out of range.
     */
    MathExpression<T> pow(MathExpression<T> expression);

    /**
     * Register a variable in the expression. Sometimes it's necessary to evaluate expression that has variables.
     * 
     * @param variable
     *            The variable to be used. Might not be <code>null</code>.
     * @param <R>
     *            The type of the variable value.
     * @return The same {@link MathExpression}'s reference but now with the given variable.
     */
    <R> MathExpression<T> withVariable(Variable<R> variable);

    /**
     * Register a new variable in the expression.
     * 
     * @param name
     *            The name of the variable. Might not be <code>null</code> and might starts with a letter and cannot has any whitespace.
     * @param value
     *            The value of the variable.
     * @param <R>
     *            The type of the variable value.
     * @return The same {@link MathExpression}'s reference but now with a variable with the name and value.
     */
    <R> MathExpression<T> withVariable(String name, Value<R> value);

    /**
     * Register a new variable in the expression.
     * 
     * @param name
     *            The name of the variable. Might not be <code>null</code> and might starts with a letter and cannot has any whitespace.
     * @param value
     *            The value of the be assigned to the new variable.
     * @param <R>
     *            The type of the variable value.
     * @return The same {@link MathExpression}'s reference but now with a variable with the name and value.
     */
    <R> MathExpression<T> withVariable(String name, R value);

    /**
     * Evaluate this {@link MathExpression} and returns its value.
     * 
     * @return The math expression result. It's never <code>null</code>.
     * @throws EvaluationException
     *             If the math expression is invalid.
     */
    Value<T> evaluate() throws EvaluationException;

    /**
     * Register a function to be used in the evaluation process.
     * 
     * @param function
     *            A math function. Might not be <code>null</code>.
     * @return The same {@link MathExpression}'s reference but now with a new function registered.
     */
    MathExpression<T> withFunction(Function<Value<T>> function);

    /**
     * Register a function to be used in the math operations.
     * 
     * @param function
     *            The class of the function. Might not be <code>null</code>.
     * @return The same {@link MathExpression}'s reference but now with a new function registered.
     */
    MathExpression<T> withFunction(Class<Function<Value<T>>> function);

    /**
     * Defines a set of variables according its position in the expression. In this case, the expression is parsed and the variables are gather in the
     * order that them appear in the expression.
     * 
     * @param variablesValues
     *            The variables values.
     * @return The same {@link MathExpression}'s reference but now with the variables registered.
     */
    // MathExpression<T> withVariables(Object ... variablesValues);
}
