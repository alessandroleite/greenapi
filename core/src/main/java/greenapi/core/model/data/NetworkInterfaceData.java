package greenapi.core.model.data;

import static java.util.Objects.*;

import java.io.Serializable;

import greenapi.core.model.resources.net.NetworkInterface;

public abstract class NetworkInterfaceData implements Serializable {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 2878550440733362963L;

	public enum NetworkInterfaceDataType {
		RX, TX;
	}

	private final NetworkInterface networkInterface;

	/**
	 * The amount of bytes read or write.
	 */
	private final long bytes;

	/**
	 * The amount of packets dropped.
	 */
	private final long dropped;

	/**
	 * The amount of packets overruns.
	 */
	private final long overruns;

	/**
	 * The amount of errors.
	 */
	private final long errors;

	/**
	 * The amount of packets.
	 */
	private final long packets;

	public NetworkInterfaceData(NetworkInterface networkInterface, long bytes,
			long dropped, long overruns, long errors, long packets) {
		this.networkInterface = requireNonNull(networkInterface);
		this.bytes = bytes;
		this.dropped = dropped;
		this.overruns = overruns;
		this.errors = errors;
		this.packets = packets;
	}
	
	public abstract NetworkInterfaceDataType type();

	/**
	 * @return the networkInterface
	 */
	public NetworkInterface networkInterface() {
		return networkInterface;
	}

	/**
	 * @return the bytes
	 */
	public long bytes() {
		return bytes;
	}

	/**
	 * @return the dropped
	 */
	public long dropped() {
		return dropped;
	}

	/**
	 * @return the overruns
	 */
	public long overruns() {
		return overruns;
	}

	/**
	 * @return the errors
	 */
	public long errors() {
		return errors;
	}

	/**
	 * @return the packets
	 */
	public long packets() {
		return packets;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (bytes ^ (bytes >>> 32));
		result = prime * result + (int) (dropped ^ (dropped >>> 32));
		result = prime * result + (int) (errors ^ (errors >>> 32));
		result = prime
				* result
				+ ((networkInterface == null) ? 0 : networkInterface.hashCode());
		result = prime * result + (int) (overruns ^ (overruns >>> 32));
		result = prime * result + (int) (packets ^ (packets >>> 32));
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
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
		NetworkInterfaceData other = (NetworkInterfaceData) obj;
		if (bytes != other.bytes) {
			return false;
		}
		if (dropped != other.dropped) {
			return false;
		}
		if (errors != other.errors) {
			return false;
		}
		if (networkInterface == null) {
			if (other.networkInterface != null) {
				return false;
			}
		} else if (!networkInterface.equals(other.networkInterface)) {
			return false;
		}
		if (overruns != other.overruns) {
			return false;
		}
		if (packets != other.packets) {
			return false;
		}
		return true;
	}
}