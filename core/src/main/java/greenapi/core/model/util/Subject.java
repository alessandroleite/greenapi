/**
 * Copyright (c) 2012 Alessandro Ferreira Leite, http://www.alessandro.cc/
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
package greenapi.core.model.util;

import java.util.Collection;

public interface Subject {

	/**
	 * Add a new {@link Observer} to this observable ({@link Subject}) that will
	 * be notified when the state of the observable object change.
	 * 
	 * @param obs
	 *            The {@link Observable} to be add. Must be not
	 *            <code>null</code>.
	 */
	void attach(Observer obs);

	/**
	 * Remove a given {@link Observer} of this {@link Subject}.
	 * 
	 * @param obs
	 *            The {@link Observer} to be removed of this {@link Subject}.
	 */
	void detach(Observer obs);

	/**
	 * Returns a read-only {@link Collection} with the {@link Observer}s of a
	 * {@link Subject}.
	 * 
	 * @return A read-only {@link Collection} with the {@link Observer}s of a
	 *         {@link Subject}.
	 */
	Collection<Observer> getObservers();

	/**
	 * Returns the new state of the observable object.
	 * 
	 * @return The new state of the observable object.
	 */
	// Data getData();
}