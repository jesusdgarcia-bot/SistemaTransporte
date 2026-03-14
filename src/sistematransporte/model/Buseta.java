/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematransporte.model;

/**
 *
 * @author abisa
 */
public class Buseta extends Vehiculo {
  public Buseta() {
  }
  
  public Buseta(String placa, String ruta) {
    super(placa, ruta, 8000.0, 18, 0, true, null, 0);
  }
  
  @Override
  public String getTipo(){
    return "Buseta";
  }
}
