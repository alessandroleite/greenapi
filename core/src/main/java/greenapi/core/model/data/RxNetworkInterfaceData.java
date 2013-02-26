package greenapi.core.model.data;

import greenapi.core.model.resources.net.NetworkInterface;

public class RxNetworkInterfaceData extends NetworkInterfaceData {
	
	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -6647092203396744161L;
	
	private final long frame;

	public RxNetworkInterfaceData(NetworkInterface networkInterface,
			long bytes, long dropped, long overruns, long errors, long packets, long frame) {
		super(networkInterface, bytes, dropped, overruns, errors, packets);
		this.frame = frame;
	}

	@Override
	public NetworkInterfaceDataType type() {
		return NetworkInterfaceDataType.RX;
	}

	/**
	 * @return the frame
	 */
	public long frame() {
		return frame;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) (frame ^ (frame >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (!super.equals(obj)) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		RxNetworkInterfaceData other = (RxNetworkInterfaceData) obj;
		
		if (frame != other.frame) {
			return false;
		}
		return true;
	}
}