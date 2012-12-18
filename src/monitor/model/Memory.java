package monitor.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Memory implements Resource {

	/**
	 * Serial code version <code>serialVersionUID<code>
	 */
	private static final long serialVersionUID = -1250010198583649766L;

	/** The total memory size */
	private final long size;
	
	/** The memory state */
	private MemoryState state;
	
	/** The memory properties*/
	private final Map<Integer, Map<String, String>> propertiesMap = new HashMap<Integer, Map<String,String>>();

	/**
	 * Instantiate a memory with a given size.
	 * 
	 * @param size The size of the memory. Must be greater than zero.
	 */
	public Memory(long size) {
		this.size = size;
	}

	public Memory(long size, MemoryState usage) {
		this(size);
		this.state = usage;
	}
	
	public Memory(long size, MemoryState usage, Map<Integer, Map<String, String>> properties) {
		this(size);
		this.state = usage;
		this.propertiesMap.putAll(properties);
	}

	/**
	 * Return the {@link Memory} type.
	 * 
	 * @return the {@link Memory} type.
	 * @see MemoryType
	 */
	public abstract MemoryType getType();

	/**
	 * Returns the size of this {@link Memory}
	 * @return The size of this {@link Memory}
	 */
	public long size() {
		return size;
	}

	/**
	 * Update the state of this {@link Memory}.
	 * 
	 * @param newMemoryState The new state of this {@link Memory}.
	 */
	public void updateState(MemoryState newMemoryState) {
		synchronized (this) {
			this.state = newMemoryState;
		}
	}

	/**
	 * Returns the current state of this {@link Memory}.
	 * @return the current state of this {@link Memory}.
	 * @see MemoryState
	 */
	public MemoryState state() {
		return this.state;
	}
	
	
	/**
	 * @return the propertiesMap
	 */
	public Map<Integer, Map<String, String>> propertiesMap() {
		return Collections.unmodifiableMap(propertiesMap);
	}

	@Override
	public String toString() {
		return "Memory " + this.getType().name() + "[" + this.state() + "]";
	}

	public String description() {
		StringBuilder description = new StringBuilder();

		if (!this.propertiesMap.isEmpty()) {
			description.append(propertiesMap.get(1).get("Manufacturer") + " "
					+ propertiesMap.get(1).get("Fundamental Memory type") + " "
					+ propertiesMap.get(1).get("Maximum module speed") + " "
					+ propertiesMap.size() + "/"
					+ propertiesMap.get(1).get("Size"));
		}
		
		return description.toString();
	}
}