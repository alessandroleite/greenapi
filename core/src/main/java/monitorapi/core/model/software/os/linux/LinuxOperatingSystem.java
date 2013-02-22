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
package monitorapi.core.model.software.os.linux;

import java.util.Map;

import monitorapi.core.model.Frequency;
import monitorapi.core.model.IOStats;
import monitorapi.core.model.Temperature;
import monitorapi.core.model.software.os.OperatingSystem;
import monitorapi.core.model.software.os.command.Command;
import monitorapi.core.model.software.os.command.linux.CpuScalingAvailableFrequencies;
import monitorapi.core.model.software.os.command.linux.CpuTemperature;
import monitorapi.core.model.software.os.command.linux.CurrentCpuFrequency;
import monitorapi.core.model.software.os.command.linux.IOStat;


public class LinuxOperatingSystem extends OperatingSystem {
	
	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -4413996798883823614L;

	public LinuxOperatingSystem(String name) {
		super(name);
	}


	@Override
	public Command<IOStats> iostats() {
		return new IOStat();
	}

	@Override
	public Command<Frequency[]> cpuAvailableFrequencies() {
		return new CpuScalingAvailableFrequencies();
	}

	@Override
	public Command<Frequency> currentCpuFrequency() {
		return new CurrentCpuFrequency();
	}

	@Override
	public Command<Map<String, Temperature>> cpuTemperature() {
		return new CpuTemperature();
	}
}
