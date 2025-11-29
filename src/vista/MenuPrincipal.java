package vista;

import controlador.SistemaBancario;
import modelo.Cliente;
import java.util.Scanner;

public class MenuPrincipal {
    private Scanner scanner;
    private SistemaBancario sistema;

    public MenuPrincipal() {
        this.scanner = new Scanner(System.in);
        this.sistema = new SistemaBancario();
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("\n=================================");
            System.out.println("    SISTEMA BANCARIO CENFOTEC    ");
            System.out.println("=================================");
            System.out.println("\n=== SISTEMA BANCARIO ===");
            System.out.println("1. Login Administrador"); // Quitado el "model."
            System.out.println("2. Login Cliente");      // Quitado el "model."
            System.out.println("3. Registrar Cliente");  // Quitado el "model."
            System.out.println("4. Salir");
            System.out.print("\nSeleccione una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        loginAdministrador();
                        break;
                    case 2:
                        loginCliente();
                        break;
                    case 3:
                        registrarCliente();
                        break;
                    case 4:
                        System.out.println("\n=================================");
                        System.out.println("  ¡Gracias por usar el sistema!  ");
                        System.out.println("=================================");
                        return;
                    default:
                        System.out.println("\nError: Opción no válida");
                }
            } catch (Exception e) {
                System.out.println("\nError: Por favor ingrese un número válido");
                scanner.nextLine(); // Limpiar buffer en caso de error
            }
        }
    }

    private void loginAdministrador() {
        System.out.println("\n=== LOGIN ADMINISTRADOR ===");
        System.out.print("Correo electrónico: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenia = scanner.nextLine();

        if (sistema.autenticarAdministrador(correo, contrasenia)) {
            MenuAdministrador menuAdmin = new MenuAdministrador(sistema);
            menuAdmin.mostrarMenu();
        } else {
            System.out.println("\nError: Credenciales inválidas");
        }
    }

    private void loginCliente() {
        System.out.println("\n=== LOGIN CLIENTE ===");
        System.out.print("Correo electrónico: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenia = scanner.nextLine();

        if (sistema.autenticarCliente(correo, contrasenia)) {
            MenuCliente menuCliente = new MenuCliente(sistema, correo);
            menuCliente.mostrarMenu();
        } else {
            System.out.println("\nError: Credenciales inválidas");
        }
    }

    private void registrarCliente() {
        System.out.println("\n=== REGISTRO DE CLIENTE ===");
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.print("Cédula: ");
        String cedula = scanner.nextLine();
        System.out.print("Correo electrónico: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contrasenia = scanner.nextLine();
        System.out.print("Sexo: ");
        String sexo = scanner.nextLine();
        System.out.print("Profesión: ");
        String profesion = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        Cliente nuevoCliente = new Cliente(nombre, cedula, correo, contrasenia,
                sexo, profesion, direccion);

        if (sistema.registrarCliente(nuevoCliente)) {
            System.out.println("\n¡Cliente registrado exitosamente!");
        } else {
            System.out.println("\nError: No se pudo registrar el cliente");
        }
    }
}