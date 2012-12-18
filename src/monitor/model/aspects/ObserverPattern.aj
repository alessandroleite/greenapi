package monitor.model.aspects;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import monitor.model.util.Observer;
import monitor.model.util.Subject;

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
	 * This after advice to notifies all registered observers the new state of
	 * the observable ({@link Subject}) object.
	 * 
	 * @param observable
	 *            The new state of the {@link Subject}. It is never
	 *            <code>null</code>.
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

	public void Subject.remove(Observer observer) {
		if (observer != null) {
			observers.remove(observer);			
		}
	}

	public Collection<Observer> Subject.getObservers() {
		return this.observers;
	}
}
