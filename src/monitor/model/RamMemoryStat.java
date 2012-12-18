package monitor.model;

import java.util.Collection;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class RamMemoryStat extends IOStat {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 4141705067361748420L;

	public RamMemoryStat(final String device) {
		super(device);
	}

	@Override
	public Collection<IOStatProperty> properties() {
		// Collection<IOStatProperty> properties = new ArrayList<IOStatProperty>(super.properties());
		return Collections2.filter(super.properties(), new Predicate<IOStatProperty>() {
					public boolean apply(IOStatProperty property) {
						return property.name().startsWith("dm");
					}
				});
	}
}