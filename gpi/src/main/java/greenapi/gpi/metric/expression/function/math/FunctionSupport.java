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
package greenapi.gpi.metric.expression.function.math;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

import greenapi.gpi.metric.Expression;
import greenapi.gpi.metric.expression.Computable;
import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.evaluator.Evaluator;
import greenapi.gpi.metric.expression.evaluator.impl.Evaluators;
import greenapi.gpi.metric.expression.function.Function;

public abstract class FunctionSupport implements Function<Value<BigDecimal>>
{
    /**
     * The number of arguments required by the function.
     */
    private final int numberOfArguments;

    /**
     * Creates an instance of {@link FunctionSupport} with the given number of arguments to be checked before call this function.
     * 
     * @param numberOfArgs
     *            The number of arguments that is required to evaluate this function.
     */
    public FunctionSupport(int numberOfArgs)
    {
        this.numberOfArguments = numberOfArgs;
    }

    @Override
    public String name()
    {
        return this.getClass().getSimpleName().toLowerCase();
    }

    @Override
    public <T> Value<BigDecimal> evaluate(Expression<T> expression)
    {
        return this.evaluate(expression, Evaluators.<Expression<T>, Value<BigDecimal>> get(expression.getClass()));
    }

    @Override
    public <T> Value<BigDecimal> evaluate(Expression<T> expression, Evaluator<Expression<T>, Value<BigDecimal>> evaluator)
    {
        return null;
    }

    @Override
    public Value<BigDecimal> evaluate(Value<BigDecimal>[] arguments)
    {
        return this.evaluate(Arrays.asList(arguments));
    }

    @Override
    public Value<BigDecimal> evaluate(List<Value<BigDecimal>> arguments)
    {
        checkArguments(arguments);

        List<BigDecimal> args = Lists.transform(arguments, new com.google.common.base.Function<Computable<BigDecimal>, BigDecimal>()
        {
            @SuppressWarnings("unchecked")
            public BigDecimal apply(Computable<BigDecimal> input)
            {
                // FIXME I don't need this. Generic hell.
                Object value = input.getValue();
                if (value instanceof Value)
                {
                    return ((Value<BigDecimal>) value).getValue();
                }
                return input.getValue();
            }
        });

        BigDecimal value = this.eval(args.toArray(new BigDecimal[args.size()]));
        return new Value<BigDecimal>(value);
    }

    /**
     * This method throws the exception {@link IllegalArgumentException} with the instructions to call this {@link Function}.
     * 
     * @param args {@link List} with the arguments passed to the function.
     * @throws IllegalArgumentException
     *             Exception with the instruction to call this {@link Function}.
     */
    protected void checkArguments(List<Value<BigDecimal>> args)
    {
        StringBuilder message = new StringBuilder();
        
        if (args == null)
        {
            message.append(String.format("The function %s requires %s argument%s. But there wasn't any argument.", this.name(),
                    this.numberOfArguments, this.numberOfArguments > 0 ? "s" : ""));
            
            throw new IllegalArgumentException(message.toString());
        }
        else if (args.size() != this.numberOfArguments)
        {
            message.append(String.format("The function %s requires %s argument%s.", this.name(),
                    this.numberOfArguments, this.numberOfArguments > 0 ? "s" : ""));
            
            throw new IllegalArgumentException(message.toString());
        }
    }

    /**
     * This method executes the correspondent computation according with the function.
     * 
     * @param args
     *            The arguments of the function. It's never <code>null</code> and the length is always {@link #numberOfArguments}.
     * @return The value after evaluation of this function.
     */
    protected abstract BigDecimal eval(BigDecimal[] args);

}
