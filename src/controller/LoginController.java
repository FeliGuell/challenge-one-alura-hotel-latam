package controller;

import java.sql.Connection;
import java.util.List;

import dao.LoginDAO;
import factory.ConnectionFactory;
import model.LoginModel;

public class LoginController {
	private LoginDAO loginDAO;
	
	public LoginController() {
		Connection connection =  new ConnectionFactory().recuperarConexion();
		this.loginDAO = new LoginDAO(connection);		
	}
	
	public List<LoginModel> listar(){
		return loginDAO.listar();
	}
}
