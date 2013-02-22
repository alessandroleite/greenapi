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
package monitorapi.core.common.base;

import com.google.common.base.Preconditions;

public final class Strings {
	
	public static final String NEW_LINE = "\n";
	public static final String TAB = "\t";
	public static final String RETURN = "\r";
	

	private Strings(){
		throw new UnsupportedOperationException();
	}

	public static String format(double val, boolean multi) {
		String p = String.valueOf(val * (multi ? 100.0 : 1));
		int ix = p.indexOf(".") + 1;
		String percent = p.substring(0, ix) + p.substring(ix, ix + 1);
		return percent + "%";
	}
	
	public static String format(double val){
		return format(val,true);
	}
	
	public static String checkArgumentIsNullOrEmpty(String value) {
		Preconditions.checkArgument(!com.google.common.base.Strings.isNullOrEmpty(value));
		return value;
	}

}
