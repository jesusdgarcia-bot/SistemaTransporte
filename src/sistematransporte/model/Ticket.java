
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematransporte.model;
import sistematransporte.model.interfaces.Calculable;
import sistematransporte.model.interfaces.Imprimible;

/**
 *
 * @author Equipo
 */
public class Ticket implements Calculable, Imprimible {
 
    private int id;
    private Pasajero pasajero;
    private Vehiculo vehiculo;
    private String fechaCompra;
    private String origen;
    private String destino;
    private double valorFinal;
 
    public Ticket(int id, Pasajero pasajero, Vehiculo vehiculo,
                  String fechaCompra, String origen, String destino) {
        this.id = id;
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
        this.fechaCompra = fechaCompra;
        this.origen = origen;
        this.destino = destino;
        // Calcula el valor automaticamente al crear el ticket
        calcularTotal();
    }
     /**
     * valorFinal = tarifaBase * (1 - descuento del pasajero)
     */
    @Override
    public double calcularTotal() {
        valorFinal = vehiculo.getTarifaBase() * (1 - pasajero.calcularDescuento());

        valorFinal = vehiculo.getTarifa() * (1 - pasajero.calcularDescuento());
        return valorFinal;
    }

    
     @Override
    public void imprimirDetalle() {
        System.out.println("=== TICKET ===");
        System.out.println("ID:           " + id);
        System.out.println("Pasajero:     " + pasajero.getNombre() + " (" + pasajero.getCedula() + ")");
        System.out.println("Vehiculo:     " + vehiculo.getPlaca());
        System.out.println("Origen:       " + origen);
        System.out.println("Destino:      " + destino);
        System.out.println("Fecha compra: " + fechaCompra);
        System.out.println("Valor final:  $" + String.format("%.0f", valorFinal));
    }
    
     public int getId() { return id; }
    public void setId(int id) { this.id = id; }
 
    public Pasajero getPasajero() { return pasajero; }
    public void setPasajero(Pasajero pasajero) { this.pasajero = pasajero; }
 
    public Vehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }
 
    public String getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(String fechaCompra) { this.fechaCompra = fechaCompra; }
 
    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }
 
    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }
 
    public double getValorFinal() { return valorFinal; }
    public void setValorFinal(double valorFinal) { this.valorFinal = valorFinal; }
    
}