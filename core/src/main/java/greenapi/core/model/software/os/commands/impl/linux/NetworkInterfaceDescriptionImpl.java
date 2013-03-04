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
package greenapi.core.model.software.os.commands.impl.linux;

import greenapi.core.model.exception.GreenApiException;
import greenapi.core.model.resources.net.NetworkInterface;
import greenapi.core.model.software.os.commands.Argument;
import greenapi.core.model.software.os.commands.NetworkInterfaceDescription;
import greenapi.core.model.software.os.commands.Result;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import lshw.parser.exception.LshwParserException;
import lshw.parser.xml.Lshw;
import lshw.types.Nodes;

public class NetworkInterfaceDescriptionImpl extends
		LinuxCommandSupport<NetworkInterface> implements
		NetworkInterfaceDescription {

	private String id;

	public NetworkInterfaceDescriptionImpl(String id) {
		super(true, false);
		this.id = id;
	}
	
//	LoadingCache<String, Result<NetworkInterface>> NETWORK_INTERFACE_CACHE = CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.MINUTES).
//			build(new CacheLoader<String, Result<NetworkInterface>>(){
//				@Override
//				public Result<NetworkInterface> load(String id) throws Exception {
//					return new NetworkInterfaceDescriptionImpl(id).execute();
//				}});

	@Override
	public NetworkInterface execute(String id) {
		Result<NetworkInterface> result = execute(Argument.valueOf(id));
		return result.getValue();
	}

	@Override
	public Result<NetworkInterface> execute(Argument... args) {
		
		if (this.isRootRequired() && !isRoot()) {
			GreenApiException exception = new GreenApiException("Please, execute this command as a root user!");
			errors.put(exception.getMessage(), exception);
			return null;
		}
		
		if (this.id == null && (args == null || args.length == 0 || args[0] == null)){
			
			GreenApiException exception = new GreenApiException("networkId is null!");
			errors.put(exception.getMessage(), exception);
			
			return null;
		} else if (this.id == null && (args != null && args.length > 0)) {
			
			this.id = args[0].value().toString();
		}

		String[][] commands = getCommandToExecute(args);

		try {
			Process process = Runtime.getRuntime().exec(commands[0], commands[1]);
			process.waitFor();
			
			try (InputStream is = process.getInputStream()) {
				Nodes nodes = Lshw.unmarshall(IOUtils.toString(is).trim());
				
				this.result = new Result<NetworkInterface> (NetworkInterface.valueOf(nodes.findNodeByHardwareId(id.trim())));
			}
		} catch (IOException | InterruptedException | LshwParserException | IllegalArgumentException exception) {
			this.errors.put(exception.getMessage(), exception);
		}
		
		this.result().addAll(this.getErrors());
		
		return this.result();
	}

	@Override
	public String[] commandLine(Argument ... args) {
		return new String[] {"lshw","-class", "network", "-xml"};
	}

	@Override
	protected NetworkInterface parser(String result, InputStream source)
			throws IOException {
		throw new UnsupportedOperationException("Not available!");
	}
}