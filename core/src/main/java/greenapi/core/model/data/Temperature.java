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

import java.util.Objects;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

public class Temperature implements Data<Double> {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -4142905195780850697L;
	
	/** The current temperature. */
	private final Measure<Double, javax.measure.quantity.Temperature> value;
	/** High temperature */
	private final Measure<Double, javax.measure.quantity.Temperature> high;
	/** Critical temperature */
	private final Measure<Double, javax.measure.quantity.Temperature> critical;

	/**
	 * 
	 * @param valueInCelsius
	 * @param highInCelsius
	 * @param criticalInCelsius
	 */
	public Temperature(double valueInCelsius, double highInCelsius,
			double criticalInCelsius) {
		this(valueInCelsius, highInCelsius, criticalInCelsius, SI.CELSIUS);
	}

	/**
	 * 
	 * @param value
	 * @param high
	 * @param critical
	 * @param unit
	 */
	public Temperature(double value, double high, double critical,
			Unit<javax.measure.quantity.Temperature> unit) {
		this(DecimalMeasure.valueOf(value, Objects.requireNonNull(unit)),
				DecimalMeasure.valueOf(high, unit), DecimalMeasure.valueOf(
						critical, unit));
	}

	/**
	 * 
	 * @param value
	 * @param high
	 * @param critical
	 */
	public Temperature(
			Measure<Double, javax.measure.quantity.Temperature> value,
			Measure<Double, javax.measure.quantity.Temperature> high,
			Measure<Double, javax.measure.quantity.Temperature> critical) {

		this.value = value;
		this.high = high;
		this.critical = critical;
	}

	public static Temperature newTemperatureInCelsius(double valueInCelsius,
			double highInCelsius, double criticalInCelsius) {
		return new Temperature(valueInCelsius, highInCelsius, criticalInCelsius);
	}

	public static Temperature newTemperatureInCelsius(double valueInCelsius) {
		return newTemperatureInCelsius(valueInCelsius, 0, 0);
	}

	public static Temperature newTemperature(double value, double high,
			double critical, Unit<javax.measure.quantity.Temperature> unit) {
		return new Temperature(value, high, critical, unit);
	}

	/**
	 * 
	 * Return the current temperature on the Kelvin scale.
	 * 
	 * @return Return the current temperature on the Kelvin scale.
	 */
	public double valueInKelvin() {
		return this.value.to(SI.KELVIN).getValue();
	}

	/**
	 * Return the current temperature on the Fahrenheit scale.
	 * 
	 * @return Return the current temperature on the Fahrenheit scale.
	 */
	public double valueInFahrenheit() {
		return 1.8 * this.value.to(SI.CELSIUS).getValue() + 32;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double value() {
		return this.value.to(SI.CELSIUS).getValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Temperature other = (Temperature) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return String.format("Temperature %s °C", this.getValue()
		/* (high = %s, crit = %s), this.getHigh(), this.getCritical() */);
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return this.value.to(SI.CELSIUS).getValue();
	}

	/**
	 * @return the high
	 */
	public double getHigh() {
		return high.to(SI.CELSIUS).getValue();
	}

	/**
	 * @return the critical
	 */
	public double getCritical() {
		return critical.to(SI.CELSIUS).getValue();
	}
}