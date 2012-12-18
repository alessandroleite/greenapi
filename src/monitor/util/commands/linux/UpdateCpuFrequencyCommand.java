package monitor.util.commands.linux;

import static monitor.util.IOUtils.asString;

import java.io.InputStream;

import monitor.model.Frequency;
import monitor.util.commands.AbstractRuntimeCommand;

public class UpdateCpuFrequencyCommand extends AbstractRuntimeCommand<Boolean> {

	public UpdateCpuFrequencyCommand(Frequency frequency) {
		super("bash", "-c", "cpupower frequency-set -f " + frequency.value());
	}

	@Override
	public Boolean parserOutput(InputStream input) {
		return asString(input).indexOf("Setting cpu") > -1;
	}
}