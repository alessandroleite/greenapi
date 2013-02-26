package greenapi.core.model.resources.builders;

import greenapi.core.model.resources.net.NetworkInterface;
import greenapi.core.model.resources.net.NetworkInterfaceConfiguration;
import greenapi.core.model.resources.net.NetworkInterfaceInfo;
import greenapi.core.model.software.os.OperatingSystem;

public interface NetInterfaceBuilder extends Builder<NetworkInterface> {

	NetInterfaceBuilder useOperatingSystemInformations(OperatingSystem os);

	NetInterfaceBuilder withNetworkInfo(
			NetworkInterfaceInfo networkInterfaceInfo);

	NetInterfaceBuilder withId(Integer id);

	NetInterfaceBuilder withDescription(String description);

	NetInterfaceBuilder withProductName(String name);

	NetInterfaceBuilder ofVendor(String vendor);

	NetInterfaceBuilder withBusInfo(String busInfo);

	NetInterfaceBuilder withLogicalName(String name);

	NetInterfaceBuilder withVersion(String version);

	NetInterfaceBuilder withHardwareId(String serial);

	NetInterfaceBuilder withSize(String size);

	NetInterfaceBuilder withCapacity(String capacity);

	NetInterfaceBuilder withWidth(String width);

	NetInterfaceBuilder withClock(String clock);

	NetInterfaceBuilder withCapabilities(String capabilities);

	NetInterfaceBuilder withConfiguration(
			NetworkInterfaceConfiguration configuration);
}
