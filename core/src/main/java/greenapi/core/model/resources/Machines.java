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

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Machines implements Iterable<Machine>
{

    /**
     * The {@link Map} with the machines. The key if the machine's id and it's never <code>null</code>.
     */
    private final Map<String, Machine> machines = new ConcurrentHashMap<>();

    /**
     * Creates a {@link Machines}' instance.
     */
    public Machines()
    {
    }

    /**
     * Add a new machine to this list.
     * 
     * @param machine The machine to be added. Might not be <code>null</code>.
     */
    public void add(Machine machine)
    {
        this.machines.put(machine.getId(), machine);
    }

    @Override
    public Iterator<Machine> iterator()
    {
        return machines().iterator();
    }

    /**
     * Returns a read-only {@link Collection} with the entries of this {@link java.util.List}.
     * 
     * @return A read-only {@link Collection} with the entries of this {@link java.util.List}.
     */
    public Collection<Machine> machines()
    {
        return Collections.unmodifiableCollection(machines.values());
    }

}
