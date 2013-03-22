/**
 * Copyright (c) 2012 GreenI2R
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
package greenapi.ui.panels;

import static greenapi.ui.util.ComponentsUtil.createPainelContainer;

import greenapi.core.model.data.Frequency;
import greenapi.core.model.resources.CpuSocket;
import greenapi.ui.charts.line.CpuFrequencyLineChart;
import greenapi.ui.charts.line.TemperatureMultiCoreLineChart;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class CpuFrequencyPanel extends JPanel {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -988817802082379338L;

	private final CpuSocket socket;

	public CpuFrequencyPanel(CpuSocket socket) {
		this.socket = socket;
		setLayout(new BoxLayout(this, 1));
		createCpuFrequencyLineChartPanel();
		createCpuTemperatureLineChartPanel();
		this.createFrequencyPanel();		
	}

	private void createCpuFrequencyLineChartPanel() {
		CpuFrequencyLineChart frequencyLineChart = new CpuFrequencyLineChart(this.socket);
		add(createPainelContainer("CPU/cores Frequency", frequencyLineChart));
	}
	
	private void createCpuTemperatureLineChartPanel(){
		new TemperatureMultiCoreLineChart(this.socket);
		add(createPainelContainer("CPUs/cores Temperature", new TemperatureMultiCoreLineChart(this.socket)));
	}

	private void createFrequencyPanel() {
		final Frequency[] frequencies = this.socket.availableFrequencies();

		if (frequencies.length > 1) {			
			final JComboBox<Frequency> combo = new JComboBox<Frequency>(frequencies);			
			combo.setSelectedIndex(Arrays.binarySearch(frequencies, this.socket.frequency()));
			combo.setSelectedItem(this.socket.frequency());
			
			JButton apply = new JButton("Apply...");
			apply.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Frequency newFrequency = frequencies[combo.getSelectedIndex()];
					//FIXME
					Boolean changed = false; //CommandFactory.instance().setCpuFrequency(newFrequency).execute();					
					if (changed != null && changed)
					{
						JOptionPane.showMessageDialog(CpuFrequencyPanel.this, "New CPU frequency:" + newFrequency);
					}
				}
			});
			
			JPanel panel = new JPanel(new GridLayout());
			panel.add(combo);
			panel.add(apply);
			
			this.add(createPainelContainer("Frequency", panel));
		}
	}
}