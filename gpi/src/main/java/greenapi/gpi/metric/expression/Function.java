package greenapi.gpi.metric.expression;

import greenapi.gpi.metric.Expression;

/**
 * This class is used to create a function.
 */
public interface Function
{
    /**
     * Returns the name of the function. Every function has a name that starts with a letter [a-z,A-Z], or underscore (_) and cannot has space.
     * 
     * @return Returns the name of the function. May not be <code>null</code> or empty.
     */
    String name();

    /**
     * Returns the value of this function.
     * 
     * @param expression
     *            The expression of the function. In this case, an expression can have variable(s), constant(s) value(s), function(s) or a combination
     *            of them.
     * @param <T>
     *            The type of the value expression.
     * @return The value of the function after it had been executed.
     */
    <T> Value<T> evaluate(Expression<T> expression);
}
