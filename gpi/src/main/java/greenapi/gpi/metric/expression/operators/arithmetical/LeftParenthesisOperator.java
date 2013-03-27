package greenapi.gpi.metric.expression.operators.arithmetical;

import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.operators.AbstractOperator;

public class LeftParenthesisOperator extends AbstractOperator<String>
{
    /**
     * Creates an {@link LeftParenthesisOperator}.
     */
    public LeftParenthesisOperator()
    {
        super("(", 0, null);
    }

    @Override
    public <T> Value<String> evaluate(Value<T> leftOperand, Value<T> rightOperand)
    {
        return new Value<String>(leftOperand.getValue().toString());
    }
}
