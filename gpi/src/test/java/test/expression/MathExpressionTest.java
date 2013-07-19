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
package test.expression;

import greenapi.gpi.metric.MathExpression;
import greenapi.gpi.metric.expression.Decimal;
import greenapi.gpi.metric.expression.EvaluationException;
import greenapi.gpi.metric.expression.ExpressionBuilder;
import greenapi.gpi.metric.expression.UndefinedFunctionException;
import greenapi.gpi.metric.expression.UndefinedVariableException;
import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.evaluator.impl.ExpressionEvaluator;


import junit.framework.Assert;

import org.junit.Test;

public class MathExpressionTest extends TestSupport
{

    /**
     * @throws EvaluationException
     *             If the expression is invalid.
     */
    @Test
    public void must_be_valid_expressions() throws EvaluationException
    {

        for (Expression expression : expressions())
        {
            Value<Decimal> result = ExpressionBuilder.<Decimal> newMathExpression(expression.getExpression())
                    .withVariable("a", Decimal.from(7))
                    .withVariable("b", Decimal.from(8))
                    .withVariable("c", Decimal.from(9))
                    .withVariable("d", Decimal.from(-8))
                    .withVariable("n", Decimal.from(2))
                    .evaluate(new ExpressionEvaluator<Decimal>());

            Assert.assertEquals(expression.getValue(), result.getValue());
        }
    }

    /**
     * @throws EvaluationException
     *             If the expression is invalid.
     */
    @Test
    public void must_assign_eight_to_a_variable() throws EvaluationException
    {
        MathExpression<Decimal> math = ExpressionBuilder.<Decimal> newMathExpression("a = 2 ^ 3");
        Assert.assertTrue(math.variables().isEmpty());

        Value<Decimal> value = math.evaluate(new ExpressionEvaluator<Decimal>());
        Assert.assertEquals(Decimal.from(8), value.getValue());

        Assert.assertEquals(1, math.variables().size());
    }

    /**
     * Tests the use of undefined variable.
     * 
     * @throws EvaluationException
     *             If the expression is invalid.
     */
    @Test(expected = UndefinedVariableException.class)
    public void must_throw_undefined_variable_use() throws EvaluationException
    {
        ExpressionBuilder.<Decimal> newMathExpression("abs(x)").evaluate(new ExpressionEvaluator<Decimal>());
    }

    /**
     * Tests the use of implicit variables.
     * 
     * @throws EvaluationException
     *             If the expression is invalid.
     */
    @Test
    public void must_work_with_implict_variables() throws EvaluationException
    {
        Decimal value = ExpressionBuilder.<Decimal> evaluate("5 + a * b", 5, 2);
        Assert.assertEquals(15, value.intValue());

        value = ExpressionBuilder.<Decimal> evaluate("x + y * z ^ x", 1, 2, 3);
        Assert.assertEquals(7, value.intValue());
    }

    /**
     * Tests the use of undefined function.
     * 
     * @throws EvaluationException
     *             If the expression is invalid.
     */
    @Test(expected = UndefinedFunctionException.class)
    public void must_throw_undefined_function_call() throws EvaluationException
    {
        ExpressionBuilder.<Decimal> newMathExpression("abss(1)").evaluate(new ExpressionEvaluator<Decimal>());
    }

    /**
     * Tests a function call that requires arguments but without inform its arguments.
     * 
     * @throws EvaluationException
     *             If the expression is invalid.
     */
    @Test(expected = IllegalArgumentException.class)
    public void must_be_an_illegal_function_call_with_wrong_arguments() throws EvaluationException
    {
        ExpressionBuilder.<Decimal> newMathExpression("abs()").evaluate(new ExpressionEvaluator<Decimal>());
    }

    /**
     * Tests a function call that passing more arguments.
     * 
     * @throws EvaluationException
     *             If the expression is invalid.
     */
    @Test(expected = IllegalArgumentException.class)
    public void must_be_an_illegal_function_call_with_more_arguments_than_required() throws EvaluationException
    {
        ExpressionBuilder.<Decimal> newMathExpression("max(2,3,4)").evaluate(new ExpressionEvaluator<Decimal>());
    }
}
