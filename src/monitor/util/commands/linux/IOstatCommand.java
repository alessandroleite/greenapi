package monitor.util.commands.linux;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.*;

import monitor.model.IOStat;
import monitor.model.IOStatProperty;
import monitor.model.IOStats;
import monitor.model.Machine;
import monitor.util.IOUtils;
import monitor.util.Strings;
import monitor.util.commands.AbstractRuntimeCommand;

public class IOstatCommand extends AbstractRuntimeCommand<IOStats> {
	
	private final Machine machine;

	public IOstatCommand(Machine machine) {
		super("bash", "-c", "iostat ALL -x");
		this.machine = checkNotNull(machine);
	}

	@Override
	public IOStats parserOutput(InputStream is) {
		final String output = IOUtils.asString(is);
		
		String[] devices_measures = output.split(Strings.NEW_LINE);
		
		if (devices_measures != null && devices_measures.length > 0) {
			//Device:         rrqm/s   wrqm/s     r/s     w/s    rkB/s    wkB/s avgrq-sz avgqu-sz   await r_await w_await  svctm  %util
			
			List<IOStat> measures = new ArrayList<IOStat>();			
			for(Integer i = 6, j = 0; i < devices_measures.length; i++, j = 0) {
				String[] fields = devices_measures[i].trim().split(" ");
				
				IOStat stat = new IOStat(machine, fields[j++]);
				stat.add(new IOStatProperty("rrqm/s", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)])));
				stat.add(new IOStatProperty("wrqm/s", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)])));
				stat.add(new IOStatProperty("r/s", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)])));
				stat.add(new IOStatProperty("w/s", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)])));
				stat.add(new IOStatProperty("rkB/s", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)])));
				stat.add(new IOStatProperty("wkB/s", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)])));
				stat.add(new IOStatProperty("avgrq-sz", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)])));
				stat.add(new IOStatProperty("avgqu-sz", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)])));
				stat.add(new IOStatProperty("await", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)])));
				stat.add(new IOStatProperty("r_await", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)])));
				stat.add(new IOStatProperty("w_await", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)])));
				stat.add(new IOStatProperty("svctm", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)])));
				stat.add(new IOStatProperty("%util", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)])));
				
				measures.add(stat);
			}
			return new IOStats(measures);
		}
		
		return new IOStats(new IOStat[0]);
	}
	
	private int nextFieldValue(String[] fields, Integer startFrom) {
		for (int i = startFrom; i < fields.length; i++) {
			if (!fields[i].trim().isEmpty()) {
				return i;
			}
		}
		return startFrom;
	}
}