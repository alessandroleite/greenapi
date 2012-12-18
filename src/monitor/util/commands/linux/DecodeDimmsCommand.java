package monitor.util.commands.linux;

import static monitor.util.IOUtils.asString;
import static monitor.util.Strings.NEW_LINE;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import monitor.util.commands.AbstractRuntimeCommand;

public class DecodeDimmsCommand extends
		AbstractRuntimeCommand<Map<Integer, Map<String, String>>> {

	public DecodeDimmsCommand() {
		super("decode-dimms");
	}

	@Override
	public Map<Integer, Map<String, String>> parserOutput(InputStream input) {
		Map<Integer, Map<String, String>> decode = new HashMap<Integer, Map<String, String>>();
		final String output = asString(input);

		String[] banks = output.split("Decoding EEPROM:");
		if (banks != null && banks.length > 1) {
			for (int i = 1; i < banks.length; i++) {
				decode.put(i, this.decodeMemoryCard(banks[i]));
			}
		}
		return Collections.unmodifiableMap(decode);
	}

	private Map<String, String> decodeMemoryCard(final String memory_bank) {
		Map<String, String> data = new HashMap<String, String>();

		String[] lines = asArray(new StringTokenizer(memory_bank, NEW_LINE));
		String lkey = null;
		for (int i = 0; i < lines.length; i++) {
			if (lines[i] != null && !lines[i].trim().isEmpty() && !lines[i].startsWith("---")) {
				StringBuilder key = new StringBuilder();
				char[] chars = lines[i].toCharArray();
				parts: for (int j = 0; j < chars.length; j++) {
					if (Character.isWhitespace(chars[j]) &&  (j + 1 < chars.length && Character.isWhitespace(chars[j + 1]))) {
						if (!key.toString().trim().isEmpty()) {
							data.put(lkey = key.toString().trim(), lines[i].substring(j).trim());							
						} else {
							data.put(lkey, data.get(lkey).concat(NEW_LINE + lines[i].substring(j).trim()));
						}
						break parts;
					}
					key.append(chars[j]);
				}
			}
		}
		return Collections.unmodifiableMap(data);
	}

	private String[] asArray(StringTokenizer tokenizer) {
		List<String> tokens = new ArrayList<String>();
		
		while (tokenizer.hasMoreElements()) {
			tokens.add((String)tokenizer.nextElement());
		}		
		
		return tokens.toArray(new String[tokens.size()]);
	}
}