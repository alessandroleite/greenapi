package monitor.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import monitor.model.exception.ResourceException;

public final class IOUtils {

	private IOUtils() {
		throw new UnsupportedOperationException();
	}

	public static String asString(InputStream is) {
		InputStreamReader isr = null;
		BufferedReader bis = null;

		StringBuilder sb = new StringBuilder();

		try {
			bis = new BufferedReader(isr = new InputStreamReader(is, "UTF-8"));

			int i;
			while ((i = bis.read()) != -1) {
				sb.append((char) i);
			}
		} catch (IOException exception) {
			throw new ResourceException(exception);
		} finally {
			closeQuietly(isr);
			closeQuietly(bis);
		}

		return sb.toString();
	}

	public static void closeQuietly(InputStream is) {

		if (is != null) {
			try {
				is.close();
			} catch (IOException ignore) {
			}
		}
	}

	public static void closeQuietly(Reader resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException ignore) {
			}
		}
	}
}
