package greenapi.gpi.metric.expression.token;


public interface ExpressionVisitor<T>
{

    T visit(Variable<T> variable);

    T visit(Assign<T> assign);

    T visit(Number<T> number);

    T visit(FunctionToken<T> function);

    T visit(BinaryOperator<T> bynaryOperator);

    T visit(Unary<T> unary);
}
