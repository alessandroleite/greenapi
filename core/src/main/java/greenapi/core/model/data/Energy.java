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