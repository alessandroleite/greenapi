package greenapi.gpi.metric.expression.token;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import greenapi.gpi.metric.expression.Value;

public class FunctionToken<T> extends Expr<T>
{

    /**
     * The name of the function.
     */
    private final String name;

    /**
     * The function's arguments.
     */
    private final List<Expr<T>> args = new ArrayList<>();

    /**
     * 
     * @param token
     *            The token that represents a function.
     * @param arguments
     *            The function's arguments.
     */
    @SuppressWarnings("unchecked")
    public FunctionToken(Token token, Expr<T>... arguments)
    {
        super(token);
        this.name = token.getText();

        if (arguments != null)
        {
            for (Expr<T> arg : arguments)
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
    public FunctionToken(Token token, List<Expr<T>> arguments)
    {
        this(token, arguments.toArray(new Expr[arguments.size()]));
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
    public List<Expr<T>> getArgs()
    {
        return Collections.unmodifiableList(args);
    }

    /**
     * Returns an array with the arguments.
     * 
     * @return An array with the arguments of this function.
     */
    @SuppressWarnings("unchecked")
    public Expr<T>[] getArgsAsArray()
    {
        return args.toArray(new Expr[args.size()]);
    }

    @Override
    public Value<T> visit(ExpressionVisitor<T> visitor)
    {
        return new Value<T>(visitor.visit(this));
    }
}
