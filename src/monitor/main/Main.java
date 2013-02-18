package monitor.main;

import monitor.model.Machine;
import monitor.model.builder.MachineBuilder;
import monitor.ui.PanelCharts;

public class Main {
	
	public static void main(String[] args) {
		Machine machine = MachineBuilder.build();
		new PanelCharts(machine);
	}
}