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
import java.util.ArrayList;
import java.util.List;

import greenapi.gpi.metric.expression.EvaluationException;
import greenapi.gpi.metric.expression.MathExpressionImpl;
import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.evaluator.impl.ExpressionEvaluator;
import greenapi.gpi.metric.expression.function.math.Abs;
import greenapi.gpi.metric.expression.function.math.Max;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class MathExpressionTest
{

    /**
     * A math expression and its value.
     */
    static final class Expression
    {

        /**
         * The expected value of the expression.
         */
        private final BigDecimal value;

        /**
         * The math expression.
         */
        private final String expression;

        /**
         * Creates an instance of this class.
         * 
         * @param expr
         *            The math expression.
         * @param result
         *            The expected value of this expression.
         */
        private Expression(String expr, BigDecimal result)
        {
            this.expression = expr;
            this.value = result;
        }

        /**
         * Creates an instance of this class.
         * 
         * @param expr
         *            The math expression.
         * @param result
         *            The expected value of this expression.
         */
        private Expression(String expr, long result)
        {
            this(expr, BigDecimal.valueOf(result));
        }
    }

    /**
     * The expression to be tested.
     */
    private List<Expression> expressions = new ArrayList<>();

    /**
     * Prepares this test resources.
     */
    @Before
    public void setUp()
    {
        expressions.add(new Expression("1+2+3+4", 10));
        expressions.add(new Expression("(a * b + c)", 65));
        expressions.add(new Expression("a + b * c", 79));

        expressions.add(new Expression("2.5e2 + 10 * 10", 350));
        expressions.add(new Expression("(2.5e2 + 10) * 10", 2600));

        expressions.add(new Expression("3 * (5 + a)", 36));
        expressions.add(new Expression("3 * (5 + (a * b + c))", 210));

        expressions.add(new Expression("3 + (2 * abs(d) + n)", 21));

        expressions.add(new Expression("max(2, abs (-(3)) )", 3));

        // expressions.add(new Expression("a = b + 2", 4));
    }

    /**
     * 
     * @throws EvaluationException
     *             If the expression is invalid.
     */
    @Test
    public void must_be_valid_expressions() throws EvaluationException
    {

        for (Expression expression : expressions)
        {
            Value<BigDecimal> result = new MathExpressionImpl<BigDecimal>(expression.expression)
                    .withVariable("a", BigDecimal.valueOf(7))
                    .withVariable("b", BigDecimal.valueOf(8))
                    .withVariable("c", BigDecimal.valueOf(9))
                    .withVariable("d", BigDecimal.valueOf(-8))
                    .withVariable("n", BigDecimal.valueOf(2))
                    .withFunction(new Abs())
                    .withFunction(new Max())
                    .evaluate(new ExpressionEvaluator<BigDecimal>());
            
            Assert.assertEquals(expression.value, result.getValue());
        }
    }
}
