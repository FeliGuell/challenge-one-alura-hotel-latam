package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.LoginModel;

/**
 * Clase LoginDAO que se encarga de la interacción con la base de datos para la tabla 'login'.
 * Esta clase proporciona métodos para listar las credenciales de usuario desde la base de datos.
 */
public class LoginDAO {
	
	// Conexión a la base de datos.
	private Connection connection;

   	/**
    	* Constructor de la clase LoginDAO.
     	*
     	* @param connection La conexión a la base de datos.
     	*/
	public LoginDAO(Connection connection) {
		this.connection = connection;
	}

	/**
     	* Método que obtiene una lista de todas las credenciales de usuario en la tabla 'login'.
     	*
     	* @return Una lista de objetos LoginModel que contiene las credenciales de usuario.
     	*/
	public List<LoginModel> listar() {
		// Lista para almacenar el resultado.
		List<LoginModel> resultado = new ArrayList();

		try {
			// Preparar la consulta SQL para seleccionar los campos 'user' y 'password' de la tabla 'login'.
			final PreparedStatement statement = connection.prepareStatement("SELECT user, password FROM login");
			try (statement) {
				// Ejecutar la consulta y obtener los resultados.
				final ResultSet resultSet = statement.executeQuery();

				try (resultSet) {
					 // Recorrer el ResultSet y agregar cada registro a la lista 'resultado'.
					while (resultSet.next()) {
						var login = new LoginModel(resultSet.getString("USER"), resultSet.getString("PASSWORD"));

						resultado.add(login);
					}
				}
			}
		} catch (SQLException e) {
			// Si ocurre un error de SQL, lanzar una excepción de tiempo de ejecución.
			throw new RuntimeException(e);
		}
		// Devolver la lista de credenciales de usuario.
		return resultado;
	}
}
