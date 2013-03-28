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

import java.util.HashMap;
import java.util.Map;

import greenapi.gpi.metric.Expression;
import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.Variable;
import greenapi.gpi.metric.expression.evaluator.Evaluator;
import greenapi.gpi.metric.expression.function.Function;
import greenapi.gpi.metric.expression.operators.Operator;


public class ExpressionEvaluator<T> implements Evaluator<Expression<T>, Value<T>>
{
    /**
     * {@link Map} with all operators. The key is the operator's symbol.
     */
    private final Map<String, Operator<?>> operators = new HashMap<>();

    /**
     * {@link Map} with the available functions. The key if the name of the {@link Function}.
     */
    private final Map<String, Function<?>> functions = new HashMap<>();

    /**
     * {@link Map} with the variables.
     */
    private Map<String, Variable<?>> variables = new HashMap<>();

    @Override
    public Value<T> eval(Expression<T> expression)
    {
        final String exp = expression.expression();

        for (int i = 0; i < exp.length(); i++)
        {
            if (isWhitespace(exp.charAt(i)))
            {
                continue;
            }
            
//            Operator operator = getNextOperator(exp, exp.charAt(i), null);
//
//            if (operator != null) {
//                operator = nextOperator.getOperator();
//                operatorIndex = nextOperator.getIndex();
//            }
        }

        return null;
    }

    /**
     * Determines if a character is a space or white space.
     * 
     * @param character
     *            The character being evaluated.
     * 
     * @return True if the character is a space or white space and false if not.
     */
    private boolean isWhitespace(char character)
    {
        return character == ' ' || character == '\t' || character == '\n' || character == '\r' || character == '\f';
    }
}
