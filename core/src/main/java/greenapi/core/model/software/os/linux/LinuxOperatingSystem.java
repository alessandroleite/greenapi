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
package greenapi.core.model.software.os.linux;

import greenapi.core.model.data.Frequency;
import greenapi.core.model.data.IOStats;
import greenapi.core.model.data.Temperature;
import greenapi.core.model.resources.net.NetworkInterface;
import greenapi.core.model.resources.net.NetworkInterfaces;
import greenapi.core.model.software.os.OperatingSystem;
import greenapi.core.model.software.os.commands.Command;
import greenapi.core.model.software.os.commands.Who;
import greenapi.core.model.software.os.commands.impl.linux.CpuScalingAvailableFrequencies;
import greenapi.core.model.software.os.commands.impl.linux.CpuTemperature;
import greenapi.core.model.software.os.commands.impl.linux.CurrentCpuFrequency;
import greenapi.core.model.software.os.commands.impl.linux.IOStat;
import greenapi.core.model.software.os.commands.impl.linux.NetworkInterfaceDescriptionImpl;
import greenapi.core.model.software.os.commands.impl.linux.NetworkInterfacesDescriptionImpl;
import greenapi.core.model.software.os.commands.impl.linux.Whoami;

import java.util.Map;


public class LinuxOperatingSystem extends OperatingSystem {
	
	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -8793069665840194590L;

	public LinuxOperatingSystem(String name) {
		super(name);
	}

	@Override
	public Command<IOStats> iostats() {
		return new IOStat();
	}

	@Override
	public CpuScalingAvailableFrequencies cpuAvailableFrequencies() {
		return new CpuScalingAvailableFrequencies();
	}

	@Override
	public Frequency currentCpuFrequency() {
		return new CurrentCpuFrequency().execute().getValue();
	}

	@Override
	public Map<String, Temperature> cpuTemperature() {
		return new CpuTemperature().execute().getValue();
	}
	
	@Override
	public NetworkInterfaces networkInterfaces() {
		NetworkInterfacesDescriptionImpl command = new NetworkInterfacesDescriptionImpl();
		return NetworkInterfaces.valueOf(command.execute().getValue());
	}

	@Override
	public Who who() {
		return new Whoami();
	}
	
	@Override
	public NetworkInterface networkInterfaceDescription(String id) {
		return new NetworkInterfaceDescriptionImpl(id).execute().getValue(); 
	}
}