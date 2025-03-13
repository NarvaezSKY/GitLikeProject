/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancalinea;

/**
 *
 * @author MMEDIA-MOVIL-1
 */
class CuentaAhorro extends cuenta {
    private double interesAnual;

    public CuentaAhorro(int numeroCuenta, double saldoInicial, double interesAnual) {
        super(numeroCuenta, saldoInicial);
        this.interesAnual = interesAnual;
    }

    public double calcularInteres() {
        double interes = getSaldo() * (interesAnual / 100) / 12; // Mensual
        return interes;
    }

    public void aplicarInteres() {
        double interes = calcularInteres();
        depositar(interes);
    }

    @Override
    public String toString() {
        return "Cuenta de Ahorro " + super.toString() + " - Interés Anual: " + interesAnual + "%";
    }
}