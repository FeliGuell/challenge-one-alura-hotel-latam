package factory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConexion {
	
    	// Método principal que se ejecuta cuando el programa comienza.
    	// El método 'main' lanza una excepción de tipo SQLException.
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
