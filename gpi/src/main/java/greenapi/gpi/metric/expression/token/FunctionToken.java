package greenapi.gpi.metric.expression.token;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import greenapi.gpi.metric.expression.Value;

public class FunctionToken<T> extends ExpressionToken<T, Value<T>>
{

    /**
     * The name of the function.
     */
    private final String name;

    /**
     * The function's arguments.
     */
    private final List<ExpressionToken<T, Value<T>>> args = new ArrayList<>();

    /**
     * 
     * @param token
     *            The token that represents a function.
     * @param arguments
     *            The function's arguments.
     */
    @SuppressWarnings("unchecked")
    public FunctionToken(Token token, ExpressionToken<T, Value<T>>... arguments)
    {
        super(token);
        this.name = token.getText();

        if (arguments != null)
        {
            for (ExpressionToken<T, Value<T>> arg : arguments)
            {
                this.args.add(arg);
            }
        }
    }

    /**
     * 
     * @param token
     *            The token that represents a function.
     * @param arguments
     *            The arguments of the given function.
     */
    @SuppressWarnings("unchecked")
    public FunctionToken(Token token, List<ExpressionToken<T, Value<T>>> arguments)
    {
        this(token, arguments.toArray(new ExpressionToken[arguments.size()]));
    }

    /**
     * Returns the name of the function.
     * 
     * @return The name of the function.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the arguments of the functions.
     * 
     * @return A read-only list with the arguments.
     */
    public List<ExpressionToken<T, Value<T>>> getArgs()
    {
        return Collections.unmodifiableList(args);
    }

    /**
     * Returns an array with the arguments.
     * 
     * @return An array with the arguments of this function.
     */
    @SuppressWarnings("unchecked")
    public ExpressionToken<T, Value<T>>[] getArgsAsArray()
    {
        return args.toArray(new ExpressionToken[args.size()]);
    }

    @Override
    public Value<T> visit(ExpressionVisitor<T> visitor)
    {
        return visitor.visit(this);
    }
}
