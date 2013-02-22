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
package monitorapi.ui.charts.line;

import monitorapi.core.model.resource.Machine;
import monitorapi.ui.charts.ChartPanelSupport;
import monitorapi.ui.charts.LineChartPanelSupport;
import monitorapi.ui.charts.data.xy.TranslatingXYDataset;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;

public class SystemLoadLineChart extends LineChartPanelSupport<Machine> {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 6966886887275583838L;

	public SystemLoadLineChart(Machine machine) {
		super("System Load", "Load", machine, ChartPanelSupport.DEFAULT_DELAY);

		setRangeAxisRange(0, 150);
		setDefaultSeriesPaint();

		createSeries();
	}

	@Override
	protected void createSeries() {

		if (this.getSeries().size() < 1) {
			this.getTimeSeries().addSeries(new TimeSeries("Combined CPUs: " + this.getResource().cpus()[0].cores().size()));
			this.setDataset(new TranslatingXYDataset(this.getTimeSeries()));
		}
	}

	@Override
	public void update() {
		if (this.getSeries().size() < 1) {
			return;
		}

		((TimeSeries) getSeries().get(0)).add(new Millisecond(), this.getResource().combinedCpuLoad());
	}
}