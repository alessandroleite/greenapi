package greenapi.gpi.metric.expression.token;

public final class Token
{
    /**
     * The type of this token.
     */
    private final int type;

    /**
     * The text of the token. Might not be <code>null</code>.
     */
    private final String text;

    /**
     * 
     * @param tokenType
     *            The type of the token.
     * @param tokenText
     *            The text of the token. Might not be <code>null</code>.
     */
    public Token(int tokenType, String tokenText)
    {
        this.type = tokenType;
        this.text = tokenText;
    }

    /**
     * 
     * @param tokenType
     *            The type of the token.
     * @param c
     *            The value of this token.
     */
    public Token(int tokenType, char c)
    {
        this(tokenType, String.valueOf(c));
    }

    /**
     * @return the type
     */
    public int getType()
    {
        return type;
    }

    /**
     * @return the text
     */
    public String getText()
    {
        return text;
    }

    @Override
    public String toString()
    {
        return "type = " + getType() + " text = " + getText();
    }
}
