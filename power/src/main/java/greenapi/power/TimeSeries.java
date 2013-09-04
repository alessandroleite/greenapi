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
package greenapi.power;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

import greenapi.core.model.data.Data;

public abstract class TimeSeries<E, T extends Data<E>> implements Serializable, Cloneable
{
    /**
     * Serial code version <code>serialVersionUID</code> for serialization.
     */
    private static final long serialVersionUID = -4433684799195225186L;

    /**
     * The id of this {@link TimeSeries}. It's never <code>null</code> and is greater than zero.
     */
    private final BigInteger id;

    /**
     * The values of this {@link TimeSeries}.
     */
    private final List<T> data = new CopyOnWriteArrayList<>();

    /**
     * Creates a {@link TimeSeries} with the given id.
     * 
     * @param timeSeriesId
     *            The {@link TimeSeries} id. Might not be <code>null</code> and must be greater than zero.
     */
    public TimeSeries(BigInteger timeSeriesId)
    {
        this.id = timeSeriesId;
    }

    /**
     * Add a new time series data.
     * 
     * @param item
     *            The time series data to be add. Might not be <code>null</code>.
     */
    public void add(T item)
    {
        data.add(Objects.requireNonNull(item, "The item might not be null!"));
    }

    /**
     * @return the data
     */
    public List<T> getData()
    {
        return Collections.unmodifiableList(this.data);
    }

    /**
     * @return the id
     */
    public BigInteger getId()
    {
        return id;
    }
}
