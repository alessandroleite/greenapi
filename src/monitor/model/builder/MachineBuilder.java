package monitor.model.builder;

import static monitor.util.SystemUtil.machineName;
import monitor.model.Machine;
import monitor.model.exception.ResourceException;
import monitor.util.commands.CommandFactory;

public class MachineBuilder extends ResourceBuilder<Machine> {

	@Override
	public Machine fetch() throws ResourceException {
		Machine machine = new Machine(machineName(),
				MemoryBuilder.build(), CpuBuilder.build());
		
		machine.setIOStats(CommandFactory.instance().iostat(machine).execute());
		
		return machine;
	}

	public static Machine build() {
		return new MachineBuilder().fetch();
	}
}