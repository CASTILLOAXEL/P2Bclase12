package edu.umg.domain;

public class UsuarioDTO {
    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private String email;
    // Otros campos según tus necesidades


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Otros métodos getter/setter para los campos adicionales

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "nombreUsuario='" + username + '\'' +
                ", contraseña='" + password + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                // Otros campos
                '}';
    }
}

