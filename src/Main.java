public class Main {
    public static void main(String[] args) {
        // Crear instancia del banco
        Banco banco = new Banco();

        // Crear y registrar administrador
        Administrador admin = new Administrador("Admin", "123456789",
                "admin@banco.com", "admin123");
        banco.setAdministrador(admin);

        // Crear y registrar clientes
        Cliente cliente1 = new Cliente("Gabriel Vega", "987654321", "gabo@email.com",
                "pass123", "Masculino", "Ingeniero",
                "Coronado");
        Cliente cliente2 = new Cliente("Alejandra Arias", "987654322", "ale@email.com",
                "pass456", "Femenino", "Doctora",
                "Escazu");

        banco.agregarCliente(cliente1);
        banco.agregarCliente(cliente2);

        // Crear diferentes tipos de cuentas
        CuentaAhorro cuentaAhorro1 = new CuentaAhorro("AH001", 1000.0, 2.5);
        CuentaDebito cuentaDebito1 = new CuentaDebito("DB001", 500.0, 1.5);
        CuentaCredito cuentaCredito1 = new CuentaCredito("CR001", 1000.0, "Cashback");

        // Agregar cuentas al cliente1
        cliente1.agregarCuentaAhorro(cuentaAhorro1);
        cliente1.agregarCuentaDebito(cuentaDebito1);
        cliente1.agregarCuentaCredito(cuentaCredito1);

        // Crear y agregar cuentas al cliente2
        CuentaAhorro cuentaAhorro2 = new CuentaAhorro("AH001", 2000.0, 2.5);
        CuentaCredito cuentaCredito2 = new CuentaCredito("CR002", 2000.0, "Millas");

        cliente2.agregarCuentaAhorro(cuentaAhorro2);
        cliente2.agregarCuentaCredito(cuentaCredito2);

        // Pruebas
        System.out.println("=== Pruebas del Sistema Bancario ===\n");

        // Probar información del administrador
        System.out.println("Información del Administrador:");
        System.out.println(admin);

        // Probar listado de clientes
        System.out.println("\nListado de Clientes:");
        admin.listarClientes(banco.getClientes());

        // Probar operaciones en cuentas
        System.out.println("\nPruebas de Operaciones en Cuentas:");

        // Prueba cuenta de ahorro
        System.out.println("\nCuenta de Ahorro:");
        System.out.println("Saldo inicial: " + cuentaAhorro1.getSaldo());
        cuentaAhorro1.deposito(500.0);
        System.out.println("Después de depósito: " + cuentaAhorro1.getSaldo());
        cuentaAhorro1.generarIntereses();
        System.out.println("Después de generar intereses: " + cuentaAhorro1.getSaldo());

        // Prueba cuenta de débito
        System.out.println("\nCuenta de Débito:");
        System.out.println("Saldo inicial: " + cuentaDebito1.getSaldo());
        cuentaDebito1.deposito(200.0);
        System.out.println("Después de depósito: " + cuentaDebito1.getSaldo());
        cuentaDebito1.retiro(300.0);
        System.out.println("Después de retiro: " + cuentaDebito1.getSaldo());

        // Prueba cuenta de crédito
        System.out.println("\nCuenta de Crédito:");
        System.out.println("Saldo inicial: " + cuentaCredito1.getSaldo());
        cuentaCredito1.retiro(500.0);
        System.out.println("Después de retiro: " + cuentaCredito1.getSaldo());
        cuentaCredito1.abono(200.0);
        System.out.println("Después de abono: " + cuentaCredito1.getSaldo());

        // Listar todas las cuentas
        System.out.println("\nListado de todas las cuentas:");
        admin.listarTodasLasCuentas(banco.getClientes());
    }
}