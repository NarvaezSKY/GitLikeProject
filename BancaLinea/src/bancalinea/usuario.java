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
public class usuario {
    private String nombreUsuario;
    private String contraseña;
    private String rol;
    private List<cuenta> cuentas;

    public usuario(String nombreUsuario, String contraseña, String rol) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.rol = rol;
        this.cuentas = new ArrayList<>();
    }

    public boolean autenticar(String contraseña) {
        return this.contraseña.equals(contraseña);
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    //nueva propiedad del usuario
    public String getRol() {
        return rol;
    }

    public List<cuenta> getCuentas() {
        return cuentas;
    }

    public void agregarCuenta(cuenta cuenta) {
        cuentas.add(cuenta);
    }
}
