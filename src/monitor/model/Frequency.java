package monitor.model;

public final class Frequency implements Data<Long>, Comparable<Frequency> {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 361164863478257017L;

	private final long value;

	private static final Long UNKNOWN = 0L;

	public static final Frequency NULL_FREQUENCY = new Frequency(UNKNOWN);

	public Frequency(Long value) {
		this.value = (value == null ? UNKNOWN : value);
	}
	
	public static Frequency valueOf(Long value) {
		return new Frequency(value);
	}

	@Override
	public Long value() {
		return this.value;
	}

	/**
	 * Returns the frequency value in MHz.
	 * 
	 * @return The frequency value in MHz.
	 */
	public double inMhz() {
		return this.value() / 1000000d;
	}
	
	@Override
	public int compareTo(Frequency other) {
		return this.value().compareTo(other.value());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (value ^ (value >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		Frequency other = (Frequency) obj;
		if (value != other.value)
			return false;

		return true;
	}
	
	@Override
	public String toString() {
		return this.value().toString();
	}
}