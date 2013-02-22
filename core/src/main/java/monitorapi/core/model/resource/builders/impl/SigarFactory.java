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
package monitorapi.core.model.resource.builders.impl;

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