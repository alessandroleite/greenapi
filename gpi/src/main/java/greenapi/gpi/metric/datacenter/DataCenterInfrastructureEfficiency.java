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
import formulaj.expression.Decimal;
import greenapi.core.model.resources.Datacenter;

/**
 * 
 * This metric measures the energy efficiency of a Data Center. It quantifies how much energy the Data Center computing equipments consume from the
 * total energy consumption. It is computed as shown in Equation below:
 * 
 * <pre>
 *    DCiE = IT Equipment Power / Total Facility Power. <br/>
 * </pre>
 * 
 * The IT Equipment Power is defined as the power consumed by equipments that are used to manage, process, store or route data within the compute
 * space. The Total Facility Power is defined as the power measured at the utility meter. It includes all IT Equipments plus everything that supports
 * the Data Center computing equipments load such as: UPS, PDUs, computer room air conditioning units (CRAC’s), Data Center lightening, among others.
 */
public class DataCenterInfrastructureEfficiency extends DatacenterMetric
{

    /**
     * Creates an instance of this {@link greenapi.gpi.metric.Metric}.
     * 
     * @param datacentre
     *            The data center to calculate its energy efficient. Might not be <code>null</code>.
     */
    public DataCenterInfrastructureEfficiency(Datacenter datacentre)
    {
        super(datacentre, "DCiE");
    }

    @Override
    protected Expression<Decimal> getExpression()
    {
        return null;
    }

}
