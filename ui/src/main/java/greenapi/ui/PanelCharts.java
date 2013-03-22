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
package greenapi.ui;

import greenapi.core.model.resources.Machine;
import greenapi.ui.charts.line.CpuFrequencyLineChart;
import greenapi.ui.charts.line.CpuLoadMultiCoreLineChart;
import greenapi.ui.charts.line.SystemMemoryLineChart;
import greenapi.ui.charts.line.TemperatureMultiCoreLineChart;
import greenapi.ui.panels.CpuFrequencyPanel;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.google.common.base.Preconditions;

public class PanelCharts extends JFrame
{

    /**
     * Serial code version <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 802444743246742398L;

    private final JTabbedPane tabbedPane;

    private final Machine machine;

    private final List<JPanel> panels = new ArrayList<JPanel>();

    public PanelCharts(Machine machine)
    {
        this.machine = Preconditions.checkNotNull(machine);
        this.tabbedPane = new JTabbedPane();
        this.addContent();
    }

    protected void addContent()
    {

        this.addPanel(new CpuLoadMultiCoreLineChart(machine.cpus()[0]));
        this.addPanel(new TemperatureMultiCoreLineChart(machine.cpus()[0]));
        this.addPanel(new CpuFrequencyLineChart(machine.cpus()[0]));
        this.addPanel(new SystemMemoryLineChart(machine.ram()));
        this.addPanel(new CpuFrequencyPanel(machine.cpus()[0]));
        // this.addPanel(new IOStatPanel(machine));
        // this.addPanel(new SystemLoadPanel(machine));

        this.tabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
        getContentPane().add(this.tabbedPane);

        for (JPanel panel : this.getPanels())
        {
            panel.setVisible(true);
            this.tabbedPane.add(panel, panel.getClass().getSimpleName());
        }
        setPreferredSize(new Dimension(1024, 768));

        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addPanel(JPanel panel)
    {
        this.panels.add(panel);
    }

    public List<JPanel> getPanels()
    {
        return Collections.unmodifiableList(panels);
    }
}
