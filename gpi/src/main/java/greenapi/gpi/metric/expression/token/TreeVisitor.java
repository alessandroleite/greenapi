package greenapi.gpi.metric.expression.token;

import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.Variable;
import greenapi.gpi.metric.expression.evaluator.Evaluator;
import greenapi.gpi.metric.expression.function.Function;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class TreeVisitor<T> implements ExpressionVisitor<T>
{
    private final Evaluator<?, ?> evaluator;

    /**
     * Creates an instance of this visitor with a given expression evaluator.
     * 
     * @param evaluator
     *            The expression evaluator. An evaluator is useful to get variables, operators and functions.
     */
    public TreeVisitor(Evaluator<?, ?> evaluator)
    {
        this.evaluator = evaluator;
    }

    /**
     * Visits the given AST node.
     * 
     * @param node
     *            The node to be visited.
     * @return The result of the node's visit.
     */
    public <V> V visit(MathNodeToken<T, V> node)
    {
        if (node instanceof BinaryOperatorToken)
        {
            return (V) this.visit((BinaryOperatorToken<T>) node);
        }
        else if (node instanceof AssignToken)
        {
            return (V) this.visit((AssignToken<T>) node);
        }
        else if (node instanceof NumberToken)
        {
            return (V) this.visit((NumberToken<T>) node);
        }
        else if (node instanceof VarToken)
        {
            return (V) this.visit((VarToken<T>) node);
        }
        else if (node instanceof FunctionToken)
        {
            return (V) this.visit((FunctionToken<T>) node);
        }
        throw new IllegalArgumentException("Unknown token " + node.getToken().getText());
    }

    public Value<T> visit(NumberToken<T> number)
    {
        return new Value<T>((T) new BigDecimal(number.getToken().getText()));
    }

    @Override
    public Value<T> visit(FunctionToken<T> functionToken)
    {
        Function<Value<T>> function = this.evaluator.getFunctionByName(functionToken.getName());

        List<T> values = new ArrayList<>();

        for (ExpressionToken<T, Value<T>> arg : functionToken.getArgs())
        {
            values.add(arg.visit(this).getValue());
        }

        return function.evaluate((List<Value<T>>) values);
    }

    @Override
    public Value<T> visit(BinaryOperatorToken<T> bynaryOperator)
    {
        Value<T> left = bynaryOperator.getLeft().visit(this);
        Value<T> right = bynaryOperator.getRight().visit(this);

        return (Value<T>) this.evaluator.getOperatorBySymbol(bynaryOperator.symbol()).evaluate(left, right);
    }

    @Override
    public greenapi.gpi.metric.expression.Variable<Value<T>> visit(VarToken<T> variable)
    {
        return this.evaluator.getVariableByName(variable.name());
    }

    @Override
    public Value<T> visit(UnaryToken<T> unary)
    {
        return null;
    }

    @Override
    public greenapi.gpi.metric.expression.Variable<Value<T>> visit(AssignToken<T> assign)
    {
        Variable<Value<T>> variable = assign.getId().visit(this);
        variable.setValue(assign.getValue().visit(this));
        this.evaluator.register(variable);

        return variable;
    }
}
