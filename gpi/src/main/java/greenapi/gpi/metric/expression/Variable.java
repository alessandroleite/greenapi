package greenapi.gpi.metric.expression;

import greenapi.core.common.base.Strings;

public class Variable<T>
{
    /**
     * The name of the variable.
     */
    private final String name;

    /**
     * The value of the variable.
     */
    private Value<T> value;

    /**
     * Create a new variable.
     * 
     * @param varName
     *            The name of the variable.
     */
    public Variable(String varName)
    {
        this.name = Strings.checkArgumentIsNotNullOrEmpty(varName);

        int firstChar = (int) name.charAt(0);
        if ((firstChar < 65 || firstChar > 90) && (firstChar < 97 || firstChar > 122))
        {
            throw new IllegalArgumentException("Invalid variable name!");
        }
    }

    /**
     * Create a new variable with a name and a {@link Value}.
     * 
     * @param varName
     *            This variable name. Might not be <code>null</code>.
     * @param varValue
     *            This variable value.
     */
    public Variable(String varName, Value<T> varValue)
    {
        this(varName);
        this.value = varValue;
    }

    /**
     * Returns this variable value.
     * 
     * @return This variable value
     */
    public Value<T> value()
    {
        return value;
    }

    /**
     * Assign a new value to this variable.
     * 
     * @param newValue
     *            The value to assign to this variable.
     */
    public void setValue(Value<T> newValue)
    {
        this.value = newValue;
    }

    /**
     * Returns the name of this variable.
     * 
     * @return The name of this variable
     */
    public String name()
    {
        return name;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Variable<?> other = (Variable<?>) obj;
        if (name == null)
        {
            if (other.name != null)
            {
                return false;
            }
        }
        else if (!name.equals(other.name))
        {
            return false;
        }
        return true;
    }
}
