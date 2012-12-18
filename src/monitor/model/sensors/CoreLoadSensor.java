package monitor.model.sensors;

import monitor.model.Cpu;
import monitor.model.CpuState;
import monitor.model.builder.SigarFactory;
import monitor.util.commands.CommandFactory;

import org.hyperic.sigar.CpuPerc;

@Deprecated
public class CoreLoadSensor implements Sensor<Double[], CpuState>, Runnable {

	private final Cpu cpu;

	public CoreLoadSensor(Cpu cpu) {
		this.cpu = cpu;
	}

	@Override
	public void run() {
		collect();
	}

	@Override
	public CpuState collect() {
		final int i = Integer.valueOf(cpu.getName());
		CpuPerc[] cpus = SigarFactory.getInstance().getCpuPercList();

		cpu.updateState(new CpuState(cpu, cpus[i].getCombined(), cpus[i]
				.getUser(), cpus[i].getSys(), cpus[i].getIdle(), cpus[i]
				.getWait(), cpus[i].getNice(), CommandFactory.instance()
				.currentCpuFrequency(cpu).execute()));

		return cpu.getState();

	}

	@Override
	public String name() {
		return this.getClass().getSimpleName();
	}
}