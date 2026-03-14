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
  
  
  public Vehiculo() {
  }

  public Vehiculo(String placa, String ruta, double tarifa, int capacidadMaxima,int pasajerosActuales, Boolean estado, String idLicenciaConductor, int ticketsVendidos) {
    this.placa = placa;
    this.ruta = ruta;
    this.tarifa = tarifa;
    this.capacidadMaxima = capacidadMaxima;
    this.pasajerosActuales = pasajerosActuales;
    this.estado = estado;
    this.idLicenciaConductor = idLicenciaConductor;
    this.ticketsVendidos = ticketsVendidos;
  }
  
  public abstract String getTipo();
  
  public String getPlaca() {
    return placa;
  }

  public String getRuta() {
    return ruta;
  }

  public double getTarifa() {
    return tarifa;
  }

  public int getCapacidadMaxima() {
    return capacidadMaxima;
  }

  public int getPasajerosActuales() {
    return pasajerosActuales;
  }

  public Boolean getEstado() {
    return estado;
  }

  public String getIdLicenciaConductor() {
    return idLicenciaConductor;
  }

  public int getTicketsVendidos() {
    return ticketsVendidos;
  }


  public void setPlaca(String placa) {
    this.placa = placa;
  }

  public void setRuta(String ruta) {
    this.ruta = ruta;
  }

  public void setTarifa(double tarifa) {
    this.tarifa = tarifa;
  }

  public void setCapacidadMaxima(int capacidadMaxima) {
    this.capacidadMaxima = capacidadMaxima;
  }

  public void setPasajerosActuales(int pasajerosActuales) {
    this.pasajerosActuales = pasajerosActuales;
  }

  public void setEstado(Boolean estado) {
    this.estado = estado;
  }

  public void setIdLicenciaConductor(String idLicenciaConductor) {
    this.idLicenciaConductor = idLicenciaConductor;
  }

  public void setTicketsVendidos(int ticketsVendidos) {
    this.ticketsVendidos = ticketsVendidos;
  }
}
