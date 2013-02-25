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
package greenapi.core.model.software.os.command.impl.linux;

import greenapi.core.common.base.Booleans;
import greenapi.core.model.resources.net.NetworkConfiguration;
import greenapi.core.model.resources.net.NetworkInterface;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Strings;


public class NetworkInterfaceDescription extends
		LinuxCommandSupport<NetworkInterface[]> {

	public NetworkInterfaceDescription() {
		super(true, true);
	}

	@Override
	protected NetworkInterface[] parser(String result, InputStream source)
			throws IOException {
		List<NetworkInterface> cards = new LinkedList<>();
		String[] networks = result.split("*-network");

		for (String network : networks) {
			String[] attributes = network.split("\n");
			NetworkInterface ni = null;

			if (attributes.length >= 14) {
				ni = new NetworkInterface(Integer.parseInt(attributes[3]),
						attributes[0], attributes[1], attributes[2],
						attributes[4], attributes[5], attributes[6],
						attributes[7], attributes[8], attributes[9],
						attributes[10], attributes[11], attributes[12],
						getConfiguration(attributes[13]));
			}

			if (attributes.length == 15) {
				ni.setResources(attributes[14]);
			}

			if (ni != null) {
				cards.add(ni);
			}
		}
		return cards.toArray(new NetworkInterface[cards.size()]);
	}

	@Override
	public String commandLine() {
		return "lshw -class network";
	}

	private NetworkConfiguration getConfiguration(String configuration) {

		NetworkConfiguration nc = null;
		if (!Strings.isNullOrEmpty(configuration)) {
			String[] attributes = configuration.substring(
					configuration.indexOf(":") + 1).split(" ");
			int i = 0;
			nc = new NetworkConfiguration(
					Booleans.valueOf(split(attributes[i++])[1]),
					Booleans.valueOf(split(attributes[i++])[1]),
					split(attributes[i++])[1], split(attributes[i++])[1],
					split(attributes[i++])[1], split(attributes[i++])[1],
					split(attributes[i++])[1],
					Double.valueOf(split(attributes[i++])[1]),
					Booleans.valueOf(split(attributes[i++])[1]),
					Booleans.valueOf(split(attributes[i++])[1]),
					split(attributes[i++])[1], split(attributes[i++])[1]);
		}
		return nc;
	}

	private static String[] split(String value) {
		if (!Strings.isNullOrEmpty(value)) {
			return value.split("=");
		}
		return new String[0];
	}
}