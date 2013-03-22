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
package greenapi.core.model.resources.builders.impl;

import com.google.common.base.Preconditions;

import greenapi.core.common.base.Strings;
import greenapi.core.model.data.Frequency;
import greenapi.core.model.exception.ResourceException;
import greenapi.core.model.resources.Cpu;
import greenapi.core.model.resources.CpuSocket;
import greenapi.core.model.resources.builders.CpuSocketBuilder;
import greenapi.core.model.sensors.Sensor;

public class CpuSocketBuilderImpl implements CpuSocketBuilder
{

    /**
     * The vendor's name.
     */
    private String vendor;

    /**
     * The CPU model.
     */
    private String cpuModel;

    /**
     * The maximum CPU's frequency.
     */
    private long maxFrequency;
    /**
     * The minimum CPU's frequency.
     */
    private long minFrequency;
    
    /**
     * The CPU's cache size.
     */
    private long cacheSize;

    /**
     * 
     */
    private Cpu[] cores;
    
    /**
     * The CPU's frequencies.
     */
    private Frequency[] cpuFrequencies;
    
    /**
     * The CPU's sensors.
     */
    private Sensor<?, ?>[] sensors;

    @Override
    public CpuSocket build() throws ResourceException
    {

        Preconditions.checkArgument(cores != null && cores.length > 0, "Must be at least one core/cpu.");

        Preconditions.checkArgument(maxFrequency > 0 || cpuFrequencies != null && cpuFrequencies.length > 0,
                "CPU maximum frequency must be greater than zero.");

        if (this.cpuFrequencies == null || this.cpuFrequencies.length == 0)
        {
            this.cpuFrequencies = new Frequency[] {Frequency.newFrequencyInMhz(this.minFrequency), Frequency.newFrequencyInMhz(this.maxFrequency)};
        }

        CpuSocket cpuSocket = new CpuSocket(vendor, cpuModel, maxFrequency, cacheSize, this.cores, cpuFrequencies);

//        if (sensors != null)
//        {
//            for (Sensor<?, ?> sensor : this.sensors)
//            {
//                cpu
//            }
//        }

        return cpuSocket;
    }

    @Override
    public CpuSocketBuilder ofVendor(String vendorName)
    {
        this.vendor = Strings.checkArgumentIsNotNullOrEmpty(vendorName);
        return this;
    }

    @Override
    public CpuSocketBuilder ofModel(String model)
    {
        this.cpuModel = Strings.checkArgumentIsNotNullOrEmpty(model);
        return this;
    }

    @Override
    public CpuSocketBuilder withMaxFrequency(int maxFrequencyValue)
    {
        this.maxFrequency = maxFrequencyValue;
        return this;
    }

    @Override
    public CpuSocketBuilder withMinFrequency(int minFrequencyValue)
    {
        this.minFrequency = minFrequencyValue;
        return this;
    }

    @Override
    public CpuSocketBuilder withCacheSize(long cacheSizeValue)
    {
        this.cacheSize = cacheSizeValue;
        return this;
    }

    @Override
    public CpuSocketBuilder withCores(Cpu... cpus)
    {
        this.cores = cpus;
        return this;
    }

    @Override
    public CpuSocketBuilder withScalingFrequencies(Frequency... cpuFrequencyValues)
    {
        this.cpuFrequencies = cpuFrequencyValues;
        return this;
    }

    @Override
    public CpuSocketBuilder withSensors(Sensor<?, ?>... availableSensors)
    {
        this.sensors = availableSensors;
        return this;
    }
}
