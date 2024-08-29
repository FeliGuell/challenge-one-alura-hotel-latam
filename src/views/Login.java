package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.LoginController;
import model.LoginModel;


/**
 * Clase Login.
 * 
 * Esta clase representa la ventana principal de inicio de sesión de la aplicación.
 * Proporciona una interfaz de usuario para que los usuarios ingresen su nombre de usuario
 * y contraseña. Incluye botones para iniciar sesión y salir de la aplicación.
 */
public class Login extends JFrame {

    // Serial ID para la serialización de la clase
    private static final long serialVersionUID = 1L;

    // Panel principal que contiene todos los componentes de la ventana
    private JPanel contentPane;
    
    // Campos de texto para ingresar el nombre de usuario y la contraseña
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    
    // Variables para manejar el arrastre de la ventana
    int xMouse, yMouse;
    
    // Etiqueta de botón de salida
    private JLabel labelExit;
    
    // Controlador para manejar la lógica de inicio de sesión
    private LoginController loginController;

    /**
     * Método principal para lanzar la aplicación de inicio de sesión.
     * 
     * @param args Argumentos de línea de comando.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor de la clase Login.
     * 
     * Configura la interfaz gráfica del inicio de sesión, incluyendo el diseño de
     * la ventana, los campos de entrada, y los botones.
     */
    public Login() {
        // Inicializa el controlador de inicio de sesión
        this.loginController = new LoginController();

        // Configuración de la ventana principal
        setResizable(false);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 788, 527);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        // Panel principal que contiene el contenido de la ventana
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 788, 527);
        panel.setBackground(Color.WHITE);
        contentPane.add(panel);
        panel.setLayout(null);

        // Panel de diseño de la parte derecha con imagen
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(12, 138, 199));
        panel_1.setBounds(484, 0, 304, 527);
        panel.add(panel_1);
        panel_1.setLayout(null);

        // Etiqueta con la imagen del hotel
        JLabel imgHotel = new JLabel("");
        imgHotel.setBounds(0, 0, 304, 538);
        panel_1.add(imgHotel);
        imgHotel.setIcon(new ImageIcon(Login.class.getResource("/imagenes/img-hotel-login-.png")));

        // Panel para el botón de salida
        JPanel btnexit = new JPanel();
        btnexit.setBounds(251, 0, 53, 36);
        panel_1.add(btnexit);
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int opcionSalir = JOptionPane.showConfirmDialog(panel_1, "Desea salir del programa?");
                if (opcionSalir ==  JOptionPane.YES_OPTION) {
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
                btnexit.setBackground(new Color(12, 138, 199));
                labelExit.setForeground(Color.white);
            }
        });
        btnexit.setBackground(new Color(12, 138, 199));
        btnexit.setLayout(null);
        btnexit.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Etiqueta con la "X" para cerrar el programa
        labelExit = new JLabel("X");
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);
        labelExit.setForeground(SystemColor.text);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);

        // Campo de texto para ingresar el nombre de usuario
        txtUsuario = new JTextField();
        txtUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (txtUsuario.getText().equals("Ingrese su nombre de usuario")) {
                    txtUsuario.setText("");
                    txtUsuario.setForeground(Color.black);
                }
                if (String.valueOf(txtContrasena.getPassword()).isEmpty()) {
                    txtContrasena.setText("********");
                    txtContrasena.setForeground(Color.gray);
                }
            }
        });
        txtUsuario.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtUsuario.setText("Ingrese su nombre de usuario");
        txtUsuario.setBorder(BorderFactory.createEmptyBorder());
        txtUsuario.setForeground(SystemColor.activeCaptionBorder);
        txtUsuario.setBounds(65, 256, 324, 32);
        panel.add(txtUsuario);
        txtUsuario.setColumns(10);

        // Separador para el campo de texto del nombre de usuario
        JSeparator separator = new JSeparator();
        separator.setBackground(new Color(0, 120, 215));
        separator.setBounds(65, 292, 324, 2);
        panel.add(separator);

        // Etiqueta con el título "INICIAR SESIÓN"
        JLabel labelTitulo = new JLabel("INICIAR SESIÓN");
        labelTitulo.setForeground(SystemColor.textHighlight);
        labelTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 26));
        labelTitulo.setBounds(65, 149, 202, 26);
        panel.add(labelTitulo);

        // Separador para el campo de texto de la contraseña
        JSeparator separator_1 = new JSeparator();
        separator_1.setBackground(SystemColor.textHighlight);
        separator_1.setBounds(65, 393, 324, 2);
        panel.add(separator_1);

        // Campo de texto para ingresar la contraseña
        txtContrasena = new JPasswordField();
        txtContrasena.setText("********");
        txtContrasena.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (String.valueOf(txtContrasena.getPassword()).equals("********")) {
                    txtContrasena.setText("");
                    txtContrasena.setForeground(Color.black);
                }
                if (txtUsuario.getText().isEmpty()) {
                    txtUsuario.setText("Ingrese su nombre de usuario");
                    txtUsuario.setForeground(Color.gray);
                }
            }
        });
        txtContrasena.setForeground(SystemColor.activeCaptionBorder);
        txtContrasena.setFont(new Font("Roboto", Font.PLAIN, 16));
        txtContrasena.setBorder(BorderFactory.createEmptyBorder());
        txtContrasena.setBounds(65, 353, 324, 32);
        panel.add(txtContrasena);

        // Etiqueta con el texto "USUARIO"
        JLabel LabelUsuario = new JLabel("USUARIO");
        LabelUsuario.setForeground(SystemColor.textInactiveText);
        LabelUsuario.setFont(new Font("Roboto Black", Font.PLAIN, 20));
        LabelUsuario.setBounds(65, 219, 107, 26);
        panel.add(LabelUsuario);

        // Etiqueta con el texto "CONTRASEÑA"
        JLabel lblContrasea = new JLabel("CONTRASEÑA");
        lblContrasea.setForeground(SystemColor.textInactiveText);
        lblContrasea.setFont(new Font("Roboto Black", Font.PLAIN, 20));
        lblContrasea.setBounds(65, 316, 140, 26);
        panel.add(lblContrasea);

        // Panel del botón de inicio de sesión
        JPanel btnLogin = new JPanel();
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnLogin.setBackground(new Color(0, 156, 223));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnLogin.setBackground(SystemColor.textHighlight);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Login();
            }
        });
        btnLogin.setBackground(SystemColor.textHighlight);
        btnLogin.setBounds(65, 431, 122, 44);
        panel.add(btnLogin);
        btnLogin.setLayout(null);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Etiqueta dentro del botón de inicio de sesión
        JLabel lblNewLabel = new JLabel("ENTRAR");
        lblNewLabel.setBounds(0, 0, 122, 44);
        btnLogin.add(lblNewLabel);
        lblNewLabel.setForeground(SystemColor.controlLtHighlight);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        // Etiqueta con el logo de la aplicación
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/imagenes/logo.png")));
        lblNewLabel_1.setBounds(65, 63, 140, 115);
        panel.add(lblNewLabel_1);

        // Panel para manejar el movimiento de la ventana
        JPanel panelMov = new JPanel();
        panelMov.setBounds(0, 0, 788, 43);
        panel.add(panelMov);
        panelMov.setLayout(null);
        panelMov.setCursor(new Cursor(Cursor.MOVE_CURSOR));
        panelMov.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
        });
        panelMov.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - xMouse, y - yMouse);
            }
        });
    }

    /**
     * Método que maneja el proceso de inicio de sesión.
     * Valida las credenciales y muestra un mensaje de error si son incorrectas.
     */
    private void Login() {
        String username = txtUsuario.getText();
        String password = String.valueOf(txtContrasena.getPassword());

        // Validación de las credenciales ingresadas
        Optional<Usuario> usuarioOpt = loginController.validateCredentials(username, password);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            JOptionPane.showMessageDialog(this, "Bienvenido " + usuario.getNombre());
            // Aquí puedes agregar la lógica para redirigir a la pantalla principal
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
        }
    }
}
