/**
 * Copyright (c) 2012 I2RGreen
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
package greenapi.core.model.resources.builders;

import greenapi.core.model.resources.net.NetworkInterface;
import greenapi.core.model.resources.net.NetworkInterfaceConfiguration;
import greenapi.core.model.resources.net.NetworkInterfaceInfo;
import greenapi.core.model.software.os.OperatingSystem;

import java.math.BigDecimal;

import lshw.types.Capabilities;

public interface NetInterfaceBuilder extends Builder<NetworkInterface> {

	NetInterfaceBuilder useOperatingSystemInformations(OperatingSystem os);

	NetInterfaceBuilder withNetworkInfo(
			NetworkInterfaceInfo networkInterfaceInfo);

	NetInterfaceBuilder withId(String id);

	NetInterfaceBuilder withDescription(String description);

	NetInterfaceBuilder withProductName(String name);

	NetInterfaceBuilder ofVendor(String vendor);

	NetInterfaceBuilder withBusInfo(String busInfo);

	NetInterfaceBuilder withLogicalName(String name);

	NetInterfaceBuilder withVersion(String version);

	NetInterfaceBuilder withHardwareId(String serial);

	NetInterfaceBuilder withSize(BigDecimal size);

	NetInterfaceBuilder withCapacity(BigDecimal capacity);

	NetInterfaceBuilder withWidth(BigDecimal width);

	NetInterfaceBuilder withClock(BigDecimal clock);

	NetInterfaceBuilder withCapabilities(Capabilities capabilities);

	NetInterfaceBuilder withConfiguration(
			NetworkInterfaceConfiguration configuration);

	NetInterfaceBuilder ofType(String type);

	NetInterfaceBuilder isPrimary(boolean value);
}
