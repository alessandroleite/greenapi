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

import greenapi.core.model.resources.Datacenter;
import greenapi.gpi.metric.Expression;
import greenapi.gpi.metric.expression.Decimal;
import greenapi.gpi.metric.expression.ExpressionBuilder;

/**
 * The Power Usage Effectiveness (PUE) measures the energy efficiency of a Data Center, calculated as a ratio between the Total Facility Power and IT
 * Equipment Power. It is computed as the inverse of DCiE metric as shown in Equation below.
 * 
 * <pre>
 *    PUE = Total Facility Power / IT Equipment Power
 * </pre>
 */
public class PowerUsageEffectiveness extends DatacenterMetric
{

    /**
     * Creates an instance of the PUE metric.
     * 
     * @param datacentre
     *            The data center to be evaluated. Might not be <code>null</code>.
     */
    public PowerUsageEffectiveness(Datacenter datacentre)
    {
        super(datacentre, "PUE");
    }

    @Override
    protected Expression<Decimal> getExpression()
    {
        return ExpressionBuilder.<Decimal> newMathExpression("pue(n)").withVariable("n", this.getDatacenter());
    }
}
