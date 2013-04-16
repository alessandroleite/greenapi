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
package greenapi.gpi.metric.expression.evaluator.impl;

import greenapi.gpi.metric.Expression;
import greenapi.gpi.metric.expression.EvaluationException;
import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.lexer.ExpressionLexer;
import greenapi.gpi.metric.expression.parser.ExpressionParser;
import greenapi.gpi.metric.expression.token.MathNodeToken;
import greenapi.gpi.metric.expression.token.TreeVariableVisitor;

public class ImplictVariableExpressionEvaluator<T> extends ExpressionEvaluator<T>
{

    /**
     * The value to be assigned to the variables of an expression.
     */
    private final Object[] implictVarValue;

    /**
     * Creates an instance of the {@link ImplictVariableExpressionEvaluator}.
     * 
     * @param variableValues
     *            The variable values.
     */
    public ImplictVariableExpressionEvaluator(Object... variableValues)
    {
        this.implictVarValue = variableValues;
    }

    @Override
    public Value<T> eval(Expression<T> expression) throws EvaluationException
    {
        ExpressionParser<Value<T>> parser = new ExpressionParser<>(new ExpressionLexer(expression.expression()));
        MathNodeToken<Value<T>, Value<T>> stat = parser.<Value<T>> stat();
        return new TreeVariableVisitor<Value<T>>(this, implictVarValue).visit(stat);
    }
}
