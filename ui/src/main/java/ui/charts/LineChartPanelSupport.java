package monitor.ui.charts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Date;
import java.util.List;

import monitor.model.Resource;
import monitor.ui.charts.data.xy.TranslatingXYDataset;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.general.Series;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleInsets;

public abstract class LineChartPanelSupport<T extends Resource> extends ChartPanelSupport<T> {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1299430162357492697L;

	private TimeSeriesCollection timeSeries;
	private TranslatingXYDataset dataset;

	public LineChartPanelSupport(String title, String axisLabel, T resource){
		this(title, axisLabel, resource, DEFAULT_DELAY);
	}
	
	public LineChartPanelSupport(String title, String axisLabel, T resource, int updateIntervalInSeconds) {
		super(title, axisLabel, resource, updateIntervalInSeconds);
		
		createSeries();
		setMaximumItemAge(30000, true);
		start();
	}

	/**
	 * 
	 */
	protected abstract void createSeries();

	@Override
	public JFreeChart createChart() {
		this.timeSeries = new TimeSeriesCollection();
		this.dataset = new TranslatingXYDataset(this.timeSeries);

		JFreeChart chart = ChartFactory.createTimeSeriesChart(this.getTitle(),
				null, this.getAxisLabel(), this.dataset, true, true, false);

		chart.setBackgroundPaint(getBackground());
		XYPlot xyPlot = chart.getXYPlot();
		xyPlot.setOrientation(PlotOrientation.VERTICAL);
		xyPlot.setBackgroundPaint(Color.WHITE);
		xyPlot.setDomainGridlinePaint(Color.BLACK.darker());
		xyPlot.setRangeGridlinePaint(Color.BLACK.darker());
		xyPlot.setAxisOffset(new RectangleInsets(5.0D, 5.0D, 5.0D, 5.0D));
		xyPlot.setDomainCrosshairLockedOnData(true);
		xyPlot.setRangeCrosshairVisible(true);
		chart.setAntiAlias(true);

		return chart;
	}

	public void setDefaultSeriesPaint() {
		setSeriesPaint(this.getDefaultLineColor(), this.getDefaultLineStroke());
	}

	public void setRangeAxisRange(int lower, int upper) {
		XYPlot localXYPlot = (XYPlot) this.getChart().getPlot();
		NumberAxis localNumberAxis = (NumberAxis) localXYPlot.getRangeAxis();
		localNumberAxis.setRange(lower, upper);
	}

	public void setSeriesPaint(int serie, Color paramColor) {
		XYItemRenderer localXYItemRenderer = getPlot().getRenderer();
		localXYItemRenderer.setSeriesPaint(serie, paramColor);
		localXYItemRenderer.setSeriesStroke(serie, this.getDefaultLineStroke());
		getPlot().setRenderer(localXYItemRenderer);
	}

	protected XYPlot getPlot() {
		return (XYPlot) this.getChart().getPlot();
	}

	public void setSeriesPaint(Color paramColor, BasicStroke paramBasicStroke) {
		XYItemRenderer localXYItemRenderer = getPlot().getRenderer();
		for (int i = 0; i < this.getTimeSeries().getSeriesCount(); i++) {
			localXYItemRenderer.setSeriesPaint(i, paramColor);
			localXYItemRenderer.setSeriesStroke(i, paramBasicStroke);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Series> getSeries() {
		return this.getTimeSeries().getSeries();
	}

	public void setMaximumItemAge(int paramInt, boolean paramBoolean) {
		for (int i = 0; i < this.getTimeSeries().getSeriesCount(); i++) {
			this.getTimeSeries().getSeries(i).setMaximumItemAge(paramInt);
			this.getTimeSeries().getSeries(i).removeAgedItems(paramBoolean);
		}
	}
	
	public void fakeEvents(int max)
	  {
	    long l = System.currentTimeMillis();
	    for (int i = 0; i < 2; i++)
	    {
	      TimeSeries localTimeSeries = (TimeSeries) this.getSeries().get(i);
	      for (int j = 0; j < max; j++)
	      {
	        localTimeSeries.add(new Millisecond(new Date(l)), 0.0D);
	        l -= 500L;
	      }
	      this.getTimeSeries().addSeries(localTimeSeries);
	    }
	  }

	/**
	 * @return the timeSeries
	 */
	public TimeSeriesCollection getTimeSeries() {
		return timeSeries;
	}

	/**
	 * @return the dataset
	 */
	public TranslatingXYDataset getDataset() {
		return dataset;
	}

	/**
	 * @param dataset
	 *            the dataset to set
	 */
	public void setDataset(TranslatingXYDataset dataset) {
		this.dataset = dataset;
	}
}