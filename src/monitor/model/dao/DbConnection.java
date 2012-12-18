package monitor.model.dao;

import static monitor.model.dao.DataSourceConfig.instance;
import static monitor.util.ClassUtils.newInstanceForName;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class DbConnection {

	private static final DbConnection INSTANCE = new DbConnection();
	
	private final BasicDataSource dataSource;
	
	private DbConnection() {
		this.dataSource = new BasicDataSource();
		configureDataSource();
	}
	
	public static DbConnection getInstance(){
		return INSTANCE;
	}
	
	public Connection getConnection() throws SQLException{
		return this.dataSource.getConnection();
	}

	private void configureDataSource() {

		dataSource.setLogAbandoned(true);
		dataSource.setDriverClassName(instance().getDriverClassName());
		dataSource.setUrl(instance().getUrl());
		dataSource.setUsername(instance().getUsername());
		dataSource.setPassword(instance().getPassword());
		dataSource.setInitialSize(instance().getInitialSize());
		dataSource.setMaxActive(instance().getMaxActive());
		dataSource.setMaxIdle(instance().getMaxIdle());
		dataSource.setMaxWait(instance().getMaxWait());
		dataSource.setTimeBetweenEvictionRunsMillis(instance().getTimeBetweenEvictionRuns());
		dataSource.setRemoveAbandoned(instance().isRemoveAbandoned());
		dataSource.setRemoveAbandonedTimeout(instance().getRemoveAbandonedTimeout());
		dataSource.setTestOnReturn(instance().isTestOnReturn());
		dataSource.setTestOnBorrow(instance().isTestOnBorrow());
		dataSource.setTestWhileIdle(instance().isTestWhileIdle());
		dataSource.setValidationQuery(instance().getValidationQuery());
		
		newInstanceForName(instance().getDriverClassName());
	}
}