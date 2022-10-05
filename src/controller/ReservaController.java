package controller;

import java.sql.Connection;
import java.util.List;

import dao.ReservasDAO;
import factory.ConnectionFactory;
import model.Huespedes;
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
	
	public Integer traerUltimoRegistro() {
		return reservaDAO.traerUltimoRegistro();
	}
	
	public List<Reservas> listar(){
		return reservaDAO.listar();
	}
	
	public Reservas traeReservaPorId(Integer id) {
		return reservaDAO.traeReservaPorId(id);
	}
	
	

	
}
