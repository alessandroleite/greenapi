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

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import monitorapi.core.model.resource.Machine;

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