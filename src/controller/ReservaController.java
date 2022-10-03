package controller;

import java.sql.Connection;

import dao.ReservasDAO;
import factory.ConnectionFactory;
import model.Reservas;

public class ReservaController {
	private ReservasDAO reservaDAO;

	public ReservaController() {
		Connection connection =  new ConnectionFactory().recuperarConexion();
		this.reservaDAO = new ReservasDAO(connection);		
	}
	
	public void guardar(Reservas reservas) {
		this.reservaDAO.guardar(reservas);
	}
	

}
