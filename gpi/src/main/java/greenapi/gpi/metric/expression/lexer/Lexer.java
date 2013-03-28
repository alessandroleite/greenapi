package greenapi.gpi.metric.expression.lexer;

import java.util.Objects;

import greenapi.gpi.metric.expression.token.Token;

public abstract class Lexer 
{
    /**
     * Represents the end of file char.
     */
    public static final char EOF = '¤';

    /**
     * Represents EOF token type.
     */
    public static final char EOF_TOKEN = 1;

    /**
     * The input expression to be analyzed.
     */
    private final String input;

    /**
     * The index into input of current character.
     */
    private int p;

    /**
     * The current character.
     */
    private char c;

    /**
     * Create a instance of the {@link Lexer} with a given expression (input).
     * 
     * @param expression
     *            The input to be analyzed. Might not be <code>null</code>.
     */
    public Lexer(String expression)
    {
        this.input = Objects.requireNonNull(expression);
        this.c = input.charAt(p);
    }

    /**
     * Returns the current character of the input.
     * 
     * @return the current character of the input.
     */
    protected char current()
    {
        return this.c;
    }

    /**
     * Returns the last character read or the current if it's the first.
     * 
     * @return The last character read or the current if it's the first.
     */
    protected char last()
    {
        return this.p == 0 ? current() : this.input.charAt(p - 1);
    }

    /**
     * Returns the next character of the input. It's {@link #EOF} if there insn't more character.
     * 
     * @return The next character of the input. It's {@link #EOF} if there insn't more character.
     */
    protected char nextWithoutConsume()
    {
        return this.p + 1 >= this.length() ? EOF : this.input.charAt(p + 1);
    }

    /**
     * Returns the index of the position in the input.
     * 
     * @return The index of the position in the input. If the index is greater than |size(input)| the {@link #current()} value is {@link #EOF}.
     */
    protected int index()
    {
        return p;
    }

    /**
     * Returns the length of the input.
     * 
     * @return The length of the input.
     */
    protected int length()
    {
        return this.input.length();
    }

    /**
     * Move to next non-whitespace character.
     */
    public void consume()
    {
        advance();
    }

    /**
     * Move one character if there is one available or detect "end of file".
     */
    public void advance()
    {
        p++;
        c = p >= input.length() ? EOF : input.charAt(p);
    }

    /**
     * Ensure that x is the next character on the input stream.
     * 
     * @param x
     *            The value to be checked as the next character on the input.
     * @throws Error
     *             If the next character of the input is different of x.
     */
    public void match(char x)
    {
        if (c == x)
        {
            consume();
        }
        else
        {
            throw new Error("Expecting " + x + "; found " + c);
        }
    }

    /**
     * Returns the next {@link Token} found.
     * 
     * @return The next {@link Token} found.
     */
    public abstract Token nextToken();
    
}
