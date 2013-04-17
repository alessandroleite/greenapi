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

import greenapi.gpi.metric.expression.Computable;
import greenapi.gpi.metric.expression.UndefinedVariableException;
import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.Variable;
import greenapi.gpi.metric.expression.evaluator.Evaluator;

public class TreeVariableVisitor<T> extends TreeVisitor<T>
{
    /**
     * The value of the variables.
     */
    private final Object[] varValues;

    /**
     * A lock to avoid that two threads increase the varIndex.
     */
    private final Object lock = new Object();

    /**
     * The variable index in the expression.
     */
    private int varIndex;

    /**
     * Creates an instance of the visitor.
     * 
     * @param evaluator
     *            The expression's evaluator. An evaluator is useful to get variables, operators and functions.
     * @param variableValues
     *            The value of the variables to be assigned to each variable found in the expression. The value is assigned accordingly the variable
     *            appears in the expression.
     */
    public TreeVariableVisitor(Evaluator<?, ?> evaluator, Object ... variableValues)
    {
        super(evaluator);
        this.varValues = variableValues;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Computable<T> visit(VarToken<T> variable) throws UndefinedVariableException
    {
        synchronized (lock)
        {
            Variable<T> var = this.getEvaluator().<T> getVariableByName(variable.name());

            if (var == null)
            {
                @SuppressWarnings("rawtypes")
                Value<T> value = new Value(varValues[varIndex++]);
                var = new Variable<T>(variable.name(), value);
                this.getEvaluator().register(var);
            }

            return super.visit(variable);
        }
    }
}
