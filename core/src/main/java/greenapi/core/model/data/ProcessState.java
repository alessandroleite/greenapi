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