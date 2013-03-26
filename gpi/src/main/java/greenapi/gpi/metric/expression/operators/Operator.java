package greenapi.gpi.metric.expression.operators;

import greenapi.gpi.metric.expression.Value;

/**
 * @param <R>
 *            The type of the return value after evaluate the operands.
 */
public interface Operator<R>
{

    /**
     * Return the character(s) that makes up the operator.
     * 
     * @return The operator symbol(s).
     */
    String symbol();

    /**
     * Returns the precedence of this operator.
     * 
     * @return The precedence of this operator.
     */
    int precedence();

    /**
     * Returns an indicator if this operator is unary or not.
     * 
     * @return An indicator if this operator is unary or not.
     */
    boolean isUnary();

    /**
     * Evaluate the operands and returns its value.
     * 
     * @param leftOperand
     *            The left operand to be evaluated.
     * @param rightOperand
     *            The right operand to be evaluated.
     * @param <T>
     *            The type of the operands.
     * @return The value of the evaluated operands.
     * @throws greenapi.gpi.metric.expression.EvaluationException
     *             If it's impossible to evaluate the operands.
     */
    <T> Value<R> evaluate(Value<T> leftOperand, Value<T> rightOperand);

    /**
     * Evaluate one operand and returns its value.
     * 
     * @param operand
     *            The operand to be evaluated.
     * @param <T>
     *            The type of the given operand.
     * @return The value of the evaluated operand.
     * @throws greenapi.gpi.metric.expression.EvaluationException
     *             If it's impossible to evaluate the given operand.
     */
    <T> Value<R> evaluate(Value<T> operand);
}
