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
package greenapi.core.model.software.os.commands;

/**
 * Base interface all the Operating System Command have to implement.
 * 
 * @param <T>
 */
public interface Command<T> {

	/**
	 * Returns <code>true</code> is administrator permission is required,
	 * otherwise <code>false</code>.
	 * 
	 * @return <code>true</code> is administrator permission is required,
	 *         otherwise <code>false</code>.
	 */
	boolean isRootRequired();

	/**
	 * Returns the result of the execution of a command.
	 * 
	 * @return the result of the execution of a command.
	 */
	Result<T> result();

	/**
	 * Execute a command with its arguments. The result of execution is
	 * available calling the method {@link #output()}. If the execution requires
	 * root permission the command will not be executed.
	 * 
	 * @param args
	 *            The arguments to execute a command.
	 * @return The result of the execution. The same value as {@link #output()}.
	 */
	Result<T> execute(Argument... args);
}
