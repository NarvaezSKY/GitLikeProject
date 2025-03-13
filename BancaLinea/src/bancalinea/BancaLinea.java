/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bancalinea;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author MMEDIA-MOVIL-1
 */
public class BancaLinea {

    private static Scanner scanner = new Scanner(System.in);
    private static SistemaBancario sistema = new SistemaBancario();
    private static usuario usuarioActual = null;

    public static void main(String[] args) {
        // Crear algunos datos de ejemplo
        inicializarDatosEjemplo();

        boolean salir = false;

        while (!salir) {
            if (usuarioActual == null) {
                mostrarMenuPrincipal();
            } else {
                mostrarMenuUsuario();
            }

            int opcion = obtenerOpcion();

            if (usuarioActual == null) {
                procesarMenuPrincipal(opcion);
            } else {
                salir = procesarMenuUsuario(opcion);
            }
        }

        scanner.close();
        System.out.println("¡Gracias por utilizar nuestro sistema bancario!");
    }

    private static void inicializarDatosEjemplo() {
        sistema.registrarUsuario("usuario1", "123456");
        usuario usuario1 = sistema.autenticarUsuario("usuario1", "123456");
        sistema.crearCuentaAhorro(usuario1, 1000, 2.5);
        sistema.crearCuentaCorriente(usuario1, 500, 1000);

        sistema.registrarUsuario("usuario2", "654321");
        usuario usuario2 = sistema.autenticarUsuario("usuario2", "654321");
        sistema.crearCuentaAhorro(usuario2, 2000, 2.0);
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n===== SISTEMA BANCARIO =====");
        System.out.println("1. Iniciar sesion");
        System.out.println("2. Registrarse");
        System.out.println("3. Salir");
        System.out.println("==============================");
        System.out.print("Seleccione una opción: ");
    }

    private static void mostrarMenuUsuario() {

        if (usuarioActual.getRol().equals("admin")) {
            System.out.println(
                    "\n===== BIENVENIDO ADMINISTRADOR " + usuarioActual.getNombreUsuario().toUpperCase() + " =====");
            System.out.println("1. Ver usuarios");
            System.out.println("2. Ver cuentas de usuario");
            System.out.println("3. Cerrar sesion");
            System.out.print("Seleccione una opcion: ");

            return;
        }

        System.out.println("\n===== BIENVENIDO " + usuarioActual.getNombreUsuario().toUpperCase() + " =====");
        System.out.println("1. Ver mis cuentas");
        System.out.println("2. Crear cuenta de ahorro");
        System.out.println("3. Crear cuenta corriente");
        System.out.println("4. Realizar depósito");
        System.out.println("5. Realizar retiro");
        System.out.println("6. Realizar transferencia");
        System.out.println("7. Ver transacciones");
        System.out.println("8. Cerrar sesion");
        System.out.println("9. Salir");
        System.out.println("==============================");
        System.out.print("Seleccione una opcion: ");

    }

    private static int obtenerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void procesarMenuPrincipal(int opcion) {
        switch (opcion) {
            case 1:
                iniciarSesion();
                break;
            case 2:
                registrarUsuario();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Opción invalida. Intente nuevamente.");
        }
    }

    private static boolean procesarMenuUsuario(int opcion) {

        if (usuarioActual.getRol().equals("admin")) {
            switch (opcion) {
                case 1:
                    verUsuarios();
                    break;
                case 2:
                    verCuentasDeUsuario();
                    break;
                case 3:
                    cerrarSesion();
                    break;
                default:
                    System.out.println("Opción invalida. Intente nuevamente.");
            }
            return false;
        }

        switch (opcion) {
            case 1:
                verCuentas();
                break;
            case 2:
                crearCuentaAhorro();
                break;
            case 3:
                crearCuentaCorriente();
                break;
            case 4:
                realizarDeposito();
                break;
            case 5:
                realizarRetiro();
                break;
            case 6:
                realizarTransferencia();
                break;
            case 7:
                verTransacciones();
                break;
            case 8:
                cerrarSesion();
                break;
            case 9:
                return true;
            default:
                System.out.println("Opción invalida. Intente nuevamente.");
        }
        return false;
    }

    private static void iniciarSesion() {
        System.out.print("Nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Contrasena: ");
        String contraseña = scanner.nextLine();

        usuarioActual = sistema.autenticarUsuario(nombreUsuario, contraseña);

        if (usuarioActual == null) {
            System.out.println("Credenciales incorrectas. Intente nuevamente.");
        } else {
            System.out.println("Sesión iniciada correctamente.");
        }
    }

    private static void registrarUsuario() {
        System.out.print("Nuevo nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Nueva contrasena: ");
        String contraseña = scanner.nextLine();

        boolean registrado = sistema.registrarUsuario(nombreUsuario, contraseña);

        if (registrado) {
            System.out.println("Usuario registrado correctamente.");
        } else {
            System.out.println("El nombre de usuario ya existe. Intente con otro.");
        }
    }

    private static void verCuentas() {
        List<cuenta> cuentas = usuarioActual.getCuentas();

        if (cuentas.isEmpty()) {
            System.out.println("No tiene cuentas registradas.");
            return;
        }

        System.out.println("\n----- Sus Cuentas -----");
        for (int i = 0; i < cuentas.size(); i++) {
            System.out.println((i + 1) + ". " + cuentas.get(i));
        }
    }

    private static void crearCuentaAhorro() {
        System.out.print("Saldo inicial: $");
        double saldoInicial = Double.parseDouble(scanner.nextLine());
        System.out.print("Tasa de interés anual (%): ");
        double interesAnual = Double.parseDouble(scanner.nextLine());

        cuenta cuenta = sistema.crearCuentaAhorro(usuarioActual, saldoInicial, interesAnual);
        System.out.println("Cuenta de ahorro creada con exito: " + cuenta);
    }

    private static void crearCuentaCorriente() {
        System.out.print("Saldo inicial: $");
        double saldoInicial = Double.parseDouble(scanner.nextLine());
        System.out.print("Límite de sobregiro: $");
        double limiteSobregiro = Double.parseDouble(scanner.nextLine());

        cuenta cuenta = sistema.crearCuentaCorriente(usuarioActual, saldoInicial, limiteSobregiro);
        System.out.println("Cuenta corriente creada con éxito: " + cuenta);
    }

    private static cuenta seleccionarCuenta() {
        List<cuenta> cuentas = usuarioActual.getCuentas();

        if (cuentas.isEmpty()) {
            System.out.println("No tiene cuentas registradas.");
            return null;
        }

        System.out.println("\n----- Seleccione una cuenta -----");
        for (int i = 0; i < cuentas.size(); i++) {
            System.out.println((i + 1) + ". " + cuentas.get(i));
        }

        System.out.print("Número de opcion: ");
        try {
            int opcion = Integer.parseInt(scanner.nextLine());
            if (opcion >= 1 && opcion <= cuentas.size()) {
                return cuentas.get(opcion - 1);
            } else {
                System.out.println("Opcion invalida.");
                return null;
            }
        } catch (NumberFormatException e) {
            System.out.println("Opcion invalida.");
            return null;
        }
    }

    private static void realizarDeposito() {
        cuenta cuenta = seleccionarCuenta();
        if (cuenta == null)
            return;

        System.out.print("Monto a depositar: $");
        try {
            double monto = Double.parseDouble(scanner.nextLine());
            if (monto <= 0) {
                System.out.println("El monto debe ser mayor a cero.");
                return;
            }

            boolean resultado = sistema.depositar(cuenta, monto);
            if (resultado) {
                System.out.println("Depósito realizado con exito. Nuevo saldo: $" + cuenta.getSaldo());
            } else {
                System.out.println("No se pudo realizar el depósito.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Monto inválido.");
        }
    }

    private static void realizarRetiro() {
        cuenta cuenta = seleccionarCuenta();
        if (cuenta == null)
            return;

        System.out.print("Monto a retirar: $");
        try {
            double monto = Double.parseDouble(scanner.nextLine());
            if (monto <= 0) {
                System.out.println("El monto debe ser mayor a cero.");
                return;
            }

            boolean resultado = sistema.retirar(cuenta, monto);
            if (resultado) {
                System.out.println("Retiro realizado con éxito. Nuevo saldo: $" + cuenta.getSaldo());
            } else {
                System.out.println("No se pudo realizar el retiro. Fondos insuficientes.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Monto inválido.");
        }
    }

    private static void realizarTransferencia() {
        System.out.println("Seleccione la cuenta de origen:");
        cuenta origen = seleccionarCuenta();
        if (origen == null)
            return;

        System.out.println("Seleccione la cuenta de destino:");
        cuenta destino = seleccionarCuenta();
        if (destino == null)
            return;

        if (origen.getNumeroCuenta() == destino.getNumeroCuenta()) {
            System.out.println("No puede transferir a la misma cuenta.");
            return;
        }

        System.out.print("Monto a transferir: $");
        try {
            double monto = Double.parseDouble(scanner.nextLine());
            if (monto <= 0) {
                System.out.println("El monto debe ser mayor a cero.");
                return;
            }

            boolean resultado = sistema.transferir(origen, destino, monto);
            if (resultado) {
                System.out.println("Transferencia realizada con éxito.");
                System.out.println("Nuevo saldo cuenta origen: $" + origen.getSaldo());
                System.out.println("Nuevo saldo cuenta destino: $" + destino.getSaldo());
            } else {
                System.out.println("No se pudo realizar la transferencia. Fondos insuficientes.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Monto invalido.");
        }
    }

    private static void verTransacciones() {
        cuenta cuenta = seleccionarCuenta();
        if (cuenta == null)
            return;

        List<Transaccion> transacciones = cuenta.getTransacciones();

        if (transacciones.isEmpty()) {
            System.out.println("No hay transacciones registradas para esta cuenta.");
            return;
        }

        System.out.println("\n----- Transacciones de la Cuenta #" + cuenta.getNumeroCuenta() + " -----");
        for (Transaccion t : transacciones) {
            System.out.println(t);
        }
    }

    private static void verUsuarios() {
        List<usuario> usuarios = sistema.obtenerTodosLosUsuarios();

        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados en el sistema.");
            return;
        }

        System.out.println("\n===== LISTA DE USUARIOS =====");
        for (usuario u : usuarios) {
            System.out.println("Usuario: " + u.getNombreUsuario());
        }
    }

    private static void verCuentasDeUsuario() {
        System.out.print("Ingrese el nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();

        List<cuenta> cuentas = sistema.obtenerCuentasDeUsuario(nombreUsuario);

        if (cuentas == null) {
            System.out.println("El usuario no existe.");
            return;
        }

        if (cuentas.isEmpty()) {
            System.out.println("El usuario no tiene cuentas registradas.");
            return;
        }

        System.out.println("\n===== CUENTAS DEL USUARIO " + nombreUsuario.toUpperCase() + " =====");
        for (cuenta c : cuentas) {
            System.out.println(c);
        }
    }

    private static void cerrarSesion() {
        usuarioActual = null;
        System.out.println("Sesion cerrada correctamente.");
    }

}
