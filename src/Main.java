// Main.java
public class Main {
    public static void main(String[] args) {
        try {
            // Mensaje de bienvenida
            System.out.println("=================================");
            System.out.println("    SISTEMA BANCARIO CENFOTEC    ");
            System.out.println("=================================");
            System.out.println();

            // Iniciar el sistema
            vista.MenuPrincipal menuPrincipal = new vista.MenuPrincipal();
            menuPrincipal.mostrarMenu();

        } catch (Exception e) {
            System.out.println("Error en el sistema: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Asegurar que se cierra el scanner y otros recursos
            try {
                // Aquí podrías agregar código para cerrar conexiones o recursos
                System.out.println("\nGracias por usar el Sistema Bancario Cenfotec");
                System.out.println("=================================");
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
}