/**
 * Copyright (c) 2012 GreenI2R
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
package greenapi.core.model.resource.io;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import static java.util.Objects.*;
import java.util.concurrent.ConcurrentHashMap;

public class Storages implements Iterable<Storage> {

	/**
	 * The {@link Map} with the storages. The keys must be not null.
	 */
	private final Map<String, Storage> storages = new ConcurrentHashMap<>();

	@Override
	public Iterator<Storage> iterator() {
		return values().iterator();
	}

	/**
	 * Returns the storages in a unmodifiable {@link Collection}.
	 * @return The storages in a unmodifiable {@link Collection}.
	 */
	public Collection<Storage> values() {
		return Collections.unmodifiableCollection(storages.values());
	}

	/**
	 * @param storage
	 *            The disk to be added (attached).
	 * @return the previous disk state.
	 */
	public Storage add(Storage storage) {
		if (storage != null) {
			return this.storages.put(requireNonNull(storage.id()), storage);
		}
		return storage;
	}

	/**
	 * Returns a storage that has a given id.
	 * 
	 * @param id
	 *            The id of the {@link Storage} to be returned.
	 * @return The storage that has a given id.
	 */
	public Storage get(String id) {
		return this.storages.get(id);
	}

	/**
	 * 
	 */
	public Storage remove(Storage storage) {
		return this.storages.remove(requireNonNull(storage).id());
	}
}