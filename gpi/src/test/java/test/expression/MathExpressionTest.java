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

import java.math.BigDecimal;

import greenapi.gpi.metric.expression.EvaluationException;
import greenapi.gpi.metric.expression.MathExpressionImpl;
import greenapi.gpi.metric.expression.UndefinedFunctionException;
import greenapi.gpi.metric.expression.UndefinedVariableException;
import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.evaluator.impl.ExpressionEvaluator;
import greenapi.gpi.metric.expression.function.math.Abs;
import greenapi.gpi.metric.expression.function.math.Max;

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
            Value<BigDecimal> result = new MathExpressionImpl<BigDecimal>(expression.getExpression())
                    .withVariable("a", BigDecimal.valueOf(7))
                    .withVariable("b", BigDecimal.valueOf(8))
                    .withVariable("c", BigDecimal.valueOf(9))
                    .withVariable("d", BigDecimal.valueOf(-8))
                    .withVariable("n", BigDecimal.valueOf(2))
                    .withFunction(new Abs())
                    .withFunction(new Max())
                    .evaluate(new ExpressionEvaluator<BigDecimal>());
            
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
        MathExpressionImpl<BigDecimal> math = new MathExpressionImpl<BigDecimal>("a = 2 ^ 3");
        Assert.assertTrue(math.variables().isEmpty());
        
        Value<BigDecimal> value = math.evaluate(new ExpressionEvaluator<BigDecimal>());
        Assert.assertEquals(value.getValue(), BigDecimal.valueOf(8));
        
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
        new MathExpressionImpl<BigDecimal>("max(a)").withFunction(new Abs()).withFunction(new Max()).evaluate(new ExpressionEvaluator<BigDecimal>());
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
        new MathExpressionImpl<BigDecimal>("abss(1)").withFunction(new Abs()).evaluate(new ExpressionEvaluator<BigDecimal>());
    }
}
