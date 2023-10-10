package edu.umg.test;

import edu.umg.datos.Conexion;
import edu.umg.domain.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

public class InterfazUsuario {
    private JTextField usuarioTextField;
    private JPasswordField contraseñaPasswordField;
    private JButton iniciarSesionButton;
    private UsuarioDAO usuarioDAO;

    public InterfazUsuario(Connection conexion) {
        JFrame frame = new JFrame("Inicio de Sesión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usuarioLabel = new JLabel("Nombre de Usuario:");
        usuarioTextField = new JTextField();
        JLabel contraseñaLabel = new JLabel("Contraseña:");
        contraseñaPasswordField = new JPasswordField();
        iniciarSesionButton = new JButton("Iniciar Sesión");

        panel.add(usuarioLabel);
        panel.add(usuarioTextField);
        panel.add(contraseñaLabel);
        panel.add(contraseñaPasswordField);
        panel.add(iniciarSesionButton);

        frame.add(panel);
        frame.setVisible(true);

        usuarioDAO = new UsuarioDAO(conexion);

        // Agregar un ActionListener al botón para realizar la validación
        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioTextField.getText();
                char[] contraseña = contraseñaPasswordField.getPassword();

                // Validar el usuario en la base de datos
                try {
                    if (usuarioDAO.validarUsuario(usuario, new String(contraseña))) {
                        JOptionPane.showMessageDialog(frame, "Inicio de sesión exitoso.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos.");
                    }
                } catch (SQLException | NoSuchAlgorithmException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error al validar el usuario.");
                }
            }
        });
    }

    public static void main(String[] args) throws SQLException {
        // Establecer la conexión a la base de datos
        Connection conexion = Conexion.getConnection();

        // Crear la interfaz gráfica y pasar la conexión al constructor
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new InterfazUsuario(conexion);
            }
        });
    }
}
