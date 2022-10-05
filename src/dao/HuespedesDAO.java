package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Huespedes;

public class HuespedesDAO {
	private Connection connection;

	public HuespedesDAO(Connection connection) {
		this.connection = connection;
	}

	public void guardar(Huespedes huesped) {
		try {
			String sql = "INSERT INTO huespedes (nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva) "
					+ "VALUES (?,?,?,?,?,?) ";
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, huesped.getNombre());
				pstm.setString(2, huesped.getApellido());
				pstm.setDate(3, huesped.getFecha_de_nacimiento());
				pstm.setString(4, huesped.getNacionalidad());
				pstm.setString(5, huesped.getTelefono());
				pstm.setInt(6, huesped.getId_reserva());

				pstm.executeUpdate();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						huesped.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Huespedes> listar() {
		List<Huespedes> resultado = new ArrayList();

		try {
			String sql = "SELECT id, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes";

			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.execute();

				try (ResultSet resultSet = ps.getResultSet()) {
					while (resultSet.next()) {
						Huespedes huesped = new Huespedes(resultSet.getInt("id"), resultSet.getString("nombre"),
								resultSet.getString("apellido"), resultSet.getDate("fecha_de_nacimiento"),
								resultSet.getString("nacionalidad"), resultSet.getString("telefono"),
								resultSet.getInt("id_reserva"));

						resultado.add(huesped);
					}
				}
			}
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Huespedes traeHuespedPorIdReserva(Integer idReserva) {
		Huespedes huesped = null;

		try {
			String sql = "SELECT id, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes WHERE id_reserva = ? ";

			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, idReserva);
				ps.execute();

				try (ResultSet resultSet = ps.getResultSet()) {
					while (resultSet.next()) {
						huesped = new Huespedes(resultSet.getInt("id"), resultSet.getString("nombre"),
								resultSet.getString("apellido"), resultSet.getDate("fecha_de_nacimiento"),
								resultSet.getString("nacionalidad"), resultSet.getString("telefono"),
								resultSet.getInt("id_reserva"));

					}
				}
			}
			return huesped;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
