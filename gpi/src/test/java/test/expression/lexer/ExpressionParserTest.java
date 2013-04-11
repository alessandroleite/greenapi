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
package test.expression.lexer;

import java.math.BigDecimal;

import greenapi.gpi.metric.expression.lexer.ExpressionLexer;
import greenapi.gpi.metric.expression.parser.ExpressionParser;
import greenapi.gpi.metric.expression.parser.RecognitionException;
import greenapi.gpi.metric.expression.token.AssignToken;
import greenapi.gpi.metric.expression.token.BinaryOperatorToken;
import greenapi.gpi.metric.expression.token.FunctionToken;
import greenapi.gpi.metric.expression.token.MathNodeToken;

import junit.framework.Assert;
import org.junit.Test;

public class ExpressionParserTest
{
    /**
     * Expressions to be tested.
     */
    private final String[] exprs = {"1+2+3+4", "3 + 5", "2.5e2 + 10 * 10", "3 * (5 + (a * b +c))", "3 * (5 + a)", "3 + (2 * abs(b) + n)",
            "3 * (5 + (a * b +c))", "max(2, abs (-(3)) )", "a = b + 2"};

    
    @Test
    public void must_be_an_binary_operation_expression() throws RecognitionException
    {
        for (int i = 0; i < exprs.length - 4; i++)
        {
            MathNodeToken<BigDecimal, BinaryOperatorToken<BigDecimal>> stat = new ExpressionParser<BigDecimal>(new ExpressionLexer(exprs[i])).stat();
            Assert.assertNotNull(stat);
            Assert.assertEquals(BinaryOperatorToken.class, stat.getClass());
        }
    }

    /**
     * 
     * @throws RecognitionException
     */
    @Test
    public void must_be_function_call_expression() throws RecognitionException
    {
        MathNodeToken<BigDecimal, BinaryOperatorToken<BigDecimal>> stat = new ExpressionParser<BigDecimal>(new ExpressionLexer(
                exprs[exprs.length - 2])).stat();
        Assert.assertNotNull(stat);
        Assert.assertEquals(FunctionToken.class, stat.getClass());
    }

    @Test
    public void must_be_an_assignment_expression() throws RecognitionException
    {
        MathNodeToken<BigDecimal, BinaryOperatorToken<BigDecimal>> stat = new ExpressionParser<BigDecimal>(new ExpressionLexer(
                exprs[exprs.length - 1])).stat();
        Assert.assertNotNull(stat);
        Assert.assertEquals(AssignToken.class, stat.getClass());
    }
}
