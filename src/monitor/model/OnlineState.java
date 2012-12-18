package monitor.model;

public enum OnlineState {

	DISABLE(0), ENABLE(1);

	private final int value;

	private OnlineState(int value) {
		this.value = value;
	}

	public static OnlineState valueOf(int value) {

		for (OnlineState state : OnlineState.values()) {
			if (state.getValue() == value) {
				return state;
			}
		}
		return null;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
}
