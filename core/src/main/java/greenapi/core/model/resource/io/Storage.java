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
package greenapi.core.model.resource.io;

import java.math.BigInteger;

import greenapi.core.model.data.Data;
import greenapi.core.model.resources.Resource;
import greenapi.core.model.sensors.Sensor;

public class Storage implements Resource
{

    /**
     * Serial code version <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = 8726051732367611687L;

    /**
     * The storage's id.
     */
    private final String id;

    /**
     * The storage's name.
     */
    private final String name;

    /**
     * The storage's size.
     */
    private final BigInteger size;

    /**
     * The storage's info.
     */
    private final StorageInfo info;

    /**
     * 
     * @param storageId The storage's id. Might not be <code>null</code>.
     * @param storageName The storage's name. Might not be <code>null</code> or empty.
     * @param storageSize The storage's size. Might not be <code>null</code> or zero.
     * @param storageInfo The storage's info. Might not be <code>null</code>.
     */
    public Storage(String storageId, String storageName, BigInteger storageSize, StorageInfo storageInfo)
    {
        super();
        this.id = storageId;
        this.name = storageName;
        this.size = storageSize;
        this.info = storageInfo;
    }

    @Override
    public Sensor<?, Data<?>>[] sensors()
    {
        return null;
    }

    /**
     * @return the id
     */
    public String id()
    {
        return id;
    }

    /**
     * @return the name
     */
    public String name()
    {
        return name;
    }

    /**
     * @return the size
     */
    public BigInteger size()
    {
        return size;
    }

    /**
     * @return the info
     */
    public StorageInfo info()
    {
        return info;
    }
}
