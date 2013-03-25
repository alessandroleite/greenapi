package greenapi.gpi.metric.expression;

public final class Value<T>
{
    /**
     * The correspondent value.
     */
    private final T value;

    /**
     * Create an instance with a given value.
     * 
     * @param valueToAssign
     *            The value to be assigned.
     */
    public Value(T valueToAssign)
    {
        this.value = valueToAssign;
    }

    /**
     * Return the value.
     * @return The value
     */
    public T getValue()
    {
        return value;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        Value<?> other = (Value<?>) obj;
        if (value == null)
        {
            if (other.value != null)
            {
                return false;
            }
        }
        else if (!value.equals(other.value))
        {
            return false;
        }
        return true;
    }
}
