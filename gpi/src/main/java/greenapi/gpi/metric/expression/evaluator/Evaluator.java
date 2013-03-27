package greenapi.gpi.metric.expression.evaluator;

public interface Evaluator<T, R>
{

    /**
     * Evaluate a given type and returns its result.
     * 
     * @param type
     *            The type (function, expression, variable, etc.) to be evaluate.
     * @return The value after evaluate the given type.
     */
    R eval(T type);
}
