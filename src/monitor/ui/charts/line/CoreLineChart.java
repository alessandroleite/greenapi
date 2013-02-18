package monitor.ui.charts.line;

import monitor.model.Cpu;
import monitor.ui.charts.ChartPanelSupport;
import monitor.ui.charts.LineChartPanelSupport;
import monitor.ui.charts.data.xy.TranslatingXYDataset;

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
					new TimeSeries("CPU " + getResource().getName() + " "
							+ this.getResource().getCpuSocket().vendor() + " "
							+ this.getResource().getCpuSocket().model()));
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
				this.getResource().getLoad());
	}
}