package greenapi.gpi.metric.expression.token;

import greenapi.gpi.metric.expression.Computable;
import greenapi.gpi.metric.expression.UndefinedVariableException;
import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.Variable;
import greenapi.gpi.metric.expression.evaluator.Evaluator;

public class TreeVariableVisitor<T> extends TreeVisitor<T>
{
    /**
     * The value of the variables.
     */
    private final Object[] varValues;

    /**
     * A lock to avoid that two threads increase the varIndex.
     */
    private final Object lock = new Object();

    /**
     * The variable index in the expression.
     */
    private int varIndex;

    /**
     * Creates an instance of the visitor.
     * 
     * @param evaluator
     *            The expression's evaluator. An evaluator is useful to get variables, operators and functions.
     * @param variableValues
     *            The value of the variables to be assigned to each variable found in the expression. The value is assigned accordingly the variable
     *            appears in the expression.
     */
    public TreeVariableVisitor(Evaluator<?, ?> evaluator, Object ... variableValues)
    {
        super(evaluator);
        this.varValues = variableValues;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Computable<T> visit(VarToken<T> variable) throws UndefinedVariableException
    {
        synchronized (lock)
        {
            Variable<T> var = this.getEvaluator().<T> getVariableByName(variable.name());

            if (var == null)
            {
                @SuppressWarnings("rawtypes")
                Value<T> value = new Value(varValues[varIndex]);
                var = new Variable<T>(variable.name(), value);
                this.getEvaluator().register(var);
            }

            return super.visit(variable);

        }
    }
}
