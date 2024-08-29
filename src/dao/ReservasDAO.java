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

/**
 * Clase ReservasDAO.
 * 
 * Esta clase maneja las operaciones de acceso a datos relacionadas con la entidad `Reservas` 
 * en la base de datos. Se encarga de realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) 
 * sobre la tabla `reservas`.
 */
public class ReservasDAO {
	private Connection connection;

       /**
       * Constructor de ReservasDAO.
       * 
       * @param connection la conexión a la base de datos que se usará para las operaciones.
       */
	public ReservasDAO(Connection connection) {
		this.connection = connection;
	}

	
     	/**
        * Guarda una nueva reserva en la base de datos.
        * 
        * @param reserva el objeto `Reservas` que contiene los datos de la nueva reserva.
        */
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

				// Obtener la clave generada (ID) para la nueva reserva
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


    	/**
     	* Trae el ID del último registro insertado en la tabla `reservas`.
     	* 
     	* @return el ID del último registro, o null si no hay registros.
     	*/
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

    	/**
     	* Lista todas las reservas en la base de datos.
     	* 
     	* @return una lista de objetos `Reservas` que representan todas las reservas.
     	*/
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

    	/**
     	* Obtiene una reserva específica por su ID.
     	* 
     	* @param id el ID de la reserva a recuperar.
     	* @return el objeto `Reservas` correspondiente, o null si no se encuentra.
     	*/
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


    	/**
     	* Modifica una reserva existente en la base de datos.
     	* 
     	* @param id el ID de la reserva a modificar.
     	* @param fecha_entrada la nueva fecha de entrada.
     	* @param fecha_salida la nueva fecha de salida.
     	* @param valor el nuevo valor de la reserva.
     	* @param forma_pago la nueva forma de pago.
     	* @return el número de registros modificados.
     	*/
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
	

    	  /**
	  * Elimina una reserva de la base de datos.
	  * 
	  * @param id el ID de la reserva a eliminar.
	  * @return el número de registros eliminados.
	  */
	  public int eliminar(Integer id){     
	        try {     
	        	String sql = "DELETE FROM reservas WHERE id = ?";

	            try (PreparedStatement ps = connection.prepareStatement(sql)) {
	                ps.setInt(1, id);
	                ps.execute();

	                int updateCount = ps.getUpdateCount();

	                return updateCount;
	            }
	        }catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
	
	
}
