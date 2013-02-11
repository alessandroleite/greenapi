package monitor.model;

public class IOStatProperty implements Data<Double> {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -9004443006436945284L;

	private final String name;

	private final Double value;

	/**
	 * 
	 */
	private final long timestamp;

	public IOStatProperty(String name, Double value) {
		this(name, value, System.currentTimeMillis());
	}

	public IOStatProperty(String name, Double value, long timeMillis) {
		this.name = name;
		this.value = value;
		this.timestamp = timeMillis;
	}

	/**
	 * @return the name
	 */
	public String name() {
		return name;
	}

	@Override
	public Double value() {
		return this.value;
	}

	/**
	 * @return the timestamp
	 */
	public long timestamp() {
		return timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		if (!(obj instanceof IOStatProperty)) {
			return false;
		}
		IOStatProperty other = (IOStatProperty) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s:%s", this.name(), this.value());
	}
}