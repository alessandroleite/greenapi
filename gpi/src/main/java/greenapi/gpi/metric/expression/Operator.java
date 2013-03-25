package greenapi.gpi.metric.expression;

public interface Operator
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
}
