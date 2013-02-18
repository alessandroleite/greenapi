package monitor.ui.panels;

import static monitor.ui.util.ComponentsUtil.createPainelContainer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import monitor.model.CpuSocket;
import monitor.model.Frequency;
import monitor.ui.charts.line.CpuFrequencyLineChart;
import monitor.ui.charts.line.TemperatureMultiCoreLineChart;
import monitor.util.commands.CommandFactory;

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
					Boolean changed = CommandFactory.instance().setCpuFrequency(newFrequency).execute();					
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