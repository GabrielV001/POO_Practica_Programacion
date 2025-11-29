package modelo;

public class CuentaDebito extends Cuenta {
    private double porcentajeInteres;

    public CuentaDebito(String numeroCuenta, double saldo, double porcentajeInteres) {
        super(numeroCuenta, saldo);
        this.porcentajeInteres = porcentajeInteres;
    }

    public double getPorcentajeInteres() {
        return porcentajeInteres;
    }

    public void setPorcentajeInteres(double porcentajeInteres) {
        this.porcentajeInteres = porcentajeInteres;
    }

    public void generarIntereses() {
        if (activa) {
            saldo += saldo * (porcentajeInteres / 100);
        }
    }

    @Override
    public boolean retiro(double monto) {
        if (!activa || monto > saldo) {
            return false;
        }
        saldo -= monto;
        return true;
    }

    @Override
    public boolean deposito(double monto) {
        if (!activa) {
            return false;
        }
        saldo += monto;
        return true;
    }

    @Override
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

