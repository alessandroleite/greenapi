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

	public ProcessMemoryState(long resident, long pageFaults, long majorFaults,
			long share, long minorFaults, long size) {
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