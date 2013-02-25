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

import greenapi.core.model.exception.ResourceException;
import greenapi.core.model.resources.Cpu;
import greenapi.core.model.resources.CpuSocket;
import greenapi.core.model.resources.Machine;
import greenapi.core.model.resources.Memory;
import greenapi.core.model.resources.MemoryType;
import greenapi.core.model.resources.net.NetworkInterface;
import greenapi.core.model.software.os.OperatingSystem;
import greenapi.core.model.software.os.OperatingSystemFactory;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.Swap;

public class SigarResourceBuilder extends ResourceBuilder<Machine> {

	@Override
	public Machine build() throws ResourceException {
		final OperatingSystem os = OperatingSystemFactory.getSystemOS();
		
		try{
		return new MachineBuilderImpl()
				.withId(physicalMachineId())
				.withName(physicalMachineId())
				.withNetInterfaces(netInterfaces())
				.withCpus(cpus(os))
				.withMemory(memories())
				.withStorages(new StorageBuilderImpl().os(os).build())
				.withOs(os).build();
		}catch(SigarException exception){
			throw new ResourceException(exception);
		}
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

	private CpuSocket[] cpus(OperatingSystem os) throws SigarException {
		CpuPerc[] cpuPercs = hypervisor().getCpuPercList();
		Cpu[] cpus = new Cpu[cpuPercs.length];

		for (int i = 0; i < cpuPercs.length; i++) {
			cpus[i] = new CpuBuilderImpl()
					.withId(cpus[i].id())
					.withIrq(cpus[i].irq())
					.withSoftIrq(cpus[i].softIrq())
					.withStole(cpus[i].stole())
					.build();
		}

		org.hyperic.sigar.CpuInfo[] infos = hypervisor().getCpuInfoList();

		CpuSocket[] sockets = new CpuSocket[infos.length];

		for (int i = 0; i < sockets.length; i++) {
			sockets[i] = new CpuSocketBuilderImpl()
					.ofModel(infos[i].getModel())
					.withCores(cpus)
					.withScalingFrequencies()
					.withMinFrequency(infos[i].getMhzMin())
					.withMaxFrequency(infos[i].getMhzMax())					
					.withCacheSize(infos[i].getCacheSize())										
					.build();
		}
		return sockets;
	}

	private NetworkInterface[] netInterfaces() throws SigarException {
		return null;
	}

	public static void main(String[] args) throws SigarException {
		SigarProxy proxy = new SigarResourceBuilder().hypervisor();
		System.out.println(proxy.getNetInterfaceConfig().getHwaddr());
	}
}