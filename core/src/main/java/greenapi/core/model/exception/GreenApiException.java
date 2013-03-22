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
package greenapi.core.model.exception;

public class GreenApiException extends RuntimeException
{

    /**
     * Serial code version <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = -1653041892643887935L;

    /**
     * Create an exception with a given cause.
     * 
     * @param cause
     *            The cause (which is saved for later retrieval by the Throwable.getCause() method). (A null value is permitted, and indicates that
     *            the cause is nonexistent or unknown.).
     */
    public GreenApiException(Throwable cause)
    {
        super(cause);
    }

    /**
     * Create an exception with a given message.
     * 
     * @param message
     *            the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     */
    public GreenApiException(String message)
    {
        super(message);
    }

    /**
     * Create a message with a given message and cause.
     * 
     * @param message
     *            the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     * @param cause
     *            The cause (which is saved for later retrieval by the Throwable.getCause() method). (A null value is permitted, and indicates that
     *            the cause is nonexistent or unknown.).
     */
    public GreenApiException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
