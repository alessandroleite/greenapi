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
package greenapi.core.model.software.os.commands.impl.linux;

import greenapi.core.common.base.CopyInputStream;
import greenapi.core.model.software.os.commands.Argument;
import greenapi.core.model.software.os.commands.impl.ExecutableCommand;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;


public abstract class LinuxCommandSupport<T> extends ExecutableCommand<T> {

	private final boolean rootRequired;
	private final boolean bash;

	/**
	 * @param rootRequired
	 *            is root required?
	 * @param commands
	 *            array containing the command to call and its arguments.
	 * @param envp
	 *            array of strings, each element of which has environment
	 *            variable settings in the format name=value, or null if the
	 *            subprocess should inherit the environment of the current
	 *            process.
	 */
	public LinuxCommandSupport(boolean rootRequired, boolean bash,
			String[] commands, String... envp) {
		super(commands, envp);
		this.rootRequired = rootRequired;
		this.bash = bash;
	}

	/**
	 * @param rootRequired
	 *            is root required?
	 */
	public LinuxCommandSupport(boolean rootRequired, boolean bash,
			String... envp) {
		this(rootRequired, bash, new String[0], envp);
	}

	public LinuxCommandSupport(boolean rootRequired, boolean bash) {
		this(rootRequired, bash, new String[0]);
	}

	@Override
	protected boolean isRoot() {
		return new Whoami().execute().getValue().isRoot();
	}

	@Override
	public boolean isRootRequired() {
		return rootRequired;
	}

	/**
	 * Return <code>true</code> if the command requires to execute in the bash
	 * mode.
	 * 
	 * @return <code>true</code> if the command requires to execute in the bash
	 *         mode.
	 */
	public boolean isBashNeeded() {
		return this.bash;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[][] getCommandToExecute(Argument... args) {

		String[] instructions = this.commandLine(args);
		
		String[] commands = new String[instructions.length + (isBashNeeded() ? 2 : 0)];
		
		if (isBashNeeded()) {
			commands[0] = "bash";
			commands[1] = "-c";
		} 
		System.arraycopy(instructions, 0, commands, (isBashNeeded() ? 2 : 0), instructions.length);
		return new String[][] { commands, envp };
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected T parser(InputStream input) throws IOException {
		try (InputStream copy = new CopyInputStream(input).copy()) {
			return parser(IOUtils.toString(copy).trim(), input);
		}
	}

	/**
	 * Parser the result of the command and wrapper it in the type expected by
	 * the class.
	 * 
	 * @param result
	 *            The result of the command as {@link String}.
	 * @param source
	 *            The {@link InputStream} obtained with the execution of the
	 *            command.
	 * @return The type expected by the class.
	 * @throws IOException
	 */
	protected abstract T parser(String result, InputStream source) throws IOException;

	public abstract String[] commandLine(Argument... args);
}