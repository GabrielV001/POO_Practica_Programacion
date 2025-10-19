import java.util.ArrayList;

public class Cliente {
    private String nombreCompleto;
    private String cedula;
    private String correoElectronico;
    private String contrasenia;
    private String sexo;
    private String profesion;
    private String direccion;
    private ArrayList<CuentaAhorro> cuentasAhorro;
    private ArrayList<CuentaDebito> cuentasDebito;
    private ArrayList<CuentaCredito> cuentasCredito;

    public Cliente(String nombreCompleto, String cedula, String correoElectronico,
                   String contrasenia, String sexo, String profesion, String direccion) {
        this.nombreCompleto = nombreCompleto;
        this.cedula = cedula;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
        this.sexo = sexo;
        this.profesion = profesion;
        this.direccion = direccion;
        this.cuentasAhorro = new ArrayList<>();
        this.cuentasDebito = new ArrayList<>();
        this.cuentasCredito = new ArrayList<>();
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // Métodos para manejar cuentas
    public void agregarCuentaAhorro(CuentaAhorro cuenta) {
        cuentasAhorro.add(cuenta);
    }

    public void agregarCuentaDebito(CuentaDebito cuenta) {
        cuentasDebito.add(cuenta);
    }

    public void agregarCuentaCredito(CuentaCredito cuenta) {
        cuentasCredito.add(cuenta);
    }

    public ArrayList<CuentaAhorro> getCuentasAhorro() {
        return cuentasAhorro;
    }

    public ArrayList<CuentaDebito> getCuentasDebito() {
        return cuentasDebito;
    }

    public ArrayList<CuentaCredito> getCuentasCredito() {
        return cuentasCredito;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", cedula='" + cedula + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", sexo='" + sexo + '\'' +
                ", profesion='" + profesion + '\'' +
                ", direccion='" + direccion + '\'' +
                ", número de cuentas de ahorro=" + cuentasAhorro.size() +
                ", número de cuentas de débito=" + cuentasDebito.size() +
                ", número de cuentas de crédito=" + cuentasCredito.size() +
                '}';
    }
}