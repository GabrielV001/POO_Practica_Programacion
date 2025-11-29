package modelo;

public class CuentaCredito extends Cuenta {
    private double limiteCredito;
    private String tipo;

    public CuentaCredito(String numeroCuenta, double limiteCredito, String tipo) {
        super(numeroCuenta, 0); // Siempre inicia con saldo 0
        this.limiteCredito = limiteCredito;
        this.tipo = tipo;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean retiro(double monto) {
        if (!activa || (saldo - monto) < -limiteCredito) {
            return false;
        }
        saldo -= monto;
        return true;
    }

    @Override
    public boolean deposito(double monto) {
        return false; // Las cuentas de crédito no aceptan depósitos
    }

    public boolean abono(double monto) {
        if (!activa || (saldo + monto) > 0) {
            return false;
        }
        saldo += monto;
        return true;
    }

    @Override
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