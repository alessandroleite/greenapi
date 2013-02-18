package monitor.model.aspects;

import monitor.model.CpuSocket;
import monitor.model.CpuSocketState;
import monitor.model.util.Subject;

public aspect CpuSocketObserver extends ObserverPattern {

	declare parents: CpuSocket implements Subject;

	pointcut stateChanges(Subject s): target(s) && (call 
			(CpuSocketState CpuSocket.setState(..)));

	public CpuSocketState CpuSocket.getData() {
		return this.state();
	}
}
