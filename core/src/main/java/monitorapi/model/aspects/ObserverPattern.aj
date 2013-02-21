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
package monitorapi.model.aspects;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

import monitorapi.model.util.Observer;
import monitorapi.model.util.Subject;

/**
 * 
 * @author Alessandro
 */
public abstract aspect ObserverPattern {

	/**
	 * 
	 * @param observable
	 */
	abstract pointcut stateChanges(Subject observable);

	/**
	 * This after advice notifies all registered observers the new state of the
	 * observable ({@link Subject}) object.
	 * 
	 * @param observable
	 *            The new state of the observable ({@link Subject}) object. It
	 *            is never <code>null</code>.
	 */
	after(Subject observable): stateChanges(observable) {
		for (Observer obs : observable.getObservers()) {
			obs.update(observable);
		}
	}

	// Subject inter-type declaration

	private final Collection<Observer> Subject.observers = new CopyOnWriteArrayList<Observer>();

	public void Subject.add(Observer observer) {
		if (observer != null && !observers.contains(observer)) {
			observers.add(observer);
		}
	}

	/**
	 * Remove a {@link Observer}
	 * 
	 * @param observer
	 */
	public void Subject.remove(Observer observer) {
		if (observer != null) {
			observers.remove(observer);
		}
	}

	/**
	 * Returns the {@link Observer}s of a observable instance.
	 * 
	 * @return An unmodified {@link Collection} with the {@link Observer}s
	 *         instances of this observable.
	 */
	public Collection<Observer> Subject.getObservers() {
		return Collections.unmodifiableCollection(this.observers);
	}
}