package monitor.util.commands.linux;


import static monitor.util.IOUtils.asString;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import monitor.model.Temperature;
import monitor.util.StringTokenizer2;
import monitor.util.commands.AbstractRuntimeCommand;

public class CpuTemperatureCommand extends AbstractRuntimeCommand<Map<String, Temperature>> {
	
	private static final String LM_CPU_SENSOR_NAME = "Adapter: ISA adapter";

	public CpuTemperatureCommand() {
		super("sensors");		
	}

	@Override
	public Map<String, Temperature> parserOutput(InputStream input) {	
		final String lmOutput = asString(input);
		
		if (lmOutput == null || lmOutput.indexOf(LM_CPU_SENSOR_NAME) == -1) {
			return Collections.emptyMap();
		}

		StringTokenizer token = new StringTokenizer(lmOutput.substring(lmOutput.indexOf(LM_CPU_SENSOR_NAME)), "\n");

		Map<String, Temperature> temperatures = new HashMap<String, Temperature>();

		token.nextToken();

		while (token.hasMoreElements()) {
			StringTokenizer2 coreToken = new StringTokenizer2(
					token.nextToken(), " ");
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
}
