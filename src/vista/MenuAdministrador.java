package vista;

import controlador.SistemaBancario;
import modelo.*;
import java.util.Scanner;
import java.util.List;

public class MenuAdministrador {
    private Scanner scanner;
    private SistemaBancario sistema;

    public MenuAdministrador(SistemaBancario sistema) {
        this.scanner = new Scanner(System.in);
        this.sistema = sistema;
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("\n=== MENÚ ADMINISTRADOR ===");
            System.out.println("1. Listar todos los clientes");
            System.out.println("2. Listar todas las cuentas");
            System.out.println("3. Gestionar estados de cuentas");
            System.out.println("4. Crear cuenta para cliente");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    listarClientes();
                    break;
                case 2:
                    listarCuentas();
                    break;
                case 3:
                    gestionarEstadosCuentas();
                    break;
                case 4:
                    crearCuentaCliente();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void listarClientes() {
        System.out.println("\n=== LISTA DE CLIENTES ===");
        List<Cliente> clientes = sistema.obtenerTodosLosClientes();
        if (clientes != null && !clientes.isEmpty()) {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        } else {
            System.out.println("No hay clientes registrados");
        }
    }

    private void listarCuentas() {
        System.out.println("\n=== LISTA DE CUENTAS ===");
        System.out.println("\n--- Cuentas de Ahorro ---");
        sistema.listarTodasLasCuentasAhorro();
        System.out.println("\n--- Cuentas de Débito ---");
        sistema.listarTodasLasCuentasDebito();
        System.out.println("\n--- Cuentas de Crédito ---");
        sistema.listarTodasLasCuentasCredito();
    }

    private void gestionarEstadosCuentas() {
        System.out.println("\n=== GESTIÓN DE ESTADOS DE CUENTA ===");
        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Tipo de cuenta (AHORRO/DEBITO/CREDITO): ");
        String tipoCuenta = scanner.nextLine();
        System.out.print("¿Activar cuenta? (S/N): ");
        boolean activar = scanner.nextLine().equalsIgnoreCase("S");

        if (sistema.actualizarEstadoCuenta(numeroCuenta, tipoCuenta, activar)) {
            System.out.println("Estado de cuenta actualizado exitosamente");
        } else {
            System.out.println("Error al actualizar el estado de la cuenta");
        }
    }

    private void crearCuentaCliente() {
        System.out.println("\n=== CREAR CUENTA PARA CLIENTE ===");
        System.out.print("Correo del cliente: ");
        String correoCliente = scanner.nextLine();
        System.out.println("Tipo de cuenta:");
        System.out.println("1. Cuenta de Ahorro");
        System.out.println("2. Cuenta de Débito");
        System.out.println("3. Cuenta de Crédito");
        System.out.print("Seleccione una opción: ");
        int tipoCuenta = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Número de cuenta: ");
        String numeroCuenta = scanner.nextLine();

        switch (tipoCuenta) {
            case 1:
                crearCuentaAhorro(numeroCuenta, correoCliente);
                break;
            case 2:
                crearCuentaDebito(numeroCuenta, correoCliente);
                break;
            case 3:
                crearCuentaCredito(numeroCuenta, correoCliente);
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    private void crearCuentaAhorro(String numeroCuenta, String correoCliente) {
        System.out.print("Saldo inicial (mínimo $100): ");
        double saldoInicial = scanner.nextDouble();
        System.out.print("Porcentaje de interés: ");
        double porcentajeInteres = scanner.nextDouble();

        try {
            CuentaAhorro cuenta = new CuentaAhorro(numeroCuenta, saldoInicial, porcentajeInteres);
            if (sistema.crearCuentaAhorro(cuenta, correoCliente)) {
                System.out.println("Cuenta de ahorro creada exitosamente");
            } else {
                System.out.println("Error al crear la cuenta de ahorro");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void crearCuentaDebito(String numeroCuenta, String correoCliente) {
        System.out.print("Saldo inicial: ");
        double saldoInicial = scanner.nextDouble();
        System.out.print("Porcentaje de interés: ");
        double porcentajeInteres = scanner.nextDouble();

        CuentaDebito cuenta = new CuentaDebito(numeroCuenta, saldoInicial, porcentajeInteres);
        if (sistema.crearCuentaDebito(cuenta, correoCliente)) {
            System.out.println("Cuenta de débito creada exitosamente");
        } else {
            System.out.println("Error al crear la cuenta de débito");
        }
    }

    private void crearCuentaCredito(String numeroCuenta, String correoCliente) {
        System.out.print("Límite de crédito: ");
        double limiteCredito = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Tipo de cuenta (Cashback/Millas/etc.): ");
        String tipo = scanner.nextLine();

        CuentaCredito cuenta = new CuentaCredito(numeroCuenta, limiteCredito, tipo);
        if (sistema.crearCuentaCredito(cuenta, correoCliente)) {
            System.out.println("Cuenta de crédito creada exitosamente");
        } else {
            System.out.println("Error al crear la cuenta de crédito");
        }
    }
}
