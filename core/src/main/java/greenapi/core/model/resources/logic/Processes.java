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
package greenapi.core.model.resources.logic;

import greenapi.core.common.base.Strings;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Processes implements Serializable, Iterable<Process> {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 6373147351109904529L;
	
	private Map<Long, Process> processes = new ConcurrentHashMap<>();

	public Processes(Process... processes) {
		if (processes != null) {
			for (Process process : processes) {
				this.add(process);
			}
		}
	}

	public Processes(List<Process> processes) {
		this(processes.toArray(new Process[processes.size()]));
	}

	public Processes newProcesses(Process... processes) {
		return new Processes(processes);
	}

	public void add(Process process) {
		this.processes.put(Objects.requireNonNull(process).pid(), process);
	}

	public Collection<Process> processes() {
		return Collections.unmodifiableCollection(processes.values());
	}

	public boolean isEmpty() {
		return this.processes.isEmpty();
	}

	public int size() {
		return this.processes.size();
	}

	@Override
	public Iterator<Process> iterator() {
		return processes().iterator();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Processes [\n\t");
		
		for (Process process : this.processes.values()) {
			sb.append(process).append(Strings.NEW_LINE).append(Strings.TAB);
		}
		sb.append(Strings.RETURN).append("]\n");
		
		return sb.toString();
	}
}