/**
 * Copyright (c) 2012 I2RGreen
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
import greenapi.ui.charts.ChartPanelSupport;
import greenapi.ui.charts.LineChartPanelSupport;
import greenapi.ui.charts.data.xy.TranslatingXYDataset;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;

public class CoreLineChart extends LineChartPanelSupport<Cpu> {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 6519924654258093340L;

	public CoreLineChart(Cpu cpu) {
		super("", "", cpu, ChartPanelSupport.DEFAULT_DELAY);
	}

	@Override
	protected void createSeries() {
		if (this.getSeries().size() < 1) {
			this.getTimeSeries().addSeries(
					new TimeSeries("CPU " + getResource().name() + " "
							+ this.getResource().cpuSocket().vendor() + " "
							+ this.getResource().cpuSocket().model()));
			this.setDataset(new TranslatingXYDataset(this.getTimeSeries()));
		}
	}

	@Override
	public void update() {

		if (this.getSeries().size() < 1) {
			this.createSeries();
			return;
		}

		((TimeSeries) getSeries().get(0)).add(new Millisecond(),
				this.getResource().load());
	}
}