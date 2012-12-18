package monitor.ui.charts.line;

import java.util.Collection;

import monitor.model.IOStat;
import monitor.model.IOStatProperty;
import monitor.ui.charts.LineChartPanelSupport;
import monitor.ui.charts.data.xy.TranslatingXYDataset;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;

public class IOMultiLineChart extends LineChartPanelSupport<IOStat> {

	/**
	 *  Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 6007205657683628198L;

	public IOMultiLineChart(IOStat iostat) {
		super("", "", iostat, DEFAULT_DELAY);
		createSeries();
		setRangeAxisRange(0, 100);
	}

	@Override
	protected void createSeries() {
		
		Collection<IOStatProperty> properties = this.getResource().properties();
		
		if (this.getSeries().size() < properties.size()) {
			for (IOStatProperty property : properties) {
				this.getTimeSeries().addSeries(new TimeSeries(String.format("%s", property.name())));
			}
			this.setDataset(new TranslatingXYDataset(this.getTimeSeries()));
		}
	}

	@Override
	public void update() {
		
		Collection<IOStatProperty> properties = this.getResource().properties();
		
		if (this.getSeries().size() < properties.size()) {
			return;
		}

		int i = 0;
		for (IOStatProperty property : properties) {
			((TimeSeries) getSeries().get(i++)).add(new Millisecond(), property.value());
		}
	}
}