package monitor.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import monitor.model.sensors.Sensor;

public final class Cores implements Iterable<Cpu> {

	private final Map<String, Cpu> cpus = new ConcurrentHashMap<String, Cpu>();

	private final CpuSocket cpuSocket;

	public Cores(CpuSocket cpuSocket) {
		this.cpuSocket = cpuSocket;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<Cpu> iterator() {
		return this.get().iterator();
	}

	/**
	 * Add a given {@link Cpu} to this {@link Cores} {@link List}.
	 * 
	 * @param cpu
	 *            The {@link Cpu} instance to be added. May not be
	 *            <code>null</code>.
	 * @return The old instance of the given {@link Cpu} or <code>null</code>.
	 */
	public Cpu add(Cpu cpu) {
		return this.cpus.put(cpu.getName(), cpu);
	}

	/**
	 * Return the sensors available in the cores.
	 * 
	 * @return the sensors available in each core.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Sensor<?, Data<?>>[] sensors() {
		if (cpus.isEmpty())
			return Sensor.NULL_SENSORS;
		
		List< Sensor<?, Data<?>>> sensors = new ArrayList();
		
		for(Cpu cpu : this.cpus.values()){
			sensors.addAll(Arrays.asList(cpu.sensors()));
		}
		
		return sensors.toArray(new Sensor[sensors.size()]);
	}

	/**
	 * Return an unmodifiable {@link Collection} with the current view of this
	 * {@link List}.
	 * 
	 * @return Return an unmodifiable {@link Collection} with the current view
	 *         of this {@link List}.
	 * @see Collections#unmodifiableCollection(Collection)
	 */
	public Collection<Cpu> get() {
		List<Cpu> list = new ArrayList<Cpu>(this.cpus.values());
		Collections.sort(list);
		return Collections.unmodifiableCollection(list);
	}

	/**
	 * Return the {@link Cpu} instance that has the given name.
	 * 
	 * @param name
	 *            The name of {@link Cpu} to be returned.
	 * @return The {@link Cpu} instance that has the given name or
	 *         <code>null</code> if it doesn't exist.
	 */
	public Cpu get(String name) {
		return this.cpus.get(name);
	}

	/**
	 * @return the cpuSocket
	 */
	public CpuSocket getCpuSocket() {
		return cpuSocket;
	}

	/**
	 * Remove a given {@link Cpu}
	 * 
	 * @param cpu
	 *            {@link Cpu} instance to be removed.
	 * @return The {@link Cpu} removed.
	 */
	public Cpu remove(Cpu cpu) {
		return this.cpus.remove(cpu.getName());
	}

	/**
	 * Return the number of cores.
	 * 
	 * @return Return the number of cores.
	 */
	public int size() {
		return this.cpus.size();
	}
}