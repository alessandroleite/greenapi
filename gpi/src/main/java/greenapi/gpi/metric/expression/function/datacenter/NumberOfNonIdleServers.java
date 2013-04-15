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
package greenapi.gpi.metric.expression.function.datacenter;

import java.math.BigDecimal;
import java.util.List;

import com.google.common.collect.Lists;

import greenapi.core.model.resources.Machine;
import greenapi.gpi.metric.expression.Computable;
import greenapi.gpi.metric.expression.function.math.FunctionSupport;

public class NumberOfNonIdleServers extends FunctionSupport<Machine>
{
    /**
     * Creates an instance of this {@link Class}.
     */
    public NumberOfNonIdleServers()
    {
        super(1, "nonIdleServers");
    }

    @Override
    protected BigDecimal eval(Machine[] args)
    {
        int i = 0;
        for (Machine machine : args)
        {
            if (machine.combinedCpuLoad() > 1)
            {
                i++;
            }
        }
        return BigDecimal.valueOf(i);
    }

    @Override
    protected <R, T extends Computable<R>> Machine[] transform(List<T> arguments)
    {
        List<R> args = Lists.transform(arguments, new com.google.common.base.Function<Computable<R>, R>()
        {
            public R apply(Computable<R> input)
            {
                return input.getValue();
            }
        });

        return args.toArray(new Machine[args.size()]);
    }
}
