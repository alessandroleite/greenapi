package greenapi.core.model.data;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ProcessTime implements Data<ProcessTime> {
	
	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 2290872398916695044L;

	private final long user;
	
	private final long startTime;
	
	private final long total;
	
	private final long sys;
	

	public ProcessTime(long user, long startTime, long total, long sys) {
		super();
		this.user = user;
		this.startTime = startTime;
		this.total = total;
		this.sys = sys;
	}
	
	
	/**
	 * @return the user
	 */
	public long getUser() {
		return user;
	}


	/**
	 * @return the startTime
	 */
	public long getStartTime() {
		return startTime;
	}



	/**
	 * @return the total
	 */
	public long getTotal() {
		return total;
	}



	/**
	 * @return the sys
	 */
	public long getSys() {
		return sys;
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
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}


	@Override
	public ProcessTime value() {
		return this;
	}
}
