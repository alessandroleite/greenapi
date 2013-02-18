package monitor.model.sensors;

import monitor.model.Cpu;
import monitor.model.Temperature;
import monitor.util.commands.CommandFactory;

public class CpuTemperature implements Sensor<Double, Temperature>{

	public static final Temperature NULL_TEMPERATURE = new Temperature(-1, -1,
			-1);

	private final Cpu cpu;

	public CpuTemperature(Cpu cpu) {
		this.cpu = cpu;
	}

	@Override
	public Temperature collect () {
		Temperature temperature = CommandFactory.instance().cpuTemperature().execute().get(this.cpu.getName()); 
		temperature = (temperature == null ? NULL_TEMPERATURE : temperature);
		this.cpu.setTemperature(temperature);
		return temperature;
	}

	@Override
	public String name() {
		return "CPU Temperature";
	}
	
	@Override
	public void run() {
		this.collect();
	}
}