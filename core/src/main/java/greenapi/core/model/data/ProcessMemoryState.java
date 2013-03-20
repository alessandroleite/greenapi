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
package greenapi.core.model.data;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ProcessMemoryState implements Data<ProcessMemoryState> {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -5534875570239912403L;

	private final long resident;

	private final long pageFaults;

	private final long majorFaults;

	private final long share;

	private final long minorFaults;

	private final long size;

	public ProcessMemoryState(long resident, long pageFaults, long majorFaults, long share, long minorFaults, long size) {
		this.resident = resident;
		this.pageFaults = pageFaults;
		this.majorFaults = majorFaults;
		this.share = share;
		this.minorFaults = minorFaults;
		this.size = size;
	}

	/**
	 * @return the resident
	 */
	public long getResident() {
		return resident;
	}

	/**
	 * @return the pageFaults
	 */
	public long getPageFaults() {
		return pageFaults;
	}

	/**
	 * @return the majorFaults
	 */
	public long getMajorFaults() {
		return majorFaults;
	}

	/**
	 * @return the share
	 */
	public long getShare() {
		return share;
	}

	/**
	 * @return the minorFaults
	 */
	public long getMinorFaults() {
		return minorFaults;
	}

	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}
	
	@Override
	public ProcessMemoryState value() {
		return this;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}
}