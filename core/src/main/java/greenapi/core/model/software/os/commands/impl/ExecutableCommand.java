/**
 * Copyright (c) 2012 I2RGreen
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

import java.io.IOException;
import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Preconditions;

import greenapi.core.model.exception.GreenApiException;
import greenapi.core.model.software.os.commands.Argument;
import greenapi.core.model.software.os.commands.Command;
import greenapi.core.model.software.os.commands.Result;

public abstract class ExecutableCommand<T> implements Command<T>
{

    /**
     * Array containing the command to call and its arguments.
     */
    private final String[] commands;

    /**
     * Array of strings, each element of which has environment variable settings in the format name=value.
     */
    private final String[] envp;

    /**
     * The result of the execution.
     */
    private volatile Result<T> result;

    /**
     * @param executableCommands
     *            array containing the command to call and its arguments.
     * @param envps
     *            array of strings, each element of which has environment variable settings in the format name=value, or null if the subprocess should
     *            inherit the environment of the current process.
     */
    public ExecutableCommand(String[] executableCommands, String... envps)
    {
        this.commands = Preconditions.checkNotNull(executableCommands);
        this.envp = envps;
    }

    /**
     * @param executableCommands
     *            array containing the command to call and its arguments.
     */
    public ExecutableCommand(String... executableCommands)
    {
        this(executableCommands, new String[0]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Result<T> execute(Argument... args)
    {
        Map<String, Throwable> errors = new HashMap<>();

        if (this.isRootRequired() && !isRoot())
        {
            return Result.newFailureResult(new GreenApiException("You need to be root to perform this command!"));
        }

        String[][] consoleCommands = getCommandToExecute(args);

        try
        {
            Process process = Runtime.getRuntime().exec(consoleCommands[0], consoleCommands[1]);
            process.waitFor();
            this.result = Result.newResult(this.parser(process.getInputStream()));
        }
        catch (IOException | InterruptedException exception)
        {
            errors.put(exception.getMessage(), exception);
        }

        if (result == null)
        {
            result = new Result<>(null);
        }

        this.result().addAll(errors);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Result<T> result()
    {
        return this.result;
    }

    /**
     * Assign a new result value of this command.
     * 
     * @param newResultValue
     *            The new result value to be assigned.
     */
    protected void setResult(Result<T> newResultValue)
    {
        this.result = newResultValue;
    }

    /**
     * Returns <code>true</code> is the root is root.
     * 
     * @return <code>true</code> is the root is root.
     */
    protected abstract boolean isRoot();

    /**
     * Parser the {@link InputStream} resulted of the execution of the {@link Command} and returns the type expected by the user.
     * 
     * @param input
     *            The input to be parsed.
     * @return The type resulted of the parser.
     * @throws IOException
     *             Throws in case of some exception.
     */
    protected abstract T parser(InputStream input) throws IOException;

    /**
     * Returns the console's instruction to be executed.
     * 
     * @param args
     *            The arguments to be passed to the command.
     * @return The console's instruction to be executed
     */
    public String[][] getCommandToExecute(Argument... args)
    {
        return new String[][] {this.commands, this.envp};
    }

    /**
     * Returns <code>true</code> if the execution of succeeded without error.
     * 
     * @return <code>true</code> if the execution of succeeded without error.
     */
    protected boolean wasSuccessful()
    {
        return this.result() != null ? this.result().wasSuccessfully() : false;
    }

    /**
     * Returns an array of strings, each element of which has environment variable settings in the format name=value, or <code>null</code> if the
     * subprocess should inherit the environment of the current process.
     * 
     * @return An array of strings, each element of which has environment variable settings in the format name=value.
     */
    public String[] getEnvp()
    {
        return envp;
    }
}
