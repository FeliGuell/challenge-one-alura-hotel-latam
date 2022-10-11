package controller;

import java.sql.Connection;
import java.sql.Date;
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
	
	public int modificar(Integer id, Date fecha_entrada, Date fecha_salida,String valor,String forma_pago) {
		return reservaDAO.modificar(id, fecha_entrada, fecha_salida, valor, forma_pago);
	}
	
	public int eliminar(Integer id) {
		return reservaDAO.eliminar(id);
	}
	
	

	
}
