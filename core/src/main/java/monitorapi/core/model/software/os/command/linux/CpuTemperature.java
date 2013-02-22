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
package monitorapi.core.model.software.os.command.linux;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import monitorapi.core.model.Temperature;
import monitorapi.util.StringTokenizer2;

public class CpuTemperature extends
		LinuxCommandSupport<Map<String, Temperature>> {

	private static final String LM_CPU_SENSOR_NAME = "Adapter: ISA adapter";

	public CpuTemperature() {
		super(false, false);
	}

	@Override
	protected Map<String, Temperature> parser(String result, InputStream source)
			throws IOException {

		if (result == null || result.indexOf(LM_CPU_SENSOR_NAME) == -1) {
			return Collections.emptyMap();
		}

		StringTokenizer token = new StringTokenizer(result.substring(result.indexOf(LM_CPU_SENSOR_NAME)), "\n");

		Map<String, Temperature> temperatures = new HashMap<String, Temperature>();

		token.nextToken();

		while (token.hasMoreElements()) {
			StringTokenizer2 coreToken = new StringTokenizer2(token.nextToken(), " ");
			String[] tokens = coreToken.tokenAsArray();

			if (tokens.length > 0) {
				// core id: +value ()
				temperatures.put(
						tokens[1].substring(0, tokens[1].indexOf(":")),
						new Temperature(parserTemperature(tokens[2]), 0, 0));
			}
		}
		return temperatures;
	}

	private double parserTemperature(String value) {
		return Double.valueOf(value.replaceAll("°C", ""));
	}

	@Override
	public String commandLine() {
		return "sensors";
	}
}