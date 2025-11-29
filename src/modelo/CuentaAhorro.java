package modelo;

public class CuentaAhorro extends Cuenta {
    private double porcentajeInteres;

    public CuentaAhorro(String numeroCuenta, double saldo, double porcentajeInteres) {
        super(numeroCuenta, saldo);
        if (saldo < 100) {
            throw new IllegalArgumentException("El saldo mínimo debe ser 100 dólares");
        }
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
        if (!activa || monto > saldo || (saldo - monto) < 100) {
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
        return "CuentaAhorro{" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", saldo=" + saldo +
                ", activa=" + activa +
                ", porcentajeInteres=" + porcentajeInteres +
                '}';
    }
}
