package monitor.model;


import static monitor.util.Strings.*;

public final class MemoryState implements Data<Double> {

	/**
	 *  Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1469229872195311515L;	
	
	private final double usedPercentual;
	private final double freePercent;

	public MemoryState(double usedPercent, double freePercent) {
		this.usedPercentual = usedPercent;
		this.freePercent = freePercent;
	}

	@Override
	public Double value() {
		return usedPercentual;
	}

	public double free() {
		return this.freePercent;
	}
	
	@Override
	public String toString() {	
		return "Used: " + format(this.value(),false) + ", Free: " + format(this.free(), false);
	}
}