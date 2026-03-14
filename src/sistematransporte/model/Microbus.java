/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematransporte.model;

/**
 *
 * @author abisa
 */
public class Microbus extends Vehiculo {
  public Microbus() {
  }
  
  public Microbus(String placa, String ruta) {
    super(placa, ruta, 10000.0, 25, 0, true, null, 0);
  }
  
  @Override
  public String getTipo(){
    return "Microbus";
  }
}