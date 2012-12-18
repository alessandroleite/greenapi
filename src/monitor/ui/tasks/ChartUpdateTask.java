package monitor.ui.tasks;

import monitor.model.Resource;
import monitor.ui.charts.ChartPanelSupport;

public class ChartUpdateTask<T extends Resource> implements Runnable {

	private final ChartPanelSupport<T> panel;

	public ChartUpdateTask(ChartPanelSupport<T> panel) {
		this.panel = panel;
	}

	@Override
	public void run() {
		this.panel.update();
	}	
}