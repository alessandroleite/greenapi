package greenapi.gpi.metric.expression;

/**
 * This class represents a constant. A constant it's a variable that can't change its value.
 * 
 * @param <T>
 *            The type of this constant value.
 */
public final class Constant<T> extends Variable<T>
{

    /**
     * Create a new constant.
     * 
     * @param constName
     *            The constant name. Might not be <code>null</code>.
     * @param constValue
     *            The value of this constant.
     */
    public Constant(String constName, Value<T> constValue)
    {
        super(constName, constValue);
    }

    @Override
    public void setValue(Value<T> newValue)
    {
        throw new UnsupportedOperationException();
    }
}
