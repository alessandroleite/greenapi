package greenapi.gpi.metric.expression.evaluator.impl;

import greenapi.gpi.metric.Expression;
import greenapi.gpi.metric.expression.EvaluationException;
import greenapi.gpi.metric.expression.Value;
import greenapi.gpi.metric.expression.lexer.ExpressionLexer;
import greenapi.gpi.metric.expression.parser.ExpressionParser;
import greenapi.gpi.metric.expression.token.MathNodeToken;
import greenapi.gpi.metric.expression.token.TreeVariableVisitor;

public class ImplictVariableExpressionEvaluator<T> extends ExpressionEvaluator<T>
{
    
    @Override
    public Value<T> eval(Expression<T> expression) throws EvaluationException
    {
        ExpressionParser<Value<T>> parser = new ExpressionParser<>(new ExpressionLexer(expression.expression()));
        MathNodeToken<Value<T>, Value<T>> stat = parser.<Value<T>> stat();
        return new TreeVariableVisitor<Value<T>>(this).visit(stat);
    }
}
