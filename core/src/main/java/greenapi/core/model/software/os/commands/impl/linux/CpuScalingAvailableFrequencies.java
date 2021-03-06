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
import java.util.StringTokenizer;


import greenapi.core.common.base.StringTokenizer2;
import greenapi.core.model.data.Frequency;
import greenapi.core.model.software.os.commands.Argument;

public class CpuScalingAvailableFrequencies extends LinuxCommandSupport<Frequency[]> implements
        greenapi.core.model.software.os.commands.CpuScalingAvailableFrequencies
{

    /**
     * The Linux command to return the available CPU frequencies.
     */
    private static final String INSTRUCTION_LINE = "cat /sys/devices/system/cpu/cpu%S/cpufreq/scaling_available_frequencies";

    /**
     * Create an instance of this command.
     */
    public CpuScalingAvailableFrequencies()
    {
        super(true, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Frequency[] parser(String result, InputStream source) throws IOException
    {

        StringTokenizer tokens = new StringTokenizer2(result.trim(), " ");
        Frequency[] frequencies = new Frequency[tokens.countTokens()];

        int i = 0;
        while (tokens.hasMoreElements())
        {
            frequencies[i++] = Frequency.newFrequencyInMhz(Long.parseLong(tokens.nextToken()));
        }
        return frequencies;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] commandLine(Argument... args)
    {
        return new String[] {INSTRUCTION_LINE};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Frequency[] frequencies()
    {
        return this.result().getValue();
    }
}
