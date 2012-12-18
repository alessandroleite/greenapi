package monitor.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class IOStats implements Data<List<IOStat>>, Iterable<IOStat> {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -64961862962649080L;

	private final List<IOStat> list;

	public IOStats(IOStat[] data) {
		this(Arrays.asList(data));
	}

	public IOStats(List<IOStat> measures) {
		this.list = new ArrayList<IOStat>(measures);
	}

	@Override
	public List<IOStat> value() {
		return Collections.unmodifiableList(list);
	}

	@Override
	public Iterator<IOStat> iterator() {
		return value().iterator();
	}
}