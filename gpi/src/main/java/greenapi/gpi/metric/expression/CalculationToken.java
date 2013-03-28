/**
 * Copyright (c) 2012 GreenI2R
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
package greenapi.gpi.metric.expression;

import java.util.Map;
import java.util.Stack;

public abstract class CalculationToken<T> extends Token
{

    /**
     * Create a new {@link CalculationToken} with the given value (token).
     * 
     * @param tokenValue
     *            The character that represents the {@link Token}. Might not be <code>null</code>.
     */
    public CalculationToken(String tokenValue)
    {
        super(tokenValue);
    }

    /**
     * @param stack
     *            The {@link Stack} of execution.
     * @param variableValues
     *            The {@link Map} with the variables associated with the {@link Stack}.
     * @return The {@link Value} after evaluation and execution of the {@link Token}.
     */
    public abstract Value<T> evaluate(Stack<Double> stack, Map<String, Variable<?>> variableValues);

}
