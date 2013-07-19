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
package greenapi.ui.charts;

import greenapi.core.model.resources.Resource;
import greenapi.ui.tasks.ChartUpdateTask;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.JPanel;


import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.RectangleInsets;

public abstract class ChartPanelSupport<T extends Resource> extends JPanel 
{

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -8944621566988479109L;
	protected static final int DEFAULT_DELAY = 1;

	private String title;
	private String axisLabel;
	private Color defaultLineColor = Color.GREEN;

	private org.jfree.chart.ChartPanel chartPanel;
	private JFreeChart chart;
	private BasicStroke defaultLineStroke = new BasicStroke(1.5F);

	private int updateDelayInSeconds;
	private final ScheduledExecutorService updateChartExecutorService;
	
	private final T resource;

	/**
	 * Update the chart view.
	 */
	public abstract void update();

	/**
	 * Returns an instance of the {@link JFreeChart} to be rendered to user.
	 * 
	 * @return An instance of the {@link JFreeChart} to be rendered to user.
	 */
	protected abstract JFreeChart createChart();

	public ChartPanelSupport(String title, String axisLabel, T resource) {
		this(title, axisLabel, resource, DEFAULT_DELAY);
	}

	public ChartPanelSupport(String title, String axisLabel, T resource, int updateIntervalInSeconds) {
		this.title = title;
		this.axisLabel = axisLabel;
		this.resource = resource;
		this.updateDelayInSeconds = updateIntervalInSeconds;

		this.chartPanel = new org.jfree.chart.ChartPanel(
				chart = this.createChart());
		this.chartPanel.setDomainZoomable(true);
		this.chartPanel.setRangeZoomable(true);

		setLayout(new BoxLayout(this, 2));
		this.add(this.chartPanel);

		updateChartExecutorService = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors());
	}

	/**
	 * Start the schedule to update the view with a given delay.
	 */
	public void start() {
		this.updateChartExecutorService.scheduleWithFixedDelay(
				new ChartUpdateTask<T>(this), DEFAULT_DELAY, this.getUpdateDelayInSeconds(),
				TimeUnit.SECONDS);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		this.chartPanel.setPreferredSize(new Dimension(width, height));
	}

	/**
	 * Clean the value of the chart.
	 * 
	 * @see #createChart()
	 */
	public void cleanChart() {
		this.chart.removeLegend();
		this.chartPanel.setBorder(null);

		cleanLabels();

		DateAxis localDateAxis = (DateAxis) ((XYPlot) this.chart.getPlot())
				.getDomainAxis();
		localDateAxis.setTickLabelsVisible(false);
		localDateAxis.setTickMarksVisible(false);
		localDateAxis.setAxisLineVisible(false);

		ValueAxis localValueAxis = ((XYPlot) this.chart.getPlot())
				.getRangeAxis();
		localValueAxis.setTickLabelsVisible(false);
		localValueAxis.setTickMarksVisible(false);
		localValueAxis.setAxisLineVisible(false);

		XYPlot localXYPlot = (XYPlot) this.chart.getPlot();
		localXYPlot.setDomainGridlineStroke(this.defaultLineStroke);
		localXYPlot.setDomainGridlinesVisible(true);
		localXYPlot.setRangeGridlineStroke(this.defaultLineStroke);
		localXYPlot.setRangeGridlinesVisible(true);

		localXYPlot.setAxisOffset(new RectangleInsets(0.0D, 0.0D, 0.0D, 0.0D));
		localXYPlot
				.setInsets(new RectangleInsets(0.0D, 0.0D, 0.0D, 0.0D), true);
	}

	public void cleanLabels() {
		this.getChart().setTitle("");
		DateAxis localDateAxis = (DateAxis) ((XYPlot) this.chart.getPlot())
				.getDomainAxis();
		localDateAxis.setLabel("");
		ValueAxis localValueAxis = ((XYPlot) this.chart.getPlot())
				.getRangeAxis();
		localValueAxis.setLabel("");
	}

	/**
	 * Returns the chart instance of this {@link JPanel}.
	 * 
	 * @return The chart instance of this {@link JPanel}.
	 * @see #createChart()
	 */
	public JFreeChart getChart() {
		return this.chart;
	}
	
	/**
	 * Returns the resource of this {@link JPanel}
	 * @return
	 */
	public T getResource() {
		return resource;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the axisLabel
	 */
	public String getAxisLabel() {
		return axisLabel;
	}

	/**
	 * @param axisLabel
	 *            the axisLabel to set
	 */
	public void setAxisLabel(String axisLabel) {
		this.axisLabel = axisLabel;
	}

	/**
	 * @return the defaultLineColor
	 */
	public Color getDefaultLineColor() {
		return defaultLineColor;
	}

	/**
	 * @param defaultLineColor
	 *            the defaultLineColor to set
	 */
	public void setDefaultLineColor(Color defaultLineColor) {
		this.defaultLineColor = defaultLineColor;
	}

	/**
	 * @return the chartPanel
	 */
	public org.jfree.chart.ChartPanel getChartPanel() {
		return chartPanel;
	}

	/**
	 * @return the updateInterval
	 */
	public int getUpdateDelayInSeconds() {
		return updateDelayInSeconds;
	}

	/**
	 * @param updateInterval
	 *            the updateInterval to set
	 */
	public void updateDelayInSeconds(int updateInterval) {
		this.updateDelayInSeconds = updateInterval;
	}

	/**
	 * @return the defaultLineStroke
	 */
	public BasicStroke getDefaultLineStroke() {
		return defaultLineStroke;
	}

	/**
	 * @param defaultLineStroke the defaultLineStroke to set
	 */
	public void setDefaultLineStroke(BasicStroke defaultLineStroke) {
		this.defaultLineStroke = defaultLineStroke;
	}
}