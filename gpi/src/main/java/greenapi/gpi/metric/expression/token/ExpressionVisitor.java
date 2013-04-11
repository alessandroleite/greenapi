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
package greenapi.gpi.metric.expression.token;

import greenapi.gpi.metric.expression.Computable;
import greenapi.gpi.metric.expression.EvaluationException;
import greenapi.gpi.metric.expression.UndefinedVariableException;
import greenapi.gpi.metric.expression.Variable;

public interface ExpressionVisitor<T>
{
    /**
     * The method visits all the child tree nodes a that has a {@link VarToken} root type and returns its value.
     * 
     * @param variable
     *            The variable tree to be visited.
     * @param <R>
     *            The variable's value type.
     * @return The value of the variable.
     * @throws UndefinedVariableException
     *             If the given variable was not defined in the system.
     */
    <R> Variable<R> visit(VarToken<T> variable) throws UndefinedVariableException;

    /**
     * This method visits a {@link NumberToken} (atom) token type. In this case, this token can be seen as a
     * {@link greenapi.gpi.metric.expression.Constant} value.
     * 
     * @param number
     *            The token to be visited. Might not be <code>null</code>.
     * @return The value of the token. In that case, a <a href='https://en.wikipedia.org/wiki/Real_number'>real</a> number.
     */
    Computable<T> visit(NumberToken<T> number);

    /**
     * Evaluates a function. To the evaluation be succeed it's necessary that the function was registered before.
     * 
     * @param function
     *            The function to be evaluated. Might not be <code>null</code>.
     * @return The value after evaluate the function.
     * @throws EvaluationException
     *             If the evaluation process found something wrong. For instance, an unknown variable or
     *             {@link greenapi.gpi.metric.expression.function.Function}.
     */
    Computable<T> visit(FunctionToken<T> function) throws EvaluationException;

    /**
     * This method visits a {@link BinaryOperatorToken}. First it visits the left operand and second the right.
     * 
     * @param bynaryOperator
     *            The binary operator to be visit. Might not be <code>null</code>.
     * @return The result of the operator execution.
     * @throws EvaluationException
     *             If the evaluation process found something wrong. For instance, an unknown variable or
     *             {@link greenapi.gpi.metric.expression.function.Function}.
     */
    Computable<T> visit(BinaryOperatorToken<T> bynaryOperator) throws EvaluationException;

    /**
     * Visits a {@link UnaryToken} token type and returns its value.
     * 
     * @param unary
     *            The unary token to be visited. Might not be <code>null</code>.
     * @return The value after the evaluation of the unary token.
     * @throws EvaluationException
     *             If the evaluation process found something wrong. For instance, an unknown variable or
     *             {@link greenapi.gpi.metric.expression.function.Function}.
     */
    Computable<T> visit(UnaryToken<T> unary) throws EvaluationException;

    /**
     * Returns the value of a given assignment token.
     * 
     * @param assign
     *            The assigned token to visited.
     * @return A variable that represents the assignment.
     * @throws EvaluationException
     *             If the evaluation process found something wrong. For instance, an unknown variable or
     *             {@link greenapi.gpi.metric.expression.function.Function}.
     */
    Computable<T> visit(AssignToken<T> assign) throws EvaluationException;
}
