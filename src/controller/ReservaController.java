package controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import dao.ReservasDAO;
import factory.ConnectionFactory;
import model.Huespedes;
import model.Reservas;


/**
 * Clase ReservaController.
 * 
 * Este controlador gestiona las operaciones relacionadas con la entidad `Reservas`.
 * Proporciona métodos para guardar, listar, modificar y eliminar registros de reservas
 * en la base de datos, interactuando con la capa de acceso a datos (DAO).
 */
public class ReservaController {
	private ReservasDAO reservaDAO;


    	/**
     	* Constructor de ReservaController.
     	* 
     	* Inicializa el controlador creando una conexión a la base de datos y configurando
     	* el objeto `ReservasDAO` que se utilizará para las operaciones de acceso a datos.
     	*/
	public ReservaController() {
		Connection connection =  new ConnectionFactory().recuperarConexion();
		this.reservaDAO = new ReservasDAO(connection);		
	}

    	/**
     	* Guarda un nuevo registro de reserva en la base de datos.
     	* 
     	* @param reservas El objeto `Reservas` que contiene los datos de la reserva a guardar.
     	*/
	public void guardar(Reservas reservas) {
		this.reservaDAO.guardar(reservas);
	}

    	/**
     	* Obtiene el ID del último registro de reserva en la base de datos.
     	* 
     	* @return El ID de la última reserva registrada.
     	*/
	public Integer traerUltimoRegistro() {
		return reservaDAO.traerUltimoRegistro();
	}

    	/**
     	* Lista todos los registros de reservas.
     	* 
     	* @return Una lista de objetos `Reservas` que representan los registros de reservas en la base de datos.
     	*/
	public List<Reservas> listar(){
		return reservaDAO.listar();
	}
	
    /**
     * Obtiene una reserva por su ID.
     * 
     * @param id El ID de la reserva que se desea obtener.
     * @return El objeto `Reservas` correspondiente al ID proporcionado.
     */
    public Reservas traeReservaPorId(Integer id) {
        return reservaDAO.traeReservaPorId(id);
    }

    /**
     * Modifica un registro de reserva existente en la base de datos.
     * 
     * @param id El ID de la reserva a modificar.
     * @param fecha_entrada La nueva fecha de entrada de la reserva.
     * @param fecha_salida La nueva fecha de salida de la reserva.
     * @param valor El nuevo valor de la reserva.
     * @param forma_pago La nueva forma de pago de la reserva.
     * @return El número de registros modificados en la base de datos.
     */
    public int modificar(Integer id, Date fecha_entrada, Date fecha_salida, String valor, String forma_pago) {
        return reservaDAO.modificar(id, fecha_entrada, fecha_salida, valor, forma_pago);
    }

    /**
     * Elimina un registro de reserva de la base de datos.
     * 
     * @param id El ID de la reserva a eliminar.
     * @return El número de registros eliminados en la base de datos.
     */
    public int eliminar(Integer id) {
        return reservaDAO.eliminar(id);
    }
	

}
