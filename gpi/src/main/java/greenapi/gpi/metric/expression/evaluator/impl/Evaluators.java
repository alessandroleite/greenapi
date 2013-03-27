package greenapi.gpi.metric.expression.evaluator.impl;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import greenapi.gpi.metric.expression.evaluator.Evaluator;

@SuppressWarnings("unchecked")
public final class Evaluators
{

    /**
     * The {@link Map} with the available evaluators.
     */
    @SuppressWarnings("rawtypes")
    private static final Map<Class<?>, Evaluator> REGISTERED_EVALUATORS = new ConcurrentHashMap<>();

    /**
     * 
     */
    private Evaluators()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the {@link Evaluator} registered for a given {@link Class}.
     * 
     * @param type
     *            The {@link Class} to get its {@link Evaluator}.
     * @param <T>
     *            The {@link Class} of the type to be evaluate.
     * @param <V>
     *            The {@link Class} of the returned type.
     * @return The {@link Evaluator} of the given {@link Class} or <code>null</code> if it's unknown.
     */
    public static <T, V> Evaluator<T, V> get(Class<?> type)
    {
        return REGISTERED_EVALUATORS.get(type);
    }

    /**
     * Register a {@link Evaluator} for a given {@link Class}.
     * 
     * @param type
     *            The type to be evaluated by a given evaluator. Might not be <code>null</code>.
     * @param evaluator
     *            The evaluator of the given type. Might not be <code>null</code>.
     * @param <T>
     *            The {@link Class} of the type to be evaluate.
     * @param <V>
     *            The {@link Class} of the returned type.
     * 
     * @return The previous evaluator defined for the given type or <code>null</code> if there wasn't one.
     */
    public static <T, V> Evaluator<T, V> register(Class<?> type, Evaluator<T, V> evaluator)
    {
        return REGISTERED_EVALUATORS.put(Objects.requireNonNull(type), Objects.requireNonNull(evaluator));
    }
}
