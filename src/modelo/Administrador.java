package modelo;

public class Administrador extends Usuario {
    public Administrador(String nombreCompleto, String cedula,
                         String correoElectronico, String contrasenia) {
        super(nombreCompleto, cedula, correoElectronico, contrasenia);
    }

    @Override
    public String toString() {
        return "model.Administrador{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", cedula='" + cedula + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                '}';
    }
}