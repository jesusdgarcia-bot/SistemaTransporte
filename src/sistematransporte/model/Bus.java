/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematransporte.model;

/**
 *
 * @author abisa
 */
class Bus extends Vehiculo {
  public Bus() {
  }
  
  public Bus(String placa, String ruta) {
    super(placa, ruta, 15000.0, 45, 0, true, null, 0);
  }
  
  @Override
  public String getTipo(){
    return "Bus";
  }
}