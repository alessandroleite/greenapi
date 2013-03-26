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
package greenapi.core.model.software.os;

import java.io.Serializable;
import java.util.Map;

import static java.util.Objects.requireNonNull;

import greenapi.core.model.data.Frequency;
import greenapi.core.model.data.IOStats;
import greenapi.core.model.data.Temperature;
import greenapi.core.model.resources.logic.Processes;
import greenapi.core.model.resources.net.NetworkInterface;
import greenapi.core.model.resources.net.NetworkInterfaces;
import greenapi.core.model.software.os.commands.Command;
import greenapi.core.model.software.os.commands.CpuScalingAvailableFrequencies;
import greenapi.core.model.software.os.commands.Who;

public abstract class OperatingSystem implements Serializable, Comparable<OperatingSystem>
{

    /**
     * Serial code version <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = -63543969636463188L;

    /**
     * OS's name.
     */
    private final String name;

    /**
     * Create an instance of this class.
     * 
     * @param osName
     *            The OS's name.
     */
    public OperatingSystem(String osName)
    {
        this.name = requireNonNull(osName);
    }

    /**
     * Returns O.S's name.
     * 
     * @return The O.S's name.
     */
    public String name()
    {
        return name;
    }

    /**
     * Returns the command to get the I/O states of a machine. This method does not execute the command.
     * 
     * @return the command to get the I/O states of a machine.
     */
    public abstract Command<IOStats> iostats();

    /**
     * Returns the command to get the CPU frequencies. This method does not execute the command.
     * 
     * @return Returns the command to get the CPU frequencies.
     */
    public abstract CpuScalingAvailableFrequencies cpuAvailableFrequencies();

    /**
     * Returns the actually CPU frequency.
     * 
     * @return The actually CPU frequency.
     */
    public abstract Frequency currentCpuFrequency();

    /**
     * Returns the CPUs' temperature.
     * 
     * @return A {@link Map} with the temperature of each CPU. The key is id CPU's id.
     */
    public abstract Map<String, Temperature> cpuTemperature();

    /**
     * Returns the description of a given network interface.
     * 
     * @param id
     *            The id of the network interface.
     * @return The description of a given network interface.
     */
    public abstract NetworkInterface networkInterfaceDescription(String id);

    /**
     * Returns the available network interfaces.
     * 
     * @return The available network interfaces.
     */
    public abstract NetworkInterfaces networkInterfaces();

    /**
     * Returns the processes of this O.S.
     * 
     * @return The processes of this O.S.
     */
    public abstract Processes processes();

    /**
     * Returns the name of the active user.
     * 
     * @return the name of the active user.
     */
    public abstract Who who();

    @Override
    public int compareTo(OperatingSystem other)
    {
        return this.name.compareTo(other.name());
    }
}
