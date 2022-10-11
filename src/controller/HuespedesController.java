package controller;

import java.sql.Connection;
import java.sql.Date;
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
	
	public int modificar(Integer id, String nombre, String apellido, Date fecha_de_nacimiento, String nacionalidad,String telefono, Integer id_reserva) {
		return huespedesDAO.modificar(id, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva);
	}
	
	public int eliminar(Integer id) {
		return huespedesDAO.eliminar(id);
	}
	
	public int eliminarPorIdReserva(Integer id) {
		return huespedesDAO.eliminarPorIdReserva(id);
	}
}
