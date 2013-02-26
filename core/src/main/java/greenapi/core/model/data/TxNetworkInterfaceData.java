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