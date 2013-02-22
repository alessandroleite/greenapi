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
package monitorapi.core.model.resource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import monitorapi.core.model.data.MemoryState;

public abstract class Memory implements Resource {

	/**
	 * Serial code version <code>serialVersionUID<code>
	 */
	private static final long serialVersionUID = -1250010198583649766L;

	/** The total memory size */
	private final long size;

	/** The memory state */
	private MemoryState state;

	/** The memory properties */
	private final Map<Integer, Map<String, String>> propertiesMap = new HashMap<Integer, Map<String, String>>();

	/**
	 * Instantiate a memory with a given size.
	 * 
	 * @param size
	 *            The size of the memory. Must be greater than zero.
	 */
	public Memory(long size) {
		this.size = size;
	}

	public Memory(long size, MemoryState usage) {
		this(size);
		this.state = usage;
	}

	public Memory(long size, MemoryState usage,
			Map<Integer, Map<String, String>> properties) {
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
	 * 
	 * @return The size of this {@link Memory}
	 */
	public long size() {
		return size;
	}

	/**
	 * Update the state of this {@link Memory}.
	 * 
	 * @param newMemoryState
	 *            The new state of this {@link Memory}.
	 */
	public MemoryState setState(MemoryState newMemoryState) {
		synchronized (this) {
			final MemoryState previousMemoryState = this.state;
			this.state = newMemoryState;
			
			return previousMemoryState;
		}
	}

	/**
	 * Returns the current state of this {@link Memory}.
	 * 
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