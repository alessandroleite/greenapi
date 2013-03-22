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

/**
 * 
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Objects;

public final class CopyInputStream
{
    /**
     * The reference to the origin {@link InputStream}.
     */
    private final InputStream source;

    /**
     * Create a instance of this class but it does not copy the {@link InputStream} yet.
     * 
     * @param is
     *            The {@link InputStream} to be copied. Might not be <code>null</code>.
     */
    public CopyInputStream(InputStream is)
    {
        this.source = Objects.requireNonNull(is);
    }

    /**
     * Return a copy of the given {@link InputStream}.
     * 
     * @return A copy of the given {@link InputStream}.
     * @throws IOException
     *             If it doesn't possible to copy the {@link InputStream}.
     */
    public InputStream copy() throws IOException
    {
        try (ByteArrayOutputStream copy = new ByteArrayOutputStream())
        {
            copyIn(copy);
            return (InputStream) new ByteArrayInputStream(copy.toByteArray());
        }
    }

    /**
     * Copy all content of the given {@link InputStream} into a given {@link OutputStream}.
     * 
     * @param dest
     *            The {@link OutputStream} to write the data of the {@link InputStream}.
     * @return The number of bytes reads.
     * @throws IOException
     *             If the {@link InputStream} is in an invalid state.
     */
    private int copyIn(ByteArrayOutputStream dest) throws IOException
    {
        int read = 0;
        int chunk = 0;
        byte[] data = new byte[256];

        while (-1 != (chunk = source.read(data)))
        {
            read += data.length;
            dest.write(data, 0, chunk);
        }
        return read;
    }
}
