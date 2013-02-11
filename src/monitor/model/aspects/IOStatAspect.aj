package monitor.model.aspects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import monitor.model.IOStat;
import monitor.model.IOStatProperty;
import monitor.model.Machine;
import monitor.model.dao.DbConnection;

public aspect IOStatAspect {

	// -------------------------------------------------------
	// - Inter-type declarations 							 -
	// -------------------------------------------------------

	private Integer IOStat.id;

	public void IOStat.setId(Integer id) {
		this.id = id;
	}

	public Integer IOStat.id() {
		return this.id;
	}

	static final String SQL_INSERT_STAT = "insert into io_stat (machine_id,device) values(?,?)";
	static final String SQL_INSERT_STAT_PROPERTY = "insert into io_stat_property (io_stat_id, name, value, timestamp_stat) values (?,?,?,?)";

	public void IOStat.update() throws SQLException {
		try (Connection connection = DbConnection.getInstance().getConnection()) {
			connection.setAutoCommit(false);
			this.update(connection);
			connection.commit();
		}
	}

	public void IOStat.update(Connection connection) throws SQLException {

		try (PreparedStatement pstt = connection
				.prepareStatement(SQL_INSERT_STAT)) {

			Integer stat_id = getIdByMachineAndDevice(this.machine(),
					this.device(), connection);

			if (stat_id == null) {
				int i = 0;
				pstt.setInt(++i, this.machine().id());
				pstt.setString(++i, this.device());				
				pstt.execute();
				this.setId(getIdByMachineAndDevice(this.machine(),
						this.device(), connection));
			} else {
				this.setId(stat_id);
			}

			this.update(connection, this);
		}
	}

	private void IOStat.update(Connection connection, IOStat stat)
			throws SQLException {
		try (PreparedStatement pstt = connection
				.prepareStatement(SQL_INSERT_STAT_PROPERTY)) {

			pstt.setInt(1, stat.id());
			for (IOStatProperty property : stat.properties()) {
				int i = 1;
				pstt.setString(++i, property.name());
				pstt.setDouble(++i, property.value());
				pstt.setTimestamp(++i, new Timestamp(property.timestamp()));
				pstt.execute();
			}
		}
	}

	private Integer IOStat.getIdByMachineAndDevice(Machine machine,
			String device, Connection connection) throws SQLException {

		try (PreparedStatement pstt = connection
				.prepareStatement("SELECT id, device FROM io_stat where machine_id = ? and lower(device) = lower(?)")) {

			pstt.setInt(1, machine.id());
			pstt.setString(2, device);

			try (ResultSet res = pstt.executeQuery()) {
				if (res.first()) {
					return res.getInt(1);
				}
			}
		}
		return null;
	}
}