package monitor.model.sensors;

import monitor.model.Data;

/**
 * 
 * @author alessandro
 * 
 * @param <E>
 * @param <T>
 */
public interface Sensor<E, T extends Data<E>> extends Runnable {

	@SuppressWarnings("unchecked")
	Sensor<?, Data<?>>[] NULL_SENSORS = new Sensor[] {};

	/**
	 * Return the current value of this sensor.
	 * 
	 * @return The current value of this sensor.
	 */
	T collect();

	/**
	 * Returns the sensor name.
	 * 
	 * @return The sensor name.
	 */
	String name();
}