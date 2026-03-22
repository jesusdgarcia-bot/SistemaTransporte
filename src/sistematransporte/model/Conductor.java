
package sistematransporte.model;

import sistematransporte.model.interfaces.Imprimible;

/**
 *
 * @author almen
 */
public class Conductor extends Persona implements Imprimible {
    private String numeroLicencia;
    private String categoriaLicencia; // B1, B2, C1, C2
    private boolean disponible;
 
    public Conductor(String cedula, String nombre, String numeroLicencia, String categoriaLicencia) {
        super(cedula, nombre);
        this.numeroLicencia = numeroLicencia;
        this.categoriaLicencia = categoriaLicencia;
    }


 
    public String getNumeroLicencia() {
        return numeroLicencia;
    }
 
    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }
 
    public String getCategoriaLicencia() {
        return categoriaLicencia;
    }
 
    public void setCategoriaLicencia(String categoriaLicencia) {
        this.categoriaLicencia = categoriaLicencia;
    }
 
    // Retorna true si el conductor tiene licencia valida
    public boolean tieneLicencia() {
        return numeroLicencia != null && !numeroLicencia.trim().isEmpty();
    }
    ;
    
    public boolean getDisponible(){
        return disponible;
    }
    
     @Override
    public void imprimirDetalle() {
        System.out.println("=== CONDUCTOR ===");
        System.out.println("Cedula:    " + getCedula());
        System.out.println("Nombre:    " + getNombre());
        System.out.println("Licencia:  " + numeroLicencia);
        System.out.println("Categoria: " + categoriaLicencia);
    }
}
