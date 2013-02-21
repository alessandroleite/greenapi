/**
 * Copyright (c) 2012 Alessandro Ferreira Leite, http://www.alessandro.cc/
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package monitorapi.ui.panels;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import monitorapi.core.model.Cpu;
import monitorapi.core.model.Machine;
import monitorapi.ui.charts.line.CoreLineChart;
import monitorapi.ui.charts.line.CpuLoadMultiCoreLineChart;
import monitorapi.ui.charts.line.SystemLoadLineChart;

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
		for (Cpu cpu : this.machine.cpus()[0].cores()) {
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
