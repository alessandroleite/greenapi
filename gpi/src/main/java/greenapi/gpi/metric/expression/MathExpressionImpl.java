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
package greenapi.gpi.metric.expression;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import greenapi.core.common.base.ClassUtils;
import greenapi.core.common.base.Strings;
import greenapi.gpi.metric.Expression;
import greenapi.gpi.metric.MathExpression;
import greenapi.gpi.metric.expression.evaluator.Evaluator;
import greenapi.gpi.metric.expression.function.Function;
import greenapi.gpi.metric.expression.function.Functions;

public final class MathExpressionImpl<T> implements MathExpression<T>
{
    /**
     * The math expression to be evaluated.
     */
    private StringBuilder expression;

    /**
     * The expression evaluator to be used when no one had be defined.
     */
    private Evaluator<Expression<T>, Value<T>> evaluator;

    /**
     * The variables' value of the expression.
     */
    private Map<String, Variable<?>> variables = new HashMap<>();

    /**
     * Creates a {@link MathExpression} with an empty {@link Expression}.
     */
    public MathExpressionImpl()
    {
        this.expression = new StringBuilder();
    }

    /**
     * Creates a {@link MathExpression} with the given expression.
     * 
     * @param mathExpression
     *            The String expression to be evaluated and converted to a {@link MathExpression}. Might not be <code>null</code> or empty.
     */
    public MathExpressionImpl(String mathExpression)
    {
        this.expression = new StringBuilder(Strings.checkArgumentIsNotNullOrEmpty(mathExpression).trim());
    }

    /**
     * Creates a {@link MathExpression} instance with the given expression and the evaluator.
     * 
     * @param mathExpression
     *            The String expression to be evaluated and converted to a {@link MathExpression}. Might not be <code>null</code> or empty.
     * @param eval
     *            The {@link Evaluator} to be used in the evaluation process.
     */
    public MathExpressionImpl(String mathExpression, Evaluator<Expression<T>, Value<T>> eval)
    {
        this(mathExpression);
        this.evaluator = eval;
    }

    @Override
    public String expression()
    {
        return this.expression.toString();
    }

    @Override
    public Collection<Variable<?>> variables()
    {
        return Collections.unmodifiableCollection(variables.values());
    }

    @Override
    public Value<T> evaluate(Evaluator<Expression<T>, Value<T>> eval) throws EvaluationException
    {
        if (!this.variables.isEmpty())
        {
            for (Variable<?> var : this.variables.values())
            {
                eval.register(var);
            }
        }

        Value<T> result = eval.eval(this);
        this.variables.putAll(eval.variables());
        return result;
    }

    @Override
    public Value<T> evaluate() throws EvaluationException
    {
        Value<T> result = this.evaluator.eval(this);
        this.variables.putAll(this.evaluator.variables());
        
        return result;
    }

    @Override
    public MathExpression<T> divide(MathExpression<T> divisor)
    {
        this.expression.append("/").append(divisor.expression());
        return this;
    }

    @Override
    public MathExpression<T> multiply(MathExpression<T> multiplicand)
    {
        this.expression.append("*").append(multiplicand.expression());
        return this;
    }

    @Override
    public MathExpression<T> pow(MathExpression<T> exp)
    {
        this.expression.append("^").append(exp.expression());
        return this;
    }

    @Override
    public <R> MathExpression<T> withVariable(Variable<R> variable)
    {
        if (this.evaluator == null)
        {
            this.variables.put(variable.name(), variable);
        }
        else
        {
            this.evaluator.register(variable);
        }
        return this;
    }

    @Override
    public <R> MathExpression<T> withVariable(String name, Value<R> value)
    {
        return this.withVariable(new Variable<R>(name, value));
    }

    @Override
    public <R> MathExpression<T> withVariable(String name, R value)
    {
        return this.withVariable(new Variable<R>(name, new Value<R>(value)));
    }

    @Override
    public MathExpression<T> withFunction(Function<Value<T>> function)
    {
        Functions.register(function);
        return this;
    }

    @Override
    public MathExpression<T> withFunction(Class<Function<Value<T>>> function)
    {
        return this.withFunction(ClassUtils.newInstanceForName(function));
    }
}
