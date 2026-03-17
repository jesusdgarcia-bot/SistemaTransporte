/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematransporte.model;
import sistematransporte.model.interfaces.Imprimible;
/**
 *
 * @author Equipo
 */
public abstract class Pasajero extends Persona implements Imprimible {
 
    public Pasajero(String cedula, String nombre) {
        super(cedula, nombre);
    }
 
    // Cada subclase define su propio descuento
    public abstract double calcularDescuento();
}