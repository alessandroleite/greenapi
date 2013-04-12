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
import greenapi.gpi.metric.expression.EvaluationException;
import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.Variable;

public class VarToken<T> extends ExpressionToken<T, Computable<T>>
{
    /**
     * The {@link Variable}'s instance.
     */
    private final Variable<Value<T>> variable;

    /**
     * Creates a new variable's token.
     * 
     * @param token
     *            The token that represents the variable.
     */
    public VarToken(Token token)
    {
        super(token);
        variable = new Variable<Value<T>>(token.getText());
    }

    @Override
    public Computable<T> visit(ExpressionVisitor<T> visitor) throws EvaluationException
    {
        return visitor.visit(this);
    }

    /**
     * Returns the variable name.
     * 
     * @return The variable name. The name always start with a letter.
     */
    public String name()
    {
        return this.variable.name();
    }

    /**
     * Returns an instance of the Variable's type. The name of the variable is the value of this token.
     * 
     * @return An instance of the Variable's type.
     */
    public Variable<Value<T>> getVariable()
    {
        return variable;
    }
}
