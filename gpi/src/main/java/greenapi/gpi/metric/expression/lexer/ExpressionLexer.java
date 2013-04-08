package greenapi.gpi.metric.expression.lexer;

import greenapi.gpi.metric.expression.lexer.ExpressionParser.ExpressionTokens;
import greenapi.gpi.metric.expression.token.Token;

public class ExpressionLexer extends AbstractLexer
{

    /**
     * Creates an instance of the {@link ExpressionLexer}.
     * 
     * @param input
     *            The input with the expression to be analyzed.
     */
    public ExpressionLexer(String input)
    {
        super(input);
    }

    @Override
    public Token nextToken()
    {
        Token t;
        while (current() != EOF)
        {
            switch (current())
            {
            case ' ':
            case '\n':
            case '\t':
            case '\r':
            case '\f':
                WS();
                continue;
            case '+':
            case '-':
                t = new Token(ExpressionTokens.UNARY.getId(), current());
                consume();
                return t;
            case '*':
            case '/':
            case '%':
            case '^':
                t = new Token(ExpressionTokens.OP.getId(), current());
                consume();
                return t;
            case '(':
                consume();
                return new Token(ExpressionTokens.LPARENTHESIS.getId(), '(');
            case ')':
                consume();
                return new Token(ExpressionTokens.RPARENTHESIS.getId(), ')');
            case '=':
                consume();
                return new Token(ExpressionTokens.EQUALS.getId(), '=');
            case ',':
                consume();
                return new Token(ExpressionTokens.COMMA.getId(), ",");
            default:
                if (isLETTER())
                {
                    return name();
                }
                else if (isNUMBER())
                {
                    return number();
                }
                throw new Error("invalid character: " + current());
            }
        }
        return new Token(EOF_TYPE, ExpressionTokens.EOT.name());
    }

    @Override
    public String getTokenName(int tokenType)
    {
        //return ExpressionTokens.get(tokenType).name();
        return null;
    }
}
