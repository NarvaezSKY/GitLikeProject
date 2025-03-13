/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancalinea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MMEDIA-MOVIL-1
 */
class SistemaBancario {
    private Map<String, usuario> usuarios;
    private int contadorCuentas;

    public SistemaBancario() {
        this.usuarios = new HashMap<>();
        this.contadorCuentas = 1000; // Contador para generar números de cuenta
        //creacion del admin
        usuario admin = new usuario("admin", "admin123", "admin");
        usuarios.put("admin", admin);
    }

    public boolean registrarUsuario(String nombreUsuario, String contraseña) {
        if (usuarios.containsKey(nombreUsuario)) {
            return false;
        }
        //usuarios por defecto con rol "usuario"
        usuario nuevoUsuario = new usuario(nombreUsuario, contraseña, "usuario");
        usuarios.put(nombreUsuario, nuevoUsuario);
        return true;
    }

    public usuario autenticarUsuario(String nombreUsuario, String contraseña) {
        usuario usuario = usuarios.get(nombreUsuario);
        if (usuario != null && usuario.autenticar(contraseña)) {
            return usuario;
        }
        return null;
    }

    public cuenta crearCuentaAhorro(usuario usuario, double saldoInicial, double interesAnual) {
        int numeroCuenta = contadorCuentas++;
        CuentaAhorro cuenta = new CuentaAhorro(numeroCuenta, saldoInicial, interesAnual);
        usuario.agregarCuenta(cuenta);
        return cuenta;
    }

    public cuenta crearCuentaCorriente(usuario usuario, double saldoInicial, double limiteSobregiro) {
        int numeroCuenta = contadorCuentas++;
        CuentaCorriente cuenta = new CuentaCorriente(numeroCuenta, saldoInicial, limiteSobregiro);
        usuario.agregarCuenta(cuenta);
        return cuenta;
    }

    public boolean transferir(cuenta origen, cuenta destino, double monto) {
        if (validarFondosSuficientes(origen, monto)) {
            origen.retirar(monto);
            destino.depositar(monto);
            registrarTransaccion(origen, destino, monto, "TRANSFERENCIA");
            return true;
        }
        return false;
    }

    private boolean validarFondosSuficientes(cuenta cuenta, double monto) {
        if (cuenta instanceof CuentaCorriente) {
            return ((CuentaCorriente) cuenta).verificarLimiteSobregiro(monto);
        }
        return cuenta.getSaldo() >= monto;
    }

    private void registrarTransaccion(cuenta origen, cuenta destino, double monto, String tipo) {
        Transaccion transaccionSalida = new Transaccion(-monto, tipo + " SALIDA");
        Transaccion transaccionEntrada = new Transaccion(monto, tipo + " ENTRADA");

        origen.agregarTransaccion(transaccionSalida);
        destino.agregarTransaccion(transaccionEntrada);
    }

    // Métodos para depósitos y retiros
    public boolean depositar(cuenta cuenta, double monto) {
        cuenta.depositar(monto);
        cuenta.agregarTransaccion(new Transaccion(monto, "DEPOSITO"));
        return true;
    }

    public boolean retirar(cuenta cuenta, double monto) {
        if (validarFondosSuficientes(cuenta, monto)) {
            cuenta.retirar(monto);
            cuenta.agregarTransaccion(new Transaccion(-monto, "RETIRO"));
            return true;
        }
        return false;
    }

    //Metodos admin
    public List<usuario> obtenerTodosLosUsuarios() {
        return new ArrayList<>(usuarios.values());
    }

    public List<cuenta> obtenerCuentasDeUsuario(String nombreUsuario) {
        usuario usuario = usuarios.get(nombreUsuario);
        return (usuario != null) ? usuario.getCuentas() : new ArrayList<>();
    }

}