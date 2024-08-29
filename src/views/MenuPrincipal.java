package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Clase MenuPrincipal.
 * 
 * Esta clase representa la ventana principal de la aplicación después del inicio de sesión.
 * Proporciona una interfaz de usuario con un fondo de imagen, un logo, un pie de página con información
 * de derechos de autor, y botones para navegar a otras partes de la aplicación.
 * 
 * La ventana permite al usuario:
 * - Salir de la aplicación.
 * - Acceder a la ventana de inicio de sesión.
 */
@SuppressWarnings("serial")
public class MenuPrincipal extends JFrame {

    private JPanel contentPane;
    private JLabel labelExit;
    int xMouse, yMouse;

    /**
     * Método principal para lanzar la aplicación principal.
     * 
     * @param args Argumentos de línea de comando.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuPrincipal frame = new MenuPrincipal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor de la clase MenuPrincipal.
     * 
     * Configura la interfaz gráfica principal, incluyendo el diseño de la ventana, el fondo, el logo,
     * el pie de página, y los botones de navegación. También gestiona el movimiento de la ventana.
     */
    public MenuPrincipal() {
        // Configuración de la ventana principal
        setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/imagenes/aH-40px.png")));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 910, 537);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);

        // Panel principal que contiene todos los componentes de la ventana
        Panel panel = new Panel();
        panel.setBackground(SystemColor.window);
        panel.setBounds(0, 0, 910, 537);
        contentPane.add(panel);
        panel.setLayout(null);

        // Etiqueta con la imagen de fondo
        JLabel imagenFondo = new JLabel("");
        imagenFondo.setBounds(-50, 0, 732, 501);
        imagenFondo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/menu-img.png")));
        panel.add(imagenFondo);

        // Etiqueta con el logo de la aplicación
        JLabel logo = new JLabel("");
        logo.setBounds(722, 80, 150, 156);
        logo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/aH-150px.png")));
        panel.add(logo);

        // Panel de pie de página con información de derechos de autor
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 500, 910, 37);
        panel_1.setBackground(new Color(12, 138, 199));
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblCopyR = new JLabel("Desarrollado por Fulanita de Tal © 2022");
        lblCopyR.setBounds(315, 11, 284, 19);
        lblCopyR.setForeground(new Color(240, 248, 255));
        lblCopyR.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel_1.add(lblCopyR);

        // Panel para controlar el movimiento de la ventana
        JPanel header = new JPanel();
        header.setBounds(0, 0, 910, 36);
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
        panel.add(header);

        // Botón de salir
        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int opcionSalir = JOptionPane.showConfirmDialog(null, "Desea salir del programa?");
                if (opcionSalir == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        // Etiqueta para el botón de salir
        labelExit = new JLabel("X");
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));

        // Botón para acceder a la ventana de inicio de sesión
        JPanel btnLogin = new JPanel();
        btnLogin.setBounds(754, 300, 83, 70);
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Login login = new Login();
                login.setVisible(true);
                dispose();
            }
        });
        btnLogin.setLayout(null);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.setBackground(SystemColor.window);
        panel.add(btnLogin);

        // Imagen del botón de inicio de sesión
        JLabel imagenLogin = new JLabel("");
        imagenLogin.setBounds(0, 0, 80, 70);
        btnLogin.add(imagenLogin);
        imagenLogin.setHorizontalAlignment(SwingConstants.CENTER);
        imagenLogin.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/login.png")));

        // Etiqueta del título del botón de inicio de sesión
        JLabel lblTitulo = new JLabel("LOGIN");
        lblTitulo.setBounds(754, 265, 83, 24);
        lblTitulo.setBackground(SystemColor.window);
        panel.add(lblTitulo);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setForeground(SystemColor.textHighlight);
        lblTitulo.setFont(new Font("Roboto Light", Font.PLAIN, 20));
    }

    /**
     * Maneja el evento de presionar el mouse en el encabezado de la ventana para iniciar el movimiento.
     * 
     * @param evt Evento de mouse que contiene la información de la posición.
     */
    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    /**
     * Maneja el evento de arrastrar el mouse en el encabezado de la ventana para mover la ventana.
     * 
     * @param evt Evento de mouse que contiene la información de la posición en la pantalla.
     */
    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }
}
