package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Huespedes;

public class HuespedesDAO {
	// Instancia de Connection que se usará para interactuar con la base de datos.
	private Connection connection;

	// Constructor que recibe una conexión a la base de datos y la asigna a la variable de instancia.
	public HuespedesDAO(Connection connection) {
		this.connection = connection;
	}

	// Método para guardar un nuevo huésped en la base de datos.
	public void guardar(Huespedes huesped) {
		try {
			// SQL para insertar un nuevo registro en la tabla 'huespedes'.
			String sql = "INSERT INTO huespedes (nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva) "
					+ "VALUES (?,?,?,?,?,?) ";
			
			 // Se prepara la declaración SQL y se configura para que devuelva las claves generadas.
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, huesped.getNombre());
				pstm.setString(2, huesped.getApellido());
				pstm.setDate(3, huesped.getFecha_de_nacimiento());
				pstm.setString(4, huesped.getNacionalidad());
				pstm.setString(5, huesped.getTelefono());
				pstm.setInt(6, huesped.getId_reserva());

				// Se ejecuta la actualización (inserción) en la base de datos.
				pstm.executeUpdate();

				// Se recupera la clave generada para el nuevo registro y se asigna al objeto 'huesped'.
				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						huesped.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			// Si ocurre una excepción SQL, se lanza una RuntimeException con la causa original.
            throw new RuntimeException(e);
			throw new RuntimeException(e);
		}
	}

	 // Método para listar todos los registros de huéspedes en la base de datos.
	public List<Huespedes> listar() {
		List<Huespedes> resultado = new ArrayList();

		try {
			// SQL para seleccionar todos los campos de la tabla 'huespedes'.
			String sql = "SELECT id, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes";

			// Se prepara la declaración SQL.
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				// Se ejecuta la consulta en la base de datos.
				ps.execute();
				
				// Se obtiene el ResultSet con los resultados de la consulta.
				try (ResultSet resultSet = ps.getResultSet()) {
					// Se itera sobre los resultados y se crean objetos Huespedes que se agregan a la lista 'resultado'.
					while (resultSet.next()) {
						Huespedes huesped = new Huespedes(resultSet.getInt("id"), resultSet.getString("nombre"),
								resultSet.getString("apellido"), resultSet.getDate("fecha_de_nacimiento"),
								resultSet.getString("nacionalidad"), resultSet.getString("telefono"),
								resultSet.getInt("id_reserva"));

						resultado.add(huesped);
					}
				}
			}
			// Se retorna la lista de huéspedes.
			return resultado;
		} catch (SQLException e) {
			// Si ocurre una excepción SQL, se lanza una RuntimeException con la causa original.
			throw new RuntimeException(e);
		}
	}

	// Método para obtener un huésped según su id de reserva.
	public Huespedes traeHuespedPorIdReserva(Integer idReserva) {
		Huespedes huesped = null;

		try {
			// SQL para seleccionar un huésped basado en su 'id_reserva'.
			String sql = "SELECT id, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes WHERE id_reserva = ? ";

			// Se prepara la declaración SQL.
			try (PreparedStatement ps = connection.prepareStatement(sql)) {
				// Se establece el valor del 'id_reserva' en la declaración SQL.
				ps.setInt(1, idReserva);
				// Se ejecuta la consulta en la base de datos.
				ps.execute();

				// Se obtiene el ResultSet con los resultados de la consulta.
				try (ResultSet resultSet = ps.getResultSet()) {
					// Si hay un resultado, se crea un objeto Huespedes y se asigna a 'huesped'.
					while (resultSet.next()) {
						huesped = new Huespedes(resultSet.getInt("id"), resultSet.getString("nombre"),
								resultSet.getString("apellido"), resultSet.getDate("fecha_de_nacimiento"),
								resultSet.getString("nacionalidad"), resultSet.getString("telefono"),
								resultSet.getInt("id_reserva"));

					}
				}
			}
			// Se retorna el huésped encontrado.
			return huesped;
		} catch (SQLException e) {
			// Si ocurre una excepción SQL, se lanza una RuntimeException con la causa original.
			throw new RuntimeException(e);
		}
	}

	// Método para modificar los datos de un huésped en la base de datos.
	public int modificar(Integer id, String nombre, String apellido, Date fecha_de_nacimiento, String nacionalidad,String telefono, Integer id_reserva) {
		try {
			 // SQL para actualizar los datos de un huésped existente.
			String sql = "UPDATE huespedes SET " 
					+ "nombre = ?,"
					+ "apellido = ?," 
					+ "fecha_de_nacimiento = ?,"
					+ "nacionalidad = ?,"
					+ "telefono = ?,"
					+ "id_reserva = ?" 
					+ " WHERE id = ? ";

			// Se prepara la declaración SQL.
			try (PreparedStatement ps = connection.prepareStatement(sql)) {	
				// Se establecen los nuevos valores en la declaración SQL.
				ps.setString(1, nombre);				
				ps.setString(2, apellido);
				ps.setDate(3, fecha_de_nacimiento);
				ps.setString(4, nacionalidad);
				ps.setString(5, telefono);
				ps.setInt(6, id_reserva);
				ps.setInt(7, id);

				// Se ejecuta la actualización en la base de datos.
				ps.executeUpdate();
					
				System.out.println(String.format("Fue modificado el producto %s", ps));

				// Se obtiene el número de filas afectadas por la actualización.
				int updateCount = ps.getUpdateCount();

				// Se retorna el número de filas afectadas.
				return updateCount;
			}

		} catch(SQLException e) {
			// Si ocurre una excepción SQL, se lanza una RuntimeException con la causa original.
			throw new RuntimeException(e);
		}

	}
	

	  // Método para eliminar un huésped de la base de datos según su id.
	  public int eliminar(Integer id){     
	        try {     
			// SQL para eliminar un registro de la tabla 'huespedes' basado en el 'id'.
	        	String sql = "DELETE FROM huespedes WHERE id = ?";

		    // Se prepara la declaración SQL.
	            try (PreparedStatement ps = connection.prepareStatement(sql)) {
			// Se establece el valor del 'id' en la declaración SQL.
	                ps.setInt(1, id);

			// Se ejecuta la eliminación en la base de datos.
	                ps.execute();

			// Se obtiene el número de filas afectadas por la eliminación.    
	                int updateCount = ps.getUpdateCount();

			// Se retorna el número de filas afectadas.    
	                return updateCount;
	            }
	        }catch (SQLException e) {
		    // Si ocurre una excepción SQL, se lanza una RuntimeException con la causa original.	
	            throw new RuntimeException(e);
	        }
	    }

	  // Método para eliminar un huésped de la base de datos según su id de reserva.
	  public int eliminarPorIdReserva(Integer id){     
	        try {   
			// SQL para eliminar registros de la tabla 'huespedes' basado en el 'id_reserva'.
	        	String sql = "DELETE FROM huespedes WHERE id_reserva = ?";
			
		    // Se prepara la declaración SQL.
	            try (PreparedStatement ps = connection.prepareStatement(sql)) {
			// Se establece el valor del 'id_reserva' en la declaración SQL.    
	                ps.setInt(1, id);
			// Se ejecuta la eliminación en la base de datos.    
	                ps.execute();

			// Se obtiene el número de filas afectadas por la eliminación.    
	                int updateCount = ps.getUpdateCount();

			// Se retorna el número de filas afectadas.    
	                return updateCount;
	            }
	        }catch (SQLException e) {
		    // Si ocurre una excepción SQL, se lanza una RuntimeException con la causa original.	
	            throw new RuntimeException(e);
	        }
	    }
	
	
}
