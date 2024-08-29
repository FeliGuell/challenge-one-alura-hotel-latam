package controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import dao.HuespedesDAO;
import factory.ConnectionFactory;
import model.Huespedes;

/**
 * Clase HuespedesController.
 * 
 * Este controlador gestiona las operaciones relacionadas con la entidad `Huespedes`. 
 * Proporciona métodos para guardar, listar, modificar y eliminar registros de huéspedes 
 * en la base de datos, interactuando con la capa de acceso a datos (DAO).
 */
public class HuespedesController {
	private HuespedesDAO huespedesDAO;

    	/**
     	* Constructor de HuespedesController.
     	* 
     	* Inicializa el controlador creando una conexión a la base de datos y configurando 
     	* el objeto `HuespedesDAO` que se utilizará para las operaciones de acceso a datos.
     	*/
	public HuespedesController() {
		Connection connection =  new ConnectionFactory().recuperarConexion();
		this.huespedesDAO = new HuespedesDAO(connection);		
	}


    	/**
     	* Guarda un nuevo registro de huésped en la base de datos.
     	* 
     	* @param huespedes El objeto `Huespedes` que contiene los datos del huésped a guardar.
     	*/
	public void guardar(Huespedes huespedes) {
		this.huespedesDAO.guardar(huespedes);
	}


    	/**
     	* Lista todos los registros de huéspedes.
     	* 
     	* @return Una lista de objetos `Huespedes` que representan los registros de huéspedes en la base de datos.
     	*/
	public List<Huespedes> listar(){
		return this.huespedesDAO.listar();
	}


    	/**
     	* Obtiene un huésped asociado a un ID de reserva.
     	* 
     	* @param idReserva El ID de la reserva asociada al huésped.
     	* @return El objeto `Huespedes` correspondiente al ID de la reserva.
     	*/
	public Huespedes traeHuespedPorIdReserva(Integer idReserva) {
		return huespedesDAO.traeHuespedPorIdReserva(idReserva);
	}

    	/**
     	* Modifica un registro de huésped existente en la base de datos.
     	* 
     	* @param id El ID del huésped a modificar.
     	* @param nombre El nuevo nombre del huésped.
     	* @param apellido El nuevo apellido del huésped.
     	* @param fecha_de_nacimiento La nueva fecha de nacimiento del huésped.
     	* @param nacionalidad La nueva nacionalidad del huésped.
     	* @param telefono El nuevo número de teléfono del huésped.
     	* @param id_reserva El nuevo ID de la reserva asociada al huésped.
     	* @return El número de registros modificados en la base de datos.
     	*/
	public int modificar(Integer id, String nombre, String apellido, Date fecha_de_nacimiento, String nacionalidad,String telefono, Integer id_reserva) {
		return huespedesDAO.modificar(id, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva);
	}

    	/**
     	* Elimina un registro de huésped de la base de datos.
	* 
     	* @param id El ID del huésped a eliminar.
     	* @return El número de registros eliminados en la base de datos.
     	*/
	public int eliminar(Integer id) {
		return huespedesDAO.eliminar(id);
	}


    	/**
     	* Elimina un registro de huésped basado en el ID de la reserva.
     	* 
     	* @param id El ID de la reserva asociada al huésped a eliminar.
     	* @return El número de registros eliminados en la base de datos.
     	*/
	public int eliminarPorIdReserva(Integer id) {
		return huespedesDAO.eliminarPorIdReserva(id);
	}
}
