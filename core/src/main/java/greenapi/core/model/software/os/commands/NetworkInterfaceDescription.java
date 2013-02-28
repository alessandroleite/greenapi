package greenapi.core.model.software.os.commands;

import greenapi.core.model.resources.net.NetworkInterface;

public interface NetworkInterfaceDescription extends Command<NetworkInterface> {

	NetworkInterface execute(String id);
}
