package greenapi.core.model.data;

import greenapi.core.model.resources.logic.Process;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ProcessState implements Data<ProcessState> {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -6668201327845284177L;

	private final Process process;

	private final long threads;

	private final int tty;

	private final int processor;

	private final int priority;

	private final int nice;

	private final long fd;

	private final ProcessCpuState cpuState;

	private final ProcessMemoryState memoryState;

	private final ProcessTime processTime;

	public ProcessState(Process process, long threads, int tty, int processor,
			int priority, int nice, long fd, ProcessCpuState cpuState,
			ProcessMemoryState memoryState, ProcessTime processTime) {

		this.process = process;
		this.threads = threads;
		this.tty = tty;
		this.processor = processor;
		this.priority = priority;
		this.nice = nice;
		this.fd = fd;
		this.cpuState = cpuState;
		this.memoryState = memoryState;
		this.processTime = processTime;
	}


	/**
	 * @return the process
	 */
	public Process getProcess() {
		return process;
	}

	/**
	 * @return the threads
	 */
	public long getThreads() {
		return threads;
	}

	/**
	 * @return the tty
	 */
	public int getTty() {
		return tty;
	}

	/**
	 * @return the processor
	 */
	public int getProcessor() {
		return processor;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @return the nice
	 */
	public int getNice() {
		return nice;
	}

	/**
	 * @return the fd
	 */
	public long getFd() {
		return fd;
	}

	/**
	 * @return the cpuState
	 */
	public ProcessCpuState getCpuState() {
		return cpuState;
	}

	/**
	 * @return the memoryState
	 */
	public ProcessMemoryState getMemoryState() {
		return memoryState;
	}

	/**
	 * @return the processTime
	 */
	public ProcessTime getProcessTime() {
		return processTime;
	}

	@Override
	public ProcessState value() {
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