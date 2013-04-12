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
import java.util.ArrayList;
import java.util.List;

import greenapi.gpi.metric.expression.Variable;
import greenapi.gpi.metric.expression.lexer.ExpressionLexer;
import greenapi.gpi.metric.expression.parser.ExpressionParser;
import greenapi.gpi.metric.expression.parser.RecognitionException;
import greenapi.gpi.metric.expression.token.AssignToken;
import greenapi.gpi.metric.expression.token.BinaryOperatorToken;
import greenapi.gpi.metric.expression.token.FunctionToken;
import greenapi.gpi.metric.expression.token.MathNodeToken;
import greenapi.gpi.metric.expression.token.UnaryToken;
import greenapi.gpi.metric.expression.token.VarToken;

import junit.framework.Assert;
import org.junit.Test;

import test.expression.TestSupport;

public class ExpressionParserTest extends TestSupport
{

    /**
     * Tests mathematical operations with or without variables.
     * 
     * @throws RecognitionException
     *             If it's an invalid expression.
     */
    @Test
    public void must_be_mathematical_expressions() throws RecognitionException
    {
        List<Expression> binaryOperationExpressions = new ArrayList<>(mathematicalOperations());
        binaryOperationExpressions.addAll(this.mathematicalOperationsExpressionWithVariables());

        for (int i = 0; i < binaryOperationExpressions.size(); i++)
        {
            MathNodeToken<BigDecimal, BinaryOperatorToken<BigDecimal>> stat = new ExpressionParser<BigDecimal>(new ExpressionLexer(
                    binaryOperationExpressions.get(i).getExpression())).stat();

            Assert.assertNotNull(stat);
            assertEquals(new Class[] {BinaryOperatorToken.class, UnaryToken.class, VarToken.class}, stat.getClass());
        }
    }
    

    /**
     * Tests the function call expressions.
     * 
     * @throws RecognitionException
     *             If it's an invalid expression.
     */
    @Test
    public void must_be_mathematical_function_call_expressions() throws RecognitionException
    {
        for (int i = 0; i < functionOperations().size(); i++)
        {
            MathNodeToken<BigDecimal, BinaryOperatorToken<BigDecimal>> stat = new ExpressionParser<BigDecimal>(new ExpressionLexer(
                    getFunctionExpression(i))).stat();
            Assert.assertNotNull(stat);
            Assert.assertEquals(FunctionToken.class, stat.getClass());
        }
    }

    /**
     * Tests the assignment expressions.
     * 
     * @throws RecognitionException
     *             If it's an invalid expression.
     */
    @Test
    public void must_be_mathematical_assignment_expressions() throws RecognitionException
    {
        for (int i = 0; i < assignmentOperations().size(); i++)
        {
            MathNodeToken<BigDecimal, Variable<BigDecimal>> stat = new ExpressionParser<BigDecimal>(new ExpressionLexer(
                    getAssignmentOperationExpression(i))).stat();

            Assert.assertNotNull(stat);
            Assert.assertEquals(AssignToken.class, stat.getClass());
        }
    }
}
