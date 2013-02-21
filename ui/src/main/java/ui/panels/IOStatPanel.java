package monitor.ui.panels;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import monitor.model.Machine;

public class IOStatPanel extends JPanel {
	
	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -2391092710222885802L;
	private final Machine machine;
	
	public IOStatPanel(Machine machine){
		this.machine = machine;
		setLayout(new BoxLayout(this, 1));
		this.createStatPanels();
	}
	
	
	private void createStatPanels() {
		/*int i = 0;
		for (IOStat stat: this.machine.ioStats()) {
			if (++i > 10)
				this.add(createPainelContainer(stat.device(), new IOMultiLineChart(stat)));
			
		}*/		
	}
}