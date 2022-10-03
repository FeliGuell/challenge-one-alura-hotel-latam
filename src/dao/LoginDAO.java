package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.LoginModel;

public class LoginDAO {

	private Connection connection;

	public LoginDAO(Connection connection) {
		this.connection = connection;
	}

	public List<LoginModel> listar() {
		List<LoginModel> resultado = new ArrayList();

		try {
			final PreparedStatement statement = connection.prepareStatement("SELECT user, password FROM login");
			try (statement) {
				final ResultSet resultSet = statement.executeQuery();

				try (resultSet) {
					while (resultSet.next()) {
						var login = new LoginModel(resultSet.getString("USER"), resultSet.getString("PASSWORD"));

						resultado.add(login);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}
}