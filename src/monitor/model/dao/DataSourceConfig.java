package monitor.model.dao;

import java.io.IOException;
import java.util.Properties;

import static monitor.util.PropertiesUtils.*;

public final class DataSourceConfig {

	private static final DataSourceConfig instance = new DataSourceConfig();

	private static Properties properties;

	static {
		try {
			configure(properties = loadPropertyFile("database.properties"));
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	private String driverClassName;
	private String url;
	private String username;
	private String password;
	private Integer initialSize;
	private Integer maxActive;
	private Integer maxIdle;
	private long maxWait;
	private long timeBetweenEvictionRuns;
	private boolean removeAbandoned;
	private int removeAbandonedTimeout;
	private boolean testOnReturn;
	private boolean testOnBorrow;
	private boolean testWhileIdle;
	private String validationQuery;
	private String connectionProperties;

	private DataSourceConfig() {
		super();
	}

	
	public static DataSourceConfig configure(Properties properties) {
		configure(properties.getProperty("jdbc.driverClassName"),
				properties.getProperty("jdbc.url"),
				properties.getProperty("jdbc.username"),
				properties.getProperty("jdbc.password"),
				Integer.parseInt(properties.getProperty(
						"jdbc.initial.size.connections", "5")),
				Integer.parseInt(properties.getProperty(
						"jdbc.max.active.connections", "30")),
				Integer.parseInt(properties.getProperty(
						"jdbc.max.idle.connections", "10")),
				Long.parseLong(properties.getProperty(
						"jdbc.max.wait.connections", "5")),
				Long.parseLong(properties.getProperty(
						"jdbc.time.between.eviction.runs", "10")),
				Boolean.parseBoolean(properties.getProperty(
						"jdbc.remove.abandoned.connections", "true")),
				Integer.parseInt(properties.getProperty(
						"jdbc.remove.abandoned.timeout", "300")),
				Boolean.parseBoolean(properties.getProperty(
						"jdbc.test.on.return", "true")),
				Boolean.parseBoolean(properties.getProperty(
						"jdbc.test.on.borrow", "true")),
				Boolean.parseBoolean(properties.getProperty(
						"jdbc.test.while.idle", "true")),
				properties.getProperty("jdbc.validation.query"),
				properties.getProperty("jdbc.connection.properties"));
		return instance();
	}

	public static DataSourceConfig configure(String driverClassName,
			String url, String username, String password, Integer initialSize,
			Integer maxActive, Integer maxIdle, long maxWait,
			long timeBetweenEvictionRuns, boolean removeAbandoned,
			int removeAbandonedTimeout, boolean testOnReturn,
			boolean testOnBorrow, boolean testWhileIdle,
			String validationQuery, String connectionProperties) {
		instance().driverClassName = driverClassName;
		instance().url = url;
		instance().username = username;
		instance().password = password;
		instance().initialSize = initialSize;
		instance().maxActive = maxActive;
		instance().maxIdle = maxIdle;
		instance().maxWait = maxWait;
		instance().timeBetweenEvictionRuns = timeBetweenEvictionRuns * 1000;
		instance().removeAbandoned = removeAbandoned;
		instance().removeAbandonedTimeout = removeAbandonedTimeout;
		instance().testOnReturn = testOnReturn;
		instance().testOnBorrow = testOnBorrow;
		instance().testWhileIdle = testWhileIdle;
		instance().validationQuery = validationQuery;
		instance().connectionProperties = connectionProperties;

		return instance();
	}

	public static DataSourceConfig instance() {
		return instance;
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	/**
	 * @return the driverClassName
	 */
	public String getDriverClassName() {
		return driverClassName;
	}

	/**
	 * @param driverClassName
	 *            the driverClassName to set
	 */
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the initialSize
	 */
	public Integer getInitialSize() {
		return initialSize;
	}

	/**
	 * @param initialSize
	 *            the initial Size to set
	 */
	public void setInitialSize(Integer initialSize) {
		this.initialSize = initialSize;
	}

	/**
	 * @return the maxActive
	 */
	public Integer getMaxActive() {
		return maxActive;
	}

	/**
	 * @param maxActive
	 *            the maxActive to set
	 */
	public void setMaxActive(Integer maxActive) {
		this.maxActive = maxActive;
	}

	/**
	 * @return the maxIdle
	 */
	public Integer getMaxIdle() {
		return maxIdle;
	}

	/**
	 * @param maxIdle
	 *            the maxIdle to set
	 */
	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}

	/**
	 * @return the maxWait
	 */
	public long getMaxWait() {
		return maxWait;
	}

	/**
	 * @param maxWait
	 *            the maxWait to set
	 */
	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}

	/**
	 * @return the removeAbandoned
	 */
	public boolean isRemoveAbandoned() {
		return removeAbandoned;
	}

	/**
	 * @param removeAbandoned
	 *            the removeAbandoned to set
	 */
	public void setRemoveAbandoned(boolean removeAbandoned) {
		this.removeAbandoned = removeAbandoned;
	}

	/**
	 * @return the removeAbandonedTimeout
	 */
	public int getRemoveAbandonedTimeout() {
		return removeAbandonedTimeout;
	}

	/**
	 * @param removeAbandonedTimeout
	 *            the removeAbandonedTimeout to set
	 */
	public void setRemoveAbandonedTimeout(int removeAbandonedTimeout) {
		this.removeAbandonedTimeout = removeAbandonedTimeout;
	}

	/**
	 * @return the testOnReturn
	 */
	public boolean isTestOnReturn() {
		return testOnReturn;
	}

	/**
	 * @param testOnReturn
	 *            the testOnReturn to set
	 */
	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	/**
	 * @return the testOnBorrow
	 */
	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	/**
	 * @param testOnBorrow
	 *            the testOnBorrow to set
	 */
	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	/**
	 * @return the testWhileIdle
	 */
	public boolean isTestWhileIdle() {
		return testWhileIdle;
	}

	/**
	 * @param testWhileIdle
	 *            the testWhileIdle to set
	 */
	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	/**
	 * @return the validationQuery
	 */
	public String getValidationQuery() {
		return validationQuery;
	}

	/**
	 * @param validationQuery
	 *            the validationQuery to set
	 */
	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}

	public void setConnectionProperties(String connectionProperties) {
		this.connectionProperties = connectionProperties;
	}

	/**
	 * @return the connectionProperties
	 */
	public String getConnectionProperties() {
		return connectionProperties;
	}

	/**
	 * @return the timeBetweenEvictionRuns
	 */
	public long getTimeBetweenEvictionRuns() {
		return timeBetweenEvictionRuns;
	}

	/**
	 * @param timeBetweenEvictionRuns
	 *            the timeBetweenEvictionRuns to set
	 */
	public void setTimeBetweenEvictionRuns(long timeBetweenEvictionRuns) {
		this.timeBetweenEvictionRuns = timeBetweenEvictionRuns;
	}
}