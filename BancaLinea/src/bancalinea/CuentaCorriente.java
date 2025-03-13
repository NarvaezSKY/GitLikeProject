/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancalinea;

/**
 *
 * @author MMEDIA-MOVIL-1
 */
class CuentaCorriente extends cuenta {
    private double limiteSobregiro;

    public CuentaCorriente(int numeroCuenta, double saldoInicial, double limiteSobregiro) {
        super(numeroCuenta, saldoInicial);
        this.limiteSobregiro = limiteSobregiro;
    }

    public boolean verificarLimiteSobregiro(double monto) {
        return (getSaldo() + limiteSobregiro) >= monto;
    }

    @Override
    public boolean retirar(double monto) {
        if (monto <= getSaldo()) {
            // Saldo suficiente, retiro normal
            depositar(-monto);
            return true;
        } else if (verificarLimiteSobregiro(monto)) {
            // Usando sobregiro
            depositar(-monto);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Cuenta Corriente " + super.toString() + " - Límite Sobregiro: $" + limiteSobregiro;
    }
}
