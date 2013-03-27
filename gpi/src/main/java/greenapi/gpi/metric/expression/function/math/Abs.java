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
