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
package greenapi.core.model.resources.net;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class NetworkInterfaces implements Serializable, Iterable<NetworkInterface>
{

    /**
     * Serial code version <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = 5912953555860802136L;

    /**
     * The {@link Map} with the network interface. The key if the interface's name.
     */
    private Map<String, NetworkInterface> interfaces = new ConcurrentHashMap<>();

    /**
     * The reference to the primary interface. This reference is just to avoid the search in the map every time.
     */
    private volatile NetworkInterface primary;

    /**
     * Creates a {@link NetworkInterface}'s instance.
     */
    public NetworkInterfaces()
    {
        super();
    }

    /**
     * Creates a {@link NetworkInterface}'s instance with the given interfaces.
     * 
     * @param netInterfaces
     *            The network interfaces.
     */
    public NetworkInterfaces(NetworkInterface... netInterfaces)
    {
        if (netInterfaces != null)
        {
            for (NetworkInterface ni : netInterfaces)
            {
                this.add(ni);
            }
        }
    }

    /**
     * Factory method to creates a {@link NetworkInterface}.
     * 
     * @param interfaces
     *            The network interfaces.
     * @return An instance of this class.
     */
    public static NetworkInterfaces valueOf(NetworkInterface... interfaces)
    {

        return new NetworkInterfaces(interfaces);
    }

    /**
     * Add a not <code>null</code> {@link NetworkInterface}.
     * 
     * @param ni
     *            The {@link NetworkInterface} to be added.
     * @return Return <code>null</code> if there was no {@link NetworkInterface} equals to the given {@link NetworkInterface}.
     */
    public NetworkInterface add(NetworkInterface ni)
    {
        if (requireNonNull(ni).isPrimary())
        {
            this.primary = ni;
        }
        return this.interfaces.put(ni.id(), ni);
    }

    /**
     * Removes a given {@link NetworkInterface}.
     * 
     * @param ni
     *            The {@link NetworkInterface} to be removed.
     * @return the {@link NetworkInterface} removed.
     */
    public NetworkInterface remove(NetworkInterface ni)
    {
        return this.remove(requireNonNull(ni).id());
    }

    /**
     * Removes a {@link NetworkInterface} that has a given id.
     * 
     * @param id
     *            The id of the {@link NetworkInterface} that must be removed.
     * @return the {@link NetworkInterface} removed.
     */
    public NetworkInterface remove(String id)
    {
        NetworkInterface removed = this.interfaces.remove(id);

        if (primary != null && primary.equals(removed))
        {
            Map<String, NetworkInterface> primaries = Maps.filterValues(this.interfaces, new Predicate<NetworkInterface>()
            {
                @Override
                public boolean apply(NetworkInterface input)
                {
                    return input.isPrimary() || input.isActive();
                }
            });
            this.primary = (primaries.isEmpty()) ? null : primaries.values().iterator().next();
        }
        return removed;
    }

    /**
     * Returns an unmodifiable {@link Collection} with the network interfaces of an machine.
     * 
     * @return An read-only {@link Collection} with the network interfaces of an machine
     */
    public Collection<NetworkInterface> interfaces()
    {
        return Collections.unmodifiableCollection(this.interfaces.values());
    }

    /**
     * @return the primary
     */
    public NetworkInterface primary()
    {
        return primary;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<NetworkInterface> iterator()
    {
        return this.interfaces().iterator();
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
