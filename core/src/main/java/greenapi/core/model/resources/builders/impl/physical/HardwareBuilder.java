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
package greenapi.core.model.resources.builders.impl.physical;

import static com.google.common.base.Preconditions.checkArgument;
import greenapi.core.model.exception.ResourceException;
import greenapi.core.model.resources.Cpu;
import greenapi.core.model.resources.CpuSocket;
import greenapi.core.model.resources.Machine;
import greenapi.core.model.resources.Memory;
import greenapi.core.model.resources.MemoryType;
import greenapi.core.model.resources.builders.impl.CpuBuilderImpl;
import greenapi.core.model.resources.builders.impl.CpuSocketBuilderImpl;
import greenapi.core.model.resources.builders.impl.MachineBuilderImpl;
import greenapi.core.model.resources.builders.impl.MemoryBuilderImpl;
import greenapi.core.model.resources.builders.impl.NetworkInterfaceBuilderImpl;
import greenapi.core.model.resources.builders.impl.StorageBuilderImpl;
import greenapi.core.model.resources.net.NetworkInterface;
import greenapi.core.model.resources.net.NetworkInterfaceInfo;
import greenapi.core.model.resources.net.NetworkInterfaces;
import greenapi.core.model.software.os.OperatingSystem;
import lshw.types.Capabilities;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetInfo;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;

public class HardwareBuilder extends ResourceBuilder<Machine> {
	
	private OperatingSystem os;
	
	public HardwareBuilder withOS(OperatingSystem os) {
		this.os = os;
		return this;
	}
	
	@Override
	public Machine build() throws ResourceException {	
		checkArgument(os != null, "An operation system must not be null!");
		return new MachineBuilderImpl()
				.withId(physicalMachineId())
				.withName(physicalMachineId())
				.withNetInterfaces(netInterfaces())
				.withCpus(cpus())
				.withMemory(memories())
				.withStorages(new StorageBuilderImpl().os(os).build())
				.withOs(os)
				.build();
	}

	private String physicalMachineId() throws SigarException {
		return this.hypervisor().getNetInterfaceConfig().getHwaddr();
	}

	private Memory[] memories() throws SigarException {
		Mem mem = hypervisor().getMem();
		Swap swap = hypervisor().getSwap();

		return new Memory[] {
				new MemoryBuilderImpl().withSize(mem.getTotal()).ofType(MemoryType.RAM).build(),
				new MemoryBuilderImpl().withSize(swap.getTotal()).ofType(MemoryType.SWAP).build() };
	}

	private CpuSocket[] cpus() throws SigarException {
		CpuPerc[] cpuPercs = hypervisor().getCpuPercList();
		
		
		Cpu[] cpus = new Cpu[cpuPercs.length];

		for (int i = 0; i < cpuPercs.length; i++) {
			cpus[i] = new CpuBuilderImpl()
					.withId(i)
					.withIrq(cpuPercs[i].getIrq())
					.withSoftIrq(cpuPercs[i].getSoftIrq())
					.withStole(cpuPercs[i].getStolen())
					.build();
		}
		

		org.hyperic.sigar.CpuInfo[] cpuInfos = hypervisor().getCpuInfoList();

		CpuSocket[] sockets = new CpuSocket[cpuInfos[0].getTotalSockets()];

		for (int i = 0; i < sockets.length; i++) {
			sockets[i] = new CpuSocketBuilderImpl()
					.ofModel(cpuInfos[i].getModel())
					.ofVendor(cpuInfos[i].getVendor())
					.withCores(cpus)
					.withScalingFrequencies()
					.withMinFrequency(cpuInfos[i].getMhzMin())
					.withMaxFrequency(cpuInfos[i].getMhzMax())
					.withCacheSize(cpuInfos[i].getCacheSize())
					.build();
		}
		return sockets;
	}

	private NetworkInterfaces netInterfaces() throws SigarException {
		String[] interfacesId = hypervisor().getNetInterfaceList();
		NetInterfaceConfig primaryInterface = hypervisor().getNetInterfaceConfig();
		
		NetworkInterfaces interfaces = new NetworkInterfaces();
		
		for(int i = 0; i < interfacesId.length; i++) {
			NetInterfaceConfig interfaceConfig = hypervisor().getNetInterfaceConfig(interfacesId[i]);
			
			NetInfo netInfo = hypervisor().getNetInfo();
			
			NetworkInterface ni = new NetworkInterfaceBuilderImpl()
					.useOperatingSystemInformations(this.os)
					.withId(interfaceConfig.getHwaddr().toLowerCase())
					.withLogicalName(interfaceConfig.getName())
					.withCapabilities(new Capabilities())
					.ofType(interfaceConfig.getType())
					.isPrimary(primaryInterface.getHwaddr().equalsIgnoreCase(interfaceConfig.getHwaddr()))
					.withNetworkInfo(
							new NetworkInterfaceInfo(
									netInfo.getDefaultGateway(), netInfo.getPrimaryDns(),
									netInfo.getSecondaryDns(), netInfo.getDomainName(), 
									netInfo.getHostName(), interfaceConfig.getAddress6(), 
									interfaceConfig.getNetmask(), interfaceConfig.getBroadcast(), 
									interfaceConfig.getAddress(), interfaceConfig.getDestination()))
					.build();
			interfaces.add(ni);
		}
		
		return interfaces;
	}
}