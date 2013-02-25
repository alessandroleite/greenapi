/**
 * Copyright (c) 2012 Alessandro Ferreira Leite, http://www.alessandro.cc/
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package greenapi.core.common.base;

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