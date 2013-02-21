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
package monitorapi.core.model;

public final class Frequency implements Data<Long>, Comparable<Frequency> {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 361164863478257017L;

	private final long value;

	private static final Long UNKNOWN = 0L;

	public static final Frequency NULL_FREQUENCY = new Frequency(UNKNOWN);

	public Frequency(Long value) {
		this.value = (value == null ? UNKNOWN : value);
	}
	
	public static Frequency valueOf(Long value) {
		return new Frequency(value);
	}

	@Override
	public Long value() {
		return this.value;
	}

	/**
	 * Returns the frequency value in MHz.
	 * 
	 * @return The frequency value in MHz.
	 */
	public double inMhz() {
		return this.value() / 1000000d;
	}
	
	@Override
	public int compareTo(Frequency other) {
		return this.value().compareTo(other.value());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (value ^ (value >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		Frequency other = (Frequency) obj;
		if (value != other.value)
			return false;

		return true;
	}
	
	@Override
	public String toString() {
		return this.value().toString();
	}
}