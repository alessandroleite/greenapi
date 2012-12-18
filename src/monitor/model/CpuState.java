package monitor.model;

import static monitor.util.Strings.*;

public class CpuState implements Data<Double[]> {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -8314328513249332613L;
	
	private final Cpu cpu;
	private final double user;
	private final double sys;
	private final double idle;
	private final double wait;
	private final double nice;
	private final double combined;
	private final long timeInMillis;
	private final Frequency frequency;

	
	public CpuState(Cpu cpu, double combined, double user, double sys, double idle, double wait, double nice, Frequency frequency) {
		this.cpu = cpu;
		this.combined = combined;
		this.user = user;
		this.sys = sys;
		this.idle = idle;
		this.wait = wait;
		this.nice = nice;
		this.frequency = (frequency == null ? Frequency.NULL_FREQUENCY: frequency);
		this.timeInMillis = System.currentTimeMillis();
	}

	@Override
	public String toString() {
		return "states ....: " 
				+ format(this.combined) + " combined, " 
				+ format(this.user) + " user, "
				+ format(this.sys)  + " system, " 
				+ format(this.nice) + " nice, " 
				+ format(this.wait) + " wait, " 
				+ format(this.idle) + " idle] ";
	}

	@Override
	public Double[] value() {		
		return new Double[] {  this.user, this.sys, this.nice, this.wait, this.idle, this.frequency.inMhz() };
	}

	/**
	 * @return the cpu
	 */
	public Cpu getCpu() {
		return cpu;
	}

	/**
	 * @return the user
	 */
	public double getUser() {
		return user;
	}

	/**
	 * @return the sys
	 */
	public double getSys() {
		return sys;
	}

	/**
	 * @return the idle
	 */
	public double getIdle() {
		return idle;
	}

	/**
	 * @return the wait
	 */
	public double getWait() {
		return wait;
	}

	/**
	 * @return the nice
	 */
	public double getNice() {
		return nice;
	}

	/**
	 * @return the combined
	 */
	public double getCombined() {
		return combined;
	}
	
	/**
	 * Return the time in millis;
	 * @return The time in millis;
	 */
	public long getTime(){
		return this.timeInMillis;
	}

	/**
	 * @return the frequency
	 */
	public Frequency getFrequency() {
		return frequency;
	}
}