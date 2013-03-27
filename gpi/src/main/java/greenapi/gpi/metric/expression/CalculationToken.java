package greenapi.gpi.metric.expression;

import java.util.Map;
import java.util.Stack;

public abstract class CalculationToken<T> extends Token
{

    /**
     * Create a new {@link CalculationToken} with the given value (token).
     * 
     * @param tokenValue
     *            The character that represents the {@link Token}. Might not be <code>null</code>.
     */
    public CalculationToken(String tokenValue)
    {
        super(tokenValue);
    }

    /**
     * @param stack
     *            The {@link Stack} of execution.
     * @param variableValues
     *            The {@link Map} with the variables associated with the {@link Stack}.
     * @return The {@link Value} after evaluation and execution of the {@link Token}.
     */
    public abstract Value<T> evaluate(Stack<Double> stack, Map<String, Variable<?>> variableValues);

}
