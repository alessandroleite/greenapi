package monitor.model.builder;

import java.io.File;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarProxy;

public final class SigarFactory {

	static {
		final String path = SigarFactory.class.getName().replace('.', '/').trim();
		String full_path = SigarFactory.class.getResource("/" + path + ".class").toString();

		boolean injar = full_path.startsWith("jar:");
		if (injar) {
			String jar = full_path.substring(full_path.lastIndexOf(":") + 1, full_path.lastIndexOf("!"));
			File lib_dir = new File(jar.substring(0, jar.lastIndexOf("/") + 1));
			System.setProperty("java.library.path", System.getProperty("java.library.path") + File.pathSeparator + lib_dir.getPath());
		}
	}

	private static final Sigar SIGAR_INSTANCE = new Sigar();

	private SigarFactory() {
		throw new UnsupportedOperationException();
	}

	public static SigarProxy getInstance() {
		return SIGAR_INSTANCE;
	}
}