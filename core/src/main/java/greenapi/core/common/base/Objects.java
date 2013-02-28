package greenapi.core.common.base;

import net.vidageek.mirror.dsl.Mirror;

public final class Objects {

	private Objects() {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("unchecked")
	public static <T extends Cloneable> T clone(T object) {
		if (object != null) {
			return (T) new Mirror().on(object).invoke().method("clone")
					.withoutArgs();
		}
		return null;
	}
}
