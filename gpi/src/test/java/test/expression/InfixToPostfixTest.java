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

import greenapi.gpi.metric.expression.evaluator.impl.InfixToPostfix;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class InfixToPostfixTest
{

    /**
     * Test the conversion of infix expression to postfix expression.
     */
    @Test
    public void must_convert_valid_infix_expressions_to_valid_postfix_expressions()
    {
        assertEquals("3 4 5 * 6 / +", InfixToPostfix.convert("3+4*5/6"));
        assertEquals("300 23 + 43 21 - * 84 7 + /", InfixToPostfix.convert("(300+23)*(43-21)/(84+7)"));
        assertEquals("4 8 + 6 5 - * 3 2 - 2 2 + * /", InfixToPostfix.convert("(4+8)*(6-5)/((3-2)*(2+2))"));
        
        System.out.println(InfixToPostfix.convert("3 * (5 + (7 * 8 + 9))"));
    }
}
