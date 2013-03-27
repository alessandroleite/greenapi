package greenapi.gpi.metric.expression.function;

import greenapi.gpi.metric.Expression;
import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.evaluator.Evaluator;

/**
 * This class is used to create a function.
 */
public interface Function<V>
{
    /**
     * Returns the name of the function. Every function has a name that starts with a letter [a-z,A-Z], or underscore (_) and cannot has space.
     * 
     * @return Returns the name of the function. May not be <code>null</code> or empty.
     */
    String name();

    /**
     * Evaluate and returns the value of a given {@link Expression}.
     * 
     * @param expression
     *            The expression of the function. In this case, an expression can have variable(s), constant(s) value(s), function(s) or a combination
     *            of them.
     * @param <T>
     *            The type of the value returned by the evaluate and execution of the given expression.
     * @return The value of the function after it had been executed.
     */
    <T> Value<V> evaluate(Expression<T> expression);

    /**
     * 
     * @param expression
     *            The expression that represents the function to be executed.
     * @param evaluator
     *            The evaluator to be used in the evaluation.
     * @param <T>
     *            The type of the value returned by the evaluate and execution of the given expression.
     * @return Return the function's value after its execution.
     */
    <T> Value<V> evaluate(Expression<T> expression, Evaluator<Expression<T>, Value<V>> evaluator);
}
