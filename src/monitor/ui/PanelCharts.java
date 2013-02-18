package monitor.ui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import monitor.model.Machine;
import monitor.ui.charts.line.CpuFrequencyLineChart;
import monitor.ui.charts.line.CpuLoadMultiCoreLineChart;
import monitor.ui.charts.line.SystemMemoryLineChart;
import monitor.ui.charts.line.TemperatureMultiCoreLineChart;
import monitor.ui.panels.CpuFrequencyPanel;

import com.google.common.base.Preconditions;

public class PanelCharts extends JFrame {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 802444743246742398L;

	private final JTabbedPane tabbedPane;
	
	private final Machine machine;
	
	private final List<JPanel> panels = new ArrayList<JPanel>();
	
	public PanelCharts(Machine machine){
		this.machine = Preconditions.checkNotNull(machine);
		this.tabbedPane = new JTabbedPane();
		this.addContent();
	}

	protected void addContent() {
		
		this.addPanel(new CpuLoadMultiCoreLineChart(machine.cpus()[0]));
		this.addPanel(new TemperatureMultiCoreLineChart(machine.cpus()[0]));
		this.addPanel(new CpuFrequencyLineChart(machine.cpus()[0]));
		this.addPanel(new SystemMemoryLineChart(machine.memory()));
		this.addPanel(new CpuFrequencyPanel(machine.cpus()[0]));
		//this.addPanel(new IOStatPanel(machine));
		//this.addPanel(new SystemLoadPanel(machine));
		
		this.tabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
		getContentPane().add(this.tabbedPane);

		for (JPanel panel : this.getPanels()) {
			panel.setVisible(true);
			this.tabbedPane.add(panel, panel.getClass().getSimpleName());
		}
		setPreferredSize(new Dimension(1024, 768));
		
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void addPanel(JPanel panel) {
		this.panels.add(panel);
	}
	
	public List<JPanel> getPanels() {
		return Collections.unmodifiableList(panels);
	}
}