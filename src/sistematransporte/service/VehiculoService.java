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
}
