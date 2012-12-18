package monitor.model.util;

/**
 * This interface is defined in order to describe the Observer role. All
 * Observers must be in accordance to this interface. The idea is to allow
 * each Observer to have a corresponding Subject
 * 
 * @author Alessandro
 */
public interface Observer {

	void update(Subject observable, Object ... args);
}
