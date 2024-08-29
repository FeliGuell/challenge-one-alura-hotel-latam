package controller;

import java.sql.Connection;
import java.util.List;

import dao.LoginDAO;
import factory.ConnectionFactory;
import model.LoginModel;

/**
 * Clase LoginController.
 * 
 * Esta clase actúa como un controlador para gestionar las operaciones relacionadas con la entidad `Login`.
 * Se encarga de interactuar con la capa de acceso a datos (DAO) para realizar operaciones sobre los datos 
 * de login, como listar los registros existentes.
 */
public class LoginController {
	private LoginDAO loginDAO;

    	/**
     	* Constructor de LoginController.
     	* 
     	* Inicializa el controlador creando una conexión a la base de datos y configurando 
     	* el objeto `LoginDAO` que se utilizará para las operaciones de acceso a datos.
     	*/
	public LoginController() {
		Connection connection =  new ConnectionFactory().recuperarConexion();
		this.loginDAO = new LoginDAO(connection);		
	}

    	/**
     	* Lista todos los registros de login.
     	* 
     	* Este método delega la llamada a la capa de acceso a datos (DAO) para obtener 
     	* una lista de todos los registros de login en la base de datos.
     	* 
     	* @return una lista de objetos `LoginModel` que representan los registros de login.
     	*/
	public List<LoginModel> listar(){
		return loginDAO.listar();
	}
}
