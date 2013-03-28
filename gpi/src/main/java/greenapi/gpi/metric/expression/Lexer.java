package greenapi.gpi.metric.expression;

import java.util.Objects;

public abstract class Lexer {
	/**
	 * Represents the end of file char.
	 */
	public static final char EOF = (char) -1;

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
	private int p = 0;

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
	public Lexer(String expression) {
		this.input = Objects.requireNonNull(expression);
		this.c = input.charAt(p);
	}

	/**
	 * Move one character if there is one available or detect "end of file".
	 */
	public void consume() {
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
	public void match(char x) {
		if (c == x) {
			consume();
		} else {
			throw new Error("Expecting " + x + "; found " + c);
		}
	}

	public abstract <T extends Token> T nextToken();
}
