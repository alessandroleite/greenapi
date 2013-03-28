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
package greenapi.gpi.metric;

public interface MathExpression<T> extends Expression<T>
{

    /**
     * Returns an {@link Expression} whose value is (this / divisor); if the exact quotient cannot be represented (because it has a non-terminated
     * decimal expansion) an {@link ArithmeticException} if thrown.
     * 
     * @param divisor
     *            value by which this {@link Expression} is to be divided.
     * @return An {@link Expression} whose value is (this / divisor).
     * @throws ArithmeticException
     *             if the exact quotient does not have a terminating decimal expansion.
     */
    MathExpression<T> divide(MathExpression<T> divisor);

    /**
     * Returns an {@link Expression} whose value is (this &times multiplicand).
     * 
     * @param multiplicand
     *            The value to be multiplied by this {@link Expression}.
     * @return An {@link Expression} whose value is (this &times multiplicand).
     */
    MathExpression<T> multiply(MathExpression<T> multiplicand);

    /**
     * Returns an {@link Expression} whose value is (this ^ n), The power is computed exactly, to unlimited precision. The given {@link Expression}
     * must returns a value in the range 0 through 999999999, inclusive. ZERO.pow(0) returns ONE.
     * 
     * @param expression
     *            power to raise this {@link Expression} to.
     * @return An {@link Expression} whose value is (this ^ n).
     * @throws ArithmeticException
     *             if the value of the given {@link Expression} is out of range.
     */
    MathExpression<T> pow(MathExpression<T> expression);
}
