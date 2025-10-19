public class CuentaCredito {
    private String numeroCuenta;
    private double saldo;
    private boolean activa;
    private double limiteCredito;
    private String tipo;

    public CuentaCredito(String numeroCuenta, double limiteCredito, String tipo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = 0; // Siempre inicia en 0
        this.activa = true;
        this.limiteCredito = limiteCredito;
        this.tipo = tipo;
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

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Métodos de operaciones
    public boolean retiro(double monto) {
        if (!activa || (saldo - monto) < -limiteCredito) {
            return false;
        }
        saldo -= monto;
        return true;
    }

    public boolean abono(double monto) {
        if (!activa || (saldo + monto) > 0) {
            return false;
        }
        saldo += monto;
        return true;
    }

    public boolean pago(double monto) {
        return abono(monto);
    }

    @Override
    public String toString() {
        return "CuentaCredito{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", saldo=" + saldo +
                ", activa=" + activa +
                ", limiteCredito=" + limiteCredito +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
