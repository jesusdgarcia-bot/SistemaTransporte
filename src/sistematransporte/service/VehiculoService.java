package sistematransporte.service;

import sistematransporte.dao.VehiculoDao;
import sistematransporte.dao.ConductorDao;
import sistematransporte.model.Conductor;
import sistematransporte.model.Vehiculo;
import sistematransporte.model.Bus;
import sistematransporte.model.Microbus;
import sistematransporte.model.Buseta;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author jssdv
 */

public class VehiculoService {

  private VehiculoDao vehiculoDao;
  private ConductorDao conductorDao;

  public VehiculoService(){
    this.vehiculoDao = new VehiculoDao();
    this.conductorDao = new ConductorDao();
  }  
  
  public void registrar(String placa, String ruta, String tipo) {
    if(placa == null || placa.trim().isEmpty()){
      throw new IllegalArgumentException("La placa no puede estar vacía");
    }
    if(ruta == null || ruta.trim().isEmpty()){
      throw new IllegalArgumentException("La ruta no puede estar vacía");
    }
    if(tipo == null || tipo.trim().isEmpty()){
      throw new IllegalArgumentException("El tipo no puede estar vacío");
    }
    if(!vehiculoDao.verificarPlaca(placa)){
      throw new IllegalArgumentException("Ya existe un vehiculo con la placa: " + placa);
    }

    switch(tipo){
      case "BUS":
        Vehiculo bus = new Bus(placa, ruta);
        vehiculoDao.registrar(bus);
        break;
      case "MICROBUS":
        Vehiculo microbus = new Microbus(placa, ruta);
        vehiculoDao.registrar(microbus);
        break;
      case "BUSETA":
        Vehiculo buseta = new Buseta(placa, ruta);
        vehiculoDao.registrar(buseta);
        break;
      default:
        throw new IllegalArgumentException("Tipo de vehiculo invalido");
    }
  }
}
