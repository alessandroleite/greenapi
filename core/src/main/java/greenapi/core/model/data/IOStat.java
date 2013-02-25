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
package greenapi.core.model.data;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 
 * @author Alessandro
 * 
 *         Field descriptions extracted from: <a
 *         href="http://dom.as/2009/03/11/iostat/">iostat -x</a>
 */
public class IOStat implements Data<IOStat> {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 5056903635052667206L;

	/**
	 * Device name
	 */
	private final String device;


	/**
	 * Properties of the {@link IOStat};
	 */
	private final Map<String, IOStatProperty> properties = new ConcurrentHashMap<String, IOStatProperty>();

	/**
	 * 
	 * @param device
	 */
	public IOStat(String device) {
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
}