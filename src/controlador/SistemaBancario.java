package controlador;

import dao.*;
import modelo.*;
import java.sql.SQLException;
import java.util.List;

public class SistemaBancario {
    private AdministradorDAO adminDAO;
    private ClienteDAO clienteDAO;
    private CuentaAhorroDAO cuentaAhorroDAO;
    private CuentaDebitoDAO cuentaDebitoDAO;
    private CuentaCreditoDAO cuentaCreditoDAO;

    public SistemaBancario() {
        this.adminDAO = new AdministradorDAO();
        this.clienteDAO = new ClienteDAO();
        this.cuentaAhorroDAO = new CuentaAhorroDAO();
        this.cuentaDebitoDAO = new CuentaDebitoDAO();
        this.cuentaCreditoDAO = new CuentaCreditoDAO();
    }

    // Métodos de autenticación
    public boolean autenticarAdministrador(String correo, String contrasenia) {
        try {
            Administrador admin = adminDAO.obtenerPorCorreo(correo);
            return admin != null && admin.getContrasenia().equals(contrasenia);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean autenticarCliente(String correo, String contrasenia) {
        try {
            Cliente cliente = clienteDAO.obtenerPorCorreo(correo);
            return cliente != null && cliente.getContrasenia().equals(contrasenia);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Métodos de gestión de clientes
    public boolean registrarCliente(Cliente cliente) {
        try {
            clienteDAO.insertar(cliente);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Cliente> obtenerTodosLosClientes() {
        try {
            return clienteDAO.obtenerTodos();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Métodos de gestión de cuentas
    public boolean crearCuentaAhorro(CuentaAhorro cuenta, String correoCliente) {
        try {
            cuentaAhorroDAO.insertar(cuenta, correoCliente);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean crearCuentaDebito(CuentaDebito cuenta, String correoCliente) {
        try {
            cuentaDebitoDAO.insertar(cuenta, correoCliente);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean crearCuentaCredito(CuentaCredito cuenta, String correoCliente) {
        try {
            cuentaCreditoDAO.insertar(cuenta, correoCliente);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Métodos para transacciones
    public boolean realizarTransaccion(String numeroCuenta, String tipoCuenta, String tipoTransaccion, double monto) {
        try {
            switch (tipoCuenta.toUpperCase()) {
                case "AHORRO":
                    return procesarTransaccionAhorro(numeroCuenta, tipoTransaccion, monto);
                case "DEBITO":
                    return procesarTransaccionDebito(numeroCuenta, tipoTransaccion, monto);
                case "CREDITO":
                    return procesarTransaccionCredito(numeroCuenta, tipoTransaccion, monto);
                default:
                    return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean procesarTransaccionAhorro(String numeroCuenta, String tipoTransaccion, double monto)
            throws SQLException {
        CuentaAhorro cuenta = cuentaAhorroDAO.obtenerPorNumeroCuenta(numeroCuenta);
        if (cuenta == null || !cuenta.isActiva()) return false;

        boolean exito = false;
        switch (tipoTransaccion.toUpperCase()) {
            case "RETIRO":
                exito = cuenta.retiro(monto);
                break;
            case "DEPOSITO":
                exito = cuenta.deposito(monto);
                break;
            case "PAGO":
                exito = cuenta.pago(monto);
                break;
        }

        if (exito) {
            cuentaAhorroDAO.actualizarSaldo(numeroCuenta, cuenta.getSaldo());
        }
        return exito;
    }

    private boolean procesarTransaccionDebito(String numeroCuenta, String tipoTransaccion, double monto)
            throws SQLException {
        CuentaDebito cuenta = cuentaDebitoDAO.obtenerPorNumeroCuenta(numeroCuenta);
        if (cuenta == null || !cuenta.isActiva()) return false;

        boolean exito = false;
        switch (tipoTransaccion.toUpperCase()) {
            case "RETIRO":
                exito = cuenta.retiro(monto);
                break;
            case "DEPOSITO":
                exito = cuenta.deposito(monto);
                break;
            case "PAGO":
                exito = cuenta.pago(monto);
                break;
        }

        if (exito) {
            cuentaDebitoDAO.actualizarSaldo(numeroCuenta, cuenta.getSaldo());
        }
        return exito;
    }

    private boolean procesarTransaccionCredito(String numeroCuenta, String tipoTransaccion, double monto)
            throws SQLException {
        CuentaCredito cuenta = cuentaCreditoDAO.obtenerPorNumeroCuenta(numeroCuenta);
        if (cuenta == null || !cuenta.isActiva()) return false;

        boolean exito = false;
        switch (tipoTransaccion.toUpperCase()) {
            case "RETIRO":
                exito = cuenta.retiro(monto);
                break;
            case "ABONO":
                exito = cuenta.abono(monto);
                break;
            case "PAGO":
                exito = cuenta.pago(monto);
                break;
        }

        if (exito) {
            cuentaCreditoDAO.actualizarSaldo(numeroCuenta, cuenta.getSaldo());
        }
        return exito;
    }

    // Método para actualizar estado de cuenta
    public boolean actualizarEstadoCuenta(String numeroCuenta, String tipoCuenta, boolean activa) {
        try {
            switch (tipoCuenta.toUpperCase()) {
                case "AHORRO":
                    cuentaAhorroDAO.actualizarEstado(numeroCuenta, activa);
                    return true;
                case "DEBITO":
                    cuentaDebitoDAO.actualizarEstado(numeroCuenta, activa);
                    return true;
                case "CREDITO":
                    cuentaCreditoDAO.actualizarEstado(numeroCuenta, activa);
                    return true;
                default:
                    return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Métodos para listar cuentas
    public void listarTodasLasCuentasAhorro() {
        try {
            List<CuentaAhorro> cuentas = cuentaAhorroDAO.obtenerTodas();
            for (CuentaAhorro cuenta : cuentas) {
                System.out.println(cuenta);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar cuentas de ahorro: " + e.getMessage());
        }
    }

    public void listarTodasLasCuentasDebito() {
        try {
            List<CuentaDebito> cuentas = cuentaDebitoDAO.obtenerTodas();
            for (CuentaDebito cuenta : cuentas) {
                System.out.println(cuenta);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar cuentas de débito: " + e.getMessage());
        }
    }

    public void listarTodasLasCuentasCredito() {
        try {
            List<CuentaCredito> cuentas = cuentaCreditoDAO.obtenerTodas();
            for (CuentaCredito cuenta : cuentas) {
                System.out.println(cuenta);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar cuentas de crédito: " + e.getMessage());
        }
    }

    // Método para mostrar estado de cuentas de un cliente
    public void mostrarEstadoCuentas(String correoCliente) {
        try {
            List<CuentaAhorro> cuentasAhorro = cuentaAhorroDAO.obtenerPorCliente(correoCliente);
            List<CuentaDebito> cuentasDebito = cuentaDebitoDAO.obtenerPorCliente(correoCliente);
            List<CuentaCredito> cuentasCredito = cuentaCreditoDAO.obtenerPorCliente(correoCliente);

            double saldoTotal = 0;

            System.out.println("\n=== ESTADO DE CUENTAS ===");

            System.out.println("\nCuentas de Ahorro:");
            for (CuentaAhorro cuenta : cuentasAhorro) {
                System.out.println(cuenta);
                saldoTotal += cuenta.getSaldo();
            }

            System.out.println("\nCuentas de Débito:");
            for (CuentaDebito cuenta : cuentasDebito) {
                System.out.println(cuenta);
                saldoTotal += cuenta.getSaldo();
            }

            System.out.println("\nCuentas de Crédito:");
            for (CuentaCredito cuenta : cuentasCredito) {
                System.out.println(cuenta);
                saldoTotal += cuenta.getSaldo();
            }

            System.out.println("\nSaldo Total Consolidado: $" + saldoTotal);
        } catch (SQLException e) {
            System.out.println("Error al mostrar estado de cuentas: " + e.getMessage());
        }
    }

    // Métodos de administrador
    public boolean existeAdministrador() {
        try {
            List<Administrador> admins = adminDAO.obtenerTodos();
            return admins != null && !admins.isEmpty();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean crearAdministradorInicial(String nombreCompleto, String cedula,
                                             String correoElectronico, String contrasenia) {
        try {
            if (!existeAdministrador()) {
                Administrador admin = new Administrador(nombreCompleto, cedula,
                        correoElectronico, contrasenia);
                adminDAO.insertar(admin);
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}