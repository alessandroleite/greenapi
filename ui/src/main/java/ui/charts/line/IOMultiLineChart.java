package monitor.ui.charts.line;

import monitor.model.Machine;
import monitor.ui.charts.LineChartPanelSupport;

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