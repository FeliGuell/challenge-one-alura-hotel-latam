package model;

import java.sql.Date;

public class Huespedes {
	private Integer id;
	private String nombre;
	private String apellido;
	private Date fecha_de_nacimiento;
	private String nacionalidad;
	private String telefono;
	private Integer id_reserva;
	
	
	
	
	
	public Huespedes(Integer id, String nombre, String apellido, Date fecha_de_nacimiento, String nacionalidad,
			String telefono, Integer id_reserva) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_de_nacimiento = fecha_de_nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.id_reserva = id_reserva;
	}


	public Huespedes(String nombre, String apellido, Date fecha_de_nacimiento, String nacionalidad, String telefono,
			Integer id_reserva) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_de_nacimiento = fecha_de_nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.id_reserva = id_reserva;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public Date getFecha_de_nacimiento() {
		return fecha_de_nacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public String getTelefono() {
		return telefono;
	}
	public Integer getId_reserva() {
		return id_reserva;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	
	
	
	
}
