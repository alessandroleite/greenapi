package monitor.util.commands.linux;

import java.io.InputStream;

import monitor.model.Cpu;
import monitor.model.OnlineState;
import monitor.util.IOUtils;
import monitor.util.commands.AbstractRuntimeCommand;

public class EnableOrDisableCpuCommand extends AbstractRuntimeCommand<Boolean> {

	public EnableOrDisableCpuCommand(Cpu cpu, OnlineState state) {
		super("bash", "sh", "-c", "echo", String.format(
				"%s > /sys/devices/system/cpu%s/online", state.getValue(),
				cpu.id()));
	}

	@Override
	public Boolean parserOutput(InputStream input) {
		return IOUtils.asString(input).isEmpty();
	}
}