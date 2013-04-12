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
package greenapi.gpi.metric.expression.evaluator;

import java.util.Map;

import greenapi.gpi.metric.expression.EvaluationException;
import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.Variable;
import greenapi.gpi.metric.expression.function.Function;
import greenapi.gpi.metric.expression.operators.Operator;

public interface Evaluator<T, V>
{

    /**
     * Evaluate a given type and returns its result.
     * 
     * @param type
     *            The type (function, expression, variable, etc.) to be evaluate.
     * @return The value after evaluate the given type.
     * @throws EvaluationException
     *             If the expression is wrong.
     */
    V eval(T type) throws EvaluationException;

    /**
     * Returns a read-only {@link Map} with the {@link Variable}s found in the expression.
     * 
     * @return A non modifiable {@link Map} with the {@link Variable}s found in the expression.
     */
    Map<String, Variable<?>> variables();

    /**
     * Returns an {@link Operator}'s instance of a given symbol or <code>null</code> if it's unknown.
     * 
     * @param symbol
     *            The symbol of the operator to be returned.
     * @param <R>
     *            The type of the function's return value.
     * @return The {@link Operator} instance that has the given symbol or <code>null</code> if it's unknown.
     */
    <R> Operator<R> getOperatorBySymbol(String symbol);

    /**
     * Returns the {@link Function} that has the given name or <code>null</code> if it doesn't exist.
     * 
     * @param name
     *            The name of the function to be returned.
     * @param <R>
     *            The type of the function result.
     * @return The {@link Function} that has the given name or <code>null</code> if it doesn't exist.
     */
    <R> Function<R> getFunctionByName(String name);

    /**
     * Returns the {@link Variable} instance that was registered with the given name or <code>null</code> if it's unknown.
     * 
     * @param varName
     *            The name of the variable to be returned.
     * @param <R>
     *            The type of the variable's value.
     * @return The {@link Variable} instance that was registered with the given name or <code>null</code> if it's unknown.
     */
    <R> Variable<R> getVariableByName(String varName);

    /**
     * Add a {@link Variable} to this evaluator. At this moment the variable can or cannot has a value.
     * 
     * @param var
     *            The variable to be added.
     * @param <R>
     *            The type of the variable value.
     * @return The previous instance of the variable.
     */
    <R> Variable<?> register(Variable<R> var);

    /**
     * Add a {@link Function} to this evaluator.
     * 
     * @param function
     *            The function to be added.
     * @param <R>
     *            The type of the function's return value.
     * @return The previous instance of the function.
     */
    <R> Function<Value<R>> register(Function<Value<R>> function);
}
