package greenapi.power;

import java.math.BigInteger;

import greenapi.core.model.data.Data;
import greenapi.core.model.resources.Resource;
import greenapi.core.model.sensors.Sensor;

public final class ResourceTimeSeries<E, T extends Data<E>> extends TimeSeries<E, T>
{
    /**
     * Serial code version <code>serialVersionUID</code> for serialization.
     */
    private static final long serialVersionUID = 1356566451876827511L;

    /**
     * The resource that was used by the sensor to got the data.
     */
    private final Resource resource;

    /**
     * The sensor that provides the data.
     */
    private final Sensor<E, Data<E>> sensor;

    /**
     * Creates an instance of the {@link ResourceTimeSeries}.
     * 
     * @param id
     *            The {@link TimeSeries} id. Might not be <code>null</code>.
     * @param res
     *            The {@link Resource} of this {@link TimeSeries}.
     * @param resourceSensor
     *            The {@link Sensor} that provides the data. Might not be <code>null</code>.
     */
    public ResourceTimeSeries(BigInteger id, Resource res, Sensor<E, Data<E>> resourceSensor)
    {
        super(id);
        this.resource = res;
        this.sensor = resourceSensor;
    }

    /**
     * @return the sensor
     */
    public Sensor<E, Data<E>> sensor()
    {
        return sensor;
    }

    /**
     * @return the resource
     */
    public Resource resource()
    {
        return resource;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((resource == null) ? 0 : resource.hashCode());
        result = prime * result + ((sensor == null) ? 0 : sensor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        ResourceTimeSeries<?, ?> other = (ResourceTimeSeries<?, ?>) obj;
        if (resource == null)
        {
            if (other.resource != null)
            {
                return false;
            }
        }
        else if (!resource.equals(other.resource))
        {
            return false;
        }
        if (sensor == null)
        {
            if (other.sensor != null)
            {
                return false;
            }
        }
        else if (!sensor.equals(other.sensor))
        {
            return false;
        }
        return true;
    }
}
