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
package greenapi.core.common.base;

import static com.google.common.base.Preconditions.checkNotNull;

import greenapi.core.model.exception.GreenApiException;

import java.lang.reflect.InvocationTargetException;


public final class ClassUtils {

	private ClassUtils() {
		throw new UnsupportedOperationException();
	}

	public static ClassLoader getDefaultClassLoader() {
		ClassLoader cl = null;
		try {
			cl = Thread.currentThread().getContextClassLoader();
		} catch (Throwable ignore) {
		}
		if (cl == null) {
			cl = ClassUtils.class.getClassLoader();
		}
		return cl;
	}

	/**
	 * Create a instance of a given class.
	 * 
	 * @param fullClassName
	 *            The name of the class to be instantiate.
	 * @return An instance of the given class.
	 * @throws GreenApiException
	 *             If the given class doesn't exist or if it is impossible to
	 *             instantiate its.
	 */
	public static Object newInstanceForName(String fullClassName) {
		try {
			return Class.forName(checkNotNull(fullClassName)).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException exception) {
			throw new GreenApiException(exception);
		}
	}

	public static Object newInstanceForName(String fullClassName,
			Object... args) {

		if (args == null || args.length == 0) {
			return newInstanceForName(fullClassName);
		} else {
			try {
				Class<?> clazz = Class.forName(checkNotNull(fullClassName));
				Class<?>[] argTypes = new Class[args.length];

				for (int i = 0; i < args.length; i++) {
					argTypes[i] = args[i] != null ? args[i].getClass() : null;
				}

				return clazz.getConstructor(clazz).newInstance(args);
			} catch (ClassNotFoundException | NoSuchMethodException
					| InvocationTargetException | IllegalAccessException
					| InstantiationException exception) {
				throw new GreenApiException(exception);
			}
		}
	}
}