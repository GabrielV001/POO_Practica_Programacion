import java.util.ArrayList;

public class Administrador {
    private String nombreCompleto;
    private String cedula;
    private String correoElectronico;
    private String contrasenia;

    public Administrador(String nombreCompleto, String cedula,
                         String correoElectronico, String contrasenia) {
        this.nombreCompleto = nombreCompleto;
        this.cedula = cedula;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
    }

    // Getters y Setters
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    // Métodos de administración
    public void listarClientes(ArrayList<Cliente> clientes) {
        System.out.println("Lista de Clientes:");
        for (Cliente cliente : clientes) {
            System.out.println(cliente.toString());
        }
    }

    public void listarCuentasAhorro(ArrayList<Cliente> clientes) {
        System.out.println("Lista de Cuentas de Ahorro:");
        for (Cliente cliente : clientes) {
            for (CuentaAhorro cuenta : cliente.getCuentasAhorro()) {
                System.out.println("Cliente: " + cliente.getNombreCompleto() +
                        " - " + cuenta.toString());
            }
        }
    }

    public void listarCuentasDebito(ArrayList<Cliente> clientes) {
        System.out.println("Lista de Cuentas de Débito:");
        for (Cliente cliente : clientes) {
            for (CuentaDebito cuenta : cliente.getCuentasDebito()) {
                System.out.println("Cliente: " + cliente.getNombreCompleto() +
                        " - " + cuenta.toString());
            }
        }
    }

    public void listarCuentasCredito(ArrayList<Cliente> clientes) {
        System.out.println("Lista de Cuentas de Crédito:");
        for (Cliente cliente : clientes) {
            for (CuentaCredito cuenta : cliente.getCuentasCredito()) {
                System.out.println("Cliente: " + cliente.getNombreCompleto() +
                        " - " + cuenta.toString());
            }
        }
    }

    public void listarTodasLasCuentas(ArrayList<Cliente> clientes) {
        listarCuentasAhorro(clientes);
        listarCuentasDebito(clientes);
        listarCuentasCredito(clientes);
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", cedula='" + cedula + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                '}';
    }
}