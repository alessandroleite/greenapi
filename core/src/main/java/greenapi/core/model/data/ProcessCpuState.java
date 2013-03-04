package greenapi.core.model.data;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ProcessCpuState implements Data<ProcessCpuState> {

	/**
	 * Serial code version <code>serialVersionUID<code>
	 */
	private static final long serialVersionUID = 8682876456867387399L;

	private final long user;
	private final long lastTime;
	private final double percent;
	private final long startTime;
	private final long total;
	private final long sys;

	public ProcessCpuState(long user, long lastTime, double percent,
			long startTime, long total, long sys) {
		this.user = user;
		this.lastTime = lastTime;
		this.percent = percent;
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
	 * @return the lastTime
	 */
	public long getLastTime() {
		return lastTime;
	}

	/**
	 * @return the percent
	 */
	public double getPercent() {
		return percent;
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
	public ProcessCpuState value() {
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}