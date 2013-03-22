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
package greenapi.core.model.software.os.commands.impl.linux;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import greenapi.core.model.resources.net.NetworkInterface;
import greenapi.core.model.software.os.commands.Argument;

import lshw.parser.xml.Lshw;
import lshw.types.NodeInfo;

public class NetworkInterfacesDescriptionImpl extends LinuxCommandSupport<NetworkInterface[]>
{

    /**
     * Default constructor. This command requires root user and the bash.
     */
    public NetworkInterfacesDescriptionImpl()
    {
        super(true, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected NetworkInterface[] parser(String result, InputStream source) throws IOException
    {
        List<NetworkInterface> cards = new LinkedList<>();

        for (NodeInfo node : Lshw.unmarshall(result))
        {
            cards.add(NetworkInterface.valueOf(node));
        }

        return cards.toArray(new NetworkInterface[cards.size()]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] commandLine(Argument... args)
    {
        return new String[] {"lshw", "-class", "network", "-xml"};
    }
}
