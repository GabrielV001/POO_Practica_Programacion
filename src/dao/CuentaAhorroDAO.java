package dao;

import modelo.CuentaAhorro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaAhorroDAO {
    public void insertar(CuentaAhorro cuenta, String correoCliente) throws SQLException {
        String sql = "INSERT INTO cuenta_ahorro (numero_cuenta, saldo, activa, porcentaje_interes, correo_cliente) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cuenta.getNumeroCuenta());
            stmt.setDouble(2, cuenta.getSaldo());
            stmt.setBoolean(3, cuenta.isActiva());
            stmt.setDouble(4, cuenta.getPorcentajeInteres());
            stmt.setString(5, correoCliente);
            stmt.executeUpdate();
        }
    }

    public List<CuentaAhorro> obtenerPorCliente(String correoCliente) throws SQLException {
        List<CuentaAhorro> cuentas = new ArrayList<>();
        String sql = "SELECT * FROM cuenta_ahorro WHERE correo_cliente = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, correoCliente);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CuentaAhorro cuenta = new CuentaAhorro(
                            rs.getString("numero_cuenta"),
                            rs.getDouble("saldo"),
                            rs.getDouble("porcentaje_interes")
                    );
                    cuenta.setActiva(rs.getBoolean("activa"));
                    cuentas.add(cuenta);
                }
            }
        }
        return cuentas;
    }

    public CuentaAhorro obtenerPorNumeroCuenta(String numeroCuenta) throws SQLException {
        String sql = "SELECT * FROM cuenta_ahorro WHERE numero_cuenta = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numeroCuenta);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CuentaAhorro cuenta = new CuentaAhorro(
                            rs.getString("numero_cuenta"),
                            rs.getDouble("saldo"),
                            rs.getDouble("porcentaje_interes")
                    );
                    cuenta.setActiva(rs.getBoolean("activa"));
                    return cuenta;
                }
            }
        }
        return null;
    }

    public void actualizarSaldo(String numeroCuenta, double nuevoSaldo) throws SQLException {
        String sql = "UPDATE cuenta_ahorro SET saldo = ? WHERE numero_cuenta = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, nuevoSaldo);
            stmt.setString(2, numeroCuenta);
            stmt.executeUpdate();
        }
    }

    public void actualizarEstado(String numeroCuenta, boolean activa) throws SQLException {
        String sql = "UPDATE cuenta_ahorro SET activa = ? WHERE numero_cuenta = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, activa);
            stmt.setString(2, numeroCuenta);
            stmt.executeUpdate();
        }
    }

    public List<CuentaAhorro> obtenerTodas() throws SQLException {
        List<CuentaAhorro> cuentas = new ArrayList<>();
        String sql = "SELECT * FROM cuenta_ahorro";

        try (Connection conn = ConexionDB.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CuentaAhorro cuenta = new CuentaAhorro(
                        rs.getString("numero_cuenta"),
                        rs.getDouble("saldo"),
                        rs.getDouble("porcentaje_interes")
                );
                cuenta.setActiva(rs.getBoolean("activa"));
                cuentas.add(cuenta);
            }
        }
        return cuentas;
    }
}
