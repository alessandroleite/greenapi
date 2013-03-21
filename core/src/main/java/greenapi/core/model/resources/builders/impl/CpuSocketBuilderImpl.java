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
package greenapi.core.model.resources.builders.impl;

import static com.google.common.base.Preconditions.checkArgument;
import greenapi.core.common.base.Strings;
import greenapi.core.model.data.Frequency;
import greenapi.core.model.exception.ResourceException;
import greenapi.core.model.resources.Cpu;
import greenapi.core.model.resources.CpuSocket;
import greenapi.core.model.resources.builders.CpuSocketBuilder;
import greenapi.core.model.sensors.Sensor;

public class CpuSocketBuilderImpl implements CpuSocketBuilder {

	private String vendor;
	private String cpuModel;
	private long maxFrequency;
	private long minFrequency;
	private long cacheSize;

	private Cpu[] cores;
	private Frequency[] frequencies;
	private Sensor<?, ?>[] sensors;

	@Override
	public CpuSocket build() throws ResourceException {

		checkArgument((cores != null && cores.length > 0), "Must be at least one core/cpu.");
		
		checkArgument((maxFrequency > 0 || frequencies != null && frequencies.length > 0), "CPU maximum frequency must be greater than zero.");
		
		if (this.frequencies == null || this.frequencies.length == 0) {
			this.frequencies = new Frequency[] { Frequency.newFrequencyInMhz(this.minFrequency), Frequency.newFrequencyInMhz(this.maxFrequency) };
		}
		
		CpuSocket cpuSocket = new CpuSocket(vendor, cpuModel, maxFrequency, cacheSize, this.cores, frequencies);

		if (sensors != null) {
			for (Sensor<?, ?> sensor : this.sensors) {
			}
		}

		return cpuSocket;
	}

	@Override
	public CpuSocketBuilder ofVendor(String vendor) {
		this.vendor = Strings.checkArgumentIsNullOrEmpty(vendor);
		return this;
	}

	@Override
	public CpuSocketBuilder ofModel(String model) {
		this.cpuModel = Strings.checkArgumentIsNullOrEmpty(model);
		return this;
	}

	@Override
	public CpuSocketBuilder withMaxFrequency(int maxFrequency) {
		this.maxFrequency = maxFrequency;
		return this;
	}

	@Override
	public CpuSocketBuilder withMinFrequency(int minFrequency) {
		this.minFrequency = minFrequency;
		return this;
	}

	@Override
	public CpuSocketBuilder withCacheSize(long cacheSize) {
		this.cacheSize = cacheSize;
		return this;
	}

	@Override
	public CpuSocketBuilder withCores(Cpu... cpus) {
		this.cores = cpus;
		return this;
	}

	@Override
	public CpuSocketBuilder withScalingFrequencies(Frequency... frequencies) {
		this.frequencies = frequencies;
		return this;
	}

	@Override
	public CpuSocketBuilder withSensors(Sensor<?, ?>... sensors) {
		this.sensors = sensors;
		return this;
	}
}