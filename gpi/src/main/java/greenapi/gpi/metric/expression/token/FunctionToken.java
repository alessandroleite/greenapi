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
package greenapi.gpi.metric.expression.token;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import greenapi.gpi.metric.expression.Computable;
import greenapi.gpi.metric.expression.EvaluationException;
import greenapi.gpi.metric.expression.Value;

public class FunctionToken<T> extends ExpressionToken<T, Computable<T>>
{

    /**
     * The name of the function.
     */
    private final String name;

    /**
     * The function's arguments.
     */
    private final List<ExpressionToken<T, Value<T>>> args = new ArrayList<>();

    /**
     * Creates a {@link FunctionToken} with zero or more arguments.
     * 
     * @param function
     *            The token with the name of the function. Might not be <code>null</code>.
     * @param arguments
     *            The function's arguments.
     */
    @SuppressWarnings("unchecked")
    public FunctionToken(Token function, ExpressionToken<T, Value<T>>... arguments)
    {
        super(function);
        this.name = function.getText();

        if (arguments != null)
        {
            for (ExpressionToken<T, Value<T>> arg : arguments)
            {
                this.args.add(arg);
            }
        }
    }

    /**
     * Creates a {@link FunctionToken} with a {@link List} of arguments.
     * 
     * @param function
     *            The token with the name of the function. Might not be <code>null</code>.
     * @param arguments
     *            The arguments of the given function.
     */
    @SuppressWarnings("unchecked")
    public FunctionToken(Token function, List<ExpressionToken<T, Value<T>>> arguments)
    {
        this(function, arguments.toArray(new ExpressionToken[arguments.size()]));
    }

    /**
     * Returns the name of the function.
     * 
     * @return The name of the function.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the arguments of the functions.
     * 
     * @return A read-only list with the arguments.
     */
    public List<ExpressionToken<T, Value<T>>> getArgs()
    {
        return Collections.unmodifiableList(args);
    }

    /**
     * Returns an array with the arguments.
     * 
     * @return An array with the arguments of this function.
     */
    @SuppressWarnings("unchecked")
    public ExpressionToken<T, Value<T>>[] getArgsAsArray()
    {
        return args.toArray(new ExpressionToken[args.size()]);
    }

    @Override
    public Computable<T> visit(ExpressionVisitor<T> visitor) throws EvaluationException
    {
        return visitor.visit(this);
    }
}
