/**
 * Copyright (c) 2012 GreenI2R
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

import static greenapi.core.common.base.Strings.format;
import greenapi.core.model.resources.Cpu;

public class CpuState implements Data<CpuState> {

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
	public CpuState value() {		
		return this;
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