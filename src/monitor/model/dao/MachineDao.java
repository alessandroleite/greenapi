package monitor.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import monitor.model.Cpu;
import monitor.model.CpuSocket;
import monitor.model.IOStat;
import monitor.model.Machine;

public final class MachineDao {

	private static final MachineDao INSTANCE = new MachineDao();

	private MachineDao() {
		super();
	}

	public static MachineDao instance() {
		return INSTANCE;
	}

	public synchronized void persist(Machine machine) {

		try (Connection connection = DbConnection.getInstance().getConnection();
				PreparedStatement pstt = connection
						.prepareStatement("INSERT INTO machine (name) values (?)")) {

			connection.setAutoCommit(false);
			Integer id = this.getIdByName(machine.name(), connection);

			if (id == null) {
				pstt.setString(1, machine.name());

				pstt.execute();
				id = this.getIdByName(machine.name(), connection);
			}

			machine.setId(id);
			this.persist(connection, machine, machine.cpus());
			
			for(IOStat stat: machine.ioStats()){
				stat.update(connection);
			}
			
			connection.commit();
		}
	}

	private Integer getIdByName(String name, Connection connection)
			throws SQLException {

		try

		(PreparedStatement pstt = connection
				.prepareStatement("SELECT id, name FROM machine where name = ?")) {
			pstt.setString(1, name);

			try (ResultSet res = pstt.executeQuery()) {

				if (res.first()) {
					return res.getInt(1);
				}
			}

		}
		return null;
	}

	private void persist(Connection connection, Machine machine,
			CpuSocket[] cpus) throws SQLException {

		for (CpuSocket cpuSocket : cpus) {
			for (Cpu cpu : cpuSocket.cores()) {
				this.persist(connection, machine, cpu);
			}
		}
	}

	private void persist(Connection connection, Machine machine, Cpu cpu)
			throws SQLException {

		try (PreparedStatement pstt = connection
				.prepareStatement("insert into cpu_monitoring (machine_id, cpu_id, date, combined, "
						+ " user, system, nice, wait, idle, temperature, frequency) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

			int i = 0;

			pstt.setInt(++i, machine.id());
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

		}
	}

}