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

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import greenapi.gpi.metric.Expression;
import greenapi.gpi.metric.expression.Computable;
import greenapi.gpi.metric.expression.Decimal;
import greenapi.gpi.metric.expression.EvaluationException;
import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.evaluator.Evaluator;
import greenapi.gpi.metric.expression.evaluator.impl.Evaluators;
import greenapi.gpi.metric.expression.function.Function;


public abstract class FunctionSupport<E> implements Function<Value<Decimal>>
{
    /**
     * The number of arguments required by the function.
     */
    private final int numberOfArguments;

    /**
     * The name of this function.
     */
    private final String name;

    /**
     * Creates an instance of {@link FunctionSupport} with the given number of arguments to be checked before call this function.
     * 
     * @param numberOfArgs
     *            The number of arguments that is required to evaluate this function.
     */
    public FunctionSupport(int numberOfArgs)
    {
        this.numberOfArguments = numberOfArgs;
        this.name = this.getClass().getSimpleName().toLowerCase();
    }

    /**
     * Creates an instance of {@link FunctionSupport} with the given number of arguments to be checked before call this function.
     * 
     * @param numberOfArgs
     *            The number of arguments that is required to evaluate this function.
     * @param functionName
     *            The function's name. Might not be <code>null</code>.
     */
    public FunctionSupport(int numberOfArgs, String functionName)
    {
        this.numberOfArguments = numberOfArgs;
        this.name = functionName;
    }

    @Override
    public String name()
    {
        return name;
    }

    @Override
    public <T> Value<Decimal> evaluate(Expression<T> expression) throws EvaluationException
    {
        return this.evaluate(expression, Evaluators.<Expression<T>, Value<Decimal>> get(expression.getClass()));
    }

    @Override
    public <T> Value<Decimal> evaluate(Expression<T> expression, Evaluator<Expression<T>, Value<Decimal>> evaluator) throws EvaluationException
    {
        return Preconditions.checkNotNull(evaluator).eval(Preconditions.checkNotNull(expression));
    }

    @Override
    public <R, T extends Computable<R>> Value<Decimal> evaluate(T[] arguments)
    {
        return this.evaluate(Arrays.asList(arguments));
    }

    @Override
    public <R, T extends Computable<R>> Value<Decimal> evaluate(List<T> arguments)
    {
        checkArguments(arguments);
        Decimal result = this.eval(transform(arguments));
        return new Value<Decimal>(result);
    }

    /**
     * This method throws the exception {@link IllegalArgumentException} with the instructions to call this {@link Function} if the number of
     * function's arguments is incorrect.
     * 
     * @param args
     *            {@link List} with the arguments passed to the function.
     * @param <R>
     *            The {@link Computable} value type.
     * @param <T>
     *            A {@link Computable} argument.
     * @throws IllegalArgumentException
     *             Exception with the instruction to call this {@link Function}.
     */
    protected <R, T extends Computable<R>> void checkArguments(List<T> args)
    {
        StringBuilder message = new StringBuilder();

        if (args == null)
        {
            message.append(String.format("The function %s requires %s argument%s. But there wasn't any argument.", this.name(),
                    this.numberOfArguments, this.numberOfArguments > 1 ? "s" : ""));

            throw new IllegalArgumentException(message.toString());
        }
        else if (args.size() != this.numberOfArguments)
        {
            message.append(String.format("The function %s requires %s argument%s.", this.name(), this.numberOfArguments,
                    this.numberOfArguments > 1 ? "s" : ""));

            throw new IllegalArgumentException(message.toString());
        }
    }

    /**
     * Transforms a {@link List} of T in an array of E. The default implementation assumes that E is a {@link BigDecimal}.
     * 
     * @param arguments
     *            The {@link List} of computable arguments that must be translated to an array of {@link BigDecimal}.
     * @param <R>
     *            The {@link Computable} value type.
     * @param <T>
     *            A {@link Computable} argument.
     * @return An array with the arguments transformed in {@link BigDecimal}.
     */
    @SuppressWarnings("unchecked")
    protected <R, T extends Computable<R>> E [] transform(List<T> arguments)
    {
        List<R> args = Lists.transform(arguments, new com.google.common.base.Function<Computable<R>, R>()
        {
            public R apply(Computable<R> input)
            {
                return input.getValue();
            }
        });

        return (E[]) args.toArray(new BigDecimal[args.size()]);
    }

    /**
     * This method executes the correspondent computation according with the function.
     * 
     * @param args
     *            The arguments of the function. It's never <code>null</code> and the length is always {@link #numberOfArguments}.
     * @return The value after evaluation of this function.
     */
    protected abstract Decimal eval(E [] args);

}
