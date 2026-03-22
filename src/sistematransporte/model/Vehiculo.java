package sistematransporte.model;
import sistematransporte.model.interfaces.*;
/**
 *
 * @author jssdv
 */
public abstract class Vehiculo implements Imprimible, Calculable {
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
  
  public boolean venderTicket(){
    if(pasajerosActuales >= capacidadMaxima){
      return false;
    }
    
    pasajerosActuales++;
    ticketsVendidos++;
    return true;
  }
  
  
  @Override
  public double calcularTotal(){
    return ticketsVendidos * tarifa;
  }


  @Override
  public String toString() {
    return placa + ";" + ruta + ";" + tarifa + ";" + capacidadMaxima + ";" +
           pasajerosActuales + ";" + estado + ";" + idLicenciaConductor + ";" + ticketsVendidos;
  }


  public void fromString(String data) {
    String[] partes = data.split(";");

    this.placa = partes[0];
    this.ruta = partes[1];
    this.tarifa = Double.parseDouble(partes[2]);
    this.capacidadMaxima = Integer.parseInt(partes[3]);
    this.pasajerosActuales = Integer.parseInt(partes[4]);
    this.estado = Boolean.parseBoolean(partes[5]);
    this.idLicenciaConductor = "null".equals(partes[6]) ? null : partes[6];
    this.ticketsVendidos = Integer.parseInt(partes[7]);
  }
  
  
  @Override
  public void imprimirDetalle() {
    System.out.println("| Tipo De Vehiculo: " + getTipo() +
                       "| Placa: " + getPlaca() +
                       "| Ruta: " + getRuta() +
                       "| Tarifa: " + getTarifa() +
                       "| Capcacidad Maxima: " + getCapacidadMaxima() +
                       "| Pasajeros Actuales: " + getPasajerosActuales() +
                       "| Estado: " + (getEstado() ? "Disponible"  : "No Disponible") + 
                       "| Id Licencia Conductor: " + (getIdLicenciaConductor() != null ? getIdLicenciaConductor() : "No Hay Conductor Asignado"));
  }

    double getTarifaBase() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean tieneCupos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void registrarVenta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
  
 
}
