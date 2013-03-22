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

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import greenapi.core.common.base.Strings;

public class Processes implements Serializable, Iterable<Process>
{

    /**
     * Serial code version <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = -4256946089168155269L;
   
    /**
     * The {@link Map} with the processes. The key if the process's id and it's never <code>null</code>.
     */
    private final Map<Long, Process> processesMap = new ConcurrentHashMap<>();

    /**
     * Create an instance of this class with the given processes.
     * 
     * @param processes
     *            The processes to be added.
     */
    public Processes(Process... processes)
    {
        if (processes != null)
        {
            for (Process process : processes)
            {
                this.add(process);
            }
        }
    }

    /**
     * Create an instance of this class with a given processes.
     * 
     * @param processes
     *            The available processes.
     */
    public Processes(List<Process> processes)
    {
        this(processes.toArray(new Process[processes.size()]));
    }

    /**
     * Factory method to create an instance of this class with a given processes.
     * 
     * @param processes
     *            The available processes.
     * @return An instance of this class with a given processes.
     */
    public Processes newProcesses(Process... processes)
    {
        return new Processes(processes);
    }

    /**
     * Add a given {@link Process}.
     * 
     * @param process
     *            The {@link Process} to be added. Might not be <code>null</code>.
     */
    public void add(Process process)
    {
        this.processesMap.put(Objects.requireNonNull(process).pid(), process);
    }

    /**
     * Returns a read-only {@link Collection} with the {@link Process}es.
     * 
     * @return A read-only {@link Collection} with the {@link Process}es
     */
    public Collection<Process> processes()
    {
        return Collections.unmodifiableCollection(processesMap.values());
    }

    /**
     * Returns <code>true</code> if there is no {@link Process}.
     * 
     * @return <code>true</code> if there is no {@link Process}
     */
    public boolean isEmpty()
    {
        return this.processesMap.isEmpty();
    }

    /**
     * Return the number of processes available.
     * 
     * @return the number of processes available.
     */
    public int size()
    {
        return this.processesMap.size();
    }

    @Override
    public Iterator<Process> iterator()
    {
        return processes().iterator();
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("Processes [\n\t");

        for (Process process : this.processesMap.values())
        {
            sb.append(process).append(Strings.NEW_LINE).append(Strings.TAB);
        }
        sb.append(Strings.RETURN).append("]\n");

        return sb.toString();
    }
}
