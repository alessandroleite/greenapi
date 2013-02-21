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
package monitorapi.ui.charts.data.xy;

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