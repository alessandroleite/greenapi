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

}
