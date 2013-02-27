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