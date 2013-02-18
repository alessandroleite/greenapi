package monitor.model.builder;

import java.util.Arrays;
import java.util.Map;

import monitor.model.Cpu;
import monitor.model.CpuSocket;
import monitor.model.CpuState;
import monitor.model.Temperature;
import monitor.model.exception.ResourceException;
import monitor.util.commands.CommandFactory;

import org.hyperic.sigar.CpuPerc;

public class CpuBuilder extends ResourceBuilder<CpuSocket> {

	@Override
	public CpuSocket fetch() throws ResourceException {

			org.hyperic.sigar.CpuInfo[] infos = hypervisor().getCpuInfoList();
			CpuPerc[] cpus = hypervisor().getCpuPercList();

			CpuSocket socket = new CpuSocket(infos[0].getVendor(),
					infos[0].getModel(), infos[0].getMhz(),
					infos[0].getCacheSize(), CommandFactory.instance().cpuScalingAvailableFrequencies(0).execute());
			

			Map<String, Temperature> temperatures = CommandFactory.instance().cpuTemperature().execute();
			for (int i = 0; i < cpus.length; i++) {
				
				Cpu cpu = new Cpu(socket, String.valueOf(i), cpus[i].getIrq(),
						cpus[i].getSoftIrq(), cpus[i].getStolen());
				
				cpu.setState(new CpuState(cpu, cpus[i].getCombined(), 
						cpus[i].getUser(), cpus[i].getSys(), cpus[i].getIdle(), 
						cpus[i].getWait(), cpus[i].getNice(), CommandFactory.instance().currentCpuFrequency(cpu).execute()));
				
				cpu.setTemperature(temperatures.get(cpu.getName()));
				socket.add(cpu);				
			}
			
			for(Cpu cpu : socket.cores().get()){
				socket.cores().add(Arrays.asList(cpu.sensors()));
			}
			
			return socket;
	}

	public static CpuSocket build() throws ResourceException {
		return new CpuBuilder().fetch();
	}
}
