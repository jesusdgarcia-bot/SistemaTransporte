package sistematransporte.model;
import sistematransporte.model.interfaces.*;
/**
 *
 * @author jssdv
 */
abstract class Vehiculo implements Imprimible, Calculable {
  private String placa;
  private String ruta;
  private double tarifa;
  private int capacidadMaxima; 
  private int pasajerosActuales;
  private Boolean estado;
  private String idLicenciaConductor;
  private int ticketsVendidos;
}
