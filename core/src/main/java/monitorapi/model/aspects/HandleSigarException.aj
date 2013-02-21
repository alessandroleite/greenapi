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
package monitorapi.model.aspects;

import monitorapi.model.exception.ResourceException;

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
