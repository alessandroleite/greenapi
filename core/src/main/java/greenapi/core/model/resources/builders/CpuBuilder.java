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
package greenapi.core.model.resources.builders;

import greenapi.core.model.data.CpuState;
import greenapi.core.model.data.Temperature;
import greenapi.core.model.resources.Cpu;
import greenapi.core.model.resources.CpuSocket;

public interface CpuBuilder extends Builder<Cpu> {
	
	CpuBuilder withId(Integer id);
	
	CpuBuilder withIrq(double irq);
	
	CpuBuilder withSoftIrq(double softIrq);
	
	CpuBuilder withStole(double stole);
	
	CpuBuilder withTemperature(Temperature temperature);
	
	CpuBuilder withState(CpuState state);
	
	CpuBuilder in(CpuSocket cpuSocket);

}