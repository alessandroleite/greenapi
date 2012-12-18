package monitor.util.commands.linux;

import static monitor.util.IOUtils.asString;

import java.io.InputStream;
import java.util.StringTokenizer;

import monitor.model.Cpu;
import monitor.model.Frequency;
import monitor.util.StringTokenizer2;
import monitor.util.commands.AbstractRuntimeCommand;

public class CpuScalingAvailableFrequenciesCommand extends
		AbstractRuntimeCommand<Frequency[]> {

	private static final String INSTRUCTION_LINE = "cat /sys/devices/system/cpu/cpu%S/cpufreq/scaling_available_frequencies";

	public CpuScalingAvailableFrequenciesCommand(Cpu cpu) {
		this(cpu.id());
	}

	public CpuScalingAvailableFrequenciesCommand(int cpuId) {
		super("bash", "-c", String.format(INSTRUCTION_LINE, cpuId));
	}

	@Override
	public Frequency[] parserOutput(InputStream input) {

		StringTokenizer tokens = new StringTokenizer2(asString(input).trim(), " ");
		Frequency[] frequencies = new Frequency[tokens.countTokens()];

		int i = 0;
		while (tokens.hasMoreElements()) {
			frequencies[i++] = new Frequency(Long.parseLong(tokens.nextToken()));
		}
		
		return frequencies;
	}
}
