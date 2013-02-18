package monitor.model.aspects;

import monitor.model.Cpu;
import monitor.model.CpuState;
import monitor.model.Temperature;
import monitor.model.util.Subject;

public aspect CpuObserverPattern extends ObserverPattern {

	declare parents: Cpu implements Subject;

	pointcut stateChanges(Subject s): target(s) &&  (
		  call(CpuState Cpu.setState(..)) || 
		  call(Temperature Cpu.setTemperature(..))) ;

	public Cpu Cpu.getData() {
		return this;
	}
}