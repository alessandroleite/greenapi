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

import greenapi.core.model.resources.Datacenter;
import greenapi.gpi.metric.Expression;
import greenapi.gpi.metric.expression.ExpressionBuilder;

public class ComputePowerEfficiency extends DatacenterMetric
{

    /**
     * Creates an instance of the CPE metric.
     * 
     * @param datacentre
     *            The data center to be evaluated. Might not be <code>null</code>.
     */
    public ComputePowerEfficiency(Datacenter datacentre)
    {
        super(datacentre, "CPE");
    }

    @Override
    protected Expression<BigDecimal> getExpression()
    {
        return ExpressionBuilder.<BigDecimal> newMathExpression("cpe(n)/pue(n)").withVariable("n", this.getDatacenter());
    }
}
