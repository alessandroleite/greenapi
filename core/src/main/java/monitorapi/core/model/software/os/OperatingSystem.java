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
package monitorapi.core.model.software.os;

import static java.util.Objects.requireNonNull;

import java.io.Serializable;
import java.util.Map;

import monitorapi.core.model.Frequency;
import monitorapi.core.model.IOStats;
import monitorapi.core.model.Temperature;
import monitorapi.core.model.software.os.command.Command;

public abstract class OperatingSystem implements Serializable,
		Comparable<OperatingSystem> {

	/**
	 * Serial code version <code>serialVersionUID</code> 
	 */
	private static final long serialVersionUID = -63543969636463188L;
	
	private final String name;

	public OperatingSystem(String name) {
		this.name = requireNonNull(name);
	}

	public String name() {
		return name;
	}

	/**
	 * Returns the command to get the I/O states of a machine. This method does
	 * not execute the command.
	 * 
	 * @return the command to get the I/O states of a machine.
	 */
	public abstract Command<IOStats> iostats();

	/**
	 * Returns the command to get the CPU frequencies. This method does not
	 * execute the command.
	 * 
	 * @return Returns the command to get the CPU frequencies.
	 */
	public abstract Command<Frequency[]> cpuAvailableFrequencies();
	
	public abstract Command<Frequency> currentCpuFrequency();
	
	public abstract Command<Map<String, Temperature>> cpuTemperature();

	
	@Override
	public int compareTo(OperatingSystem other) {
		return this.name.compareTo(other.name());
	}
}
