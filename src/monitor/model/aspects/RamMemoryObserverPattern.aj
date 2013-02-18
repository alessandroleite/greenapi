package monitor.model.aspects;

import monitor.model.Memory;
import monitor.model.MemoryState;
import monitor.model.util.Subject;

public aspect RamMemoryObserverPattern extends ObserverPattern {

	declare parents: Memory implements Subject;

	pointcut stateChanges(Subject s) : target (s) && (
			call (MemoryState Memory.setState(..)));

	public MemoryState Memory.getData() {
		return this.state();
	}
}
