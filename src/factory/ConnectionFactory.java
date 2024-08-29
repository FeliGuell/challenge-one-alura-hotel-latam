package factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	// Instancia del DataSource que se usará para obtener conexiones a la base de datos.
	public DataSource dataSource;

	// Constructor de la clase ConnectionFactory.
	public ConnectionFactory() {
		// Se crea una nueva instancia de ComboPooledDataSource, una implementación de DataSource que soporta un pool de conexiones.
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();

		// Configuración de la URL de la base de datos MySQL a la que se va a conectar.
		// La URL incluye el nombre de la base de datos ('alura_hotel'), y parámetros para el uso de la zona horaria UTC.
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/alura_hotel?useTimezone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("root");

		// Se asigna el DataSource configurado a la variable de instancia 'dataSource'.
		dataSource = comboPooledDataSource;
	}

	// Método para recuperar una conexión a la base de datos desde el pool de conexiones.
	public Connection recuperarConexion() {
		try {
			 // Intenta obtener una conexión desde el DataSource y la retorna.
			return this.dataSource.getConnection();
		}catch(SQLException e) {
			// En caso de ocurrir una excepción SQL, se lanza una RuntimeException con la causa original.
			throw new RuntimeException(e);
		}
		
	}

}
