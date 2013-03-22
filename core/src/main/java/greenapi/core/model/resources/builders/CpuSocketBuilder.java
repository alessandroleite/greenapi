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
package greenapi.core.model.resources.builders;

import greenapi.core.model.data.Frequency;
import greenapi.core.model.resources.Cpu;
import greenapi.core.model.resources.CpuSocket;
import greenapi.core.model.sensors.Sensor;

public interface CpuSocketBuilder extends Builder<CpuSocket>
{

    /**
     * Assign a vendor name to the {@link CpuSocket}.
     * 
     * @param vendor
     *            The vendor's name.
     * @return The same instance of the fluent interface.
     */
    CpuSocketBuilder ofVendor(String vendor);

    /**
     * Define the CPU's model.
     * 
     * @param model
     *            The CPU model.
     * @return The same instance of the fluent interface.
     */
    CpuSocketBuilder ofModel(String model);

    /**
     * Define the maximum frequency of the {@link CpuSocket}.
     * 
     * @param maxFrequency
     *            The frequency value.
     * @return The same instance of the fluent interface.
     */
    CpuSocketBuilder withMaxFrequency(int maxFrequency);

    /**
     * Define the minimum frequency of the {@link CpuSocket}.
     * 
     * @param minFrequency
     *            The frequency value.
     * @return The same instance of the fluent interface.
     */
    CpuSocketBuilder withMinFrequency(int minFrequency);

    /**
     * Define {@link CpuSocket}'s cache size.
     * 
     * @param cacheSize
     *            The {@link CpuSocket}'s cache size.
     * @return The same instance of the fluent interface.
     */
    CpuSocketBuilder withCacheSize(long cacheSize);

    /**
     * Add the core(s) to a {@link CpuSocket}.
     * 
     * @param cpus
     *            The core(s) of the {@link CpuSocket}
     * @return The same instance of the fluent interface.
     */
    CpuSocketBuilder withCores(Cpu... cpus);

    /**
     * Define the frequencies of the {@link CpuSocket} if it supports DVFS.
     * 
     * @param frequencies
     *            The available frequencies.
     * @return The same instance of the fluent interface.
     */
    CpuSocketBuilder withScalingFrequencies(Frequency... frequencies);

    /**
     * Define the sensors to the {@link CpuSocket}.
     * 
     * @param sensors
     *            The sensors of the {@link CpuSocket}
     * @return The same instance of the fluent interface.
     */
    CpuSocketBuilder withSensors(Sensor<?, ?>... sensors);
}
