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
package greenapi.core.model.resources.builders.impl;

import java.util.HashMap;
import java.util.Map;

import greenapi.core.model.data.MemoryState;
import greenapi.core.model.resources.Memory;
import greenapi.core.model.resources.MemoryType;
import greenapi.core.model.resources.RamMemory;
import greenapi.core.model.resources.builders.MemoryBuilder;

public class MemoryBuilderImpl implements MemoryBuilder
{
    /**
     * The type of the memory. The default is {@link MemoryType#RAM}.
     */
    private MemoryType memoryType = MemoryType.RAM;

    /**
     * The size of the memory.
     */
    private long size;

    /**
     * The properties of the memory.
     */
    private Map<String, String> properties = new HashMap<>();

    /**
     * The state of the memory (percentage of memory used and memory available).
     */
    private MemoryState memoryUsage;

    @Override
    public Memory build()
    {
        Memory mem = null;

        if (memoryType == null)
        {
            this.memoryType = MemoryType.RAM;
        }

        switch (this.memoryType)
        {
        case SWAP:
            break;
        case CACHE:
            break;
        default:
            mem = new RamMemory(size, memoryUsage);
        }
        return mem;
    }

    @Override
    public MemoryBuilder ofType(MemoryType type)
    {
        this.memoryType = type;
        return this;
    }

    @Override
    public MemoryBuilder withSize(long memorySize)
    {
        this.size = memorySize;
        return this;
    }

    @Override
    public MemoryBuilder withProperties(Map<String, String> memoryProperties)
    {
        this.properties.putAll(memoryProperties);
        return this;
    }

    @Override
    public MemoryBuilder withProperty(String propertyName, String propertyValue)
    {
        this.properties.put(propertyName, propertyValue);
        return this;
    }
}
