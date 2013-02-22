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
package monitorapi.core.model.software.os.command.impl.linux;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import monitorapi.core.common.base.Strings;
import monitorapi.core.model.data.IOStatProperty;
import monitorapi.core.model.data.IOStats;

public class IOStat extends LinuxCommandSupport<IOStats> {
	
	public IOStat() {
		super(true, true);
	}

	@Override
	protected IOStats parser(String result, InputStream source) throws IOException {
		String[] devices_measures = result.split(Strings.NEW_LINE);
		
		if (devices_measures != null && devices_measures.length > 0) {
			//Device:         rrqm/s   wrqm/s     r/s     w/s    rkB/s    wkB/s avgrq-sz avgqu-sz   await r_await w_await  svctm  %util
			
			List<monitorapi.core.model.data.IOStat> measures = new ArrayList<monitorapi.core.model.data.IOStat>();			
			for(Integer i = 6, j = 0; i < devices_measures.length; i++, j = 0) {
				String[] fields = devices_measures[i].trim().split(" ");
				
				monitorapi.core.model.data.IOStat stat = new monitorapi.core.model.data.IOStat(fields[j++]);
				stat.add(new IOStatProperty("rrqm/s", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)]),stat));
				stat.add(new IOStatProperty("wrqm/s", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)]),stat));
				stat.add(new IOStatProperty("r/s", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)]),stat));
				stat.add(new IOStatProperty("w/s", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)]),stat));
				stat.add(new IOStatProperty("rkB/s", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)]),stat));
				stat.add(new IOStatProperty("wkB/s", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)]),stat));
				stat.add(new IOStatProperty("avgrq-sz", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)]),stat));
				stat.add(new IOStatProperty("avgqu-sz", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)]),stat));
				stat.add(new IOStatProperty("await", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)]),stat));
				stat.add(new IOStatProperty("r_await", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)]),stat));
				stat.add(new IOStatProperty("w_await", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)]),stat));
				stat.add(new IOStatProperty("svctm", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)]),stat));
				stat.add(new IOStatProperty("%util", Double.parseDouble(fields[j = nextFieldValue(fields, ++j)]),stat));
				
				measures.add(stat);
			}
			return new IOStats(measures);
		}
		
		return new IOStats(new monitorapi.core.model.data.IOStat[0]);
	}
	
	private int nextFieldValue(String[] fields, Integer startFrom) {
		for (int i = startFrom; i < fields.length; i++) {
			if (!fields[i].trim().isEmpty()) {
				return i;
			}
		}
		return startFrom;
	}

	@Override
	public String commandLine() {
		return "iostat ALL -x";
	}
}