public class CuentaDebito {
    private String numeroCuenta;
    private double saldo;
    private boolean activa;
    private double porcentajeInteres;

    public CuentaDebito(String numeroCuenta, double saldo, double porcentajeInteres) {
        if (saldo < 0) {
            throw new IllegalArgumentException("El saldo no puede ser negativo");
        }
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.activa = true;
        this.porcentajeInteres = porcentajeInteres;
    }

    // Getters y Setters
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public double getPorcentajeInteres() {
        return porcentajeInteres;
    }

    public void setPorcentajeInteres(double porcentajeInteres) {
        this.porcentajeInteres = porcentajeInteres;
    }

    // Métodos de operaciones
    public void generarIntereses() {
        if (activa) {
            saldo += saldo * (porcentajeInteres / 100);
        }
    }

    public boolean retiro(double monto) {
        if (!activa || monto > saldo) {
            return false;
        }
        saldo -= monto;
        return true;
    }

    public boolean deposito(double monto) {
        if (!activa) {
            return false;
        }
        saldo += monto;
        return true;
    }

    public boolean pago(double monto) {
        return retiro(monto);
    }

    @Override
    public String toString() {
        return "CuentaDebito{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", saldo=" + saldo +
                ", activa=" + activa +
                ", porcentajeInteres=" + porcentajeInteres +
                '}';
    }
}


