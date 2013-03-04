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
package greenapi.core.model.software.os.commands;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Result<T> implements Serializable {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 8320691434005267590L;

	private final T value;

	private final Map<String, Throwable> errors = new HashMap<>();

	public Result(T value) {
		this.value = value;
	}
	
	public Result(T value, Throwable ... errors) {
		this.value = value;
	}

	public void add(Throwable error) {
		this.addAll(Collections.singletonMap(error.getMessage(), error));
	}

	public void addAll(Map<String, Throwable> errors) {
		for (String key : errors.keySet()) {
			this.errors.put(key, errors.get(key));
		}
	}

	public Map<String, Throwable> getErrors() {
		return Collections.unmodifiableMap(this.errors);
	}

	public boolean wasSuccessfully() {
		return this.errors.isEmpty();
	}

	public T getValue() {
		return value;
	}
}
