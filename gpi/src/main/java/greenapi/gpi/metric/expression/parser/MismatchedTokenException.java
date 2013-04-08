package greenapi.gpi.metric.expression.parser;

public class MismatchedTokenException extends RecognitionException
{
    /**
     * Serial code version <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = 7655570784519386485L;

    /**
     * Creates a new {@link MismatchedTokenException}.
     * 
     * @param message
     *            The message with the detail of this exception.
     */
    public MismatchedTokenException(String message)
    {
        super(message);
    }
}
