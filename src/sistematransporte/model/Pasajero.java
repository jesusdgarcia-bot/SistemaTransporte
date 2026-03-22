package sistematransporte.model;
import sistematransporte.model.interfaces.Imprimible;
/**
 *
 * @author Equipo
 */
public abstract class Pasajero extends Persona implements Imprimible {
 
    private String fechaNacimiento;
 
    public Pasajero(String cedula, String nombre) {
        super(cedula, nombre);
    }
 
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
 
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
 
    // Cada subclase define su propio descuento
    public abstract double calcularDescuento();
}