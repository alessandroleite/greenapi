package greenapi.gpi.metric.expression.operators.arithmetical;

import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.operators.AbstractOperator;

public class ClosedParentheseOperator extends AbstractOperator<String>
{

    /**
     * Creates a {@link ClosedParentheseOperator}.
     */
    public ClosedParentheseOperator()
    {
        super(")", 0, null);
    }

    @Override
    public <T> Value<String> evaluate(Value<T> leftOperand, Value<T> rightOperand)
    {
        return new Value<String>(leftOperand.getValue().toString());
    }
}
