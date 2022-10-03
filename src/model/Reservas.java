package model;

import java.sql.Date;

public class Reservas {
	private Integer id;
	private Date fecha_entrada;
	private Date fecha_salida;
	private String valor;
	private String forma_pago;
	
	

	public Reservas(Date fecha_entrada, Date fecha_salida, String valor, String forma_pago) {
		super();
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.valor = valor;
		this.forma_pago = forma_pago;
	}
	
	

	public Reservas(Integer id, Date fecha_entrada, Date fecha_salida, String valor, String forma_pago) {
		super();
		this.id = id;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.valor = valor;
		this.forma_pago = forma_pago;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha_entrada() {
		return fecha_entrada;
	}

	public Date getFecha_salida() {
		return fecha_salida;
	}

	public String getValor() {
		return valor;
	}

	public String getForma_pago() {
		return forma_pago;
	}

}
