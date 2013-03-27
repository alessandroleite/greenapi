package greenapi.gpi.metric.expression;

import java.util.Objects;
import java.util.Stack;

public abstract class Token
{
    /**
     * The {@link Token} value.
     */
    private final String value;

    /**
     * Create a new {@link Token} with the given value.
     * 
     * @param tokenValue
     *            The character that represents the {@link Token}. Might not be <code>null</code>.
     */
    protected Token(String tokenValue)
    {
        this.value = Objects.requireNonNull(tokenValue);
    }

    /**
     * Returns a {@link String} that represents this {@link Token}.
     * 
     * @return A {@link String} that represents this {@link Token}.
     */
    protected String getValue()
    {
        return value;
    }

    /**
     * 
     * @param operators
     *            The {@link Stack} with the operators.
     * @param output
     *            The output to write the infix notation value.
     */
    abstract void translateForPrefixNotation(Stack<Token> operators, StringBuilder output);

}
