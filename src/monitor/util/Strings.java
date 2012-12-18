package monitor.util;

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

}
