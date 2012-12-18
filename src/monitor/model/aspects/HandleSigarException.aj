package monitor.model.aspects;

import monitor.model.exception.ResourceException;

import org.aspectj.lang.SoftException;
import org.hyperic.sigar.SigarException;

public aspect HandleSigarException {

	/**
	 * Declare {@link SigarException} soft to enable use by clients that not
	 * declared to handle {@link SigarException}.
	 */
	declare soft: org.hyperic.sigar.SigarException : throwsResourceException();

	/**
	 * Pick out join points to convert {@link SigarException} to
	 * {@link ResourceException}. This implementation picks out execution of
	 * method declared to throw {@link SigarException} in our project.
	 */
	pointcut throwsResourceException(): ( 
			(within(monitor.model.builder..*) || within(monitor.model.sensors..*) ) && 
			( execution (* fetch(..)) || execution (* collect(..) )  ) );

	/**
	 * This around advice converts {@link SigarException} to
	 * {@link ResourceException} at all join points picked out by
	 * {@link #throwsResourceException()}. That means *no*
	 * {@link SigarException} will be thrown from this join point, and thus that
	 * none will be converted the AspectJ runtime to {@link SoftException}.
	 * 
	 * @return
	 */
	Object around(): throwsResourceException() {
		try {
			return proceed();
		} catch (SigarException exception) {
			throw new ResourceException(exception);
		}
	}
}
