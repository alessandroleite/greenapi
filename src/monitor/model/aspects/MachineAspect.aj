package monitor.model.aspects;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import monitor.model.CpuSocket;
import monitor.model.Data;
import monitor.model.Machine;
import monitor.model.dao.MachineDao;
import monitor.model.sensors.Sensor;
import monitor.model.util.Observer;
import monitor.model.util.Subject;

/**
 * This aspect declares {@link Machine} objects as {@link Observer} and
 * complements the constructor call to add the machine as observer of CPUs and
 * start the sensors.
 * 
 * @author Alessandro
 */
public aspect MachineAspect {

	declare parents: Machine implements Observer;
	
	private Integer Machine.id;
	
	public void Machine.setId(Integer id) {
		this.id = id;
	}
	
	public Integer Machine.getId(){
		return this.id;
	}
	
	private ScheduledExecutorService Machine.persistExecutorService = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors());

	public void Machine.update(final Subject observable, final Object... args) {		
		this.persistExecutorService.execute(new Runnable() {
			public void run() {
				update();
			}
		}); 
		System.out.println(observable);
	}
	
	public void Machine.update() {
		MachineDao.instance().persist(this);
	}

	private ScheduledExecutorService[] Machine.sensorsExecutorService;

	private void Machine.startMonitor() {
		Sensor<?, Data<?>>[] sensors = this.sensors();
		this.sensorsExecutorService = new ScheduledThreadPoolExecutor[sensors.length];

		for (int i = 0; i < sensors.length; i++) {
			this.sensorsExecutorService[i] = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors());
			this.sensorsExecutorService[i].scheduleWithFixedDelay(sensors[i], 1, 1, TimeUnit.SECONDS);
		}
	}

	/**
	 * This after advice complements the state of the {@link Machine} object
	 * after the constructor call.
	 * 
	 * @param machine
	 *            The target object.
	 */
	after() returning (Machine machine): call(Machine.new(..)) {

		for (CpuSocket socket : machine.cpus()) {
			socket.add(machine);
		}
		machine.memory().add(machine);
		machine.startMonitor();
	}
}