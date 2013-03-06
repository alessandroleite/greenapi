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
package greenapi.core.model.resources.builders.impl;

import static com.google.common.base.Preconditions.checkArgument;
import greenapi.core.model.data.CpuState;
import greenapi.core.model.data.Temperature;
import greenapi.core.model.resources.Cpu;
import greenapi.core.model.resources.CpuSocket;
import greenapi.core.model.resources.builders.CpuBuilder;

public class CpuBuilderImpl implements CpuBuilder {

	private Integer id;
	private double irq;
	private double softIrq;
	private double stole;
	private Temperature cpuTemperature;
	private CpuState cpuState;
	private CpuSocket cpuSocket;

	@Override
	public Cpu build() {
		checkArgument(id != null && id >= 0);
		Cpu cpu = new Cpu(String.valueOf(id), irq, softIrq, stole, cpuState, cpuTemperature);
		
		cpu.setState(null);
		
		
		if (cpuSocket != null){
			cpu.setCpuSocket(cpuSocket);
		}
		return cpu;
	}

	@Override
	public CpuBuilder withId(Integer id) {
		this.id = id;
		return this;
	}

	@Override
	public CpuBuilder withIrq(double irq) {
		this.irq = irq;
		return this;
	}

	@Override
	public CpuBuilder withSoftIrq(double softIrq) {
		this.softIrq = softIrq;
		return this;
	}

	@Override
	public CpuBuilder withStole(double stole) {
		this.stole = stole;
		return this;
	}

	@Override
	public CpuBuilder withTemperature(Temperature temperature) {
		this.cpuTemperature = temperature;
		return this;
	}

	@Override
	public CpuBuilder withState(CpuState state) {
		this.cpuState = state;
		return this;
	}

	@Override
	public CpuBuilder in(CpuSocket cpuSocket) {
		this.cpuSocket = cpuSocket;
		return this;
	}
}