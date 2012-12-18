package monitor.model;

import java.util.Map;

import monitor.model.sensors.MemoryUsageSensor;
import monitor.model.sensors.Sensor;

public class RamMemory extends Memory {

	/**
	 * Serial code version <code>serialVersionUID<code>
	 */
	private static final long serialVersionUID = 3476737325366322309L;
	
	public RamMemory(long size) {
		super(size);
	}
	
	public RamMemory(long size, MemoryState usage) {
		super(size, usage);
	}
	
	public RamMemory(long size, MemoryState usage, Map<Integer, Map<String, String>> properties) {
		super(size, usage, properties);
	}

	@Override
	public MemoryType getType() {
		return MemoryType.RAM;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Sensor<?, Data<?>>[] sensors() {
		return new Sensor[] {new MemoryUsageSensor(this)};
	}
}