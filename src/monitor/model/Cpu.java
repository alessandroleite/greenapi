package monitor.model;

import monitor.model.sensors.CpuTemperature;
import monitor.model.sensors.Sensor;

public class Cpu implements Resource, Comparable<Cpu> {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 2014295163151147759L;

	private final CpuSocket cpuSocket;
	private final String name;
	private final double irq;
	private final double softIrq;
	private final double stole;

	private CpuState state;
	private Temperature temperature;

	public Cpu(CpuSocket cpu, String name, double irq, double softIrq,
			double stole, CpuState state, Temperature temperature) {
		this(cpu, name, irq, softIrq, stole);

		this.temperature = temperature;
		this.state = state;
	}

	public Cpu(CpuSocket socket, String name, double irq, double softIrq,
			double stole) {
		this.cpuSocket = socket;
		this.name = name;
		this.irq = irq;
		this.softIrq = softIrq;
		this.stole = stole;
	}

	/**
	 * 
	 * @return
	 */
	public Integer id() {
		return Integer.parseInt(this.getName().replaceAll("\\D", ""));
	}

	/**
	 * Return the load state of the CPU.
	 * 
	 * @return the load state of the CPU
	 */
	public double getLoad() {
		return this.getState().getCombined() * 100.0D;
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
		return "CPU " + (this.name) + " .... " + this.getState()
				+ (this.getTemperature() != null ? this.getTemperature() : "");
	}

	public void updateState(CpuState state) {
		synchronized (this) {
			this.state = state;
		}
	}

	/**
	 * @param temperature
	 *            the temperature to set
	 */
	public void updateTemperature(Temperature temperature) {
		synchronized (this) {
			this.temperature = temperature;
		}
	}

	/**
	 * @return the state
	 */
	public CpuState getState() {
		return state;
	}

	/**
	 * @return the temperature
	 */
	public Temperature getTemperature() {
		return temperature;
	}

	/**
	 * @return the cpu
	 */
	public CpuSocket getCpuSocket() {
		return cpuSocket;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the irq
	 */
	public double getIrq() {
		return irq;
	}

	/**
	 * @return the softIrq
	 */
	public double getSoftIrq() {
		return softIrq;
	}

	/**
	 * @return the stole
	 */
	public double getStole() {
		return stole;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Sensor<?, Data<?>>[] sensors() {
		// return new Sensor[] { new CoreLoadSensor(this), new
		// CpuTemperature(this) };
		return new Sensor[] { new CpuTemperature(this) };
	}
}