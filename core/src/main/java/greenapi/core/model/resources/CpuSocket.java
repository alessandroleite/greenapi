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
package greenapi.core.model.resources;

import java.util.Arrays;

import greenapi.core.model.data.CpuSocketState;
import greenapi.core.model.data.Data;
import greenapi.core.model.data.Frequency;
import greenapi.core.model.sensors.Sensor;

import static com.google.common.base.Preconditions.checkNotNull;

import static greenapi.core.common.base.Strings.NEW_LINE;
import static greenapi.core.common.base.Strings.TAB;

public class CpuSocket implements Resource
{

    /**
     * Serial code version <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = -1374875406516879490L;

    /**
     * Vendor name.
     */
    private final String vendor;

    /**
     * CPU model.
     */
    private final String model;

    /**
     * Number of Mega-Hertz.
     */
    private final long mhz;

    /**
     * Cores available in this socket.
     */
    private final Cores cores;

    /**
     * The frequencies available.
     */
    private final Frequency[] availableFrequencies;

    /**
     * The cache size.
     */
    private final long cacheSize;

    /**
     * The cpu state.
     */
    private CpuSocketState state;

    /**
     * 
     * @param vendorName
     *            The vendor name.
     * @param cpuSocketModel
     *            The CPU model.
     * @param frequencyInMhz
     *            The maximum frequency in MHz.
     * @param cacheSizeInBytes
     *            The cache size in bytes.
     * @param socketCores
     *            The cores of this CPU.
     * @param frequencies
     *            This CPU frequencies.
     */
    public CpuSocket(String vendorName, String cpuSocketModel, long frequencyInMhz, long cacheSizeInBytes, Cpu[] socketCores,
            Frequency... frequencies)
    {

        this.vendor = vendorName;
        this.model = cpuSocketModel;
        this.mhz = frequencyInMhz;
        this.cacheSize = cacheSizeInBytes;

        this.availableFrequencies = frequencies == null ? new Frequency[] {Frequency.newFrequencyInMhz(new Long(mhz))} : frequencies;
        Arrays.sort(this.availableFrequencies);
        this.cores = new Cores(this);

        for (Cpu core : socketCores)
        {
            this.add(core);
        }
    }

    /**
     * 
     * @param vendorName
     *            The vendor's name.
     * @param cpuSocketModel
     *            This CPU model.
     * @param frequencyInMhz
     *            This maximum frequency.
     * @param cacheSizeInBytes
     *            This cache size in bytes.
     * @param socketCores
     *            The cores of this socket.
     */
    public CpuSocket(String vendorName, String cpuSocketModel, long frequencyInMhz, long cacheSizeInBytes, Cpu... socketCores)
    {
        this(vendorName, cpuSocketModel, frequencyInMhz, cacheSizeInBytes, socketCores, Frequency.newFrequencyInMhz(frequencyInMhz));
    }

    /**
     * Add a given CPU (core) to this {@link CpuSocket}.
     * 
     * @param cpu
     *            The CPU (core) to be added.
     */
    public void add(Cpu cpu)
    {
        checkNotNull(cpu).setCpuSocket(this);
        this.cores().add(cpu);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (cacheSize ^ (cacheSize >>> 32));
        result = prime * result + cores.size();
        result = prime * result + (int) (mhz ^ (mhz >>> 32));
        result = prime * result + ((model == null) ? 0 : model.hashCode());
        result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (!(obj instanceof CpuSocket))
        {
            return false;
        }

        CpuSocket other = (CpuSocket) obj;
        if (cacheSize != other.cacheSize)
        {
            return false;
        }

        if (!cores.equals(other.cores))
        {
            return false;
        }

        if (mhz != other.mhz)
        {
            return false;
        }

        if (model == null)
        {
            if (other.model != null)
            {
                return false;
            }
        }
        else if (!model.equals(other.model))
        {
            return false;
        }

        if (vendor == null)
        {
            if (other.vendor != null)
            {
                return false;
            }
        }
        else if (!vendor.equals(other.vendor))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder("\n");
        builder.append("Vendor......: ").append(this.vendor()).append(NEW_LINE);
        builder.append("Model.......: ").append(this.model()).append("-").append(this.mhz()).append(NEW_LINE);
        builder.append("Cache size..: ").append(this.cacheSize() / 1024).append("Kb").append(NEW_LINE);
        builder.append("Frequency...: ").append(this.frequency().inGhz()).append(" Ghz").append(NEW_LINE);
        builder.append("Cores.......: \n[").append(NEW_LINE);

        for (Cpu cpu : this.cores().get())
        {
            builder.append(TAB).append(cpu).append(NEW_LINE);
        }

        builder.append("]").append(NEW_LINE);

        return builder.toString();
    }

    /**
     * Returns the current frequency.
     * 
     * @return The current frequency.
     */
    public Frequency frequency()
    {
        synchronized (this)
        {
            Frequency lastAvailableFrequency = this.availableFrequencies()[this.availableFrequencies().length - 1];
            return this.state() == null || this.state().frequency() == null ? lastAvailableFrequency : this.state().frequency();
        }
    }

    /**
     * @return the vendor
     */
    public String vendor()
    {
        return vendor;
    }

    /**
     * @return the model
     */
    public String model()
    {
        return model;
    }

    /**
     * @return the mhz
     */
    public long mhz()
    {
        return mhz;
    }

    /**
     * @return the cores
     */
    public Cores cores()
    {
        return cores;
    }

    /**
     * @return the cacheSize
     */
    public long cacheSize()
    {
        return cacheSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sensor<?, Data<?>>[] sensors()
    {
        // return new Sensor[] { new CpuSocketSensor(this) };
        // TODO
        return null;
    }

    /**
     * Returns the combined workload of the {@link Cores} of this {@link CpuSocket}.
     * 
     * @return The combined workload of the {@link Cores} of this {@link CpuSocket}.
     */
    public Double getCombinedLoad()
    {

        double sum = 0.0D;
        for (Cpu cpu : cores())
        {
            sum += cpu.load();
        }

        return sum / cores().size();
    }

    /**
     * Update this {@link CpuSocket} state and returns the previous state.
     * 
     * @param newState
     *            the new state of this {@link CpuSocket}.
     * @return The previous state.
     */
    public CpuSocketState setState(CpuSocketState newState)
    {
        synchronized (this)
        {
            final CpuSocketState previousSocketState = this.state;
            this.state = newState;
            return previousSocketState;
        }
    }

    /**
     * Return this {@link CpuSocket} state.
     * 
     * @return This {@link CpuSocketState}.
     */
    public CpuSocketState state()
    {
        return this.state;
    }

    /**
     * @return the available frequencies.
     */
    public Frequency[] availableFrequencies()
    {
        return availableFrequencies;
    }

    /**
     * Return the description of this {@link CpuSocket}. The description is: vendor name and model - frequency in MHz.
     * 
     * @return The description of this {@link CpuSocket}.
     */
    public String description()
    {
        return this.vendor() + " " + this.model() + "-" + this.mhz();
    }
}
