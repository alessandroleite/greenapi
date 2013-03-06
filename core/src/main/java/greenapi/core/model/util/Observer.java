package greenapi.core.model.util;

public interface Observer {

	void update(Subject observable, Object ... args);
}
