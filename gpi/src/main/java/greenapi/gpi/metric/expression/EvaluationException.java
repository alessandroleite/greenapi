package greenapi.gpi.metric.expression;

public class EvaluationException extends RuntimeException
{

    /**
     * Serial code version <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = 3973185659623078738L;

    /**
     * Create an {@link EvaluationException} with the given message.
     * 
     * @param message
     *            The message that represents an expression error parser.
     */
    public EvaluationException(String message)
    {
        super(message);
    }

    /**
     * Create an {@link EvaluationException} with a given message and the cause of the exception.
     * 
     * @param message
     *            The message that represents an expression error parser.
     * @param cause
     *            The cause of the exception.
     */
    public EvaluationException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
