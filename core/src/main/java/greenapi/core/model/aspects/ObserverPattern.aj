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
package greenapi.core.model.aspects;

import greenapi.core.model.util.Observer;
import greenapi.core.model.util.Subject;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract aspect ObserverPattern
{

    /**
     * Notify the observers about the new state of the subject.
     * 
     * @param observable The {@link Subject} that is observable and has a new state.
     */
    abstract pointcut notifyObservers(Subject observable, Object data);

    /**
     * This after advice notifies all registered observers about a new state of the observable ({@link Subject}) object.
     * 
     * @param observable
     *            The new state of the observable ({@link Subject}) object. It is never <code>null</code>.
     */
    after(Subject observable, Object data): notifyObservers(observable, data) {
        for (Observer obs : observable.getObservers())
        {
            obs.update(observable, data);
        }
    }

    // Subject inter-type declaration

    private final Collection<Observer> Subject.observers = new CopyOnWriteArrayList<Observer>();

    public void Subject.attach(Observer observer)
    {
        if (observer != null && !observers.contains(observer))
        {
            observers.add(observer);
        }
    }

    /**
     * Remove a {@link Observer}
     * 
     * @param observer
     *            The {@link Observer} to be removed
     */
    public void Subject.detach(Observer observer)
    {
        if (observer != null)
        {
            observers.remove(observer);
        }
    }

    /**
     * Returns the {@link Observer}s of a observable instance.
     * 
     * @return An unmodified {@link Collection} with the {@link Observer}s instances of this observable.
     */
    public Collection<Observer> Subject.getObservers()
    {
        return Collections.unmodifiableCollection(this.observers);
    }
}
