package greenapi.core.common.primitives;

public final class Doubles {
	
	private Doubles(){
		throw new UnsupportedOperationException();
	}

	public static double valueOf(String value) {
		try {
			return value == null ? 0.d : Double.parseDouble(value.trim());
		} catch (NumberFormatException exception) {
			return 0.d;
		}
	}
}
