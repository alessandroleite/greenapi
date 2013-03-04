/**
 * Copyright (c) 2012 Alessandro Ferreira Leite, http://www.alessandro.cc/
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package greenapi.core.model.software.os.commands.impl;

import greenapi.core.model.exception.GreenApiException;
import greenapi.core.model.software.os.commands.Argument;
import greenapi.core.model.software.os.commands.Command;
import greenapi.core.model.software.os.commands.Result;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import com.google.common.base.Preconditions;

public abstract class ExecutableCommand<T> implements Command<T> {

	protected final Map<String, Throwable> errors = new ConcurrentHashMap<>();

	/**
	 * Array containing the command to call and its arguments.
	 */
	private String[] commands;

	/**
	 * Array of strings, each element of which has environment variable settings
	 * in the format name=value
	 */
	protected String[] envp;

	/**
	 * The result of the execution.
	 */
	protected volatile Result<T> result;

	/**
	 * @param commands
	 *            array containing the command to call and its arguments.
	 * @param envp
	 *            array of strings, each element of which has environment
	 *            variable settings in the format name=value, or null if the
	 *            subprocess should inherit the environment of the current
	 *            process.
	 */
	public ExecutableCommand(String[] commands, String... envp) {
		this.commands = Preconditions.checkNotNull(commands);
		this.envp = envp;
	}

	/**
	 * 
	 * @param commands
	 *            array containing the command to call and its arguments.
	 */
	public ExecutableCommand(String... commands) {
		this(commands, new String[0]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Result<T> execute(Argument... args) {

		if (this.isRootRequired() && !isRoot()) {
			GreenApiException exception = new GreenApiException("Please, execute this command as a root user!");
			errors.put(exception.getMessage(), exception);
			return null;
		}

		String[][] commands = getCommandToExecute(args);

		try {
			Process process = Runtime.getRuntime().exec(commands[0], commands[1]);
			process.waitFor();
			this.result = new Result<>(this.parser(process.getInputStream()));
		} catch (IOException | InterruptedException exception) {
			this.errors.put(exception.getMessage(), exception);
		}
		this.result().addAll(this.getErrors());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Result<T> result() {
		return this.result;
	}

	/**
	 * Returns <code>true</code> is the root is root.
	 * 
	 * @return <code>true</code> is the root is root.
	 */
	protected abstract boolean isRoot();

	/**
	 * Parser the {@link InputStream} resulted of the execution of the
	 * {@link Command} and returns the type expected by the user.
	 * 
	 * @param input
	 *            The input to be parsed.
	 * @return The type resulted of the parser.
	 * @throws IOException
	 *             Throws in case of some exception.
	 */
	protected abstract T parser(InputStream input) throws IOException;
	
	
	public String[][] getCommandToExecute(Argument... args) {
		return new String[][] { this.commands, this.envp };
	}

	/**
	 * Returns <code>true</code> if the execution of succeeded without error.
	 * 
	 * @return <code>true</code> if the execution of succeeded without error.
	 */
	protected boolean wasSuccessful() {
		return this.errors.isEmpty();
	}

	/**
	 * Returns the errors occurred during the execution of a command.
	 * 
	 * @return A {@link Map} which the key is the name or description of the
	 *         error and the value is an instance of an {@link Exception}.
	 */
	protected Map<String, Throwable> getErrors() {
		return Collections.unmodifiableMap(this.errors);
	}
}