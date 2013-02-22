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
package monitorapi.core.model.data;

import java.util.Collection;

import monitorapi.core.model.resource.Cpu;
import monitorapi.core.model.resource.CpuSocket;

import com.google.common.base.Preconditions;

public class CpuSocketState implements Data<CpuState[]> {

	/**
	 * Serial code version <code>serialVersionUID</code> 
	 */
	private static final long serialVersionUID = 1387416957174445700L;
	
	private final CpuSocket socket;
	
	private final Frequency currentFrequency; 

	public CpuSocketState(CpuSocket socket, Frequency frequency) {
		this.socket = Preconditions.checkNotNull(socket);
		this.currentFrequency = frequency;
	}

	@Override
	public CpuState[] value() {
		Collection<Cpu> cores = this.socket.cores().get();
		
		CpuState[] states = new CpuState[cores.size()];
				
		int i = 0;
		for(Cpu core : cores){
			states[i++] = core.state(); 
		}		
		return states;
	}

	/**
	 * @return the currentFrequency
	 */
	public Frequency frequency() {
		return currentFrequency;
	}
}
