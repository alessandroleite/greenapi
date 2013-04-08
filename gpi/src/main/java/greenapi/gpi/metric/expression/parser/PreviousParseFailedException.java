package greenapi.gpi.metric.expression.parser;

public class PreviousParseFailedException extends RecognitionException
{
    /**
     * Serial code version <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = -8649087401747384395L;

    /**
     * Create an instance of this exception with an empty message.
     */
    public PreviousParseFailedException()
    {
        this("");
    }

    /**
     * Creates a new {@link PreviousParseFailedException}.
     * 
     * @param message
     *            message The message of the exception.
     */
    public PreviousParseFailedException(String message)
    {
        super(message);
    }
}
