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
package greenapi.gpi.metric.datacenter;

import java.math.BigDecimal;
import java.util.Objects;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;

import greenapi.core.common.base.Strings;
import greenapi.core.model.resources.Datacenter;
import greenapi.gpi.measure.Ratio;
import greenapi.gpi.metric.Expression;
import greenapi.gpi.metric.Formulae;
import greenapi.gpi.metric.MathExpression;
import greenapi.gpi.metric.Metric;
import greenapi.gpi.metric.expression.EvaluationException;
import greenapi.gpi.metric.expression.Value;

public abstract class DatacenterMetric implements Metric<BigDecimal, Ratio>
{

    /**
     * The data center to be evaluated.
     */
    private final Datacenter datacenter;
    
    /**
     * The name of this metric. It's never <code>null</code> or empty. 
     */
    private final String name;

    /**
     * 
     * @param datacentre
     *            The data center to be evaluated.
     * @param metricName
     *            The name of the metric. Might not be <code>null</code> or empty.
     */
    public DatacenterMetric(Datacenter datacentre, String metricName)
    {
        this.datacenter = Objects.requireNonNull(datacentre);
        this.name = Strings.checkArgumentIsNotNullOrEmpty(metricName);
    }

    @Override
    public Formulae<BigDecimal, Ratio> formulae()
    {
        return new Formulae<BigDecimal, Ratio>()
        {
            @Override
            public Measure<BigDecimal, Ratio> compute(Expression<BigDecimal> expression) throws EvaluationException
            {
                Value<BigDecimal> value = ((MathExpression<BigDecimal>) expression).evaluate();
                return DecimalMeasure.<Ratio> valueOf(value.getValue(), Ratio.UNIT);
            }
        };
    }

    @Override
    public Measure<BigDecimal, Ratio> value()
    {
        try
        {
            return formulae().compute(this.getExpression());
        }
        catch (EvaluationException invalidExpression)
        {
            throw new MetricException(invalidExpression);
        }
    }

    @Override
    public Measure<BigDecimal, Ratio> value(Formulae<BigDecimal, Ratio> formulae) throws EvaluationException
    {
        return formulae.compute(getExpression());
    }

    @Override
    public String name()
    {
        return this.name;
    }

    /**
     * Returns the {@link Datacenter} that is been evaluated.
     * 
     * @return The {@link Datacenter} that is been evaluated.
     */
    public Datacenter getDatacenter()
    {
        return datacenter;
    }

    /**
     * Returns the {@link Expression} to compute the metric.
     * 
     * @return the {@link Expression} to compute the metric.
     */
    protected abstract Expression<BigDecimal> getExpression();

}
