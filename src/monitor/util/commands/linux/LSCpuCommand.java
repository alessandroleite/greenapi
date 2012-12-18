package monitor.util.commands.linux;

import java.io.InputStream;

import org.hyperic.sigar.CpuInfo;

import monitor.util.commands.AbstractRuntimeCommand;

public class LSCpuCommand extends AbstractRuntimeCommand<CpuInfo>{

	@Override
	public CpuInfo parserOutput(InputStream input) {
		// TODO Auto-generated method stub
		return null;
	}

}
