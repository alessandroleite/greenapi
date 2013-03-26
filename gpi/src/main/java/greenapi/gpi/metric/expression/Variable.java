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

    /***
     * The type of the variable.
     */
    private Class<T> type;

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
    @SuppressWarnings("unchecked")
    public Variable(String varName, Value<T> varValue)
    {
        this(varName, varValue, varValue == null || varValue.getValue() == null ? null : (Class<T>) varValue.getValue().getClass());
    }

    /**
     * Create a new variable with a name and a {@link Value}.
     * 
     * @param varName
     *            This variable name. Might not be <code>null</code>.
     * @param varValue
     *            This variable value.
     * @param varType
     *            The type of this variable. It's the same of its {@link Value}.
     */
    public Variable(String varName, Value<T> varValue, Class<T> varType)
    {
        this(varName);
        this.value = varValue;
        this.type = varType;

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

    /**
     * Returns the type of the variable.
     * 
     * @return Returns the type of the variable.
     */
    @SuppressWarnings("unchecked")
    public Class<T> type()
    {
        if (value != null && value.getValue() != null)
        {
            return (Class<T>) value.getValue().getClass();
        }
        return type;
    }

    /**
     * Define the type of this variable. The variable's type must be the same of its {@link Value}.
     * 
     * @param varType
     *            The type of the variable.
     */
    public void setType(Class<T> varType)
    {
        this.type = varType;
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
