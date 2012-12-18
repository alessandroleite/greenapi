package monitor.model.aspects;

import monitor.model.Cpu;
import monitor.model.util.Subject;

public aspect CpuObserverPattern extends ObserverPattern {

	declare parents: Cpu implements Subject;

	pointcut stateChanges(Subject s): target(s) && (call(void Cpu.updateState(..)) || call (void Cpu.updateTemperature(..))) ;

	public Cpu Cpu.getData() {
		return this;
	}
}