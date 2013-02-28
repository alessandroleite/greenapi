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
package greenapi.core.model.aspects;

import greenapi.core.model.data.Data;
import greenapi.core.model.resources.CpuSocket;
import greenapi.core.model.resources.Machine;
import greenapi.core.model.sensors.Sensor;
import greenapi.core.model.util.Observer;
import greenapi.core.model.util.Subject;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
	
	public Integer Machine.id(){
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
		//MachineDao.instance().persist(this);
		//TODO
	}

	private ScheduledExecutorService[] Machine.sensorsExecutorService;

	private void Machine.startMonitor() {
		Sensor<?, Data<?>>[] sensors = this.sensors();
		
		if (sensors != null) {
			this.sensorsExecutorService = new ScheduledThreadPoolExecutor[sensors.length];
			
			for (int i = 0; i < sensors.length; i++) {
				this.sensorsExecutorService[i] = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors());
				this.sensorsExecutorService[i].scheduleWithFixedDelay(sensors[i], 1, 1, TimeUnit.SECONDS);
			}
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
		
		//machine.memory().add(machine);
		machine.startMonitor();
	}
}