package greenapi.gpi.metric.expression.evaluator.impl;

import greenapi.gpi.metric.expression.Constant;
import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.evaluator.Evaluator;

public class ConstantEvaluator<T> implements Evaluator<Constant<Value<T>>, Value<T>>
{

    @Override
    public Value<T> eval(Constant<Value<T>> type)
    {
        return type.value().getValue();
    }
}
