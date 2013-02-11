package monitor.util;

import static com.google.common.base.Preconditions.checkNotNull;
import monitor.model.exception.MonitoringException;

public final class ClassUtils {

	private ClassUtils() {
		throw new UnsupportedOperationException();
	}

	public static ClassLoader getDefaultClassLoader() {
		ClassLoader cl = null;
		try {
			cl = Thread.currentThread().getContextClassLoader();
		} catch (Throwable ignore) {
		}
		if (cl == null) {
			cl = ClassUtils.class.getClassLoader();
		}
		return cl;
	}

	/**
	 * Create a instance of a given class.
	 * 
	 * @param fullClassName
	 *            The name of the class to be instantiate.
	 * @return An instance of the given class.
	 * @throws MonitoringException
	 *             If the given class doesn't exist or in case of any other
	 *             exception.
	 */
	public static Object newInstanceForName(String fullClassName) {
		try {
			return Class.forName(checkNotNull(fullClassName)).newInstance();
		} catch (Exception exception) {
			throw new MonitoringException(exception);
		}
	}
}