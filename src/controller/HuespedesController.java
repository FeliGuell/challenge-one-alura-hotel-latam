package controller;

import java.sql.Connection;
import java.util.List;

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
	
	public List<Huespedes> listar(){
		return this.huespedesDAO.listar();
	}
	
	public Huespedes traeHuespedPorIdReserva(Integer idReserva) {
		return huespedesDAO.traeHuespedPorIdReserva(idReserva);
	}
}
