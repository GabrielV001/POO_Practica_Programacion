package modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    private String sexo;
    private String profesion;
    private String direccion;
    private List<CuentaAhorro> cuentasAhorro;
    private List<CuentaDebito> cuentasDebito;
    private List<CuentaCredito> cuentasCredito;

    public Cliente(String nombreCompleto, String cedula, String correoElectronico,
                   String contrasenia, String sexo, String profesion, String direccion) {
        super(nombreCompleto, cedula, correoElectronico, contrasenia);
        this.sexo = sexo;
        this.profesion = profesion;
        this.direccion = direccion;
        this.cuentasAhorro = new ArrayList<>();
        this.cuentasDebito = new ArrayList<>();
        this.cuentasCredito = new ArrayList<>();
    }
    // Getters y Setters adicionales
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

    public List<CuentaAhorro> getCuentasAhorro() {
        return cuentasAhorro;
    }

    public List<CuentaDebito> getCuentasDebito() {
        return cuentasDebito;
    }

    public List<CuentaCredito> getCuentasCredito() {
        return cuentasCredito;
    }

    // Método para calcular saldo consolidado
    public double calcularSaldoConsolidado() {
        double saldoTotal = 0;

        for (CuentaAhorro cuenta : cuentasAhorro) {
            saldoTotal += cuenta.getSaldo();
        }

        for (CuentaDebito cuenta : cuentasDebito) {
            saldoTotal += cuenta.getSaldo();
        }

        for (CuentaCredito cuenta : cuentasCredito) {
            saldoTotal += cuenta.getSaldo();
        }

        return saldoTotal;
    }

    @Override
    public String toString() {
        return "model.Cliente{" +
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