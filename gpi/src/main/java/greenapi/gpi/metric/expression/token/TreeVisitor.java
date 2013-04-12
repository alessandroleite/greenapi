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
package greenapi.gpi.metric.expression.token;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import greenapi.gpi.metric.expression.Computable;
import greenapi.gpi.metric.expression.EvaluationException;
import greenapi.gpi.metric.expression.UndefinedVariableException;
import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.Variable;
import greenapi.gpi.metric.expression.evaluator.Evaluator;
import greenapi.gpi.metric.expression.function.Function;

@SuppressWarnings("unchecked")
public class TreeVisitor<T> implements ExpressionVisitor<T>
{
    /**
     * The evaluator to resolve the variables, functions.
     */
    private final Evaluator<?, ?> evaluator;

    /**
     * Creates an instance of this visitor with a given expression evaluator.
     * 
     * @param eval
     *            The expression evaluator. An evaluator is useful to get variables, operators and functions.
     */
    public TreeVisitor(Evaluator<?, ?> eval)
    {
        this.evaluator = eval;
    }

    /**
     * Visits the given AST node.
     * 
     * @param node
     *            The node to be visited.
     * @param <V>
     *            The result's type.
     * @return The result of the node's visit.
     * @throws EvaluationException
     *             If the node represents an invalid expression or has a call to an unknown function.
     */
    public <V> V visit(MathNodeToken<T, V> node) throws EvaluationException
    {
        if (node instanceof BinaryOperatorToken)
        {
            return (V) this.visit((BinaryOperatorToken<T>) node);
        }
        else if (node instanceof UnaryToken)
        {
            return (V) this.visit((UnaryToken<T>) node);
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

    @Override
    public Computable<T> visit(NumberToken<T> number)
    {
        Value<T> value = new Value<T>((T) new BigDecimal(number.getToken().getText()));
        // Constant<T> constt = new Constant<T>(number.getToken().getClass().getSimpleName(), value);

        // return (Computable<T>) constt;
        return value;
    }

    @Override
    public Computable<T> visit(FunctionToken<T> functionToken) throws EvaluationException
    {
        Function<Computable<T>> function = this.evaluator.getFunctionByName(functionToken.getName());

        List<Computable<T>> values = new ArrayList<>();

        for (ExpressionToken<T, Value<T>> arg : functionToken.getArgs())
        {
            Computable<T> val = arg.visit(this);
            values.add(val);
        }

        return function.evaluate(values);
    }

    @Override
    public Computable<T> visit(BinaryOperatorToken<T> bynaryOperator) throws EvaluationException
    {
        Computable<T> left = bynaryOperator.getLeft().visit(this);
        Computable<T> right = bynaryOperator.getRight().visit(this);

        return this.evaluator.<T> getOperatorBySymbol(bynaryOperator.symbol()).evaluate(left, right);
    }

    @Override
    public Computable<T> visit(VarToken<T> variable) throws UndefinedVariableException
    {
        final Variable<T> var = this.evaluator.<T> getVariableByName(variable.name());

        if (var == null)
        {
            throw new UndefinedVariableException(String.format("Undefined variable: %s!", variable.name()));
        }

        return var.getValue();
    }

    @Override
    public Computable<T> visit(UnaryToken<T> unary) throws EvaluationException
    {
        Computable<T> value = unary.getExpression().visit(this);
        return this.evaluator.<T> getOperatorBySymbol(unary.symbol()).evaluate(value);
    }

    @Override
    public Computable<T> visit(AssignToken<T> assign) throws EvaluationException
    {
//        Computable<T> variable = assign.getId().visit(this);
//        
//        variable.setValue(assign.getValue().visit(this));
//        this.evaluator.register(variable);
//
//        return (Computable<T>) variable;
        return null;
    }
}
