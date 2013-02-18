package monitor.model;

import java.util.Collection;

import com.google.common.base.Preconditions;

public class CpuSocketState implements Data<CpuState[]> {

	/**
	 * Serial code version <code>serialVersionUID</code> 
	 */
	private static final long serialVersionUID = 1387416957174445700L;
	
	private final CpuSocket socket;
	
	private final Frequency currentFrequency; 

	public CpuSocketState(CpuSocket socket, Frequency frequency) {
		this.socket = Preconditions.checkNotNull(socket);
		this.currentFrequency = frequency;
	}

	@Override
	public CpuState[] value() {
		Collection<Cpu> cores = this.socket.cores().get();
		
		CpuState[] states = new CpuState[cores.size()];
				
		int i = 0;
		for(Cpu core : cores){
			states[i++] = core.getState(); 
		}		
		return states;
	}

	/**
	 * @return the currentFrequency
	 */
	public Frequency frequency() {
		return currentFrequency;
	}
}
