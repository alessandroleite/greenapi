package monitor.ui.charts.line;

import monitor.model.Machine;
import monitor.ui.charts.ChartPanelSupport;
import monitor.ui.charts.LineChartPanelSupport;
import monitor.ui.charts.data.xy.TranslatingXYDataset;

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