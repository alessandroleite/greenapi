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
package greenapi.core.model.software.os.commands;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Preconditions;

/**
 * @param <T>
 */
public final class Result<T> implements Serializable
{

    /**
     * Serial code version <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = -2841637758284066768L;

    /**
     * The value of the result.
     */
    private final T value;

    /**
     * The errors of the execution.
     */
    private final Map<String, Throwable> errors = new HashMap<>();

    /**
     * Create an instance of this class assigned it a value.
     * 
     * @param resultValue The value of the result.
     */
    public Result(T resultValue)
    {
        this.value = resultValue;
    }

    /**
     * Create an instance of this class assigned it a value and its execution errors.
     * 
     * @param resultValue
     *            The value of the result.
     * @param executionErrors
     *            The errors of the execution.
     */
    public Result(T resultValue, Throwable... executionErrors)
    {
        this.value = resultValue;

        if (errors != null)
        {
            for (Throwable err : executionErrors)
                this.errors.put(err.getMessage(), err);
        }
    }

    /**
     * Factory method to create a {@link Result} with its value and errors in case of exists one.
     * 
     * @param resultValue
     *            The value of the result.
     * @param errors
     *            The errors of the execution.
     * @param <T>
     *            The result value type.
     * @return An instance of {@link Result} with its value and errors in case of exists one.
     */
    public static <T> Result<T> newResult(T resultValue, Throwable... errors)
    {
        return new Result<>(resultValue, errors);
    }

    /**
     * Factory method to create a new failure result. A failure result often has a <code>null</code> value and at least one error.
     * 
     * @param errors
     *            The errors with the cause of the failure.
     * @param <T>
     *            The result value type expected.
     * @return An instance of the {@link Result} with a <code>null</code> value and at least one error.
     */
    public static <T> Result<T> newFailureResult(Throwable... errors)
    {
        Preconditions.checkArgument(errors.length > 0);
        return new Result<T>((T) null, errors);
    }

    /**
     * Add one errors to this {@link Result}.
     * 
     * @param error
     *            The error do be added.
     */
    public void add(Throwable error)
    {
        this.addAll(Collections.singletonMap(error.getMessage(), error));
    }

    /**
     * Copy all errors of the given {@link Map} to this {@link Result}.
     * 
     * @param errorsToCopy
     *            The errors to be copied replacing the same errors.
     */
    public void addAll(Map<String, Throwable> errorsToCopy)
    {
        for (String key : errors.keySet())
        {
            this.errors.put(key, errors.get(key));
        }
    }

    /**
     * Returns a read-only {@link Map} with the errors of the execution.
     * 
     * @return A read-only {@link Map} with the errors of the execution.
     */
    public Map<String, Throwable> getErrors()
    {
        return Collections.unmodifiableMap(this.errors);
    }

    /**
     * Returns <code>true</code> if the execution occurred with errors.
     * 
     * @return <code>true</code> if the execution occurred with errors.
     */
    public boolean wasSuccessfully()
    {
        return this.errors.isEmpty();
    }

    /**
     * Returns the value of the execution.
     * 
     * @return the value of the execution.
     */
    public T getValue()
    {
        return value;
    }
}
