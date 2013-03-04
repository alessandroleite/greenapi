package greenapi.core.model.resources.builders.physical.test;

import java.util.Arrays;

import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarProxy;

import greenapi.core.model.resources.Machine;
import greenapi.core.model.resources.builders.impl.physical.HardwareBuilder;
import greenapi.core.model.software.os.OperatingSystemFactory;

public class HardwareDiscoveryTest {

	public static void main(String[] args) throws SigarException {
		Machine machine = new HardwareBuilder().withOS(
				OperatingSystemFactory.getSystemOS()).build();
		
		System.out.println(machine.getOs().processes());
		
		//System.out.println(machine);
		
		SigarProxy proxy = new HardwareBuilder().hypervisor();
		System.out.println(proxy.getNetInterfaceConfig());
		
		long[] procList = proxy.getProcList();
		for (int i = 0; i < procList.length; i++) {
			System.out.println(procList[i]);	
		}
		
		long processId = 27378;
		
		System.out.println("ProcCpu " + proxy.getProcCpu(processId));
		System.out.println("ProcState " + proxy.getProcState(processId));
		
		System.out.println("ProcMem " + proxy.getProcMem(processId));
		
		System.out.println("ProcExe " + proxy.getProcExe(processId));
		System.out.println("ProcArgs " + Arrays.toString(proxy.getProcArgs(processId)));
		System.err.println("ProcEnv " + proxy.getProcEnv(processId));
		
		System.out.println("ProcModules " + proxy.getProcModules(processId));
		System.out.println("ProcFd " + proxy.getProcFd(processId));
		
		System.out.println("ProcCred " + proxy.getProcCred(processId));
		System.out.println("ProcCredName " + proxy.getProcCredName(processId));
		

		System.out.println("ProcTime " + proxy.getProcTime(processId));
		
//		System.out.println("ProcStat " + proxy.getProcStat());
		
		
//		for(String net : proxy.getNetInterfaceList()) {
//			System.out.println(net);
//		}
		
//		NetInterfaceConfig primaryInterface = proxy.getNetInterfaceConfig();
//		
//		NetInterfaceStat netInterfaceStat = proxy.getNetInterfaceStat(primaryInterface.getName());
		
//		System.out.println(netInterfaceStat);
//		
//		System.out.println(primaryInterface);
//		
//		System.out.println(primaryInterface.getType());
//		
//		System.out.println(proxy.getNetInfo());
	}
}