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
    }
}
