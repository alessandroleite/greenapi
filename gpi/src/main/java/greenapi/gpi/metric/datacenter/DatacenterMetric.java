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

import formulaj.Expression;
import formulaj.MathExpression;
import formulaj.expression.Decimal;
import formulaj.expression.EvaluationException;
import formulaj.expression.Value;
import greenapi.core.common.base.Strings;
import greenapi.core.model.resources.Datacenter;
import greenapi.gpi.measure.Ratio;
import greenapi.gpi.metric.Formulae;
import greenapi.gpi.metric.Metric;

import java.util.Objects;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;


public abstract class DatacenterMetric implements Metric<Decimal, Ratio>
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
    public Formulae<Decimal, Ratio> formulae()
    {
        return new Formulae<Decimal, Ratio>()
        {
            @Override
            public Measure<Decimal, Ratio> compute(Expression<Decimal> expression) throws EvaluationException
            {
                Value<Decimal> value = ((MathExpression<Decimal>) expression).evaluate();
                DecimalMeasure<Ratio> valueOf = DecimalMeasure.<Ratio> valueOf(value.getValue().bigDecimalValue(), Ratio.UNIT);
                
                //FIXME
                return null;
            }
        };
    }

    @Override
    public Measure<Decimal, Ratio> value()
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
    public Measure<Decimal, Ratio> value(Formulae<Decimal, Ratio> formulae) throws EvaluationException
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
    protected abstract Expression<Decimal> getExpression();

}
