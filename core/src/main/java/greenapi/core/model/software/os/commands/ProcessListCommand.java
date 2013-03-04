package greenapi.core.model.software.os.commands;

import greenapi.core.model.data.ProcessCpuState;
import greenapi.core.model.data.ProcessMemoryState;
import greenapi.core.model.data.ProcessState;
import greenapi.core.model.data.ProcessTime;
import greenapi.core.model.data.User;
import greenapi.core.model.resources.builders.impl.physical.SigarFactory;
import greenapi.core.model.resources.logic.Process;
import greenapi.core.model.resources.logic.Processes;

import java.util.List;

import org.hyperic.sigar.ProcCpu;
import org.hyperic.sigar.ProcMem;
import org.hyperic.sigar.ProcState;
import org.hyperic.sigar.ProcTime;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarProxy;

public class ProcessListCommand implements Command<Processes> {

	@Override
	public boolean isRootRequired() {
		return false;
	}

	@Override
	public Result<Processes> result() {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Result<Processes> execute(Argument... args) {
		Result<Processes> result = new Result<Processes>(new Processes());

		SigarProxy proxy = SigarFactory.getInstance();

		try {

			for (long pid : proxy.getProcList()) {
				User user = User.newUser(proxy.getProcCredName(pid).getUser());

				ProcState state = proxy.getProcState(pid);
				List<String> modules = proxy.getProcModules(pid);
				ProcCpu cpuState = proxy.getProcCpu(pid);
				ProcMem memState = proxy.getProcMem(pid);
				ProcTime procTime = proxy.getProcTime(pid);

				Process process = new Process(pid, state.getPpid(),
						state.getName(), proxy.getProcArgs(pid), user,
						modules.toArray(new String[modules.size()]));
				process.setState(new ProcessState(process, state.getThreads(),
						state.getTty(), state.getProcessor(), state
								.getPriority(), state.getNice(), proxy
								.getProcFd(pid).getTotal(),
						new ProcessCpuState(cpuState.getUser(), cpuState
								.getLastTime(), cpuState.getPercent(), cpuState
								.getStartTime(), cpuState.getTotal(), cpuState
								.getSys()), new ProcessMemoryState(memState
								.getResident(), memState.getPageFaults(),
								memState.getMajorFaults(), memState.getShare(),
								memState.getMinorFaults(), memState.getSize()),
						new ProcessTime(procTime.getUser(), procTime
								.getStartTime(), procTime.getTotal(), procTime
								.getSys())));

				result.getValue().add(process);
			}
		} catch (SigarException exception) {
			result.add(exception);
		}
		return result;
	}
}