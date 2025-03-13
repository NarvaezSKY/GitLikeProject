/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancalinea;

import java.util.Date;

/**
 *
 * @author MMEDIA-MOVIL-1
 */
public class Transaccion {
    private double monto;
    private Date fecha;
    private String tipo;

    public Transaccion(double monto, String tipo) {
        this.monto = monto;
        this.fecha = new Date();
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Transacción [tipo=" + tipo + ", monto=$" + monto + ", fecha=" + fecha + "]";
    }
}

