package greenapi.core.model.resources.logic;

import greenapi.core.model.data.Data;
import greenapi.core.model.data.ProcessState;
import greenapi.core.model.data.User;
import greenapi.core.model.resources.Resource;
import greenapi.core.model.sensors.Sensor;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Process implements Resource, Comparable<Process> {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -6213100157196885498L;

	private final long id;
	
	private final long parentId;

	private final String name;

	private final String[] arguments;

	private final List<String> modules = new LinkedList<>();

	private final User user;

	private ProcessState state;

	public Process(long id, long parentId, String name, String[] arguments, User user, String[] modules, ProcessState state) {
		this(id, parentId, name, arguments, user, modules);
		this.state = state;
	}

	public Process(long id, long parentId, String name, String[] arguments, User user, String ... modules) {

		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.arguments = arguments == null ? new String[0] : arguments;
		this.user = user;

		if (modules != null) {
			this.modules.addAll(Arrays.asList(modules));
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Process other = (Process) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * @return the state
	 */
	public ProcessState getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(ProcessState state) {
		this.state = state;
	}

	/**
	 * @return the id
	 */
	public long pid() {
		return id;
	}
	
	/**
	 * @return the id
	 */
	public long parentPid() {
		return this.parentId;
	}

	/**
	 * @return the name
	 */
	public String name() {
		return name;
	}

	/**
	 * @return the arguments
	 */
	public String[] arguments() {
		return Arrays.copyOf(this.arguments, arguments.length);
	}

	/**
	 * @return the modules
	 */
	public List<String> modules() {
		return Collections.unmodifiableList(modules);
	}

	/**
	 * @return the user
	 */
	public User user() {
		return user;
	}

	@Override
	public Sensor<?, Data<?>>[] sensors() {
		return null;
	}

	@Override
	public int compareTo(final Process other) {
		return Long.valueOf(this.pid()).compareTo(other.pid());
	}
}