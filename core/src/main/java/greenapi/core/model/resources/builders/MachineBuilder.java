/**
 * Copyright (c) 2012 Alessandro Ferreira Leite, http://www.alessandro.cc/
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
package greenapi.core.model.resources.builders;

import greenapi.core.model.resource.io.Storage;
import greenapi.core.model.resources.CpuSocket;
import greenapi.core.model.resources.Machine;
import greenapi.core.model.resources.Memory;
import greenapi.core.model.resources.net.NetworkInterfaces;
import greenapi.core.model.sensors.Sensor;
import greenapi.core.model.software.os.OperatingSystem;

public interface MachineBuilder extends Builder<Machine> {

	MachineBuilder withId(String id);

	MachineBuilder withName(String name);

	MachineBuilder withCpus(CpuSocket... cpuSockets);

	MachineBuilder withMemory(Memory... memories);

	MachineBuilder withStorages(Storage... storages);

	MachineBuilder withOs(OperatingSystem os);

	MachineBuilder withNetInterfaces(NetworkInterfaces networkInterfaces);

	MachineBuilder withSensors(Sensor<?, ?>... sensors);
}