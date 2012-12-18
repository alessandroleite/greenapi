package monitor.ui.charts.line;

import monitor.model.Memory;
import monitor.model.MemoryType;
import monitor.ui.charts.ChartPanelSupport;
import monitor.ui.charts.LineChartPanelSupport;
import monitor.ui.charts.data.xy.TranslatingXYDataset;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;

public class SystemMemoryLineChart extends LineChartPanelSupport<Memory> {

	/**
	 * Serial code version <code>serialVersionUID<code>
	 */
	private static final long serialVersionUID = -7448536632871042915L;

	public SystemMemoryLineChart(Memory memory) {
		super(memory.description(),				
				"Memory Usage (%)", memory, ChartPanelSupport.DEFAULT_DELAY);
		getPlot().getRangeAxis().setRange(0.0d, 100d);
	}

	@Override
	protected void createSeries() {
		if (this.getSeries().size() < 1) {
			this.getTimeSeries().addSeries(new TimeSeries(MemoryType.RAM));
			this.setDataset(new TranslatingXYDataset(this.getTimeSeries()));
		}
	}

	@Override
	public void update() {

		if (this.getSeries().size() < 1) {
			return;
		}

		((TimeSeries) this.getSeries().get(0)).add(new Millisecond(),
				getResource().state().value());
	}
}