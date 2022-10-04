package controller;

import java.sql.Connection;

import dao.HuespedesDAO;
import factory.ConnectionFactory;
import model.Huespedes;

public class HuespedesController {
	private HuespedesDAO huespedesDAO;
	
	public HuespedesController() {
		Connection connection =  new ConnectionFactory().recuperarConexion();
		this.huespedesDAO = new HuespedesDAO(connection);		
	}
	
	public void guardar(Huespedes huespedes) {
		this.huespedesDAO.guardar(huespedes);
	}
}
