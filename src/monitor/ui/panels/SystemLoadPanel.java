package monitor.ui.panels;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import monitor.model.Cpu;
import monitor.model.Machine;
import monitor.ui.charts.line.CoreLineChart;
import monitor.ui.charts.line.CpuLoadMultiCoreLineChart;
import monitor.ui.charts.line.SystemLoadLineChart;

public class SystemLoadPanel extends JPanel {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 3578670382513845121L;
	
	private static final Color[] CORE_COLORS = new Color[] { Color.BLUE, Color.GREEN, Color.ORANGE, Color.YELLOW };

	private static final int MAXIMUM_ITEM_AGE = 10000;
	
	private final Machine machine;

	public SystemLoadPanel(Machine machine) {
		this.machine = machine;
		setLayout(new BoxLayout(this, 1));
		createSystemLoadPanel();

		createMultiCorePanel();
		createOnePanelForEachCpuCore();
	}

	private void createSystemLoadPanel() {
		SystemLoadLineChart systemChart = new SystemLoadLineChart(this.machine);
		systemChart.cleanChart();
		//systemChart.fakeEvents(300);
		systemChart.setMaximumItemAge(MAXIMUM_ITEM_AGE, true);

		add(createPainelContainer("Combined Load", systemChart));
	}

	private JPanel createPainelContainer(String title, JPanel... panels) {
		final JPanel container = new JPanel();
		container.setBorder(BorderFactory.createTitledBorder(title));
		container.setLayout(new BoxLayout(container, 0));

		for (JPanel panel : panels) {
			container.add(panel);
		}

		return container;
	}

	private void createMultiCorePanel() {
		CpuLoadMultiCoreLineChart multiCoreChart = new CpuLoadMultiCoreLineChart(this.machine.cpus()[0]);
		
		multiCoreChart.cleanChart();
		multiCoreChart.setDefaultSeriesPaint();
		multiCoreChart.setSeriesPaint(0, CORE_COLORS[0]);
		multiCoreChart.setSeriesPaint(1, CORE_COLORS[1]);
		multiCoreChart.setSeriesPaint(2, CORE_COLORS[2]);
		multiCoreChart.setSeriesPaint(3, CORE_COLORS[3]);
		multiCoreChart.setMaximumItemAge(MAXIMUM_ITEM_AGE, true);
		//multiCoreChart.fakeEvents(300);

		add(this.createPainelContainer("All Cores", multiCoreChart));
	}
	
	private void createOnePanelForEachCpuCore() {
		JPanel corePanel = new JPanel();
		corePanel.setLayout(new BoxLayout(corePanel, 0));

		int i = 0;
		for (Cpu cpu : this.machine.cpus()[0].getCores()) {
			CoreLineChart coreChart = new CoreLineChart(cpu);
			coreChart.setDefaultSeriesPaint();
			coreChart.cleanChart();
			coreChart.setSeriesPaint(i, CORE_COLORS[i++]);
			coreChart.setMaximumItemAge(MAXIMUM_ITEM_AGE, true);

			corePanel.add(this.createPainelContainer("Core " + i + " (" + cpu.getName() + ")", coreChart));
		}		
		add(corePanel);
	}
}
