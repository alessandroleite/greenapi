package monitor.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import monitor.model.Cpu;
import monitor.model.CpuSocket;
import monitor.model.Machine;

public final class MachineDao {
	
	private static final MachineDao INSTANCE = new MachineDao();
	
	private MachineDao() {
		super();
	}
	
	public static MachineDao instance(){
		return INSTANCE;
	}

	public synchronized void persist(Machine machine) {
		Connection connection = DbConnection.getInstance().getConnection();
		PreparedStatement pstt = null;

		try {
			connection.setAutoCommit(false);

			Integer id = this.getIdByName(machine.name(), connection);
			if (id == null) {
				pstt = connection.prepareStatement("INSERT INTO machine (name) values (?)");
				pstt.setString(1, machine.name());

				pstt.execute();
				id = this.getIdByName(machine.name(), connection);
			}

			machine.setId(id);
			this.persist(connection, machine, machine.cpus());
			connection.commit();

		} finally {
			closeQuiently(pstt, connection);
		}
	}

	private Integer getIdByName(String name, Connection connection) throws SQLException {
		PreparedStatement pstt = null;
		ResultSet res = null;

		try {

			pstt = connection.prepareStatement("SELECT id, name FROM machine where name = ?");
			pstt.setString(1, name);
			res = pstt.executeQuery();

			if (res.first()) {
				return res.getInt(1);
			}

		} finally {
			closeQuiently(res, pstt);
		}
		return null;
	}

	private void persist(Connection connection, Machine machine, CpuSocket[] cpus) {

		for (CpuSocket cpuSocket : cpus) {
			for (Cpu cpu : cpuSocket.getCores()) {
				this.persist(connection, machine, cpu);
			}
		}
	}

	private void persist(Connection connection, Machine machine, Cpu cpu) {
		PreparedStatement pstt = null;

		try {
			pstt = connection.prepareStatement("insert into cpu_monitoring (machine_id, cpu_id, date, combined, "
							+ " user, system, nice, wait, idle, temperature, frequency) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			int i = 0;

			pstt.setInt(++i, machine.getId());
			pstt.setInt(++i, cpu.id());
			pstt.setTimestamp(++i, new Timestamp(cpu.getState().getTime()));
			
			pstt.setDouble(++i, cpu.getState().getCombined());
			pstt.setDouble(++i, cpu.getState().getUser());
			pstt.setDouble(++i, cpu.getState().getSys());
			pstt.setDouble(++i, cpu.getState().getNice());
			pstt.setDouble(++i, cpu.getState().getWait());
			pstt.setDouble(++i, cpu.getState().getIdle());
			pstt.setDouble(++i, cpu.getTemperature().getValue());
			pstt.setLong(++i, cpu.getState().getFrequency().value());
			
			pstt.execute();

		} finally {
			if (pstt != null)
				closeQuiently(pstt);
		}
	}

	private void closeQuiently(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException ignore) {
			}
		}
	}

	private void closeQuiently(ResultSet res, Statement pstt) {

		if (res != null) {
			try {
				res.close();
			} catch (SQLException ignore) {
			}
		}

		closeQuiently(pstt);
	}

	private void closeQuiently(Statement pstt, Connection connection) {
		closeQuiently(null, pstt, connection);
	}

	private void closeQuiently(ResultSet res, Statement stmt,
			Connection connection) {

		closeQuiently(res, stmt);

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException ignore) {
			}
		}
	}
}