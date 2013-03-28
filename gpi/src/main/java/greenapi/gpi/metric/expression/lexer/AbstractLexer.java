package greenapi.gpi.metric.expression.lexer;

public abstract class AbstractLexer extends Lexer
{

    /**
     * Create an instance of an {@link AbstractLexer} with the given input.
     * 
     * @param input
     *            The input to be analyzed.
     */
    public AbstractLexer(String input)
    {
        super(input);
    }

    /**
     * Determines if a character is a space or white space (' '|'\t'|'\n'|'\r').
     * 
     * @param character
     *            The character being evaluated.
     * 
     * @return True if the character is a space or white space and false if not.
     */
    protected boolean isWhitespace(char character)
    {
        return character == ' ' || character == '\t' || character == '\n' || character == '\r' || character == '\f';
    }

    /**
     * Ignore any whitespace onto the input.
     */
    protected void WS()
    {
        while (current() == ' ' || current() == '\t' || current() == '\n' || current() == '\r')
        {
            advance();
        }
    }

    /**
     * Returns <code>true</code> only if the current character is a letter [a..z,A..Z] or <code>false</code> otherwise.
     * 
     * @return <code>true</code> only if the current character is a letter [a..z,A..Z] or <code>false</code> otherwise.
     */
    protected boolean isLETTER()
    {
        return current() >= 'a' && current() <= 'z' || current() >= 'A' && current() <= 'Z';
    }

    /**
     * Returns <code>true</code> if the current character is a {@link Number}.
     * 
     * @return <code>true</code> if the current character is a {@link Number}.
     */
    protected boolean isNUMBER()
    {
        return isNUMBER(current());
    }

    /**
     * Returns <code>true</code> if the given character is a {@link Number}.
     * 
     * @param value
     *            The value to be checked.
     * @return <code>true</code> if the given character is a {@link Number}.
     */
    protected boolean isNUMBER(char value)
    {
        return value >= 48 && value <= 57 || value == '.';
    }

    /**
     * Returns <code>true</code> if the {@link #current()} character is an EXPONENT.
     * 
     * @return <code>true</code> if the {@link #current()} character is an EXPONENT.
     * @see #isEXPONENT(char)
     */
    protected boolean isEXPONENT()
    {
        return this.isEXPONENT(current());
    }

    /**
     * Returns <code>true</code> if a given character is an EXPONENT. <br />
     * 
     * @param value
     *            The value to be checked.
     * @return <code>true</code> if a given character is an EXPONENT.
     */
    protected boolean isEXPONENT(char value)
    {
        int ni = index() + 1;

        if (ni >= this.length())

        {
            return false;
        }

        // final char np = nextWithoutConsume();
        // && (isNUMBER(last()) && (np == '-' || np == '+' || isNUMBER(np)));
        return (current() == 'E' || current() == 'e') || (current() == '+' || current() == '-') || (current() >= 48 && current() <= 57);
    }

    /**
     * Define what a letter is (\w). LETTER : 'a'..'z'|'A'..'Z'.
     * 
     * @throws Error
     *             if the current character is not a letter.
     * @see #isLETTER()
     * @see #current()
     */
    protected void LETTER()
    {
        if (isLETTER())
        {
            consume();
        }
        else
        {
            throw new Error("expecting LETTER; found " + current());
        }
    }

    /**
     * Defines what a number is. NUMBER: '0'..'9'.
     */
    protected void NUMBER()
    {
        if (isNUMBER())
        {
            consume();
        }
        else
        {
            throw new Error("expecting NUMBER; found " + current());
        }
    }

    /**
     * Defines what a exponent is. EXPONENT : ('e'|'E') ('+'|'-')?('0'..'9')+.
     */
    protected void EXPONENT()
    {
        if (isEXPONENT())
        {
            consume();
        }
        else
        {
            throw new Error("expecting EXPONENT; found " + current());
        }
    }
}
