package edu.umg.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private Connection conexion;

    public UsuarioDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // Función para encriptar la contraseña
    private String encriptarContraseña(String contraseña) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(contraseña.getBytes());
        StringBuilder hexString = new StringBuilder();

        for (byte b : hash) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // Función para validar un usuario por nombre de usuario y contraseña
    public boolean validarUsuario(String nombreUsuario, String contraseña) throws SQLException, NoSuchAlgorithmException {
        String contraseñaEncriptada = encriptarContraseña(contraseña);

        String sql = "SELECT COUNT(*) FROM usuario WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contraseñaEncriptada);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        }
        return false;
    }




    // Implementa métodos para CRUD (select, insert, update, delete) según tus necesidades
}

