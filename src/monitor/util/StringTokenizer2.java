package monitor.util;

import java.util.StringTokenizer;

public class StringTokenizer2 extends StringTokenizer {

	private final String delim;
	private final boolean returnDelims;
	private final String str;

	public StringTokenizer2(String str) {
		this(str, "", false);
	}

	public StringTokenizer2(String str, String delim) {
		this(str, delim, false);
	}

	public StringTokenizer2(String str, String delim, boolean returnDelims) {
		super(str, delim, returnDelims);

		this.str = str;
		this.delim = delim;
		this.returnDelims = returnDelims;
	}

	/**
	 * Return the tokens as array without change the state of the {@link StringTokenizer}.
	 *   
	 * @return The tokens as array without change the state of the {@link StringTokenizer}.
	 */
	public String[] tokenAsArray() {
		StringTokenizer copy = new StringTokenizer(this.str, this.delim, this.returnDelims);

		String[] tokens = new String[copy.countTokens()];

		for (int i = 0; i < copy.countTokens(); i++) {
			tokens[i] = copy.nextToken();
		}

		return tokens;
	}
}