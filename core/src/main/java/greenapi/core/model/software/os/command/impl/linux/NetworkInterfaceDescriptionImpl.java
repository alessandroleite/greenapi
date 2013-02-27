package greenapi.core.model.software.os.command.impl.linux;

import greenapi.core.common.base.IOUtils;
import greenapi.core.model.exception.GreenApiException;
import greenapi.core.model.resources.net.NetworkInterface;
import greenapi.core.model.software.os.command.Argument;
import greenapi.core.model.software.os.command.NetworkInterfaceDescription;

import java.io.IOException;
import java.io.InputStream;

import lshw.parser.exception.LshwParserException;
import lshw.parser.xml.Lshw;
import lshw.types.Nodes;

public class NetworkInterfaceDescriptionImpl extends
		LinuxCommandSupport<NetworkInterface> implements
		NetworkInterfaceDescription {

	private String id;

	public NetworkInterfaceDescriptionImpl(String id) {
		super(true, true);
		this.id = id;
	}

	@Override
	public NetworkInterface execute(String id) {
		return execute(Argument.valueOf(id));
	}

	@Override
	public NetworkInterface execute(Argument... args) {
		
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

		String[][] commands = commands(args);

		try {
			Process process = Runtime.getRuntime().exec(commands[0], commands[1]);
			
			try (InputStream is = process.getInputStream()) {
				Nodes nodes = Lshw.unmarshall(IOUtils.asString(is).trim());
				
				this.output = NetworkInterface.valueOf(nodes.getNodeById(id.trim()));

			}
		} catch (IOException | LshwParserException | IllegalArgumentException exception) {
			this.errors.put(exception.getMessage(), exception);
		}
		return this.output();
	}

	@Override
	public String commandLine() {
		return "lshw -class network -xml";
	}

	@Override
	protected NetworkInterface parser(String result, InputStream source)
			throws IOException {
		throw new UnsupportedOperationException("Not available!");
	}
}