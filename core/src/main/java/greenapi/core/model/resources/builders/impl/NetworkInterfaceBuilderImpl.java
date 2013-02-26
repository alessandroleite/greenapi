package greenapi.core.model.resources.builders.impl;

import greenapi.core.model.resources.builders.NetInterfaceBuilder;
import greenapi.core.model.resources.net.NetworkInterface;
import greenapi.core.model.resources.net.NetworkInterfaceConfiguration;
import greenapi.core.model.resources.net.NetworkInterfaceInfo;
import greenapi.core.model.software.os.OperatingSystem;

public class NetworkInterfaceBuilderImpl implements NetInterfaceBuilder {

	private OperatingSystem os;
	private NetworkInterfaceInfo networkInterfaceInfo;
	private Integer id;
	private String description;
	private String product;
	private String vendor;
	private String busInfo;
	private String logicalName;
	private String version;
	private String serial;
	private String size;
	private String capacity;
	private String width;
	private String clock;
	private String capabilities;
	private NetworkInterfaceConfiguration configuration;

	@Override
	public NetworkInterface build() {

		NetworkInterface networkInterface;

		if (os != null) {
			networkInterface = os.networkHardwareDescription().execute();
		} else {
			networkInterface = new NetworkInterface(this.id, this.description,
					this.product, this.vendor, this.busInfo, this.logicalName,
					this.version, this.serial, this.size, this.capacity,
					this.width, this.clock, this.capabilities,
					this.configuration);
		}

		if (this.networkInterfaceInfo != null) {
			networkInterface.setNetworkInfo(networkInterfaceInfo);
		}

		return networkInterface;
	}

	@Override
	public NetInterfaceBuilder useOperatingSystemInformations(OperatingSystem os) {
		this.os = os;
		return this;
	}

	@Override
	public NetInterfaceBuilder withNetworkInfo(
			NetworkInterfaceInfo networkInterfaceInfo) {
		this.networkInterfaceInfo = networkInterfaceInfo;
		return this;
	}

	@Override
	public NetInterfaceBuilder withId(Integer id) {
		this.id = id;
		return this;
	}

	@Override
	public NetInterfaceBuilder withDescription(String description) {
		this.description = description;
		return this;
	}

	@Override
	public NetInterfaceBuilder withProductName(String name) {
		this.product = name;
		return this;
	}

	@Override
	public NetInterfaceBuilder ofVendor(String vendor) {
		this.vendor = vendor;
		return this;
	}

	@Override
	public NetInterfaceBuilder withBusInfo(String busInfo) {
		this.busInfo = busInfo;
		return this;
	}

	@Override
	public NetInterfaceBuilder withLogicalName(String name) {
		this.logicalName = name;
		return this;
	}

	@Override
	public NetInterfaceBuilder withVersion(String version) {
		this.version = version;
		return this;
	}

	@Override
	public NetInterfaceBuilder withHardwareId(String serial) {
		this.serial = serial;
		return this;
	}

	@Override
	public NetInterfaceBuilder withSize(String size) {
		this.size = size;
		return this;
	}

	@Override
	public NetInterfaceBuilder withCapacity(String capacity) {
		this.capacity = capacity;
		return this;
	}

	@Override
	public NetInterfaceBuilder withWidth(String width) {
		this.width = width;
		return this;
	}

	@Override
	public NetInterfaceBuilder withClock(String clock) {
		this.clock = clock;
		return this;
	}

	@Override
	public NetInterfaceBuilder withCapabilities(String capabilities) {
		this.capabilities = capabilities;
		return this;
	}

	@Override
	public NetInterfaceBuilder withConfiguration(
			NetworkInterfaceConfiguration configuration) {
		this.configuration = configuration;
		return this;
	}
}