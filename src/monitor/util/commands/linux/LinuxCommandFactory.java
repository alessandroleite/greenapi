package monitor.util.commands.linux;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import monitor.model.Cpu;
import monitor.model.Frequency;
import monitor.model.IOStats;
import monitor.model.OnlineState;
import monitor.model.Temperature;
import monitor.util.commands.Command;
import monitor.util.commands.CommandFactory;

public class LinuxCommandFactory extends CommandFactory {

	@Override
	public Command<Map<String, Temperature>> cpuTemperature() {
		return new CpuTemperatureCommand();
	}

	@Override
	public Command<Frequency[]> cpuScalingAvailableFrequencies(Cpu cpu) {
		return new CpuScalingAvailableFrequenciesCommand(checkNotNull(cpu));
	}

	@Override
	public Command<Frequency[]> cpuScalingAvailableFrequencies(int cpuId) {
		return new CpuScalingAvailableFrequenciesCommand(cpuId);
	}

	@Override
	public Command<Frequency> currentCpuFrequency(Cpu cpu) {
		return new CurrentCpuFrequencyCommand(cpu);
	}

	public Command<Frequency> currentCpuFrequency(int cpuId) {
		return new CurrentCpuFrequencyCommand(cpuId);
	}

	@Override
	public Command<Boolean> setCpuFrequency(Frequency frequency) {
		return new UpdateCpuFrequencyCommand(frequency);
	}

	@Override
	public Command<Map<Integer, Map<String, String>>> decodeDimms() {
		return new DecodeDimmsCommand();
	}

	@Override
	public Command<Boolean> disableCpu(Cpu cpu) {
		return new EnableOrDisableCpuCommand(cpu, OnlineState.DISABLE);
	}

	@Override
	public Command<Boolean> enableCpu(Cpu cpu) {
		return new EnableOrDisableCpuCommand(cpu, OnlineState.ENABLE);
	}

	@Override
	public Command<OnlineState> getCpuOnlineState(Cpu cpu) {		
		return new QueryCpuOnlineStateCommand(cpu);
	}

	@Override
	public Command<IOStats> iostat() {
		return new IOstatCommand();
	}
}