package greenapi.gpi.metric.expression.token;

import java.math.BigDecimal;
import java.util.Stack;

public class NumberToken extends Token
{
    /**
     * The value as a {@link Number}.
     */
    private final BigDecimal number;

    /**
     * Create a new {@link NumberToken}.
     * 
     * @param tokenValue
     *            The value of this token that represents a {@link Number}.
     */
    public NumberToken(String tokenValue)
    {
        super(tokenValue);
        this.number = new BigDecimal(tokenValue.trim());
    }

    @Override
    void translateForPrefixNotation(Stack<Token> operators, StringBuilder output)
    {
    }

    /**
     * Returns the value of this token as a {@link BigDecimal}.
     * 
     * @return The value of this token as a {@link BigDecimal}.
     */
    public BigDecimal getValueAsNumber()
    {
        return this.number;
    }
}
