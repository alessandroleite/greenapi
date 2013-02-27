package greenapi.core.model.software.os.command;

import greenapi.core.model.resources.net.NetworkInterface;

public interface NetworkInterfaceDescription extends Command<NetworkInterface> {

	NetworkInterface execute(String id);
}
