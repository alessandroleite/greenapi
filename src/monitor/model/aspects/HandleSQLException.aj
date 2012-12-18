package monitor.model.aspects;

import java.sql.SQLException;

public aspect HandleSQLException {

	declare soft: SQLException: throwsMonitoringException();

	pointcut throwsMonitoringException(): (within(monitor.model.dao..*) && execution(* persist(..)));

	Object around(): throwsMonitoringException() {
		try {
			return proceed();
		} catch (SQLException exception) {
			// throw new MonitoringException();
			exception.printStackTrace();
			System.exit(1);
			return thisJoinPoint.getTarget();
		}
	}
}
