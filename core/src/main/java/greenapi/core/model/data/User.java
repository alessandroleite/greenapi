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
package greenapi.core.model.data;

import java.io.Serializable;

import greenapi.core.common.base.Strings;

public class User implements Serializable
{

    /**
     * Serial code version <code>serialVersionUID</code>.
     */
    private static final long serialVersionUID = 8551076312572107154L;

    /**
     * The name of the user.
     */
    private final String name;

    /**
     * Flag that indicates if the user is root.
     */
    private final boolean root;

    /**
     * Create a {@link User} with a given name and indicates that he/she is root.
     * 
     * @param username
     *            The name of the user. Might not be <code>null</code> or empty.
     * @param isRoot
     *            Flag that indicates if the user is root.
     */
    public User(String username, boolean isRoot)
    {
        this.name = Strings.checkArgumentIsNotNullOrEmpty(username);
        this.root = isRoot;
    }

    /**
     * Create a {@link User} with a given name.
     * 
     * @param username
     *            The name of the user. Might not be <code>null</code> or empty.
     */
    public User(String username)
    {
        this(username, false);
    }

    /**
     * Factory method to create a {@link User}.
     * 
     * @param username
     *            The name of the user. Might not be <code>null</code> or empty.
     * @param isRoot
     *            Flag that indicates if the user is root.
     * @return An instance of the {@link User}
     */
    public static User newUser(String username, boolean isRoot)
    {
        return new User(username, isRoot);
    }

    /**
     * Create a non root {@link User}.
     * 
     * @param username
     *            The name of the user. Might not be <code>null</code> or empty.
     * @return An instance of {@link User}.
     */
    public static User newUser(String username)
    {
        return new User(username, false);
    }

    /**
     * Create a root {@link User}.
     * 
     * @param username
     *            The name of the user. Might not be <code>null</code> or empty.
     * @return An instance of {@link User}.
     */
    public static User newRootUser(String username)
    {
        return new User(username, true);
    }

    /**
     * @return the name
     */
    public String name()
    {
        return name;
    }

    /**
     * @return the <code>true</code> if the {@link User} is root, <code>false</code> otherwise.
     */
    public boolean isRoot()
    {
        return root;
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
        User other = (User) obj;
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
