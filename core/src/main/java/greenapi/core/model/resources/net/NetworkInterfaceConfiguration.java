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

import greenapi.core.common.primitives.Booleans;
import greenapi.core.common.primitives.Doubles;

import java.io.Serializable;
import java.util.Map;

import lshw.types.Configurations;

public final class NetworkInterfaceConfiguration implements Serializable,
		Cloneable {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1946779749348162293L;

	private final boolean autoNegotiation;
	private final boolean broadcast;
	private final String driver;
	private final String driverVersion;
	private final String duplex;
	private final String firmware;
	private final String ip;
	private final double latency;
	private final boolean link;
	private final boolean multicast;
	private final String port;
	private final String speed;

	public NetworkInterfaceConfiguration(boolean autoNegotiation,
			boolean broadcast, String driver, String driverVersion,
			String duplex, String firmware, String ip, double latency,
			boolean link, boolean multicast, String port, String speed) {

		this.autoNegotiation = autoNegotiation;
		this.broadcast = broadcast;
		this.driver = driver;
		this.driverVersion = driverVersion;
		this.duplex = duplex;
		this.firmware = firmware;
		this.ip = ip;
		this.latency = latency;
		this.link = link;
		this.multicast = multicast;
		this.port = port;
		this.speed = speed;
	}

	public static NetworkInterfaceConfiguration valueOf(
			Configurations configurations) {
		Map<String, String> configurationsMap = configurations
				.getConfigurationsMap();
		return new NetworkInterfaceConfiguration(
				Booleans.valueOf(configurationsMap.get("autonegotiation")),
				Booleans.valueOf(configurationsMap.get("broadcast")),
				configurationsMap.get("driver"),
				configurationsMap.get("driverversion"),
				configurationsMap.get("duplex"),
				configurationsMap.get("firmware"), configurationsMap.get("ip"),
				Doubles.valueOf(configurationsMap.get("latency")),
				Booleans.valueOf(configurationsMap.get("link")),
				Booleans.valueOf(configurationsMap.get("multicast")),
				configurationsMap.get("port"), configurationsMap.get("speed"));
	}

	@Override
	public NetworkInterfaceConfiguration clone() {
		return new NetworkInterfaceConfiguration(this.autoNegotiation,
				broadcast, driver, driverVersion, duplex, firmware, ip,
				latency, link, multicast, port, speed);
	}

	/**
	 * @return the autoNegotiation
	 */
	public boolean autoNegotiation() {
		return autoNegotiation;
	}

	/**
	 * @return the broadcast
	 */
	public boolean broadcast() {
		return broadcast;
	}

	/**
	 * @return the driver
	 */
	public String driver() {
		return driver;
	}

	/**
	 * @return the driverVersion
	 */
	public String driverVersion() {
		return driverVersion;
	}

	/**
	 * @return the duplex
	 */
	public String duplex() {
		return duplex;
	}

	/**
	 * @return the firmware
	 */
	public String firmware() {
		return firmware;
	}

	/**
	 * @return the ip
	 */
	public String ip() {
		return ip;
	}

	/**
	 * @return the latency
	 */
	public double latency() {
		return latency;
	}

	/**
	 * @return the link
	 */
	public boolean link() {
		return link;
	}

	/**
	 * @return the multicast
	 */
	public boolean multicast() {
		return multicast;
	}

	/**
	 * @return the port
	 */
	public String port() {
		return port;
	}

	/**
	 * @return the pair
	 */
	public String speed() {
		return speed;
	}
}