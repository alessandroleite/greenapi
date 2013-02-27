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
package greenapi.core.model.resources.net;

import greenapi.core.model.data.Data;
import greenapi.core.model.data.NetworkInterfaceStat;
import greenapi.core.model.resources.Resource;
import greenapi.core.model.sensors.Sensor;
import lshw.types.Capabilities;
import lshw.types.Measured;
import lshw.types.NodeInfo;
import lshw.types.Resources;

public final class NetworkInterface implements Resource, Cloneable {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 5326203263155185690L;

	private static final NetworkInterface NULL_NETWORK_INTERFACE = new NetworkInterface(
			null, null, null, null, null, null, null, null, null, null, null,
			null, new Capabilities(), null, new Resources());
	
	/**
	 * The physical id.
	 */
	private final String id;
	private final String description;
	private final String product;
	private final String vendor;
	private final String busInfo;
	private final String logicalName;
	private final String version;
	private final String serial;
	private final Measured size;
	private final Measured capacity;
	private final Measured width;
	private final Measured clock;
	private final Capabilities capabilities;
	private final NetworkInterfaceConfiguration configuration;
	private Resources resources;
	
	private NetworkInterfaceStat state;
	
	private NetworkInterfaceInfo networkInfo;

	public NetworkInterface(String id, String description, String product,
			String vendor, String busInfo, String logicalName, String version,
			String serial, Measured size, Measured capacity, Measured width, Measured clock,
			Capabilities capabilities, NetworkInterfaceConfiguration configuration,
			Resources resources) {
		
		this(id, description, product, vendor, busInfo, logicalName, version,
				serial, size, capacity, width, clock, capabilities, configuration);
		this.resources = resources;
	}

	public NetworkInterface(String id, String description, String product,
			String vendor, String busInfo, String logicalName, String version,
			String serial, Measured size, Measured capacity, Measured width, Measured clock,
			Capabilities capabilities, NetworkInterfaceConfiguration configuration) {

		this.id = id;
		this.description = description;
		this.product = product;
		this.vendor = vendor;
		this.busInfo = busInfo;
		this.logicalName = logicalName;
		this.version = version;
		this.serial = serial;
		this.size = size;
		this.capacity = capacity;
		this.width = width;
		this.clock = clock;
		this.capabilities = capabilities;
		this.configuration = configuration;
	}
	
	public NetworkInterface(NetworkInterface other) {
		this(other.id(), other.description(), other.product(), other.vendor(),
				other.busInfo(), other.logicalName(), other.version(), 
				other.serial(), other.size(), other.capacity, other.width(),
				other.clock(), other.capabilities(), other.configuration(),
				other.resources());
	}

	public static NetworkInterface valueOf(NodeInfo node) {
		
		if (node == null) {
			return NULL_NETWORK_INTERFACE.clone();
		}
		
		return new NetworkInterface(node.getId(), node.getDescription(),
				node.getProduct(), node.getVendor(), node.getBusInfo(),
				node.getLogicalName(), node.getVersion(), node.getSerial(),
				node.getSize(), node.getCapacity(), node.getWidth(),
				node.getClock(), node.getCapabilities(),
				NetworkInterfaceConfiguration.valueOf(node.getConfiguration()),
				node.getResources());
	}
	
	@Override
	public NetworkInterface clone() {
		return new NetworkInterface(this);
	}

	@Override
	public Sensor<?, Data<?>>[] sensors() {
		return null;
	}
	
	public String hardwareAddress(){
		return this.serial();
	}

	/**
	 * @return the resources
	 */
	public Resources resources() {
		return resources;
	}

	/**
	 * @param resources the resources to set
	 */
	public void setResources(Resources resources) {
		this.resources = resources;
	}

	/**
	 * @return the id
	 */
	public String id() {
		return id;
	}

	/**
	 * @return the description
	 */
	public String description() {
		return description;
	}

	/**
	 * @return the product
	 */
	public String product() {
		return product;
	}

	/**
	 * @return the vendor
	 */
	public String vendor() {
		return vendor;
	}

	/**
	 * @return the busInfo
	 */
	public String busInfo() {
		return busInfo;
	}

	/**
	 * @return the logicalName
	 */
	public String logicalName() {
		return logicalName;
	}

	/**
	 * @return the version
	 */
	public String version() {
		return version;
	}

	/**
	 * @return the serial
	 */
	public String serial() {
		return serial;
	}

	/**
	 * @return the size
	 */
	public Measured size() {
		return size;
	}
	
	/**
	 * @return the capacity
	 */
	public Measured capacity(){
		return capacity;
	}

	/**
	 * @return the width
	 */
	public Measured width() {
		return width;
	}

	/**
	 * @return the clock
	 */
	public Measured clock() {
		return clock;
	}

	/**
	 * @return the capabilities
	 */
	public Capabilities capabilities() {
		return capabilities;
	}

	/**
	 * @return the configuration
	 */
	public NetworkInterfaceConfiguration configuration() {
		return configuration;
	}

	/**
	 * @return the state
	 */
	public NetworkInterfaceStat state() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public NetworkInterfaceStat setState(NetworkInterfaceStat state) {
		synchronized(this) {
			NetworkInterfaceStat previousState = this.state;
			this.state = state;	
			return previousState;
		}
	}

	/**
	 * @return the info
	 */
	public NetworkInterfaceInfo networkInfo() {
		return networkInfo;
	}

	/**
	 * @param networkInfo the info to set
	 */
	public void setNetworkInfo(NetworkInterfaceInfo networkInfo) {
		this.networkInfo = networkInfo;
	}
}