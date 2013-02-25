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
package greenapi.ui.charts.line;

import greenapi.core.model.resources.Cpu;
import greenapi.core.model.resources.CpuSocket;
import greenapi.ui.charts.LineChartPanelSupport;
import greenapi.ui.charts.data.xy.TranslatingXYDataset;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;

public class CpuLoadMultiCoreLineChart extends LineChartPanelSupport<CpuSocket> {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 4024562106719857078L;

	public CpuLoadMultiCoreLineChart(CpuSocket cpuSocket) {
		this(cpuSocket.description(), "CPU/Core Load (%)", cpuSocket);
	}

	public CpuLoadMultiCoreLineChart(String title, String axisLabel, CpuSocket cpuSocket) {
		this(title, axisLabel, cpuSocket, DEFAULT_DELAY);
	}

	public CpuLoadMultiCoreLineChart(String title, String axisLabel,
			CpuSocket cpuSocket, int updateIntervalInSeconds) {

		super(title, axisLabel, cpuSocket, updateIntervalInSeconds);

		createSeries();
		setRangeAxisRange(0, 100);
	}

	@Override
	protected void createSeries() {
		if (this.getSeries().size() < this.getResource().cores().size()) {
			for (Cpu cpu : this.getResource().cores()) {
				this.getTimeSeries().addSeries(new TimeSeries(String.format("CPU %s", cpu.name())));
			}
			this.setDataset(new TranslatingXYDataset(this.getTimeSeries()));
		}
	}

	@Override
	public void update() {

		if (this.getSeries().size() < this.getResource().cores().size()) {
			return;
		}

		int i = 0;
		for (Cpu cpu : this.getResource().cores()) {
			((TimeSeries) getSeries().get(i++)).add(new Millisecond(), cpu.state().getUser() * 100);
		}
	}
}
