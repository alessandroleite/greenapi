package greenapi.gpi.metric.expression.token;

import greenapi.gpi.metric.expression.Constant;
import greenapi.gpi.metric.expression.Value;

public interface ExpressionVisitor<T>
{
    /**
     * The method visits all the child tree nodes a that has a {@link VarToken} root type and returns its value.
     * 
     * @param variable
     *            The variable tree to be visited.
     * @return The value of the variable.
     */
    greenapi.gpi.metric.expression.Variable<Value<T>> visit(VarToken<T> variable);

    /**
     * This method visits a {@link NumberToken} (atom) token type. In this case, this token can be seen as a {@link Constant} value.
     * 
     * @param number
     *            The token to be visited. Might not be <code>null</code>.
     * @return The value of the token. In that case, a <a href='https://en.wikipedia.org/wiki/Real_number'>real</a> number.
     */
    Value<T> visit(NumberToken<T> number);

    Value<T> visit(FunctionToken<T> function);

    Value<T> visit(BinaryOperatorToken<T> bynaryOperator);

    /**
     * Visits a {@link UnaryToken} token type and returns its value.
     * 
     * @param unary
     *            The unary token to be visited. Might not be <code>null</code>.
     * @return The value after the evaluation of the unary token.
     */
    Value<T> visit(UnaryToken<T> unary);

    /**
     * Returns the value of a given assignment token.
     * 
     * @param assign
     *            The assigned token to visited.
     * @return A variable that represents the assignment.
     */
    greenapi.gpi.metric.expression.Variable<Value<T>> visit(AssignToken<T> assign);
}
