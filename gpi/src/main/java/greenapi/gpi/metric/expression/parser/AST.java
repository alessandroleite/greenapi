package greenapi.gpi.metric.expression.parser;

import java.util.Objects;

import greenapi.gpi.metric.expression.token.Token;

public class AST
{

    /**
     * Node created from which token.
     */
    private final Token token;

    /**
     * 
     * @param root
     *            The token that if root of this tree. Might not be <code>null</code>.
     */
    public AST(Token root)
    {
        this.token = Objects.requireNonNull(root);
    }

    /**
     * Returns the token that it the root of this tree.
     * 
     * @return The token that it the root of this tree.
     */
    public Token getToken()
    {
        return token;
    }
}
