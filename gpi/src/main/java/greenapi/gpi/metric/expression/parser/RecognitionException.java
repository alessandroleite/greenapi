package greenapi.gpi.metric.expression.parser;

public abstract class RecognitionException extends Exception
{
    /**
     * Serial code version <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = 6436170520861468053L;

    /**
     * Creates a {@link RecognitionException}.
     * 
     * @param message
     *            The detail of this exception.
     */
    public RecognitionException(String message)
    {
        super(message);
    }
}
