package monitor.model;

public class Temperature implements Data<Double> {

	/**
	 * Serial code version <code>serialVersionUID</code> 
	 */
	private static final long serialVersionUID = 8530632866881069978L;
	
	/** The current temperature. */
	private final double value;
	/** High temperature */
	private final double high;
	/** Critical temperature */
	private final double critical;

	/**
	 * @param value
	 * @param high
	 * @param critical
	 */
	public Temperature(double value, double high, double critical) {
		this.value = value;
		this.high = high;
		this.critical = critical;
	}
	
	/**
	 *
	 * Return the current temperature on the Kelvin scale.
	 * @return Return the current temperature on the Kelvin scale.
	 */
	public double valueInKelvin(){
		return 0;
	}
	
	/**
	 * Return the current temperature on the Fahrenheit scale.
	 * @return Return the current temperature on the Fahrenheit scale.
	 */
	public double valueInFahrenheit(){
		return 1.8 * this.value() + 32;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double value() {
		return this.value;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(critical);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(high);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		
		if (!(obj instanceof Temperature)) {
			return false;
		}
		
		Temperature other = (Temperature) obj;
		if (Double.doubleToLongBits(critical) != Double.doubleToLongBits(other.critical)) {
			return false;
		}
		if (Double.doubleToLongBits(high) != Double.doubleToLongBits(other.high)) {
			return false;
		}
		
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value)) {
			return false;
		}
		
		return true;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return String.format("Temperature %s °C", this.getValue()/* (high = %s, crit = %s), this.getHigh(), this.getCritical()*/);
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return value();
	}

	/**
	 * @return the high
	 */
	public double getHigh() {
		return high;
	}

	/**
	 * @return the critical
	 */
	public double getCritical() {
		return critical;
	}
}