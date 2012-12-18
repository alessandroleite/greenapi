package monitor.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Properties;

import static com.google.common.base.Preconditions.*;

public final class PropertiesUtils {

	private PropertiesUtils() {
		throw new UnsupportedOperationException();
	}

	public static Properties load(InputStream input) throws IOException {
		Properties properties = new Properties();
		properties.load(checkNotNull(input));
		return properties;
	}

	public static Properties load(String name) throws IOException {
		return load(ClassUtils.getDefaultClassLoader().getResourceAsStream(
				checkNotNull(name)));
	}

	public static Properties loadIfAvailable(String name) {
		Properties properties = null;
		try {
			properties = load(name);
		} catch (IOException ignore) {
		}
		return properties;
	}

	public static Properties loadPropertyFile(String name)
			throws IOException {
		InputStream is = ClassUtils.getDefaultClassLoader()
				.getResourceAsStream("/properties/" + name);

		if (is == null)
			is = ClassUtils.getDefaultClassLoader().getResourceAsStream(name);

		if (is == null)
			is = ClassUtils.getDefaultClassLoader().getResourceAsStream("/" + name);

		if (is == null)
			throw new IOException("Properties file " + name + " not found in any context. ");

		return load(is);
	}

	public static void print(Properties properties, PrintStream output) {
		for (Enumeration<?> propertyNames = properties.propertyNames(); propertyNames.hasMoreElements();) {
			String propertyName = propertyNames.nextElement().toString();
			String propertyValue = properties.getProperty(propertyName);
			output.println(propertyName + "=" + propertyValue);
		}
	}

	public static void putInSystemProperty(Properties properties) {
		if (properties != null) {
			putAllIn(properties, System.getProperties());
		}
	}

	public static void putAllIn(Properties origin, Properties dest) {
		if (origin != null && dest != null) {
			for (Enumeration<?> propertyNames = origin.propertyNames(); propertyNames.hasMoreElements();) {
				String propertyName = propertyNames.nextElement().toString();
				String propertyValue = origin.getProperty(propertyName);
				dest.put(propertyName, propertyValue);
			}
		}
	}
}