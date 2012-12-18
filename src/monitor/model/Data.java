package monitor.model;

import java.io.Serializable;

public interface Data<T> extends Serializable {

	T value();
}