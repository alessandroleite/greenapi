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
package greenapi.core.common.primitives;

public final class Doubles
{

    /**
     * Default constructor that's never called.
     */
    private Doubles()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Converts a {@link String} to {@link Double}. In case of wrong value, the return is <code>null</code>.
     * 
     * @param value
     *            The value to be converted to {@link Double}
     * @return The value converted to {@link Double} or <code>null</code>
     */
    public static double valueOf(String value)
    {
        try
        {
            return value == null ? 0.d : Double.parseDouble(value.trim());
        }
        catch (NumberFormatException exception)
        {
            return 0.d;
        }
    }
}
