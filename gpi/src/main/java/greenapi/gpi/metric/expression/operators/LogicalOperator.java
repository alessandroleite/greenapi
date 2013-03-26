package greenapi.gpi.metric.expression.operators;


public interface LogicalOperator extends Operator<Boolean>
{

    /**
     * Returns the value after the evaluation of {@link Number}s operands.
     * 
     * @param leftValue
     *            The value of the left operand.
     * @param rightValue
     *            The value of the right operand.
     * @return The value of the evaluation. Might not be <code>null</code>.
     */
    boolean evaluate(Number leftValue, Number rightValue);

    /**
     * Returns the value after the evaluation of {@link String} operands.
     * 
     * @param leftValue
     *            The value of the left operand.
     * @param rightValue
     *            The value of the right operand.
     * @return The value of the evaluation. Might not be <code>null</code>.
     */
    boolean evaluate(String leftValue, String rightValue);

    /**
     * Returns the value after the evaluation of the operands.
     * 
     * @param leftValue
     *            The value of the left operand.
     * @param rightValue
     *            The value of the right operand.
     * @return The value of the evaluation. Might not be <code>null</code>.
     */
    boolean evaluate(boolean leftValue, boolean rightValue);

}
