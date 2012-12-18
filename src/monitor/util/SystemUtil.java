package monitor.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public final class SystemUtil {
	
	private static final String LINUX_OS = "Linux";
	
	private SystemUtil(){
		throw new UnsupportedOperationException();
	}
	
	public static String osName(){
		return System.getProperty("os.name");
	}
	
	public static String machineName(){
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException exception) {
			return null;
		}
	}
	
	public static boolean isLinuxOS() {
		return LINUX_OS.equalsIgnoreCase(osName());
	}
}