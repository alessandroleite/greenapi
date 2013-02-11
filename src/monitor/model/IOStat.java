package monitor.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import monitor.model.sensors.Sensor;

/**
 * 
 * @author Alessandro
 * 
 *         Field descriptions extracted from: <a
 *         href="http://dom.as/2009/03/11/iostat/">iostat -x</a>
 */
public class IOStat implements Data<IOStat>, Resource {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 5056903635052667206L;

	/**
	 * Device name
	 */
	private final String device;

	/**
	 * 
	 */
	private final Machine machine;

	/**
	 * Properties of the {@link IOStat};
	 */
	private final Map<String, IOStatProperty> properties = new ConcurrentHashMap<String, IOStatProperty>();

	/**
	 * 
	 * @param machine
	 * @param device
	 */
	public IOStat(Machine machine, String device) {
		this.machine = machine;
		this.device = device;
	}

	@Override
	public IOStat value() {
		return this;
	}

	/**
	 * @return the device
	 */
	public String device() {
		return device;
	}

	/**
	 * @return the machine
	 */
	public Machine machine() {
		return machine;
	}

	/**
	 * 
	 * @param property
	 * @return
	 */
	public IOStatProperty add(IOStatProperty property) {
		return this.properties.put(property.name(), property);
	}

	/**
	 * 
	 * @param property
	 * @return
	 */
	public IOStatProperty remove(IOStatProperty property) {
		return this.properties.remove(property.name());
	}

	/**
	 * 
	 * @return
	 */
	public Collection<IOStatProperty> properties() {
		return Collections.unmodifiableCollection(this.properties.values());
	}

	/**
	 * Returns the {@link IOStatProperty} that has the given name.
	 * 
	 * @param name
	 *            The name of the {@link IOStatProperty} to be returned.
	 * @return The {@link IOStatProperty} or <code>null</code> if it didn't
	 *         find.
	 */
	public IOStatProperty getProperty(String name) {
		return this.properties.get(name);
	}

	@Override
	public Sensor<?, Data<?>>[] sensors() {
		return null;
	}

}