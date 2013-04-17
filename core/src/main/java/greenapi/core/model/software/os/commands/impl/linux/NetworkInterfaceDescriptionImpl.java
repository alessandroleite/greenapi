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

import greenapi.core.model.exception.GreenApiException;
import greenapi.core.model.resources.net.NetworkInterface;
import greenapi.core.model.software.os.commands.Argument;
import greenapi.core.model.software.os.commands.NetworkInterfaceDescription;
import greenapi.core.model.software.os.commands.Result;

import lshw.parser.exception.LshwParserException;
import lshw.parser.xml.Lshw;
import lshw.types.Nodes;

import org.apache.commons.io.IOUtils;

public final class NetworkInterfaceDescriptionImpl extends LinuxCommandSupport<NetworkInterface> implements NetworkInterfaceDescription
{

    /**
     * The network interface' id.
     */
    private String id;

    /**
     * Create an instance of this class with a given network card id.
     * 
     * @param netId
     *            The network card id to be used.
     */
    public NetworkInterfaceDescriptionImpl(String netId)
    {
        super(true, false);
        this.id = netId;
    }

    // LoadingCache<String, Result<NetworkInterface>> NETWORK_INTERFACE_CACHE = CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.MINUTES).
    // build(new CacheLoader<String, Result<NetworkInterface>>(){
    // @Override
    // public Result<NetworkInterface> load(String id) throws Exception {
    // return new NetworkInterfaceDescriptionImpl(id).execute();
    // }});

    /**
     * {@inheritDoc}
     */
    @Override
    public NetworkInterface execute(String netId)
    {
        return execute(Argument.valueOf(netId)).getValue();
    }

    @Override
    public Result<NetworkInterface> execute(Argument... args)
    {

        if (this.isRootRequired() && !isRoot())
        {
            return Result.newFailureResult(new GreenApiException("Please, execute this command as a root user!"));
        }

        if (this.id == null && (args == null || args.length == 0 || args[0] == null))
        {

            return Result.newFailureResult(new GreenApiException("networkId is null!"));

        }
        else if (this.id == null && (args != null && args.length > 0))
        {

            this.id = args[0].value().toString();
        }

        String[][] commands = getCommandToExecute(args);

        try
        {
            Process process = Runtime.getRuntime().exec(commands[0], commands[1]);
            process.waitFor();

            try (InputStream is = process.getInputStream())
            {
                Nodes nodes = Lshw.unmarshall(IOUtils.toString(is).trim());

                setResult(new Result<NetworkInterface>(NetworkInterface.valueOf(nodes.findNodeByHardwareId(id.trim()))));
            }
        }
        catch (IOException | InterruptedException | LshwParserException | IllegalArgumentException exception)
        {
            setResult(Result.<NetworkInterface> newFailureResult(exception));
        }

        return this.result();
    }

    @Override
    public String[] commandLine(Argument... args)
    {
        return new String[] { "lshw", "-class", "network", "-xml" };
    }

    @Override
    protected NetworkInterface parser(String result, InputStream source) throws IOException
    {
        throw new UnsupportedOperationException("Not available!");
    }
}
