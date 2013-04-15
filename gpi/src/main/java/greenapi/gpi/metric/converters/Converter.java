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
package greenapi.gpi.metric.converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import greenapi.core.model.resources.Resource;
import greenapi.gpi.metric.expression.Computable;
import greenapi.gpi.metric.expression.Value;

public final class Converter
{
    /**
     * Private constructor to avoid instance of this utility class.
     */
    private Converter()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Converter a set of {@link Resource} to a set of {@link Computable}. Basically this method encapsulate each {@link Resource} in a {@link Value}.
     * 
     * @param resources
     *            The resources to be converted to a {@link List} of {@link Computable} types.
     * @param <R>
     *            the resource's type.
     * @return A read-only {@link List} with the resources converted.
     */
    public static <R extends Resource> List<Computable<R>> converter(R[] resources)
    {
        return converter(Arrays.asList(resources));
    }

    /**
     * Converter a set of {@link Resource} to a set of {@link Computable}. Basically this method encapsulate each {@link Resource} in a {@link Value}.
     * 
     * @param resources
     *            The resources to be converted to a {@link List} of {@link Computable} types.
     * @param <R>
     *            the resource's type.
     * @return A read-only {@link List} with the resources converted.
     */
    public static <R extends Resource> List<Computable<R>> converter(Collection<R> resources)
    {
        List<Computable<R>> computables = new ArrayList<>();

        for (R resource : resources)
        {
            computables.add(new Value<R>(resource));
        }

        return computables;
    }
}
