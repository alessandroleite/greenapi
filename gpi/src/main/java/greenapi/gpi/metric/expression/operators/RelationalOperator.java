package greenapi.gpi.metric.expression.operators;

public interface RelationalOperator extends Operator<Boolean>
{
    /**
     * Returns the value after evaluates {@link Comparable}s operands.
     * 
     * @param leftValue
     *            The value of the left operand.
     * @param rightValue
     *            The value of the right operand.
     * @param <T>
     *            The {@link Comparable} type.
     * @return The value of the evaluation. Might not be <code>null</code>.
     */
     <T extends Comparable<T>> boolean evaluate(T leftValue, T rightValue);
}
