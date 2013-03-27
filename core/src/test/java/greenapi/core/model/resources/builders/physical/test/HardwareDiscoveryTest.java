/**
 * Copyright (c) 2012 GreenI2R
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package greenapi.core.model.resources.builders.physical.test;

import greenapi.core.model.resources.Machine;
import greenapi.core.model.resources.builders.impl.physical.HardwareBuilder;
import greenapi.core.model.software.os.OperatingSystemFactory;

import java.util.Arrays;

import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarProxy;

public class HardwareDiscoveryTest
{

    public static void main(String[] args) throws SigarException
    {

        Machine machine = new HardwareBuilder().withOS(OperatingSystemFactory.getSystemOS()).build();

        System.out.println(machine.getOs().processes());
        // System.out.println(machine);

        SigarProxy proxy = new HardwareBuilder().hypervisor();
        System.out.println(proxy.getNetInterfaceConfig());

        long[] procList = proxy.getProcList();
        for (int i = 0; i < procList.length; i++)
        {
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

        // System.out.println("ProcStat " + proxy.getProcStat());

        // for(String net : proxy.getNetInterfaceList()) {
        // System.out.println(net);
        // }

        // NetInterfaceConfig primaryInterface = proxy.getNetInterfaceConfig();
        //
        // NetInterfaceStat netInterfaceStat = proxy.getNetInterfaceStat(primaryInterface.getName());

        // System.out.println(netInterfaceStat);
        //
        // System.out.println(primaryInterface);
        //
        // System.out.println(primaryInterface.getType());
        //
        // System.out.println(proxy.getNetInfo());
    }
}
