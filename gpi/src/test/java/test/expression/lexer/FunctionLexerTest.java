package test.expression.lexer;

import greenapi.gpi.metric.expression.lexer.FunctionLexer;
import greenapi.gpi.metric.expression.token.Token;

import org.junit.Test;

public class FunctionLexerTest
{

    /**
     * This method tests correct function calls.
     */
    @Test
    public void valid_function_call()
    {
        FunctionLexer lexer = new FunctionLexer("power(5.0E-9,3000.58,2e+90)");
        Token token = lexer.nextToken();
        
        while (token != null)
        {
            System.out.println(token.getClass() + " " + token);
            token = lexer.nextToken();
        }
    }
}
