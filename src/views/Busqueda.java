package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import controller.HuespedesController;
import controller.ReservaController;
import model.Huespedes;
import model.Reservas;

/**
 * La clase `Busqueda` es una ventana de la aplicación que permite realizar búsquedas y gestionar datos de reservas y huéspedes.
 * Hereda de `JFrame` para crear una interfaz gráfica en Swing.
 * Proporciona funcionalidades para visualizar, buscar, editar y eliminar reservas y huéspedes.
 */
@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloH;
	private JLabel labelAtras;
	private JLabel labelExit;
	private HuespedesController huespedesController;
	private ReservaController reservaController;
	int xMouse, yMouse;
	
   	/**
     	* Inicia la aplicación y muestra la ventana `Busqueda`.
     	*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

   	/**
     	* Crea la ventana principal de búsqueda.
     	*/
	public Busqueda() {
		this.huespedesController = new HuespedesController();
		this.reservaController = new ReservaController();

		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), tbReservas, null);
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		cargarTablaReservas();

		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), tbHuespedes, null);
		modeloH = (DefaultTableModel) tbHuespedes.getModel();
		modeloH.addColumn("Numero de Huesped");
		modeloH.addColumn("Nombre");
		modeloH.addColumn("Apellido");
		modeloH.addColumn("Fecha de Nacimiento");
		modeloH.addColumn("Nacionalidad");
		modeloH.addColumn("Telefono");
		modeloH.addColumn("Numero de Reserva");
		cargarTablaHuespedes(); 
			

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);

		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);

		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int opcionSalir = JOptionPane.showConfirmDialog(null,"Desea volver al menú del programa?");
				if (opcionSalir ==  JOptionPane.YES_OPTION) {
					MenuPrincipal menuPrincipal = new MenuPrincipal();
					menuPrincipal.setVisible(true);
					dispose();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) { // Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) { // Al usuario quitar el mouse por el botón este volverá al estado
													// original
				btnexit.setBackground(Color.white);
				labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);

		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);

		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);

		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				limpiarTabla();

				if (!txtBuscar.getText().isEmpty()) {

					if (txtBuscar.getText().matches("[+-]?\\d*(\\.\\d+)?")) {
						filtrarPorNumeroReserva();
					} else {
						filtrarPorApellido();
					}

				} else {
					JOptionPane.showMessageDialog(null, "Debe ingresar un apellido o número de reserva válido");
					cargarTablaHuespedes();
					cargarTablaReservas();
				}
			}
		});
		
		
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!tieneFilaElegidaTablaReserva()) {
				modificarReserva();	
			}else if(!tieneFilaElegidaTablaHuesped()) {	
				modificarHuesped();
				}
				
				limpiarTabla();
				cargarTablaHuespedes();	
				cargarTablaReservas();
			}
		});

		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);

		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!tieneFilaElegidaTablaReserva()) {
					eliminarReserva();	
				}else if(!tieneFilaElegidaTablaHuesped()) {	
					eliminarHuesped();
					}
					
				 
					limpiarTabla();
					cargarTablaHuespedes();	
					cargarTablaReservas();
			}
		});

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}

	
	
	
	private void cargarTablaHuespedes() {
		var huespedes = this.huespedesController.listar();
		crearFilasHuespedes(huespedes);
	}
	
	private void cargarTablaReservas() {
		var reservas = this.reservaController.listar();
		crearFilasReservas(reservas);
	}
	
	private void calcularValor(Date fecha_entrada, Date fecha_salida) {
		if(fecha_entrada != null && fecha_salida != null) {	
			LocalDate fechaE = fecha_entrada.toLocalDate();	
			LocalDate fechaS = fecha_salida.toLocalDate();
			
			int intervalPeriod = (int) ChronoUnit.DAYS.between(fechaE, fechaS);
	
			int diaria = 200;
			int valor;

			valor = intervalPeriod * diaria;
			
			modelo.setValueAt(String.valueOf(valor),tbReservas.getSelectedRow(), 3) ;
			
		}
	}
	
	private void crearFilasReservas(List<Reservas> reservas) {
		reservas.forEach(reserva -> modelo.addRow(new Object[] { 
				reserva.getId(), 
				reserva.getFecha_entrada(),
				reserva.getFecha_salida(), 
				reserva.getValor(), 
				reserva.getForma_pago()}));
	}

	private void crearFilasReservas(Reservas reserva) {
		modelo.addRow(new Object[] { 
				reserva.getId(), 
				reserva.getFecha_entrada(),
				reserva.getFecha_salida(), 
				reserva.getValor(), 
				reserva.getForma_pago()});
	}

	private void crearFilasHuespedes(List<Huespedes> huespedes) {
		huespedes.forEach(huesped -> modeloH.addRow(new Object[] { 
				huesped.getId(), 
				huesped.getNombre(),
				huesped.getApellido(), 
				huesped.getFecha_de_nacimiento(), 
				huesped.getNacionalidad(),
				huesped.getTelefono(), 
				huesped.getId_reserva() }));
	}

	private void crearFilasHuespedes(Huespedes huesped) {
		modeloH.addRow(new Object[] { 
				huesped.getId(), 
				huesped.getNombre(),
				huesped.getApellido(), 
				huesped.getFecha_de_nacimiento(), 
				huesped.getNacionalidad(),
				huesped.getTelefono(), 
				huesped.getId_reserva() });
	}

	private void eliminarHuesped() {
		if(tieneFilaElegidaTablaHuesped()) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un item");
			return;
		}
		
		Optional.ofNullable(modeloH.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
		.ifPresentOrElse(fila -> {
			Integer id = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
			Integer id_reserva = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
			
			 var filasModificadas = this.huespedesController.eliminar(id);
			 this.reservaController.eliminar(id_reserva);
			 
             modeloH.removeRow(tbHuespedes.getSelectedRow());
             JOptionPane.showMessageDialog(this,
                     String.format("%d item eliminado con éxito!", filasModificadas));
			
		}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	}
	
	private void eliminarReserva() {
		if(tieneFilaElegidaTablaReserva()) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un item");
			return;
		}
		
		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
		.ifPresentOrElse(fila -> {
			Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
			this.huespedesController.eliminarPorIdReserva(id);
			var filasModificadas = this.reservaController.eliminar(id);
			

            modelo.removeRow(tbReservas.getSelectedRow());
            JOptionPane.showMessageDialog(this,
                    String.format("%d item eliminado con éxito!", filasModificadas));
            
		}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	}
	
	private void filtrarPorApellido() {

		var huespedes = this.huespedesController.listar();
		
		List<Huespedes> resultado = huespedes.parallelStream()
				.filter(x -> x.getApellido().equalsIgnoreCase(txtBuscar.getText()))
				.collect(Collectors.toList());
		
		if(!resultado.isEmpty()) {
			List<Reservas> listaReservas = new ArrayList<Reservas>();
			resultado.forEach(x -> {
				listaReservas.add(
						reservaController
						.traeReservaPorId(x.getId_reserva()));
			});
			
			crearFilasReservas(listaReservas);
			crearFilasHuespedes(resultado);
		}else {
			JOptionPane.showMessageDialog(null, "No se encontró ningun registro");
			cargarTablaHuespedes();
			cargarTablaReservas();
		}		
	}
	
	private void filtrarPorNumeroReserva() {
		var reservas = this.reservaController.listar();
		int id = Integer.parseInt(txtBuscar.getText());
		Huespedes huesped = null;
		
		Optional<Reservas> optionalReservas = reservas.stream()
				.filter(x -> x.getId() == id).findFirst();
		
		if(optionalReservas.isPresent()) {
			huesped = this.huespedesController.traeHuespedPorIdReserva(optionalReservas.get().getId());
			crearFilasHuespedes(huesped);
			crearFilasReservas(optionalReservas.get());
		}else {
			JOptionPane.showMessageDialog(null, "No se encontró ningun registro");
			cargarTablaHuespedes();
			cargarTablaReservas();
		}	
	}
	
	private void modificarReserva() {
		
		if(tieneFilaElegidaTablaReserva()) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un item");
			return;
		}
		
	
		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
				.ifPresentOrElse(fila -> {
	
					try {
						Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
						Date fecha_entrada = java.sql.Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());	
						Date fecha_salida = java.sql.Date.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());			
						calcularValor(fecha_entrada, fecha_salida);
						String valor = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 3);
						String forma_pago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
						var filasModificadas = this.reservaController.modificar(id, fecha_entrada, fecha_salida, valor, forma_pago);
						JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito!", filasModificadas));
					}catch(Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(this, "Revise los datos ingresados que coincidan con el formato");
					}
		
				}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		
	}
	
	private void modificarHuesped() {
		if(tieneFilaElegidaTablaHuesped()) {
			JOptionPane.showMessageDialog(this, "Por favor, elije un item");
			return;
		}
		
		Optional.ofNullable(modeloH.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
		.ifPresentOrElse(fila -> {	
			try {
				Integer id = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
				String nombre = modeloH.getValueAt(tbHuespedes.getSelectedRow(), 1).toString();	
				String apellido = modeloH.getValueAt(tbHuespedes.getSelectedRow(), 2).toString();	
				Date fecha_de_nacimiento = java.sql.Date.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());			
				String nacionalidad = modeloH.getValueAt(tbHuespedes.getSelectedRow(), 4).toString();	
				String telefono = modeloH.getValueAt(tbHuespedes.getSelectedRow(), 5).toString();
				Integer id_reserva = Integer.valueOf(modeloH.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
				
				var filasModificadas = this.huespedesController.modificar(id, nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva);
				JOptionPane.showMessageDialog(this, String.format("%d item modificado con éxito!", filasModificadas));
			}catch(Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Revise los datos ingresados que coincidan con el formato");
			}
			
		}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
	}
		
	private boolean tieneFilaElegidaTablaReserva() {
		return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;
	}
	
	private boolean tieneFilaElegidaTablaHuesped() {
		return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
	}
	
	private void limpiarTabla() {
		modeloH.setRowCount(0);
		modelo.setRowCount(0);
	}

	
	
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}
