package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;


/**
 * Clase Exito.
 * 
 * Esta clase representa un cuadro de diálogo modal que se muestra cuando se han
 * guardado los datos de forma satisfactoria. Incluye un mensaje de confirmación
 * y un botón para cerrar el diálogo y abrir el menú del usuario.
 */
@SuppressWarnings("serial")
public class Exito extends JDialog {

	// Panel principal del contenido del diálogo.
	private final JPanel contentPanel = new JPanel();

   	/**
     	* Método principal para lanzar la aplicación.
     	* 
     	* @param args Argumentos de línea de comando.
     	*/
	public static void main(String[] args) {
		try {
			Exito dialog = new Exito();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    	/**
     	* Constructor de la clase Exito.
 	* 
     	* Configura el cuadro de diálogo, incluyendo su icono, tamaño, disposición,
     	* y elementos visuales como etiquetas e iconos.
     	*/
	public Exito() {
		// Establece el icono de la ventana
		setIconImage(Toolkit.getDefaultToolkit().getImage(Exito.class.getResource("/imagenes/aH-40px.png")));
		// Configura las dimensiones y posición de la ventana
		setBounds(100, 100, 394, 226);
		getContentPane().setLayout(new BorderLayout());

		// Configuración del panel de contenido
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		// Añade una etiqueta con un icono al panel de contenido
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(Exito.class.getResource("/imagenes/Ha-100px.png")));
			lblNewLabel.setBounds(123, 11, 100, 100);
			contentPanel.add(lblNewLabel);
		}

		// Añade una etiqueta con un mensaje de éxito al panel de contenido
		{
			JLabel lblNewLabel_1 = new JLabel("Datos guardados satisfactoriamente");
			lblNewLabel_1.setForeground(new Color (12, 138, 199));
			lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 18));
			lblNewLabel_1.setBounds(27, 122, 322, 21);
			contentPanel.add(lblNewLabel_1);
		}

		// Configuración del panel de botones en la parte inferior del diálogo
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			// Botón "OK" que cierra el cuadro de diálogo y abre el menú del usuario
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose(); // Cierra el cuadro de diálogo actual
						MenuUsuario usuario = new MenuUsuario(); 
						usuario.setVisible(true);// Abre la ventana del menú de usuario
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			// Botón "Cancel" que cierra el cuadro de diálogo (no realiza ninguna acción adicional)
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
