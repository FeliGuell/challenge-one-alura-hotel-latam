package factory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConexion {
	public static void main(String[] args) throws SQLException {
		ConnectionFactory conFactory = new ConnectionFactory();
		Connection con = conFactory.recuperarConexion();
		
		System.out.println("Cerrando conexion");
		
		con.close();
	}
}
