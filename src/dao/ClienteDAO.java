package dao;

import modelo.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    public void insertar(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (nombre_completo, cedula, correo_electronico, contrasenia, " +
                "sexo, profesion, direccion) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombreCompleto());
            stmt.setString(2, cliente.getCedula());
            stmt.setString(3, cliente.getCorreoElectronico());
            stmt.setString(4, cliente.getContrasenia());
            stmt.setString(5, cliente.getSexo());
            stmt.setString(6, cliente.getProfesion());
            stmt.setString(7, cliente.getDireccion());
            stmt.executeUpdate();
        }
    }

    public Cliente obtenerPorCorreo(String correo) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE correo_electronico = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, correo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getString("nombre_completo"),
                            rs.getString("cedula"),
                            rs.getString("correo_electronico"),
                            rs.getString("contrasenia"),
                            rs.getString("sexo"),
                            rs.getString("profesion"),
                            rs.getString("direccion")
                    );
                }
            }
        }
        return null;
    }

    public List<Cliente> obtenerTodos() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection conn = ConexionDB.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                clientes.add(new Cliente(
                        rs.getString("nombre_completo"),
                        rs.getString("cedula"),
                        rs.getString("correo_electronico"),
                        rs.getString("contrasenia"),
                        rs.getString("sexo"),
                        rs.getString("profesion"),
                        rs.getString("direccion")
                ));
            }
        }
        return clientes;
    }

    public void actualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE cliente SET nombre_completo = ?, cedula = ?, contrasenia = ?, " +
                "sexo = ?, profesion = ?, direccion = ? WHERE correo_electronico = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombreCompleto());
            stmt.setString(2, cliente.getCedula());
            stmt.setString(3, cliente.getContrasenia());
            stmt.setString(4, cliente.getSexo());
            stmt.setString(5, cliente.getProfesion());
            stmt.setString(6, cliente.getDireccion());
            stmt.setString(7, cliente.getCorreoElectronico());
            stmt.executeUpdate();
        }
    }

    public void eliminar(String correo) throws SQLException {
        String sql = "DELETE FROM cliente WHERE correo_electronico = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, correo);
            stmt.executeUpdate();
        }
    }
}
