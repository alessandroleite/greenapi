package greenapi.gpi.metric.expression.parser;

public class NoViableAltException extends RecognitionException
{
    /**
     * Serial code version <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = -6958309393973738340L;

    /**
     * Creates a new {@link NoViableAltException}.
     * 
     * @param message
     *            The message of the exception.
     */
    public NoViableAltException(String message)
    {
        super(message);
    }
}
