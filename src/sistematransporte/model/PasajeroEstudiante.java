/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematransporte.model;

/**
 *
 * @author Equipo
 */
public class PasajeroEstudiante extends Pasajero {
 
    public PasajeroEstudiante(String cedula, String nombre) {
        super(cedula, nombre);
    }
 
    @Override
    public double calcularDescuento() {
        return 0.15; // 15% de descuento
    }
 
    @Override
    public void imprimirDetalle() {
        System.out.println("=== PASAJERO ESTUDIANTE ===");
        System.out.println("Cedula:    " + getCedula());
        System.out.println("Nombre:    " + getNombre());
        System.out.println("Descuento: 15%");
    }
}
 