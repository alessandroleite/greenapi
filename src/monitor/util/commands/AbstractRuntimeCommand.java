package monitor.util.commands;

import java.io.IOException;
import java.io.InputStream;

import com.google.common.base.Preconditions;

public abstract class AbstractRuntimeCommand<T> implements Command<T> {

	private final String[] commands;
	private final String[] envp;

	private T result;
	protected IOException processExecutionException;

	/**
	 * @param command
	 *            array containing the command to call and its arguments.
	 * @param envp
	 */
	public AbstractRuntimeCommand(String[] commands, String... envp) {
		this.commands = Preconditions.checkNotNull(commands);
		this.envp = envp;
	}

	public AbstractRuntimeCommand(String... commands) {
		this(commands, new String[] {});
	}

	/**
	 * @param input
	 * @return
	 */
	public abstract T parserOutput(InputStream input);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T execute() {
		try {
			Process process = Runtime.getRuntime().exec(commands, envp);
			this.result = this.parserOutput(process.getInputStream());
		} catch (IOException exception) {
			this.processExecutionException = exception;
		}
		return this.result();
	}

	/***
	 * 
	 * @return
	 */
	public T result() {
		return this.result;
	}
}