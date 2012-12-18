package monitor.model.sensors;

import java.util.List;

import monitor.model.IOStat;
import monitor.model.IOStats;
import monitor.model.Machine;
import monitor.util.commands.CommandFactory;

public class IOSensor implements Sensor<List<IOStat>, IOStats> {
	
	protected final Machine machine;
	
	public IOSensor(Machine machine){
		this.machine = machine;
	}

	@Override
	public void run() {
		collect();
	}
	
	@Override
	public IOStats collect() {
		machine.updateIOStats(CommandFactory.instance().iostat().execute());
		return machine.ioStats();
	}

	@Override
	public String name() {
		return "IO/Stat";
	}
}