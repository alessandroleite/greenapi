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
package greenapi.core.model.resource.io;

import java.io.Serializable;

public final class StorageInfo implements Serializable {

	/**
	 * Serial code version code <code>serialVersionUID</code> 
	 */
	private static final long serialVersionUID = 2494983296526446200L;
	
	private final Integer heads;
	private final Integer sectors;
	private final Integer cylinders;
	private final Integer totalOfSectors;

	public StorageInfo(Integer heads, Integer sectors, Integer cylinders, Integer totalOfSectors) {
		
		this.heads = heads;
		this.sectors = sectors;
		this.cylinders = cylinders;
		this.totalOfSectors = totalOfSectors;
	}

	/**
	 * @return the heads
	 */
	public Integer heads() {
		return heads;
	}

	/**
	 * @return the sectors
	 */
	public Integer sectors() {
		return sectors;
	}

	/**
	 * @return the cylinders
	 */
	public Integer cylinders() {
		return cylinders;
	}

	/**
	 * @return the totalSectors
	 */
	public Integer totalOfSectors() {
		return totalOfSectors;
	}
}