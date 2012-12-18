package monitor.util.commands.linux;

import java.io.InputStream;

import monitor.model.Cpu;
import monitor.model.OnlineState;
import monitor.util.IOUtils;
import monitor.util.commands.AbstractRuntimeCommand;

public class QueryCpuOnlineStateCommand extends
		AbstractRuntimeCommand<OnlineState> {

	public QueryCpuOnlineStateCommand(Cpu cpu) {
		super("bash", "-c", String.format(
				"cat /sys/devices/system/cpu/cpu%s/online", cpu.id()));
	}

	@Override
	public OnlineState parserOutput(InputStream input) {
		return OnlineState.valueOf(Integer.parseInt(IOUtils.asString(input)));
	}
}
