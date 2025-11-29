package dao;

import modelo.CuentaCredito;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaCreditoDAO {
    public void insertar(CuentaCredito cuenta, String correoCliente) throws SQLException {
        String sql = "INSERT INTO cuenta_credito (numero_cuenta, saldo, activa, limite_credito, tipo, correo_cliente) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cuenta.getNumeroCuenta());
            stmt.setDouble(2, cuenta.getSaldo());
            stmt.setBoolean(3, cuenta.isActiva());
            stmt.setDouble(4, cuenta.getLimiteCredito());
            stmt.setString(5, cuenta.getTipo());
            stmt.setString(6, correoCliente);
            stmt.executeUpdate();
        }
    }

    public List<CuentaCredito> obtenerPorCliente(String correoCliente) throws SQLException {
        List<CuentaCredito> cuentas = new ArrayList<>();
        String sql = "SELECT * FROM cuenta_credito WHERE correo_cliente = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, correoCliente);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CuentaCredito cuenta = new CuentaCredito(
                            rs.getString("numero_cuenta"),
                            rs.getDouble("limite_credito"),
                            rs.getString("tipo")
                    );
                    cuenta.setActiva(rs.getBoolean("activa"));
                    cuentas.add(cuenta);
                }
            }
        }
        return cuentas;
    }

    public CuentaCredito obtenerPorNumeroCuenta(String numeroCuenta) throws SQLException {
        String sql = "SELECT * FROM cuenta_credito WHERE numero_cuenta = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numeroCuenta);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CuentaCredito cuenta = new CuentaCredito(
                            rs.getString("numero_cuenta"),
                            rs.getDouble("limite_credito"),
                            rs.getString("tipo")
                    );
                    cuenta.setActiva(rs.getBoolean("activa"));
                    return cuenta;
                }
            }
        }
        return null;
    }

    public void actualizarSaldo(String numeroCuenta, double nuevoSaldo) throws SQLException {
        String sql = "UPDATE cuenta_credito SET saldo = ? WHERE numero_cuenta = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, nuevoSaldo);
            stmt.setString(2, numeroCuenta);
            stmt.executeUpdate();
        }
    }

    public void actualizarEstado(String numeroCuenta, boolean activa) throws SQLException {
        String sql = "UPDATE cuenta_credito SET activa = ? WHERE numero_cuenta = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, activa);
            stmt.setString(2, numeroCuenta);
            stmt.executeUpdate();
        }
    }

    public List<CuentaCredito> obtenerTodas() throws SQLException {
        List<CuentaCredito> cuentas = new ArrayList<>();
        String sql = "SELECT * FROM cuenta_credito";

        try (Connection conn = ConexionDB.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CuentaCredito cuenta = new CuentaCredito(
                        rs.getString("numero_cuenta"),
                        rs.getDouble("limite_credito"),
                        rs.getString("tipo")
                );
                cuenta.setActiva(rs.getBoolean("activa"));
                cuentas.add(cuenta);
            }
        }
        return cuentas;
    }
}
