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
package monitorapi.model.io;

import java.math.BigInteger;

import monitorapi.core.model.Data;
import monitorapi.core.model.Resource;
import monitorapi.core.model.Sensor;

public class Storage implements Resource {
	
	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 8726051732367611687L;

	private final String id;
	
	private final String name;
	
	private final BigInteger size;
	
	private final StorageInfo info;
	
	public Storage(String id, String name, BigInteger size, StorageInfo info) {
		super();
		this.id = id;
		this.name = name;
		this.size = size;
		this.info = info;
	}
	

	@Override
	public Sensor<?, Data<?>>[] sensors() {
		return null;
	}


	/**
	 * @return the id
	 */
	public String id() {
		return id;
	}


	/**
	 * @return the name
	 */
	public String name() {
		return name;
	}


	/**
	 * @return the size
	 */
	public BigInteger size() {
		return size;
	}


	/**
	 * @return the info
	 */
	public StorageInfo info() {
		return info;
	}
}