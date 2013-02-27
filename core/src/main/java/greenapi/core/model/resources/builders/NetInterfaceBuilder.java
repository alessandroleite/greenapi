package greenapi.core.model.resources.builders;

import java.math.BigDecimal;

import lshw.types.Capabilities;

import greenapi.core.model.resources.net.NetworkInterface;
import greenapi.core.model.resources.net.NetworkInterfaceConfiguration;
import greenapi.core.model.resources.net.NetworkInterfaceInfo;
import greenapi.core.model.software.os.OperatingSystem;

public interface NetInterfaceBuilder extends Builder<NetworkInterface> {

	NetInterfaceBuilder useOperatingSystemInformations(OperatingSystem os);

	NetInterfaceBuilder withNetworkInfo(
			NetworkInterfaceInfo networkInterfaceInfo);

	NetInterfaceBuilder withId(String id);

	NetInterfaceBuilder withDescription(String description);

	NetInterfaceBuilder withProductName(String name);

	NetInterfaceBuilder ofVendor(String vendor);

	NetInterfaceBuilder withBusInfo(String busInfo);

	NetInterfaceBuilder withLogicalName(String name);

	NetInterfaceBuilder withVersion(String version);

	NetInterfaceBuilder withHardwareId(String serial);

	NetInterfaceBuilder withSize(BigDecimal size);

	NetInterfaceBuilder withCapacity(BigDecimal capacity);

	NetInterfaceBuilder withWidth(BigDecimal width);

	NetInterfaceBuilder withClock(BigDecimal clock);

	NetInterfaceBuilder withCapabilities(Capabilities capabilities);

	NetInterfaceBuilder withConfiguration(
			NetworkInterfaceConfiguration configuration);
}
