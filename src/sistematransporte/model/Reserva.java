package sistematransporte.model;

import sistematransporte.model.interfaces.Imprimible;

/**
 * Representa el apartado temporal de un cupo en un vehiculo
 * por parte de un pasajero.
 */
public class Reserva implements Imprimible {

    // Estados posibles de una reserva
    public static final String ACTIVA     = "ACTIVA";
    public static final String CONVERTIDA = "CONVERTIDA";
    public static final String CANCELADA  = "CANCELADA";

    private int codigo;
    private Pasajero pasajero;
    private Vehiculo vehiculo;
    private String fechaCreacion;  // fecha en que se creo la reserva (yyyy-MM-dd HH:mm)
    private String fechaViaje;     // fecha para la que se reserva el viaje (yyyy-MM-dd)
    private String estado;         // ACTIVA | CONVERTIDA | CANCELADA

    public Reserva(int codigo, Pasajero pasajero, Vehiculo vehiculo,
                   String fechaCreacion, String fechaViaje) {
        this.codigo       = codigo;
        this.pasajero     = pasajero;
        this.vehiculo     = vehiculo;
        this.fechaCreacion = fechaCreacion;
        this.fechaViaje   = fechaViaje;
        this.estado       = ACTIVA; // toda reserva nace activa
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== RESERVA ===");
        System.out.println("Codigo:        " + codigo);
        System.out.println("Pasajero:      " + pasajero.getNombre() + " (" + pasajero.getCedula() + ")");
        System.out.println("Vehiculo:      " + vehiculo.getPlaca());
        System.out.println("Fecha creacion:" + fechaCreacion);
        System.out.println("Fecha viaje:   " + fechaViaje);
        System.out.println("Estado:        " + estado);
    }

    // Getters y Setters
    public int getCodigo()               { return codigo; }
    public void setCodigo(int codigo)    { this.codigo = codigo; }

    public Pasajero getPasajero()                    { return pasajero; }
    public void setPasajero(Pasajero pasajero)       { this.pasajero = pasajero; }

    public Vehiculo getVehiculo()                    { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo)       { this.vehiculo = vehiculo; }

    public String getFechaCreacion()                 { return fechaCreacion; }
    public void setFechaCreacion(String f)           { this.fechaCreacion = f; }

    public String getFechaViaje()                    { return fechaViaje; }
    public void setFechaViaje(String f)              { this.fechaViaje = f; }

    public String getEstado()                        { return estado; }
    public void setEstado(String estado)             { this.estado = estado; }
}