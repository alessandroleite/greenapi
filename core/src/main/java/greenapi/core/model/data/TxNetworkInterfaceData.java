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
package greenapi.core.model.data;

import greenapi.core.model.resources.net.NetworkInterface;

public class TxNetworkInterfaceData extends NetworkInterfaceData {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -6392966571035880614L;

	private final long collisions;

	private final long carrier;

	public TxNetworkInterfaceData(NetworkInterface networkInterface,
			long bytes, long dropped, long overruns, long errors, long packets,
			long collisions, long carrier) {
		super(networkInterface, bytes, dropped, overruns, errors, packets);
		this.collisions = collisions;
		this.carrier = carrier;
	}

	@Override
	public NetworkInterfaceDataType type() {
		return NetworkInterfaceDataType.TX;
	}

	/**
	 * @return the collisions
	 */
	public long collisions() {
		return collisions;
	}

	/**
	 * @return the carrier
	 */
	public long carrier() {
		return carrier;
	}
}