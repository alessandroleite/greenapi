/**
 * Copyright (c) 2012 I2RGreen
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

/**
 * Static utility methods pertaining to {@code boolean} primitives, that are not already found in either {@link Boolean}.
 */
public final class Booleans
{
    /**
     * 
     */
    private Booleans()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Convert a boolean wrapper value to a primitive value considering that a <code>null</code> is a <code>false</code> primitive value.
     * 
     * @param value
     *            The wrapper' value to be converted to a primitive value.
     * @return The wrapper value converted to a primitive boolean value.
     */
    public static boolean valueOf(Boolean value)
    {
        return value == null ? false : value;
    }

    /**
     * Converts a {@link String} value such as on, yes, 1 (one) to <code>true</code> and off, no and zero (0) to <code>false</code>.
     * 
     * @param value
     *            The {@link String} value to be converted to {@link Boolean}.
     * @return An boolean that represents the {@link String}.
     */
    public static boolean valueOf(String value)
    {
        switch (value)
        {
        case "on":
        case "yes":
        case "1":
            return true;
        case "off":
        case "no":
        case "0":
        default:
            return false;
        }
    }
}
