/**
 * Copyright (c) 2012 GreenI2R
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

/**
 * This class is a {@link StringTokenizer} but with the option to get the tokens as an array of {@link String}.
 * 
 */
public final class StringTokenizer2 extends StringTokenizer
{

    /**
     * The {@link String} delimiter.
     */
    private final String delim;

    /**
     * <code>true</code> if the delimiters must be returned as tokens.
     */
    private final boolean returnDelims;

    /**
     * The {@link String} to be worked.
     */
    private final String str;

    /**
     * Create an instance of this class with a empty delimiter.
     * 
     * @param strValue
     *            The {@link String} value.
     */
    public StringTokenizer2(String strValue)
    {
        this(strValue, "", false);
    }

    /**
     * Create an instance of this class with a given {@link String} value and a delimiter.
     * 
     * @param strValue
     *            The {@link String} value. Might not be <code>null</code>.
     * @param delimiterValue
     *            The delimiter.
     */
    public StringTokenizer2(String strValue, String delimiterValue)
    {
        this(strValue, delimiterValue, false);
    }

    /**
     * Create an instance of this class with a given {@link String} value and a delimiter.
     * 
     * @param strValue
     *            The {@link String} value. Might not be <code>null</code>.
     * @param delimitersValue
     *            The delimiter.
     * @param returnDelimsValue
     *            flag indicating whether to return the delimiters as tokens.
     */
    public StringTokenizer2(String strValue, String delimitersValue, boolean returnDelimsValue)
    {
        super(strValue, delimitersValue, returnDelimsValue);

        this.str = strValue;
        this.delim = delimitersValue;
        this.returnDelims = returnDelimsValue;
    }

    /**
     * Return the tokens as array without change the state of the {@link StringTokenizer}.
     * 
     * @return The tokens as array without change the state of the {@link StringTokenizer}.
     */
    public String[] tokenAsArray()
    {
        StringTokenizer copy = new StringTokenizer(this.str, this.delim, this.returnDelims);

        String[] tokens = new String[copy.countTokens()];

        for (int i = 0; i < copy.countTokens(); i++)
        {
            tokens[i] = copy.nextToken();
        }
        return tokens;
    }
}
