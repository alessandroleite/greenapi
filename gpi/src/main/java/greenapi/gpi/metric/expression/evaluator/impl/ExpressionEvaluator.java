package greenapi.gpi.metric.expression.evaluator.impl;

import java.util.HashMap;
import java.util.Map;

import greenapi.gpi.metric.Expression;
import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.Variable;
import greenapi.gpi.metric.expression.evaluator.Evaluator;
import greenapi.gpi.metric.expression.function.Function;
import greenapi.gpi.metric.expression.operators.Operator;


public class ExpressionEvaluator<T> implements Evaluator<Expression<T>, Value<T>>
{
    /**
     * {@link Map} with all operators. The key is the operator's symbol.
     */
    private final Map<String, Operator<?>> operators = new HashMap<>();

    /**
     * {@link Map} with the available functions. The key if the name of the {@link Function}.
     */
    private final Map<String, Function<?>> functions = new HashMap<>();

    /**
     * {@link Map} with the variables.
     */
    private Map<String, Variable<?>> variables = new HashMap<>();

    @Override
    public Value<T> eval(Expression<T> expression)
    {
        final String exp = expression.expression();

        for (int i = 0; i < exp.length(); i++)
        {
            if (isWhitespace(exp.charAt(i)))
            {
                continue;
            }
            
//            Operator operator = getNextOperator(exp, exp.charAt(i), null);
//
//            if (operator != null) {
//                operator = nextOperator.getOperator();
//                operatorIndex = nextOperator.getIndex();
//            }
        }

        return null;
    }

    /**
     * Determines if a character is a space or white space.
     * 
     * @param character
     *            The character being evaluated.
     * 
     * @return True if the character is a space or white space and false if not.
     */
    private boolean isWhitespace(char character)
    {
        return character == ' ' || character == '\t' || character == '\n' || character == '\r' || character == '\f';
    }
}
