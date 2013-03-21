/**
 * Copyright (c) 2012 I2RGreen
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
package greenapi.core.model.software.os.commands.impl.linux;

import greenapi.core.model.data.User;
import greenapi.core.model.software.os.commands.Who;
import greenapi.core.model.software.os.commands.impl.ExecutableCommand;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;


public class Whoami extends ExecutableCommand<User> implements Who {

	static final User ROOT = User.newRootUser("root");

	public Whoami() {
		super("whoami");
	}

	@Override
	public boolean isRootRequired() {
		return false;
	}

	@Override
	protected boolean isRoot() {
		return this.result().getValue().isRoot();
	}

	@Override
	protected User parser(InputStream input) throws IOException {
		String commandResult = IOUtils.toString(input).trim();
		return new User(commandResult, ROOT.name().equals(commandResult));
	}

	@Override
	public boolean isAdmin() {
		return isRoot();
	}
}