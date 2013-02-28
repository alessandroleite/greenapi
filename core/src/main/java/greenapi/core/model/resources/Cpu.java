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
package greenapi.core.model.resources;

import greenapi.core.model.data.CpuState;
import greenapi.core.model.data.Data;
import greenapi.core.model.data.Temperature;
import greenapi.core.model.sensors.Sensor;

import java.util.LinkedList;
import java.util.List;



public class Cpu implements Resource, Comparable<Cpu> {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 7738476533366442479L;
	
	private volatile CpuSocket cpuSocket;
	private final String name;
	private final double irq;
	private final double softIrq;
	private final double stole;

	private CpuState state;
	private Temperature temperature;
	
	private final List<Sensor<?, Data<?>>> sensors = new LinkedList<>();

	public Cpu(String name, double irq, double softIrq, double stole) {
		this.name = name;
		this.irq = irq;
		this.softIrq = softIrq;
		this.stole = stole;
	}

	public Cpu(CpuSocket socket, String name, double irq, double softIrq, double stole) {
		this(name, irq, softIrq, stole);
		this.cpuSocket = socket;
	}
		
	public Cpu(String name, double irq, double softIrq,
			double stole, CpuState state, Temperature temperature) {
		this(name, irq, softIrq, stole);
		this.temperature = temperature;
		this.state = state;
	}
	
	public Cpu(CpuSocket socket, String name, double irq, double softIrq,
			double stole, CpuState state, Temperature temperature) {
		this(name, irq, softIrq, stole, state, temperature);
		this.cpuSocket = cpuSocket;
	}


	/**
	 * Returns CPU id.
	 * 
	 * @return this CPU id.
	 */
	public Integer id() {
		return Integer.parseInt(this.name().replaceAll("\\D", ""));
	}

	/**
	 * Return the load state of the CPU.
	 * 
	 * @return the load state of the CPU
	 */
	public double load() {
		return this.state().getCombined() * 100.0D;
	}

	@Override
	public int compareTo(Cpu o) {
		return this.name.compareTo(o.name);
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;

		result = prime * result
				+ ((cpuSocket == null) ? 0 : cpuSocket.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Cpu)) {
			return false;
		}

		Cpu other = (Cpu) obj;
		if (cpuSocket == null) {
			if (other.cpuSocket != null) {
				return false;
			}
		} else if (!cpuSocket.equals(other.cpuSocket)) {
			return false;
		}

		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "CPU " + (this.name) + " .... state: "
				+ (this.state() == null ? "UNKNOWN" : this.state())
				+ " .... temperature: "
				+ (this.temperature() == null ? "UNKNOWN" : this.temperature());
	}

	public CpuState setState(CpuState state) {
		synchronized (this) {

			CpuState old_state = this.state;
			this.state = state;
			
			return old_state;
		}
	}

	/**
	 * @param temperature
	 *            the temperature to set
	 */
	public Temperature setTemperature(Temperature temperature) {
		synchronized (this) {
			final Temperature previousTemperature = this.temperature;

			this.temperature = temperature;
			return previousTemperature;
		}
	}

	/**
	 * @return the state
	 */
	public CpuState state() {
		return state;
	}

	/**
	 * @return the temperature
	 */
	public Temperature temperature() {
		return temperature;
	}

	/**
	 * @return the cpu
	 */
	public CpuSocket cpuSocket() {
		return cpuSocket;
	}

	/**
	 * @param cpuSocket the cpuSocket to set
	 */
	public void setCpuSocket(CpuSocket cpuSocket) {
		this.cpuSocket = cpuSocket;
	}

	/**
	 * @return the name
	 */
	public String name() {
		return name;
	}

	/**
	 * @return the irq
	 */
	public double irq() {
		return irq;
	}

	/**
	 * @return the softIrq
	 */
	public double softIrq() {
		return softIrq;
	}

	/**
	 * @return the stole
	 */
	public double stole() {
		return stole;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Sensor<?, Data<?>>[] sensors() {		
		return this.sensors.toArray(new Sensor[this.sensors.size()]);
	}
}