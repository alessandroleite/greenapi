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
package monitorapi.core.model;

import static com.google.common.base.Preconditions.checkNotNull;
import static monitorapi.util.Strings.NEW_LINE;
import static monitorapi.util.Strings.TAB;

import java.util.Arrays;

public class CpuSocket implements Resource {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -5833667144349940902L;

	/**
	 * Vendor name
	 */
	private final String vendor;

	/**
	 * CPU model
	 */
	private final String model;

	/**
	 * Number of Mega-Hertz
	 */
	private final int mhz;

	/**
	 * Cores available in this socket.
	 */
	private final Cores cores;

	/**
	 * The frequencies available.
	 */
	private final Frequency[] availableFrequencies;

	/**
	 * The cache size;
	 */
	private final long cacheSize;

	/**
	 * The cpu state.
	 */
	private CpuSocketState state;

	/**
	 * 
	 * @param vendor
	 * @param model
	 * @param mhz
	 * @param cacheSize
	 * @param frequencies
	 */
	public CpuSocket(String vendor, String model, int mhz, long cacheSize, Frequency... frequencies) {

		this.vendor = vendor;
		this.model = model;
		this.mhz = mhz;
		this.cacheSize = cacheSize;

		this.availableFrequencies = (frequencies == null ? new Frequency[] { new Frequency(new Long(mhz)) } : frequencies);
		Arrays.sort(this.availableFrequencies);
		this.cores = new Cores(this);
	}

	/**
	 * Add a given CPU (core) to this {@link CpuSocket}.
	 * 
	 * @param cpu The CPU (core) to be added.
	 */
	public void add(Cpu cpu) {
		this.cores().add(checkNotNull(cpu));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (cacheSize ^ (cacheSize >>> 32));
		result = prime * result + cores.size();
		result = prime * result + mhz;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CpuSocket)) {
			return false;
		}

		CpuSocket other = (CpuSocket) obj;
		if (cacheSize != other.cacheSize) {
			return false;
		}

		if (!cores.equals(other.cores)) {
			return false;
		}

		if (mhz != other.mhz) {
			return false;
		}

		if (model == null) {
			if (other.model != null) {
				return false;
			}
		} else if (!model.equals(other.model)) {
			return false;
		}

		if (vendor == null) {
			if (other.vendor != null) {
				return false;
			}
		} else if (!vendor.equals(other.vendor)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Vendor......:").append(this.vendor()).append(NEW_LINE);
		builder.append("Model.......:").append(this.model()).append("-").append(this.mhz()).append(NEW_LINE);
		builder.append("Frequency...:").append(this.frequency().inMhz()).append(" Mhz").append(NEW_LINE);
		builder.append("Cores.......:\n[").append(NEW_LINE);

		for (Cpu cpu : this.cores().get()) {
			builder.append(TAB).append(cpu).append(NEW_LINE);
		}

		builder.append("]").append(NEW_LINE);

		return builder.toString();
	}

	/**
	 * Returns the current frequency.
	 * 
	 * @return The current frequency.
	 */
	public Frequency frequency() {
		synchronized (this) {
			return (this.state() == null || this.state().frequency() == null) ? Frequency.NULL_FREQUENCY : this.state().frequency();
		}
	}

	/**
	 * @return the vendor
	 */
	public String vendor() {
		return vendor;
	}

	/**
	 * @return the model
	 */
	public String model() {
		return model;
	}

	/**
	 * @return the mhz
	 */
	public int mhz() {
		return mhz;
	}

	/**
	 * @return the cores
	 */
	public Cores cores() {
		return cores;
	}

	/**
	 * @return the cacheSize
	 */
	public long cacheSize() {
		return cacheSize;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Sensor<?, Data<?>>[] sensors() {
		//return new Sensor[] { new CpuSocketSensor(this) };
		//TODO
		return null;
	}

	/**
	 * Returns the combined workload of the {@link Cores} of this
	 * {@link CpuSocket}.
	 * 
	 * @return The combined workload of the {@link Cores} of this
	 *         {@link CpuSocket}.
	 */
	public Double getCombinedLoad() {

		double sum = 0.0D;
		for (Cpu cpu : cores()) {
			sum += cpu.getLoad();
		}

		return (sum / cores().size());
	}

	/**
	 * 
	 * @param state
	 *            the new state of this {@link CpuSocket}.
	 */
	public CpuSocketState setState(CpuSocketState state) {
		synchronized (this) {
			final CpuSocketState previousSocketState = this.state;
			this.state = state;
			return previousSocketState;
		}
	}

	/**
	 * 
	 * @return
	 */
	public CpuSocketState state() {
		return this.state;
	}

	/**
	 * @return the available frequencies.
	 */
	public Frequency[] availableFrequencies() {
		return availableFrequencies;
	}

	/**
	 * @return
	 */
	public String description() {
		return this.vendor() + " " + this.model() + "-" + this.mhz();
	}
}