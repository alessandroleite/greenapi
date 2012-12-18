package monitor.model.exception;


public class ResourceException extends RuntimeException {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1560811770591240606L;

	public ResourceException(Exception exception) {
		this(exception.getMessage(), exception);
	}
	
	public ResourceException(String message, Exception exception) {
		super (message, exception);
	}
}
