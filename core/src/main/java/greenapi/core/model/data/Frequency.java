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
package greenapi.core.model.data;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;
import javax.measure.unit.Unit;

import static javax.measure.unit.SI.GIGA;
import static javax.measure.unit.SI.HERTZ;
import static javax.measure.unit.SI.MEGA;

public final class Frequency implements Data<Long>, Comparable<Frequency>, Cloneable
{
    
    /**
     * This instance represents a <code>null</code> frequency. In that case, the frequency value is zero.
     */
    public static final Frequency NULL_FREQUENCY = newFrequencyInMhz(0L);

    /**
     * Serial code version <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = 5492468590469502716L;

    /**
     * The {@link Frequency}'s value.
     */
    private final Measure<Long, javax.measure.quantity.Frequency> value;

    /**
     * Creates a {@link Frequency} instance with a given value in MHz.
     * 
     * @param valueInMhz
     *            The frequency's value in MHz.
     */
    public Frequency(long valueInMhz)
    {
        this(valueInMhz, MEGA(HERTZ));
    }

    /**
     * Factory method to create an instance with a given value.
     * 
     * @param frequencyValue
     *            The frequency's value.
     * @param valueUnit
     *            The unit of the value.
     */
    public Frequency(long frequencyValue, Unit<javax.measure.quantity.Frequency> valueUnit)
    {
        this.value = DecimalMeasure.valueOf(Long.valueOf(frequencyValue), valueUnit);
    }

    /**
     * Factory method that creates an instance with a given value in MHz.
     * 
     * @param valueInMhz
     *            The frequency's value in MHz.
     * @return An instance of {@link Frequency} with the given value.
     */
    public static Frequency newFrequencyInMhz(long valueInMhz)
    {
        return new Frequency(valueInMhz);
    }

    
    /**
     * Factory method that creates an instance with a given value in GHz.
     * 
     * @param valueInGhz
     *            The frequency's value in GHz.
     * @return An instance of {@link Frequency} with the given value.
     */
    public static Frequency newFrequencyInGhz(long valueInGhz)
    {
        return new Frequency(valueInGhz, GIGA(HERTZ));
    }

    /**
     * Factory method that creates an instance with a given value in Hertz.
     * 
     * @param valueInHertz
     *            The frequency's value in hertz.
     * @return An instance of {@link Frequency} with the given value.
     */
    public static Frequency newFrequencyInHertz(long valueInHertz)
    {
        return new Frequency(valueInHertz, HERTZ);
    }

    @Override
    public Long value()
    {
        return this.value.longValue(MEGA(HERTZ));
    }

    /**
     * Returns the frequency value in GHz.
     * 
     * @return The frequency value in GHz.
     */
    public double inGhz()
    {
        return this.value.doubleValue(GIGA(HERTZ));
    }

    /**
     * Returns the frequency value in MHz.
     * 
     * @return The frequency value in MHz.
     */
    public double inMhz()
    {
        return this.value.doubleValue(MEGA(HERTZ));
    }

    @Override
    public int compareTo(Frequency other)
    {
        return this.value().compareTo(other.value());
    }

    @Override
    public String toString()
    {
        return this.value().toString();
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        Frequency other = (Frequency) obj;
        if (value == null)
        {
            if (other.value != null)
            {
                return false;
            }
        }
        else if (!value.equals(other.value))
        {
            return false;
        }
        return true;
    }

    @Override
    public Frequency clone()
    {
        try
        {
            return (Frequency) super.clone();
        }
        catch (CloneNotSupportedException exception)
        {
            return Frequency.newFrequencyInMhz(this.value());
        }
    }
}
