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
import greenapi.core.model.resources.Resource;
import greenapi.core.model.sensors.Sensor;

public final class NetworkInterface implements Resource {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -8169725322485852032L;
	
	/**
	 * The physical id.
	 */
	private final Integer id;
	private final String description;
	private final String product;
	private final String vendor;
	private final String busInfo;
	private final String logicalName;
	private final String version;
	private final String serial;
	private final String size;
	private final String capacity;
	private final String width;
	private final String clock;
	private final String capabilities;
	private final NetworkConfiguration configuration;
	private String resources;

	public NetworkInterface(Integer id, String description, String product,
			String vendor, String busInfo, String logicalName, String version,
			String serial, String size, String capacity, String width, String clock,
			String capabilities, NetworkConfiguration configuration,
			String resources) {
		this(id, description, product, vendor, busInfo, logicalName, version,
				serial, size, capacity, width, clock, capabilities, configuration);
		this.resources = resources;
	}

	public NetworkInterface(Integer id, String description, String product,
			String vendor, String busInfo, String logicalName, String version,
			String serial, String size, String capacity, String width, String clock,
			String capabilities, NetworkConfiguration configuration) {

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

	@Override
	public Sensor<?, Data<?>>[] sensors() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the resources
	 */
	public String resources() {
		return resources;
	}

	/**
	 * @param resources the resources to set
	 */
	public void setResources(String resources) {
		this.resources = resources;
	}

	/**
	 * @return the id
	 */
	public Integer id() {
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
	public String size() {
		return size;
	}
	
	/**
	 * @return the capacity
	 */
	public String capacity(){
		return capacity;
	}

	/**
	 * @return the width
	 */
	public String width() {
		return width;
	}

	/**
	 * @return the clock
	 */
	public String clock() {
		return clock;
	}

	/**
	 * @return the capabilities
	 */
	public String capabilities() {
		return capabilities;
	}

	/**
	 * @return the configuration
	 */
	public NetworkConfiguration configuration() {
		return configuration;
	}
}