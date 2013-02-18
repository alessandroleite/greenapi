package monitor.model.sensors;

import monitor.model.Cpu;
import monitor.model.CpuSocket;
import monitor.model.CpuSocketState;
import monitor.model.CpuState;
import monitor.model.Data;
import monitor.model.builder.SigarFactory;
import monitor.util.commands.CommandFactory;

import org.hyperic.sigar.CpuPerc;

public class CpuSocketSensor implements Sensor<CpuState[], CpuSocketState> {

	private final CpuSocket socket;

	public CpuSocketSensor(CpuSocket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		this.collect();
	}

	@Override
	public CpuSocketState collect() {

		CpuPerc[] cpus = SigarFactory.getInstance().getCpuPercList();		
		CpuSocketState state = new CpuSocketState(this.socket, CommandFactory
				.instance()
				.currentCpuFrequency(this.socket.cores().iterator().next())
				.execute());			

		int i = 0;
		for (Cpu cpu : this.socket.cores()) {
			cpu.setState(new CpuState(cpu, cpus[i].getCombined(), cpus[i]
					.getUser(), cpus[i].getSys(), cpus[i].getIdle(), cpus[i]
					.getWait(), cpus[i].getNice(), state.frequency()));

			for (Sensor<?, Data<?>> sensor : cpu.sensors()) {
				sensor.collect();
			}

			i++;
		}
		socket.setState(state);
		
		return state;
	}

	@Override
	public String name() {
		return this.getClass().getSimpleName();
	}
}
