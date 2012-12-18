package monitor.model;

import java.io.Serializable;

import monitor.model.sensors.Sensor;

public interface Resource extends Serializable {

	/**
	 * Returns the available sensors of this {@link Resource}.
	 * @return The available sensors of this {@link Resource}.
	 */
	Sensor<?, Data<?>>[] sensors();
}
