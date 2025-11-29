package dao;

import modelo.Administrador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDAO {
    public void insertar(modelo.Administrador admin) throws SQLException {
        String sql = "INSERT INTO administrador (nombre_completo, cedula, correo_electronico, contrasenia) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, admin.getNombreCompleto());
            stmt.setString(2, admin.getCedula());
            stmt.setString(3, admin.getCorreoElectronico());
            stmt.setString(4, admin.getContrasenia());
            stmt.executeUpdate();
        }
    }

    public Administrador obtenerPorCorreo(String correo) throws SQLException {
        String sql = "SELECT * FROM administrador WHERE correo_electronico = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, correo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Administrador(
                            rs.getString("nombre_completo"),
                            rs.getString("cedula"),
                            rs.getString("correo_electronico"),
                            rs.getString("contrasenia")
                    );
                }
            }
        }
        return null;
    }

    public List<Administrador> obtenerTodos() throws SQLException {
        List<Administrador> administradores = new ArrayList<>();
        String sql = "SELECT * FROM administrador";

        try (Connection conn = ConexionDB.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                administradores.add(new Administrador(
                        rs.getString("nombre_completo"),
                        rs.getString("cedula"),
                        rs.getString("correo_electronico"),
                        rs.getString("contrasenia")
                ));
            }
        }
        return administradores;
    }

    public void actualizar(Administrador admin) throws SQLException {
        String sql = "UPDATE administrador SET nombre_completo = ?, cedula = ?, " +
                "contrasenia = ? WHERE correo_electronico = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, admin.getNombreCompleto());
            stmt.setString(2, admin.getCedula());
            stmt.setString(3, admin.getContrasenia());
            stmt.setString(4, admin.getCorreoElectronico());
            stmt.executeUpdate();
        }
    }

    public void eliminar(String correo) throws SQLException {
        String sql = "DELETE FROM administrador WHERE correo_electronico = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, correo);
            stmt.executeUpdate();
        }
    }
}

