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
package greenapi.core.model.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import greenapi.core.model.data.Data;
import greenapi.core.model.sensors.Sensor;

import static com.google.common.base.Preconditions.checkNotNull;

public final class Cores implements Iterable<Cpu>
{
    /**
     * {@link Map} with the cpus that represents the cores where the key is the id of the core/cpu.
     */
    private final Map<String, Cpu> cpus = new ConcurrentHashMap<String, Cpu>();

    /**
     * The socket of the cpus.
     */
    private final CpuSocket cpuSocket;

    /**
     * The sensors of the {@link Cores}.
     */
    private final List<Sensor<?, Data<?>>> sensors;

    /**
     * Create an instance of this class with a given {@link CpuSocket}.
     * 
     * @param socket
     *            The socket of the cores. May not be <code>null</code>.
     */
    public Cores(CpuSocket socket)
    {
        this(socket, new ArrayList<Sensor<?, Data<?>>>());
    }

    /**
     * 
     * @param socket
     *            The socket of the cores. May not be <code>null</code>.
     * @param coreSensors
     *            The sensors of the {@link Cores}.
     */
    public Cores(CpuSocket socket, List<Sensor<?, Data<?>>> coreSensors)
    {
        this.cpuSocket = socket;
        this.sensors = coreSensors;
    }

    /**
     * Add a new sensor to {@link Cores}.
     * 
     * @param sensor
     *            The sensor to be add. May not be <code>null</code>.
     */
    public void add(Sensor<?, Data<?>> sensor)
    {
        this.add(Collections.<Sensor<?, Data<?>>> singletonList(sensor));
    }

    /**
     * Add the sensors of the cores.
     * 
     * @param sensors2Add
     *            The cores to be added.
     */
    public void add(final List<Sensor<?, Data<?>>> sensors2Add)
    {
        for (Sensor<?, Data<?>> sensor : checkNotNull(sensors2Add))
        {
            if (!this.sensors.contains(checkNotNull(sensor)))
            {
                this.sensors.add(sensor);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<Cpu> iterator()
    {
        return this.get().iterator();
    }

    /**
     * Add a given {@link Cpu} to this {@link Cores} {@link List}.
     * 
     * @param cpu
     *            The {@link Cpu} instance to be added. May not be <code>null</code>.
     * @return The old instance of the given {@link Cpu} or <code>null</code>.
     */
    public Cpu add(Cpu cpu)
    {
        return this.cpus.put(cpu.name(), cpu);
    }

    /**
     * Return the sensors available in the cores.
     * 
     * @return the sensors available in each core.
     */
    @SuppressWarnings({ "unchecked" })
    public Sensor<?, Data<?>>[] sensors()
    {
        return sensors.toArray(new Sensor[sensors.size()]);
    }

    /**
     * Return an unmodifiable {@link Collection} with the current view of this {@link List}.
     * 
     * @return Return an unmodifiable {@link Collection} with the current view of this {@link List}.
     * @see Collections#unmodifiableCollection(Collection)
     */
    public Collection<Cpu> get()
    {
        List<Cpu> list = new ArrayList<Cpu>(this.cpus.values());
        Collections.sort(list);
        return Collections.unmodifiableCollection(list);
    }

    /**
     * Return the {@link Cpu} instance that has the given name.
     * 
     * @param name
     *            The name of {@link Cpu} to be returned.
     * @return The {@link Cpu} instance that has the given name or <code>null</code> if it doesn't exist.
     */
    public Cpu get(String name)
    {
        return this.cpus.get(name);
    }

    /**
     * @return the cpuSocket
     */
    public CpuSocket getCpuSocket()
    {
        return cpuSocket;
    }

    /**
     * Remove a given {@link Cpu}.
     * 
     * @param cpu
     *            A {@link Cpu} instance to be removed.
     * @return The {@link Cpu} removed.
     */
    public Cpu remove(Cpu cpu)
    {
        return this.cpus.remove(cpu.name());
    }

    /**
     * Return the number of cores.
     * 
     * @return Return the number of cores.
     */
    public int size()
    {
        return this.cpus.size();
    }
}
