package monitor.model.builder;

import java.util.Map;

import monitor.model.Memory;
import monitor.model.MemoryState;
import monitor.model.RamMemory;
import monitor.model.exception.ResourceException;
import monitor.util.commands.CommandFactory;

import org.hyperic.sigar.Mem;

public class MemoryBuilder extends ResourceBuilder<Memory> {

	@Override
	public Memory fetch() throws ResourceException {
		Mem mem = hypervisor().getMem();		
		Map<Integer, Map<String, String>> properties = CommandFactory.instance().decodeDimms().execute();
		return new RamMemory(mem.getRam(), new MemoryState(mem.getUsedPercent(), mem.getFreePercent()), properties);
	}
	
	public static Memory build () throws ResourceException{
		return new MemoryBuilder().fetch();
	}
}