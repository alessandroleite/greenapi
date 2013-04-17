/**
 * Copyright (c) 2012 GreenI2R
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
package greenapi.core.model.data;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;
import greenapi.core.model.resources.net.NetworkInterface;

public final class NetworkInterfaceStat implements Data<NetworkInterfaceStat> {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -2848940706568199104L;

	private final NetworkInterface networkInterface;

	private final TxNetworkInterfaceData txData;

	private final RxNetworkInterfaceData rxData;

	public NetworkInterfaceStat(NetworkInterface networkInterface,
			TxNetworkInterfaceData txData, RxNetworkInterfaceData rxData) {
		
		this.networkInterface = requireNonNull(networkInterface);
		this.txData = requireNonNull(txData);
		this.rxData = requireNonNull(rxData);

		checkArgument(networkInterface == txData.networkInterface() && networkInterface == rxData.networkInterface());
	}

	@Override
	public NetworkInterfaceStat value() {
		return this;
	}

	/**
	 * @return the networkInterface
	 */
	public NetworkInterface networkInterface() {
		return networkInterface;
	}

	/**
	 * @return the txData
	 */
	public TxNetworkInterfaceData txData() {
		return txData;
	}

	/**
	 * @return the rxData
	 */
	public RxNetworkInterfaceData rxData() {
		return rxData;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((networkInterface == null) ? 0 : networkInterface.hashCode());
		result = prime * result + ((rxData == null) ? 0 : rxData.hashCode());
		result = prime * result + ((txData == null) ? 0 : txData.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		NetworkInterfaceStat other = (NetworkInterfaceStat) obj;
		if (networkInterface == null) {
			if (other.networkInterface != null) {
				return false;
			}
		} else if (!networkInterface.equals(other.networkInterface)) {
			return false;
		}
		
		if (rxData == null) {
			if (other.rxData != null) {
				return false;
			}
		} else if (!rxData.equals(other.rxData)) {
			return false;
		}
		
		if (txData == null) {
			if (other.txData != null) {
				return false;
			}
		} else if (!txData.equals(other.txData)) {
			return false;
		}
		
		return true;
	}
}