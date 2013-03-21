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

import greenapi.core.model.resources.Machine;
import greenapi.ui.charts.LineChartPanelSupport;

public class IOMultiLineChart extends LineChartPanelSupport<Machine> {

	/**
	 *  Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 6007205657683628198L;

	public IOMultiLineChart(Machine machine) {
		super("", "", machine, DEFAULT_DELAY);
		createSeries();
		setRangeAxisRange(0, 100);
	}

	@Override
	protected void createSeries() {
		
		//List<IOStat> stats = this.getResource().ioStats().value();
		
		/*Collection<IOStatProperty> properties = null;
		
		if (this.getSeries().size() < properties.size()) {
			for (IOStatProperty property : properties) {
				this.getTimeSeries().addSeries(new TimeSeries(String.format("%s", property.name())));
			}
			this.setDataset(new TranslatingXYDataset(this.getTimeSeries()));
		}*/
	}

	@Override
	public void update() {
		
		/*Collection<IOStatProperty> properties = this.getResource().properties();
		
		if (this.getSeries().size() < properties.size()) {
			return;
		}

		int i = 0;
		for (IOStatProperty property : properties) {
			((TimeSeries) getSeries().get(i++)).add(new Millisecond(), property.value());
		}*/
	}
}