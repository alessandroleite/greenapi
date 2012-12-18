package monitor.ui.util;

import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class ComponentsUtil {
	
	public static JPanel createPainelContainer(String title, Component... components) {
		JPanel panel =  createPainelContainer(title, null, components);
		panel.setLayout(new BoxLayout(panel, 0));
		
		return panel;
	}
	
	public static JPanel createPainelContainer(String title, LayoutManager layout, Component... components) {
		final JPanel container = new JPanel();
		container.setBorder(BorderFactory.createTitledBorder(title));
		
		if (layout != null)
			container.setLayout(layout);

		for (Component c : components) {
			container.add(c);
		}

		return container;
		
	}
}
