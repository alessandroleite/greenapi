package greenapi.gpi.metric.expression.lexer;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import greenapi.gpi.metric.expression.parser.MismatchedTokenException;
import greenapi.gpi.metric.expression.parser.NoViableAltException;
import greenapi.gpi.metric.expression.parser.Parser;
import greenapi.gpi.metric.expression.parser.RecognitionException;
import greenapi.gpi.metric.expression.token.Assign;
import greenapi.gpi.metric.expression.token.BinaryOperator;
import greenapi.gpi.metric.expression.token.Expr;
import greenapi.gpi.metric.expression.token.FunctionToken;
import greenapi.gpi.metric.expression.token.MathNode;
import greenapi.gpi.metric.expression.token.Stat;
import greenapi.gpi.metric.expression.token.Token;
import greenapi.gpi.metric.expression.token.Unary;
import greenapi.gpi.metric.expression.token.Variable;

import static greenapi.gpi.metric.expression.lexer.ExpressionParser.ExpressionTokens.ATOM;
import static greenapi.gpi.metric.expression.lexer.ExpressionParser.ExpressionTokens.EOT;
import static greenapi.gpi.metric.expression.lexer.ExpressionParser.ExpressionTokens.EQUALS;
import static greenapi.gpi.metric.expression.lexer.ExpressionParser.ExpressionTokens.FUNCTION_CALL;
import static greenapi.gpi.metric.expression.lexer.ExpressionParser.ExpressionTokens.IDENT;
import static greenapi.gpi.metric.expression.lexer.ExpressionParser.ExpressionTokens.LPARENTHESIS;
import static greenapi.gpi.metric.expression.lexer.ExpressionParser.ExpressionTokens.OP;
import static greenapi.gpi.metric.expression.lexer.ExpressionParser.ExpressionTokens.RPARENTHESIS;
import static greenapi.gpi.metric.expression.lexer.ExpressionParser.ExpressionTokens.UNARY;

public class ExpressionParser<T> extends Parser
{

    private final Stack<Expr<T>> operands = new Stack<>();

    static enum ExpressionTokens
    {
        /**
         * Represents the end of a token.
         */
        EOT(Lexer.EOF_TYPE),

        /**
         * Represents an unary token.
         */
        UNARY(1),

        /**
         * Represents an operator token.
         */
        OP(2),

        /**
         * Represents a left parenthesis.
         */
        LPARENTHESIS(3),

        /**
         * Represents a right parenthesis.
         */
        RPARENTHESIS(4),

        /**
         * Represents a number. It can be INT or FLOAT.
         */
        ATOM(5),

        /**
         * Represents a identifier. It can be a variable or a function name.
         */
        IDENT(6),

        /**
         * Represents an assign.
         */
        EQUALS(7),

        /**
         * Represents a function call.
         */
        FUNCTION_CALL(8),

        /**
         * Represents an argument separator.
         */
        COMMA(9);

        /**
         * The id of the enum.
         */
        private final int id;

        /**
         * Creates a instance of this enum with the given value.
         * 
         * @param enumId
         *            The id of this enum.
         */
        private ExpressionTokens(int enumId)
        {
            this.id = enumId;
        }

        /**
         * Returns the enum's id.
         * 
         * @return The enum's id.
         */
        public int getId()
        {
            return id;
        }

        /**
         * Factory method to get an enum's instance that has a given id.
         * 
         * @param enumId
         *            The id of the enum to be returned.
         * @return The enum that has the given id.
         * @throws IllegalArgumentException
         *             If the given value is unknown.
         */
        public ExpressionTokens get(int enumId)
        {
            for (ExpressionTokens type : ExpressionTokens.values())
            {
                if (type.getId() == id)
                {
                    return type;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    /**
     * Creates an instance of {@link ExpressionParser} with the given {@link Lexer}.
     * 
     * @param input
     *            The {@link Lexer}'s instance to get the tokens.
     */
    public ExpressionParser(Lexer input)
    {
        super(input);
    }

    /**
     * stat: expression EOF | assign EOF.
     * 
     * @return A {@link Expr} instance or an {@link Stat} instance.
     * @throws RecognitionException
     *             If the given expression is invalid for this parser.
     */
    public MathNode<T> stat() throws RecognitionException
    {
        MathNode<T> node;

        if (speculate_stat_alt1())
        {
            expression();
            node = operands.pop();
            match(EOT.getId());
        }
        else if (speculate_stat_alt2())
        {
            node = assign();
            match(EOT.getId());
        }
        else
        {
            throw new NoViableAltException("expecting stat found " + LT(1));
        }

        return node;
    }

    /**
     * Speculates an expression input.
     * 
     * @return <code>true</code> if the input is an expression.
     */
    private boolean speculate_stat_alt1()
    {
        boolean success = true;
        mark();
        try
        {
            expression();
            match(Lexer.EOF_TYPE);
        }
        catch (RecognitionException e)
        {
            success = false;
        }
        operands.clear();
        release();
        return success;
    }

    /**
     * Speculates an assign input.
     * 
     * @return <code>true</code> if the input is an assign.
     */
    private boolean speculate_stat_alt2()
    {
        boolean success = true;
        mark();

        try
        {
            assign();
            match(EOT.getId());
        }
        catch (RecognitionException e)
        {
            success = false;
        }
        operands.clear();
        release();
        return success;
    }

    /**
     * Speculates a function call.
     * 
     * @return <code>true</code> if it's a function call.
     */
    private boolean speculate_function_call()
    {
        boolean success = true;
        mark();

        try
        {
            function_call();
        }
        catch (RecognitionException exception)
        {
            success = false;
        }
        release();

        return success;
    }

    /**
     * Speculates an unary operator.
     * 
     * @return <code>true</code> if it's an unary operator.
     */
    private boolean speculate_unary()
    {
        boolean success = true;
        mark();
        try
        {
            if (isUnary())
            {
                match(UNARY.getId());
            }
            else
            {
                success = false;
            }
        }
        catch (RecognitionException exception)
        {
            success = false;
        }
        release();
        return success;
    }

    private boolean isUnary()
    {
        boolean newExpressionOrEOT = LA(2) == LPARENTHESIS.getId() || LA(2) == RPARENTHESIS.getId() || LA(2) == EOT.getId();
        boolean minusOrPlusSign = (LT(1).getText().equals("+") || LT(1).getText().equals("-"))
                && (LA(2) == ATOM.getId() || LA(2) == IDENT.getId() || newExpressionOrEOT);
        return minusOrPlusSign && this.operands.isEmpty();
    }

    private boolean speculate_parenthesis_expression()
    {
        boolean success = true;
        mark();
        try
        {
            parenthesis();
        }
        catch (RecognitionException exception)
        {
            success = false;
        }
        release();
        return success;
    }

    @SuppressWarnings("unchecked")
    private void expression() throws RecognitionException
    {
        boolean failed = false;
        // get current token position.
        int startTokenIndex = index();

        boolean alreadyParsedRule = alreadyParsedRule();

        if (isSpeculating() && alreadyParsedRule)
        {
            operands.push((Expr<T>) memoization(startTokenIndex).getNode());
            return;
        }
        else if (!isSpeculating() && alreadyParsedRule)
        {
            operands.push((Expr<T>) memoization(startTokenIndex).getNode());
            return;
        }

        try
        {
            expression(EOT.getId());
        }
        catch (RecognitionException exception)
        {
            failed = true;
            throw exception;
        }
        finally
        {
            // succeed or fail, must record result if backtracking.
            if (isSpeculating())
            {
                memoize(startTokenIndex, failed, operands.peek());
            }
        }
    }

    /**
     * 
     * @param endWithToken
     * @throws RecognitionException
     */
    private void expression(int endWithToken) throws RecognitionException
    {
        while (LA(1) != endWithToken)
        {
            if (LA(1) == ATOM.getId())
            {
                operands.push(atom());
            }
            else if (LA(1) == UNARY.getId())
            {
                if (speculate_unary())
                {
                    operands.push(unary());
                }
                else
                {
                    operands.push(operator());
                }
            }
            else if (LA(1) == OP.getId())
            {
                operands.push(operator());

            }
            else if (LA(1) == IDENT.getId())
            {
                if (speculate_function_call())
                {
                    operands.push(function_call());
                }
                else
                {
                    operands.push(ident());
                }
            }
            else if (LA(1) == LPARENTHESIS.getId())
            {
                if (speculate_parenthesis_expression())
                {
                    parenthesis();
                }
                // else if (speculate_function_call())
                // {
                // function_call();
                // }
                else
                {
                    throw new RecognitionException("expecting RPARENTHESIS; found " + LT(1));
                }
            }

            else if (LA(1) == RPARENTHESIS.getId())
            {
                break;
            }
            else
            {
                throw new RecognitionException("found " + LT(1) + " that was not expected!");
            }
        }
    }

    /**
     * 
     * @return
     * @throws RecognitionException
     */
    private Assign<T> assign() throws RecognitionException
    {
        Variable<T> var = new Variable<>(LT(1));
        match(IDENT.getId());
        Token token = LT(1);
        match(EQUALS.getId());
        expression();
        return new Assign<T>(var, token, operands.pop());
    }

    /**
     * 
     * @return
     * @throws RecognitionException
     */
    private Unary<T> unary() throws RecognitionException
    {
        Token token = LT(1);
        match(UNARY.getId());

        Expr<T> expr;

        if (LA(1) == IDENT.getId())
        {
            expr = ident();
        }
        else if (LA(1) == ATOM.getId())
        {
            expr = atom();
        }
        else
        {
            throw new RecognitionException("Expecting IDENT|ATOM; found " + LT(1));
        }

        return new Unary<T>(token, expr);

    }

    /**
     * 
     * @return
     * @throws MismatchedTokenException
     */
    private Expr<T> ident() throws MismatchedTokenException
    {
        Token token = LT(1);
        match(IDENT.getId());
        return new Variable<T>(token);
    }

    /**
     * 
     * @return
     * @throws MismatchedTokenException
     */
    private greenapi.gpi.metric.expression.token.Number<T> atom() throws MismatchedTokenException
    {
        greenapi.gpi.metric.expression.token.Number<T> number = new greenapi.gpi.metric.expression.token.Number<>(LT(1));
        match(ATOM.getId());
        return number;
    }

    /**
     * 
     * @return
     * @throws RecognitionException
     */
    private BinaryOperator<T> operator() throws RecognitionException
    {
        final boolean unaryPlus = LA(1) == UNARY.getId() && operands.isEmpty();
        if (LA(1) == OP.getId() && (LA(2) == ATOM.getId() || LA(2) == IDENT.getId() || LA(2) == LPARENTHESIS.getId()) || !unaryPlus)
        {

            if (unaryPlus)
            {
                match(UNARY.getId());
                return null;
            }
            else
            {
                Token token = LT(1);
                if (token.getText().charAt(0) != '+' && token.getText().charAt(0) != '-')
                {
                    match(OP.getId());

                }
                else
                {
                    match(UNARY.getId());
                }

                Expr<T> left = operands.pop();
                term();
                Expr<T> right = operands.pop();

                return new BinaryOperator<T>(token, left, right);
            }

        }
        else
        {
            throw new RecognitionException("Expecting ATOM|IDENT|LPARENTHESIS; found " + LT(1));
        }
    }

    /**
     * 
     * @throws RecognitionException
     */
    @SuppressWarnings("unchecked")
    private void parenthesis() throws RecognitionException
    {
        boolean failed = false;

        int startTokenIndex = index();

        if (isSpeculating() && alreadyParsedRule())
        {
            Expr<T> expr = (Expr<T>) memoization(startTokenIndex).getNode();
            operands.push(expr);// match(RPARENTHESIS.getId());
            return;
        }

        try
        {
            match(LPARENTHESIS.getId());

            while (LA(1) != RPARENTHESIS.getId() && LA(1) != EOT.getId())
            {
                expression(RPARENTHESIS.getId());
            }

            match(RPARENTHESIS.getId());
        }
        catch (RecognitionException exception)
        {
            failed = true;
            throw exception;
        }
        finally
        {
            if (isSpeculating())
            {
                memoize(startTokenIndex, failed, operands.pop());
            }
        }
    }

    /**
     * 
     * @throws RecognitionException
     */
    private void term() throws RecognitionException
    {
        if (isAtomTerm())
        {
            this.operands.push(atom());
            return;
        }
        else if (LA(1) == UNARY.getId())
        {
            Token token = LT(1);
            match(UNARY.getId());

            term();

            if (this.operands.isEmpty())
            {
                throw new NoViableAltException("expecting ATOM|IDENT|FUNCTION|EXPRESSION");
            }

            Unary<T> unary = new Unary<>(token, this.operands.pop());
            this.operands.push(unary);
            return;
        }
        else
        {
            expression();
        }
    }

    /**
     * 
     * @return
     */
    private boolean isAtomTerm()
    {
        return LA(1) == ATOM.getId() && (LA(2) != LPARENTHESIS.getId() && LA(2) != OP.getId());
    }

    /**
     * 
     * @return
     * @throws RecognitionException
     */
    private FunctionToken<T> function_call() throws RecognitionException
    {

        Token ident = LT(1);
        List<Expr<T>> args;

        match(IDENT.getId());
        match(LPARENTHESIS.getId());
        args = args();
        match(RPARENTHESIS.getId());

        return new FunctionToken<T>(new Token(FUNCTION_CALL.getId(), ident.getText()), args);

    }

    /**
     * (',' args)*.
     * 
     * @return
     * @throws RecognitionException
     */
    @SuppressWarnings("unchecked")
    private List<Expr<T>> args() throws RecognitionException
    {

        int size = this.operands.size();

        if (LA(1) != RPARENTHESIS.getId())
        {
            arg();
            while (LA(1) == ExpressionTokens.COMMA.getId())
            {
                match(ExpressionTokens.COMMA.getId());
                arg();
            }
        }

        int n = this.operands.size() - size;

        Expr<T> [] args = new Expr[n];

        for (int i = n; i > 0; i--)
        {
            Expr<T> arg = operands.pop();
            args[i - 1] = arg;
        }

        return Arrays.asList(args);
    }

    /**
     * 
     * @throws RecognitionException
     */
    private void arg() throws RecognitionException
    {
        term();
    }
}
