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
import javax.measure.quantity.Power;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

import com.google.common.base.Preconditions;

public final class Energy implements Data<Measure<BigDecimal, Power>>, Comparable<Energy> {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 7980385870495269655L;

	private final Measure<BigDecimal, Power> value;

	public Energy(BigDecimal powerInMegaWatt) {
		this(powerInMegaWatt, SI.MEGA(SI.WATT));
	}

	public Energy(BigDecimal value, Unit<Power> unit) {
		this.value = DecimalMeasure.valueOf(value, unit);
	}

	public static Energy fromPowerInMegaWatt(BigDecimal power) {
		return new Energy(power);
	}

	public static Energy fromPowerInMegaWatt(double power) {
		return fromPowerInMegaWatt(BigDecimal.valueOf(power));
	}

	public static Energy fromPowerInJoule(double joule, long durationInMilis) {
		Preconditions.checkArgument(durationInMilis > 0);
		return new Energy(BigDecimal.valueOf(joule / (durationInMilis / 1000)));
	}

	@Override
	public Measure<BigDecimal, Power> value() {
		return this.value;
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
		Energy other = (Energy) obj;
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
		return this.value().toString();
	}

	@Override
	public int compareTo(Energy other) {
		return this.value().compareTo(other.value());
	}
}