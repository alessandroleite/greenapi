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
package greenapi.core.model.resources;

import static java.util.Objects.requireNonNull;

import greenapi.core.model.data.Data;
import greenapi.core.model.resource.io.Storages;
import greenapi.core.model.resources.net.NetworkInterface;
import greenapi.core.model.sensors.Sensor;
import greenapi.core.model.software.os.OperatingSystem;

import java.util.Collection;


import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

public class Machine implements Resource {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -2707503612123610695L;

	/**
	 * MAC address of the primary interface.
	 */
	private final String id;

	/** The machine's name. */
	private final String name;

	/** The CPUs. */
	private final CpuSocket[] cpus;

	/** Memory system. */
	private Memory[] memories;

	/**
	 * Network interfaces.
	 */
	private final NetworkInterface[] networkInterfaces;

	/**
	 * The disks of that {@link Machine}.
	 */
	private final Storages storages;

	/**
	 * The operation system in use.
	 */
	private final OperatingSystem os;

	public Machine(String id, String name, Memory[] memories,
			Storages storages, NetworkInterface[] networkInterfaces,
			CpuSocket[] cpuSockets, OperatingSystem os) {

		this.id = requireNonNull(id);
		this.name = requireNonNull(name);
		this.memories = requireNonNull(memories);
		this.storages = requireNonNull(storages);
		this.networkInterfaces = networkInterfaces;
		this.cpus = requireNonNull(cpuSockets);
		this.os = requireNonNull(os);
	}

	/**
	 * Returns the combined CPU load of this {@link Machine}.
	 * 
	 * @return The combined CPU load of this {@link Machine}.
	 */
	public Double combinedCpuLoad() {
		return this.cpus[0].getCombinedLoad();
	}

	@Override
	public Sensor<?, Data<?>>[] sensors() {
		/*
		 * List<Sensor<?, Data<?>>> sensors = new ArrayList<Sensor<?,
		 * Data<?>>>();
		 * 
		 * for(CpuSocket cpu : this.cpus()) {
		 * sensors.addAll(Arrays.asList(cpu.sensors())); }
		 * 
		 * sensors.addAll(Arrays.asList(this.memory.sensors()));
		 * sensors.addAll((Collection<? extends Sensor<?, Data<?>>>)
		 * Arrays.asList(new Sensor[] {new IOSensor(this)}));
		 * 
		 * return sensors.toArray(new Sensor[sensors.size()]);
		 */
		// TODO
		return null;
	}

	/**
	 * @return the memory
	 */
	public Memory[] memories() {
		return memories;
	}

	/**
	 * Returns the reference to RAM memory system.
	 * 
	 * @return Returns the reference to RAM memory system.
	 */
	public Memory ram() {
		return filterMemoryBy(MemoryType.RAM)[0];
	}

	/**
	 * @return
	 */
	public Memory swap() {
		return filterMemoryBy(MemoryType.SWAP)[0];
	}
	

	/**
	 * @return the name
	 */
	public String name() {
		return name;
	}

	/**
	 * @return the cpus
	 */
	public CpuSocket[] cpus() {
		return cpus;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the networkInterfaces
	 */
	public NetworkInterface[] getNetworkInterfaces() {
		return networkInterfaces;
	}

	/**
	 * @return the storages
	 */
	public Storages getStorages() {
		return storages;
	}

	/**
	 * @return the os
	 */
	public OperatingSystem getOs() {
		return os;
	}

	private Memory[] filterMemoryBy(final MemoryType type) {
		Collection<Memory> filtered = Collections2.filter(
				Lists.newArrayList(this.memories), new Predicate<Memory>() {
					@Override
					public boolean apply(Memory input) {
						return type.equals(input.getType());
					}
				});

		return filtered.toArray(new Memory[filtered.size()]);
	}
}