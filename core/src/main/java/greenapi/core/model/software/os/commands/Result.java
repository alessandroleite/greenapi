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
