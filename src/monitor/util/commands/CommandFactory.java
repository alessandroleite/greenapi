package monitor.util.commands;

import static monitor.util.ClassUtils.newInstanceForName;
import static monitor.util.SystemUtil.osName;

import java.util.Map;

import monitor.model.Cpu;
import monitor.model.Frequency;
import monitor.model.IOStats;
import monitor.model.Machine;
import monitor.model.OnlineState;
import monitor.model.Temperature;
import monitor.util.commands.linux.LinuxCommandFactory;

public abstract class CommandFactory {

	public abstract Command<Map<String, Temperature>> cpuTemperature();

	public abstract Command<Frequency[]> cpuScalingAvailableFrequencies(Cpu cpu);
	
	public abstract Command<Frequency[]> cpuScalingAvailableFrequencies(int cpuId);

	public abstract Command<Frequency> currentCpuFrequency(Cpu cpu);
	
	public abstract Command<Frequency> currentCpuFrequency(int cpuId);
	
	public abstract Command<Boolean> setCpuFrequency(Frequency frequency);
	
	public abstract Command<Boolean> disableCpu(Cpu cpu);
	
	public abstract Command<Boolean> enableCpu(Cpu cpu);
	
	public abstract Command<OnlineState> getCpuOnlineState(Cpu cpu);
	
	public abstract Command<Map<Integer, Map<String, String>>> decodeDimms();
	
	public abstract Command<IOStats> iostat(Machine machine);

	private static CommandFactory instance;

	public final static CommandFactory instance() {
		synchronized (CommandFactory.class) {
			if (instance == null) {
				String os_name = osName().replaceAll(" ", "").trim();
				instance = (CommandFactory) newInstanceForName(String.format(
						"monitor.util.commands.%s.%sCommandFactory",
						os_name.toLowerCase(), os_name));
				return new LinuxCommandFactory();
			}
			return instance;
		}
	}
}