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
import greenapi.gpi.metric.Metric;

import java.math.BigDecimal;

/**
 * 
 * This metric helps to quantify the opportunity for servers and storage to increase their utilization by virtualizing. Knowing the load of servers as
 * well the number of deployed servers helps to find out how much energy is consumed during peak loads, relative to how much servers are being used
 * actively. It is computed as: <br />
 * 
 * <pre>
 *   DH-UE = Minimum number of servers necessary to handle peak compute load / Total number of servers. <br />
 * </pre>
 * 
 * <br />
 * 
 * Where, the minimum number of servers necessary to handle peak load is defined as: the sum of the highest percentage of compute load of each server,
 * plus any overhead incurred in virtualization, expressed as a percentage of the maximum load of a single server.
 * 
 * In some cases the compute performance can fall off as compute load rises above some utilization threshold with the limitation of resources use.
 */
public interface DeployedHardwareUtilizationEfficiency extends Metric<BigDecimal, Ratio>
{

}
