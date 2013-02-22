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
package monitorapi.core.model.resource.builders.impl;

import monitorapi.core.common.base.Strings;
import monitorapi.core.model.data.Frequency;
import monitorapi.core.model.exception.ResourceException;
import monitorapi.core.model.resource.Cpu;
import monitorapi.core.model.resource.CpuSocket;
import monitorapi.core.model.resource.builders.CpuSocketBuilder;
import monitorapi.core.model.sensors.Sensor;

public class CpuSocketBuilderImpl implements CpuSocketBuilder {

	private String vendor;
	private String cpuModel;
	private int maxFrequency;
	private long cacheSize;

	private Cpu[] cores;
	private Frequency[] frequencies;
	private Sensor<?, ?>[] sensors;

	@Override
	public CpuSocket build() throws ResourceException {
		CpuSocket cpuSocket = new CpuSocket(vendor, cpuModel, maxFrequency, cacheSize, frequencies);

		for (Cpu cpu : this.cores) {
			cpuSocket.add(cpu);
		}

		for (Sensor<?, ?> sensor : this.sensors) {
			// TODO 
			// cpuSocket.add(sensor);
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
	public CpuSocketBuilder withFrequency(int maxFrequency) {
		this.maxFrequency = maxFrequency;
		return this;
	}

	@Override
	public CpuSocketBuilder withCacheSize(long cacheSize) {
		this.cacheSize = cacheSize;
		return this;
	}

	@Override
	public CpuSocketBuilder withCores(Cpu... cpus) {
		if (cpus == null || cpus.length == 0) {
			throw new IllegalArgumentException();
		}

		this.cores = cpus;
		return this;
	}

	@Override
	public CpuSocketBuilder withScalingFrequencies(Frequency... frequencies) {
		this.frequencies = frequencies;
		return null;
	}

	@Override
	public CpuSocketBuilder withSensors(Sensor<?, ?>... sensors) {
		this.sensors = sensors;
		return this;
	}
}