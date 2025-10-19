import java.util.ArrayList;

public class Banco {
    private Administrador administrador;
    private ArrayList<Cliente> clientes;

    public Banco() {
        this.clientes = new ArrayList<>();
    }

    public void setAdministrador(Administrador admin) {
        if (this.administrador == null) {
            this.administrador = admin;
        } else {
            throw new IllegalStateException("Ya existe un administrador registrado");
        }
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void agregarCliente(Cliente cliente) {
        if (administrador == null) {
            throw new IllegalStateException("Debe existir un administrador antes de agregar clientes");
        }
        clientes.add(cliente);
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
}