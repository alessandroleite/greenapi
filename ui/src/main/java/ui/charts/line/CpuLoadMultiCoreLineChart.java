package monitor.ui.charts.line;

import monitor.model.Cpu;
import monitor.model.CpuSocket;
import monitor.ui.charts.LineChartPanelSupport;
import monitor.ui.charts.data.xy.TranslatingXYDataset;

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
				this.getTimeSeries().addSeries(new TimeSeries(String.format("CPU %s", cpu.getName())));
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
			((TimeSeries) getSeries().get(i++)).add(new Millisecond(), cpu.getState().getUser() * 100);
		}
	}
}
