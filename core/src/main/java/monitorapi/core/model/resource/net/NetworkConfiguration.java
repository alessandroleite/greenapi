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
package monitorapi.core.model.resource.net;

public final class NetworkConfiguration {

	private final boolean autonegotiation;
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
	private final String pair;

	public NetworkConfiguration(boolean autonegotiation, boolean broadcast,
			String driver, String driverVersion, String duplex,
			String firmware, String ip, double latency, boolean link,
			boolean multicast, String port, String pair) {
		
		this.autonegotiation = autonegotiation;
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
		this.pair = pair;
	}

	/**
	 * @return the autonegotiation
	 */
	public boolean autonegotiation() {
		return autonegotiation;
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
	public String pair() {
		return pair;
	}
}