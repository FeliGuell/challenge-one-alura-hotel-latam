package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
