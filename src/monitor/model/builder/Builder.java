package monitor.model.builder;

import monitor.model.Resource;
import monitor.model.exception.ResourceException;

public interface Builder<T extends Resource> {

	/**
	 * Fetch a resource
	 * 
	 * @return The instance of a {@link Resource}
	 * @throws ResourceException
	 *             Throws if fetch of the resource is not possible.
	 */
	T fetch() throws ResourceException;
}
