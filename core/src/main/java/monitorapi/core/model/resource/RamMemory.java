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

import java.util.Map;

import monitorapi.core.model.data.Data;
import monitorapi.core.model.data.MemoryState;
import monitorapi.core.model.sensors.Sensor;


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

	@Override
	public Sensor<?, Data<?>>[] sensors() {
		return Sensor.NULL_SENSORS;
	}
}