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
package greenapi.core.common.base;

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