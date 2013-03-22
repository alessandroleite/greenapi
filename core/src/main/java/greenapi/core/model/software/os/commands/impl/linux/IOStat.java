/**
 * Copyright (c) 2012 GreenI2R
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
package greenapi.core.model.software.os.commands.impl.linux;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import greenapi.core.common.base.Strings;
import greenapi.core.model.data.IOStatProperty;
import greenapi.core.model.data.IOStats;
import greenapi.core.model.software.os.commands.Argument;

public class IOStat extends LinuxCommandSupport<IOStats>
{
    
    /**
     * All fields of the IOStat command.
     */
    private static final String [] FIELDS = {"rrqm/s", "wrqm/s", "r/s", "w/s", "rkB/s", 
        "wkB/s", "avgrq-sz", "avgqu-sz", "await", "r_await", "w_await", "svctm", "%util"};

    /**
     * Default constructor. This command requires root user
     */
    public IOStat()
    {
        super(true, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected IOStats parser(String result, InputStream source) throws IOException
    {
        String[] devicesMeasures = result.split(Strings.NEW_LINE);

        if (devicesMeasures != null && devicesMeasures.length > 0)
        {
            // Device: rrqm/s wrqm/s r/s w/s rkB/s wkB/s avgrq-sz avgqu-sz await r_await w_await svctm %util

            List<greenapi.core.model.data.IOStat> measures = new ArrayList<greenapi.core.model.data.IOStat>();
            int j = 0;
            for (Integer i = 6; i < devicesMeasures.length; i++, j = 0)
            {
                String[] fields = devicesMeasures[i].trim().split(" ");
                greenapi.core.model.data.IOStat stat = new greenapi.core.model.data.IOStat(fields[j++]);

                for (int k = 0; k < FIELDS.length; k++)
                {
                    j = nextFieldValue(fields, ++j);
                    // stat.add(new IOStatProperty(FIELDS[k], Double.parseDouble(fields[j = nextFieldValue(fields, ++j)]), stat));
                    stat.add(new IOStatProperty(FIELDS[k], Double.parseDouble(fields[j]), stat));
                }
                measures.add(stat);
            }
            return new IOStats(measures);
        }

        return new IOStats(new greenapi.core.model.data.IOStat[0]);
    }

    /**
     * Search the next no blank value in the array starting from the given index and returns its index.
     * 
     * @param fields
     *            The fields of the I/O stat command.
     * @param startFrom
     *            The index to start the search.
     * @return The index of the first no empty value of the array from the given start index.
     */
    private int nextFieldValue(String[] fields, Integer startFrom)
    {
        for (int i = startFrom; i < fields.length; i++)
        {
            if (!fields[i].trim().isEmpty())
            {
                return i;
            }
        }
        return startFrom;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] commandLine(Argument... args)
    {
        return new String[] {"iostat", "ALL", "-x"};
    }
}
