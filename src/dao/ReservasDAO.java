package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Reservas;

public class ReservasDAO {
	private Connection connection;
	
	public ReservasDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void guardar(Reservas reserva) {
		try {
			String sql = "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_pago) "
					+ "VALUES (?,?,?,?) "; 
			try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				pstm.setDate(1, reserva.getFecha_entrada());
				pstm.setDate(2, reserva.getFecha_salida());
				pstm.setString(3, reserva.getValor());
				pstm.setString(4, reserva.getForma_pago());
				
				pstm.executeUpdate();
				
				try(ResultSet rst = pstm.getGeneratedKeys()){
					while(rst.next()) {
						reserva.setId(rst.getInt(1));
					}
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Integer traerUltimoRegistro() {
		Integer resultado = null;
		try {
			String sql = "SELECT id FROM reservas "
					+ "ORDER BY id DESC LIMIT 1"; 
			try(PreparedStatement pstm = connection.prepareStatement(sql)){	
				pstm.execute();		
				try(ResultSet rst = pstm.getResultSet()){
					while(rst.next()) {
						resultado = rst.getInt("ID");
					}
				}
			}
			return resultado;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reservas> listar(){
		List<Reservas> resultado = new ArrayList();
		
		try {
			String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_pago FROM reservas";
			
			try(PreparedStatement ps = connection.prepareStatement(sql)){
				ps.execute();
				
				try(ResultSet resultSet = ps.getResultSet()){
					while(resultSet.next()) {
						Reservas reserva = new Reservas(
								resultSet.getInt("id"),
								resultSet.getDate("fecha_entrada"),
								resultSet.getDate("fecha_salida"),
								resultSet.getString("valor"),
								resultSet.getString("forma_pago"));
						
						resultado.add(reserva);
					}
				}
			}
			return resultado;
		}catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
	
	public Reservas traeReservaPorId(Integer id) {
		Reservas reserva = null;
		try {
			String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_pago FROM reservas WHERE ID = ?";
			
			try(PreparedStatement ps = connection.prepareStatement(sql)){
				ps.setInt(1, id);
				ps.execute();
				
				try(ResultSet resultSet = ps.getResultSet()){
					while(resultSet.next()) {
						reserva = new Reservas(
								resultSet.getInt("id"),
								resultSet.getDate("fecha_entrada"),
								resultSet.getDate("fecha_salida"),
								resultSet.getString("valor"),
								resultSet.getString("forma_pago"));

					}
				}
			}
			return reserva;
		}catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
	
	public int modificar(Integer id, Date fecha_entrada, Date fecha_salida,String valor,String forma_pago) {
		try {
			String sql = "UPDATE reservas SET " 
					+ "fecha_entrada = ?,"
					+ "fecha_salida = ?," 
					+ "valor = ?,"
					+ "forma_pago = ?" 
					+ " WHERE id = ? ";

			try (PreparedStatement ps = connection.prepareStatement(sql)) {				
				ps.setDate(1, fecha_entrada);				
				ps.setDate(2, fecha_salida);
				ps.setString(3, valor);
				ps.setString(4, forma_pago);
				ps.setInt(5, id);
				ps.executeUpdate();
					
				System.out.println(String.format("Fue modificado el producto %s", ps));

				int updateCount = ps.getUpdateCount();
				
				return updateCount;
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
}
