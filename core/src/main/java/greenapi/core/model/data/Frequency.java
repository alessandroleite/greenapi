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

import java.math.BigDecimal;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

public final class Frequency implements
		Data<Measure<BigDecimal, javax.measure.quantity.Frequency>>,
		Comparable<Frequency> {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -1779064340006691832L;

	private static final Long UNKNOWN = 0L;

	public static final Frequency NULL_FREQUENCY = newFrequencyInMhz(UNKNOWN);

	private final Measure<BigDecimal, javax.measure.quantity.Frequency> value;

	public Frequency(long valueInMhz) {
		this(valueInMhz, SI.MEGA(SI.HERTZ));
	}

	public Frequency(long value, Unit<javax.measure.quantity.Frequency> unit) {
		this.value = DecimalMeasure.valueOf(BigDecimal.valueOf(value), unit);
	}

	public static Frequency newFrequencyInMhz(long valueInMhz) {
		return new Frequency(valueInMhz);
	}

	public static Frequency newFrequencyInGhz(long valueInGhz) {
		return new Frequency(valueInGhz, SI.GIGA(SI.HERTZ));
	}

	public static Frequency newFrequencyInHertz(long valueInHertz) {
		return new Frequency(valueInHertz, SI.HERTZ);
	}

	@Override
	public Measure<BigDecimal, javax.measure.quantity.Frequency> value() {
		return this.value;
	}

	/**
	 * Returns the frequency value in GHz.
	 * 
	 * @return The frequency value in GHz.
	 */
	public double inGhz() {
		return this.value().doubleValue(SI.GIGA(SI.HERTZ));
	}

	/**
	 * Returns the frequency value in MHz.
	 * 
	 * @return The frequency value in MHz.
	 */
	public double inMhz() {
		return this.value().doubleValue(SI.MEGA(SI.HERTZ));
	}

	@Override
	public int compareTo(Frequency other) {
		return this.value().compareTo(other.value());
	}

	@Override
	public String toString() {
		return this.value().toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (getClass() != obj.getClass()) {
			return false;
		}
		Frequency other = (Frequency) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
}