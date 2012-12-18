package monitor.model.builder;

import monitor.model.Resource;

import org.hyperic.sigar.SigarProxy;

public abstract class ResourceBuilder<T extends Resource> implements Builder<T>{

	public SigarProxy hypervisor() {
		return SigarFactory.getInstance();
	}
}
