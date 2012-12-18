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
	
	public static Object newInstanceForName(String name){
		try{
			return Class.forName(checkNotNull(name)).newInstance();
		}catch(Exception exception){
			throw new MonitoringException(exception);
		}
	}
}