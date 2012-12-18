package monitor.util.commands.linux;

import static com.google.common.base.Preconditions.checkNotNull;
import static monitor.util.IOUtils.asString;

import java.io.InputStream;

import monitor.model.Cpu;
import monitor.model.Frequency;
import monitor.util.commands.AbstractRuntimeCommand;

public class CurrentCpuFrequencyCommand extends AbstractRuntimeCommand<Frequency> {

	public CurrentCpuFrequencyCommand(Cpu cpu) {
		this(checkNotNull(cpu.id()));
	}

	public CurrentCpuFrequencyCommand(int cpuId) {
		super("bash", "-c", String.format(
				"cat /sys/devices/system/cpu/cpu%s/cpufreq/cpuinfo_cur_freq",
				cpuId));
	}

	@Override
	public Frequency parserOutput(InputStream input) {
		return new Frequency(Long.parseLong(asString(input).trim()));
	}
}