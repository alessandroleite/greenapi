package monitor.ui.charts.line;

import monitor.model.Cpu;
import monitor.model.CpuSocket;
import monitor.ui.charts.LineChartPanelSupport;
import monitor.ui.charts.data.xy.TranslatingXYDataset;

import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;

public class TemperatureMultiCoreLineChart extends
		LineChartPanelSupport<CpuSocket> {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 4816098286311764566L;
	
	public TemperatureMultiCoreLineChart(CpuSocket cpuSocket) {
		this(cpuSocket.description(), "CPU/Core Temperature (°C)", cpuSocket);
	}

	public TemperatureMultiCoreLineChart(String title, String axisLabel,
			CpuSocket resource) {
		this(title, axisLabel, resource, DEFAULT_DELAY);
	}

	public TemperatureMultiCoreLineChart(String title, String axisLabel,
			CpuSocket cpuSocket, int updateIntervalInSeconds) {

		super(title, axisLabel, cpuSocket, updateIntervalInSeconds);

		setRangeAxisRange(0, 100);
	}

	@Override
	protected void createSeries() {
		if (this.getSeries().size() < getResource().cores().size()) {
			for (Cpu cpu : getResource().cores()) {
				this.getTimeSeries().addSeries(
						new TimeSeries(String.format("CPU %s", cpu.getName())));
			}
			this.setDataset(new TranslatingXYDataset(this.getTimeSeries()));
		}		
	}

	@Override
	public void update() {
		if (this.getSeries().size() < getResource().cores().size()) {
			return;
		}

		int i = 0;
		for (Cpu cpu : getResource().cores()) {
			((TimeSeries) getSeries().get(i++)).add(new Millisecond(), cpu
					.getTemperature().value());
		}
	}
}