package monitor.ui.charts.data.xy;

import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.XYDataset;

public class TranslatingXYDataset extends AbstractXYDataset implements XYDataset, DatasetChangeListener {
	
	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -3017944294042445329L;
	
	private final XYDataset dataset;
	private double translate;

	public TranslatingXYDataset(XYDataset dataset) {
		this.dataset = dataset;
		this.dataset.addChangeListener(this);
		this.translate = 0.0D;
	}

	public double getTranslate() {
		return this.translate;
	}

	public void setTranslate(double translate) {
		this.translate = translate;
		fireDatasetChanged();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getItemCount(int series) {
		return this.dataset.getItemCount(series);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getXValue(int series, int item) {
		return this.dataset.getXValue(series, item) + this.translate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Number getX(int series, int item) {
		return new Double(getXValue(series, item));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Number getY(int series, int item) {
		return new Double(getYValue(series, item));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getYValue(int series, int item) {
		return this.dataset.getYValue(series, item);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSeriesCount() {
		return this.dataset.getSeriesCount();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Comparable<?> getSeriesKey(int series) {
		return this.dataset.getSeriesKey(series);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void datasetChanged(DatasetChangeEvent paramDatasetChangeEvent) {
		fireDatasetChanged();
	}
}