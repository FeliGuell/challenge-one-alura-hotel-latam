package factory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * La clase TestConexion se utiliza para probar la conexión a la base de datos.
 * Verifica que se pueda establecer y cerrar una conexión utilizando ConnectionFactory.
 */
public class TestConexion {
	
    	/**
     	* Método principal que ejecuta la prueba de conexión a la base de datos.
     	*
     	* @param args Argumentos de línea de comandos (no se utilizan).
     	* @throws SQLException Si ocurre un error durante la conexión o cierre de la misma.
     	*/
	public static void main(String[] args) throws SQLException {
		
		// Se crea una instancia de ConnectionFactory, la cual está configurada
       		// para proporcionar conexiones a la base de datos usando un pool de conexiones.
		ConnectionFactory conFactory = new ConnectionFactory();
		Connection con = conFactory.recuperarConexion();

		System.out.println("Cerrando conexion");

		// Se cierra la conexión a la base de datos. Esto devuelve la conexión al pool si se está utilizando uno,
        	// o simplemente libera los recursos si no es un pool.
		con.close();
	}
}
