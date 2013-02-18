package monitor.model.sensors;


import monitor.model.Memory;
import monitor.model.MemoryState;
import monitor.model.builder.SigarFactory;

import org.hyperic.sigar.Mem;

public class MemoryUsageSensor implements Sensor<Double, MemoryState>{

	private final Memory memory;

	public MemoryUsageSensor(Memory memory) {
		this.memory = memory;
	}

	@Override
	public void run() {
		collect();
	}

	@Override
	public MemoryState collect() {
		Mem mem = SigarFactory.getInstance().getMem();
		MemoryState memoryUsage = new MemoryState(mem.getUsedPercent(), mem.getFreePercent());
		this.memory.setState(memoryUsage);		
		return memoryUsage;
	}

	@Override
	public String name() {
		return "Memory Usage";
	}
}
