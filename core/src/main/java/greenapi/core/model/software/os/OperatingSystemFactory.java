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
package greenapi.core.model.software.os;

import greenapi.core.common.base.ClassUtils;

public abstract class OperatingSystemFactory
{

    /**
     * Returns an instance of {@link OperatingSystem} considering the active session. The instance is created basing in the system O.S name. In this
     * case, for every operating system must have a package named greenapi.core.model.software.os.&lt;os name&gt;.&lt;Os
     * name&gt;OperatingSystem. Example greenapi.core.model.software.os.linux.LinuxOperatingSystem.
     * 
     * @return Returns an instance of {@link OperatingSystem} considering the active session.
     */
    public static OperatingSystem getSystemOS()
    {
        String name = System.getProperty("os.name");
        String pkg = OperatingSystem.class.getPackage().getName();

        return (OperatingSystem) ClassUtils.newInstanceForName(String.format("%s.%s.%sOperatingSystem", pkg, name.toLowerCase(), name), name);
    }
}
