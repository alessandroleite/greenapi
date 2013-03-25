package greenapi.gpi.metric.expression.operators;

import greenapi.core.common.base.Strings;
import greenapi.gpi.metric.expression.Operator;

public abstract class AbstractOperator implements Operator
{

    /**
     * The symbol of the operator.
     */
    private final String symbol;

    /**
     * The precedence of the operator. See <a href="http://en.wikipedia.org/wiki/Order_of_operations">Order of operations</a> to get more information
     * about the order of common math operations.
     */
    private final int precedence;

    /**
     * Flag that indicates if operator is a unary operator.
     */
    private final boolean unary;

    /**
     * The length of the operator.
     */
    private final int length;

    /**
     * 
     * @param operatorSymbol
     *            The symbol of the operator.
     * @param operatorPrecedence
     *            The precedence of the operator.
     * @param unaryOperator
     *            Flag that indicates if the operator is a unary operator.
     */
    public AbstractOperator(String operatorSymbol, int operatorPrecedence, boolean unaryOperator)
    {
        this.symbol = Strings.checkArgumentIsNotNullOrEmpty(operatorSymbol).trim();
        this.precedence = operatorPrecedence;
        this.unary = unaryOperator;
        this.length = this.symbol.length();
    }

    /**
     * Create a non-unary operator.
     * 
     * @param operatorSymbol
     *            The symbol of the operator.
     * @param operatorPrecedence
     *            The precedence of the operator.
     */
    public AbstractOperator(String operatorSymbol, int operatorPrecedence)
    {
        this(operatorSymbol, operatorPrecedence, false);
    }

    @Override
    public String symbol()
    {
        return this.symbol;
    }

    @Override
    public int precedence()
    {
        return this.precedence;
    }

    @Override
    public boolean isUnary()
    {
        return unary;
    }

    /**
     * Returns the length of the operator.
     * 
     * @return The length of the operator. In other words, the number of characters of the operator.
     */
    public int length()
    {
        return this.length;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        AbstractOperator other = (AbstractOperator) obj;
        if (symbol == null)
        {
            if (other.symbol != null)
            {
                return false;
            }
        }
        else if (!symbol.equals(other.symbol))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return this.symbol;
    }
}
