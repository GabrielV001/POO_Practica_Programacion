package vista;

import controlador.SistemaBancario;
import java.util.Scanner;

public class MenuCliente {
    private Scanner scanner;
    private SistemaBancario sistema;
    private String correoCliente;

    public MenuCliente(SistemaBancario sistema, String correoCliente) {
        this.scanner = new Scanner(System.in);
        this.sistema = sistema;
        this.correoCliente = correoCliente;
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("\n=== MENÚ CLIENTE ===");
            System.out.println("1. Ver mis cuentas");
            System.out.println("2. Realizar transacción");
            System.out.println("3. Ver estado de cuenta consolidado");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    verMisCuentas();
                    break;
                case 2:
                    realizarTransaccion();
                    break;
                case 3:
                    verEstadoConsolidado();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void verMisCuentas() {
        System.out.println("\n=== MIS CUENTAS ===");
        sistema.mostrarEstadoCuentas(correoCliente);
    }

    private void realizarTransaccion() {
        System.out.println("\n=== REALIZAR TRANSACCIÓN ===");
        System.out.println("1. Retiro");
        System.out.println("2. Depósito");
        System.out.println("3. Pago");
        System.out.println("4. Abono a crédito");
        System.out.print("Seleccione tipo de transacción: ");

        int tipoTransaccion = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Tipo de cuenta (AHORRO/DEBITO/CREDITO): ");
        String tipoCuenta = scanner.nextLine();
        System.out.print("Monto: ");
        double monto = scanner.nextDouble();

        String tipoTransaccionStr;
        switch (tipoTransaccion) {
            case 1:
                tipoTransaccionStr = "RETIRO";
                break;
            case 2:
                tipoTransaccionStr = "DEPOSITO";
                break;
            case 3:
                tipoTransaccionStr = "PAGO";
                break;
            case 4:
                tipoTransaccionStr = "ABONO";
                break;
            default:
                System.out.println("Tipo de transacción no válida");
                return;
        }

        if (sistema.realizarTransaccion(numeroCuenta, tipoCuenta, tipoTransaccionStr, monto)) {
            System.out.println("Transacción realizada exitosamente");
        } else {
            System.out.println("Error al realizar la transacción");
        }
    }

    private void verEstadoConsolidado() {
        System.out.println("\n=== ESTADO DE CUENTA CONSOLIDADO ===");
        sistema.mostrarEstadoCuentas(correoCliente);
    }
}
