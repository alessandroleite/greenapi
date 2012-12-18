package monitor.model.exception;

public class MonitoringException extends RuntimeException{

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -3788594920193346959L;

	public MonitoringException(Throwable root) {
		super(root);
	}
}
