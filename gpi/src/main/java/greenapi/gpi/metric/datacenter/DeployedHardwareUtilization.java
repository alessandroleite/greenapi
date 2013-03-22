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

import greenapi.gpi.measure.Ratio;
import greenapi.gpi.metric.Expression;
import greenapi.gpi.metric.Formulae;
import greenapi.gpi.metric.Metric;

import java.math.BigDecimal;

import javax.measure.quantity.Quantity;

/**
 * This metric measures how much servers are running live applications in relation to the numbers of servers in the data center. In essence, this
 * metric quantify the fraction of the data center equipment that is consuming energy without running any application or handling data that anyone
 * cares about. In other words, the DH-UR metric helps data center administrator quantify the opportunity for servers to increase
 * their utilization by virtualizing. For servers, it is computed as shown below.
 * 
 * <pre>
 *    DH-UR (Servers) = Number of servers running applications / Number of servers. <br/>
 *    DH-UR (Storage) = Number of terabytes of storage holding frequently accessed data / Total terabytes of storage. <br/>
 * </pre>
 */
public interface DeployedHardwareUtilization extends Metric<BigDecimal, Ratio>
{

    interface NumberOfNonIdleServers extends Expression {
    }
    
    interface NumberOfServers extends Formulae<Long, Quantity> {
    }
}
