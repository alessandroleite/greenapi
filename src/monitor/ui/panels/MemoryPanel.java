package monitor.ui.panels;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import monitor.model.Memory;
import monitor.ui.charts.line.SystemMemoryLineChart;

public class MemoryPanel extends JPanel {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -6616785096856962739L;

	public MemoryPanel(Memory memory){
		setLayout(new BoxLayout(this, 1));
		SystemMemoryLineChart memoryLineChart = new SystemMemoryLineChart(memory);
		memoryLineChart.setSize(new Dimension(500,100));
		this.add(memoryLineChart);
	}
}
