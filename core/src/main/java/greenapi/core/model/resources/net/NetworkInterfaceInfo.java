/**
 * Copyright (c) 2012 I2RGreen
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

import java.io.Serializable;

public final class NetworkInterfaceInfo implements Serializable {
	
	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1179181398114255407L;

	/**
	 * The default gateway
	 */
	private final String defaultGateway;
	
	/**
	 * The address (IP) of the primary DNS.
	 */
	private final String primaryDns;
	
	/**
	 * The address (IP) of the second DNS.
	 */
	private final String secondDns;
	
	/**
	 * The domain name.
	 */
	private final String domainName;
	
	/**
	 * The host name.
	 */
	private final String hostName;
	
	private final String ip6;
	
	private final String netmask;
	
	private final String broadcastAddress;
	
	private final String ip; 
	
	private final String destination;
	
	public NetworkInterfaceInfo(String defaultGateway, String primaryDns,
			String secondDns, String domainName, String hostName, String ip6,
			String netmask, String broadcastAddress, String ip,
			String destination) {
		
		this.defaultGateway = defaultGateway;
		this.primaryDns = primaryDns;
		this.secondDns = secondDns;
		this.domainName = domainName;
		this.hostName = hostName;
		this.ip6 = ip6;
		this.netmask = netmask;
		this.broadcastAddress = broadcastAddress;
		this.ip = ip;
		this.destination = destination;
	}

	/**
	 * @return the defaultGateway
	 */
	public String defaultGateway() {
		return defaultGateway;
	}

	/**
	 * @return the primaryDns
	 */
	public String primaryDns() {
		return primaryDns;
	}

	/**
	 * @return the secondDns
	 */
	public String secondDns() {
		return secondDns;
	}

	/**
	 * @return the domainName
	 */
	public String domainName() {
		return domainName;
	}

	/**
	 * @return the hostName
	 */
	public String hostName() {
		return hostName;
	}

	/**
	 * @return the ip6
	 */
	public String ip6() {
		return ip6;
	}

	/**
	 * @return the netmask
	 */
	public String netmask() {
		return netmask;
	}

	/**
	 * @return the broadcastAddress
	 */
	public String broadcastAddress() {
		return broadcastAddress;
	}

	/**
	 * @return the ip
	 */
	public String ip() {
		return ip;
	}

	/**
	 * @return the destination
	 */
	public String destination() {
		return destination;
	}
}