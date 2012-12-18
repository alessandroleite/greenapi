package monitor.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import monitor.model.sensors.IOSensor;
import monitor.model.sensors.Sensor;

public class Machine implements Resource {

	/**
	 *  Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -2707503612123610695L;

	/** The machine's name. */
	private final String name;

	/** The CPUs. */
	private final CpuSocket[] cpus;

	/** Memory system. */
	private Memory memory;

	/** I/O states of this machine*/
	private IOStats iostats;
	
	public Machine(String name, Memory memory, CpuSocket... cpuSockets) {
		this.name = name;
		this.memory = memory;
		this.cpus = cpuSockets;		
	}

	
	/**
	 * Returns the combined CPU load of this {@link Machine}.
	 * @return The combined CPU load of this {@link Machine}.
	 */
	public Double combinedCpuLoad() {
		return this.cpus[0].getCombinedLoad();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Sensor<?, Data<?>>[] sensors()  {
		List<Sensor<?, Data<?>>> sensors = new ArrayList();
		
		for(CpuSocket cpu : this.cpus()) {
			sensors.addAll(Arrays.asList(cpu.sensors()));	
		}
		
		sensors.addAll(Arrays.asList(this.memory.sensors()));
		
		sensors.addAll((Collection<? extends Sensor<?, Data<?>>>) Arrays.asList(new Sensor[] {new IOSensor(this)}));
		
		return sensors.toArray(new Sensor[sensors.size()]);
	}

	/**
	 * @return the memory
	 */
	public Memory memory() {
		return memory;
	}

	/**
	 * @param memory the memory to set
	 */
	public void updateMemory(Memory memory) {		
		this.memory = memory;
	}
	
	/**
	 * 
	 * @param iostats
	 */
	public void updateIOStats(IOStats iostats) {
		synchronized(this){
		  this.iostats = iostats;
		}		
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
	 * 
	 * @return
	 */
	public IOStats ioStats() {
		return this.iostats;
	}
}