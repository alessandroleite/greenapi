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

import greenapi.gpi.metric.Expression;
import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.evaluator.Evaluator;
import greenapi.gpi.metric.expression.evaluator.impl.Evaluators;
import greenapi.gpi.metric.expression.function.Function;


public class Abs implements Function<BigDecimal>
{
    @Override
    public String name()
    {
        return this.getClass().getName().toLowerCase();
    }

    @Override
    public <T> Value<BigDecimal> evaluate(Expression<T> expression)
    {
        return this.evaluate(expression, Evaluators.<Expression<T>, Value<BigDecimal>>get(expression.getClass()));
    }

    @Override
    public <T> Value<BigDecimal> evaluate(Expression<T> expression, Evaluator<Expression<T>, Value<BigDecimal>> evaluator)
    {
        Value<BigDecimal> value = evaluator.eval(expression);
        return new Value<BigDecimal>(value.getValue().abs());
    }
}
