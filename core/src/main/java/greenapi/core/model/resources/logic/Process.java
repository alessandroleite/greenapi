/**
 * Copyright (c) 2012 GreenI2R
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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import greenapi.core.model.data.Data;
import greenapi.core.model.data.ProcessState;
import greenapi.core.model.data.User;
import greenapi.core.model.resources.Resource;
import greenapi.core.model.sensors.Sensor;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Process implements Resource, Comparable<Process>
{

    /**
     * Serial code version <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = -6213100157196885498L;

    /**
     * The process's id.
     */
    private final long id;

    /**
     * The process's parent.
     */
    private final Process parent;

    /**
     * The process's name.
     */
    private final String name;

    /**
     * The process's arguments.
     */
    private final String[] arguments;

    /**
     * The process's modules.
     */
    private final List<String> modules = new LinkedList<>();

    /**
     * The owner of the process.
     */
    private final User user;

    /**
     * The children of the process.
     */
    private final Map<Long, Process> children = new HashMap<>();

    /**
     * The state of the process.
     */
    private ProcessState state;

    /**
     * Creates a new {@link Process}'s instance.
     * 
     * @param pid
     *            The id of this {@link Process}.
     * @param ppid
     *            The parent of the process, if there is one.
     * @param processName
     *            The name of the process.
     * @param args
     *            The arguments of the process.
     * @param owner
     *            The owner of the process.
     * @param processModules
     *            The modules of the process.
     * @param processState
     *            The process state.
     */
    public Process(long pid, Process ppid, String processName, String[] args, User owner, String[] processModules, ProcessState processState)
    {
        this(pid, ppid, processName, args, owner, processModules);
        this.state = processState;
    }

    /**
     * 
     * @param pid
     *            The id of this {@link Process}.
     * @param ppid
     *            The parent of the process, if there is one.
     * @param processName
     *            The name of the process.
     * @param args
     *            The arguments of the process.
     * @param owner
     *            The owner of the process.
     * @param processModules
     *            The modules of the process.
     */
    public Process(long pid, Process ppid, String processName, String[] args, User owner, String... processModules)
    {

        this.id = pid;
        this.parent = ppid;
        this.name = processName;
        this.arguments = args == null ? new String[0] : args;
        this.user = owner;

        if (modules != null)
        {
            this.modules.addAll(Arrays.asList(processModules));
        }

        if (parent != null)
        {
            this.parent.children.put(id, this);
        }
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        Process other = (Process) obj;
        if (id != other.id)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    /**
     * @return the state
     */
    public ProcessState getState()
    {
        return state;
    }

    /**
     * @param newProcessState
     *            the state to set
     */
    public void setState(ProcessState newProcessState)
    {
        this.state = newProcessState;
    }

    /**
     * @return the pid
     */
    public long pid()
    {
        return id;
    }

    /**
     * @return the parent
     */
    public Process parent()
    {
        return this.parent;
    }

    /**
     * @return the name
     */
    public String name()
    {
        return name;
    }

    /**
     * @return the arguments
     */
    public String[] arguments()
    {
        return Arrays.copyOf(this.arguments, arguments.length);
    }

    /**
     * @return the modules
     */
    public List<String> modules()
    {
        return Collections.unmodifiableList(modules);
    }

    /**
     * @return the user
     */
    public User user()
    {
        return user;
    }

    /**
     * Returns the children of this process.
     * 
     * @return The children of this process.
     */
    public Map<Long, Process> children()
    {
        return Collections.unmodifiableMap(children);
    }

    @Override
    public Sensor<?, Data<?>>[] sensors()
    {
        return null;
    }

    @Override
    public int compareTo(final Process other)
    {
        return Long.valueOf(this.pid()).compareTo(other.pid());
    }
}
