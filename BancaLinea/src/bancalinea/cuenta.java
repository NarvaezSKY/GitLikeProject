/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancalinea;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MMEDIA-MOVIL-1
 */
abstract class cuenta {
    
        private double saldo;
        private int numeroCuenta;
        private List<Transaccion> transacciones;

        public cuenta(int numeroCuenta, double saldoInicial) {
            this.numeroCuenta = numeroCuenta;
            this.saldo = saldoInicial;
            this.transacciones = new ArrayList<>();
        }

        public double getSaldo() {
            return saldo;
        }

        public int getNumeroCuenta() {
            return numeroCuenta;
        }

        public List<Transaccion> getTransacciones() {
            return transacciones;
        }

        public void agregarTransaccion(Transaccion transaccion) {
            transacciones.add(transaccion);
        }

        public void depositar(double monto) {
            saldo += monto;
        }

        public boolean retirar(double monto) {
            if (monto <= saldo) {
                saldo -= monto;
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Cuenta #" + numeroCuenta + " - Saldo: $" + saldo;
        }
    }

