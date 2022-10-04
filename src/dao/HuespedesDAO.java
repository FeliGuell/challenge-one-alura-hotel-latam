package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				pstm.setString(1, huesped.getNombre());
				pstm.setString(2, huesped.getApellido());
				pstm.setDate(3, huesped.getFecha_de_nacimiento());
				pstm.setString(4, huesped.getNacionalidad());
				pstm.setString(5, huesped.getTelefono());
				pstm.setInt(6, huesped.getId_reserva());
				
				pstm.executeUpdate();
				
				try(ResultSet rst = pstm.getGeneratedKeys()){
					while(rst.next()) {
						huesped.setId(rst.getInt(1));
					}
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
