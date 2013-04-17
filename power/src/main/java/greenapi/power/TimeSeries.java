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
