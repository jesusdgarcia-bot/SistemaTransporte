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
  
  public Vehiculo buscar(String placa) {
    Vehiculo vehiculo = vehiculoDao.buscar(placa);

    if(vehiculo == null) {
      throw new IllegalArgumentException("Vehiculo con placa " + placa + " no encontrado");
    }

    return vehiculo;
  }
  
  public List<Vehiculo> listar() {
    List<Vehiculo> lista = vehiculoDao.listar();

    if(lista == null || lista.isEmpty()){
      throw new IllegalArgumentException("No existen vehiculos registrados en la lista");
    }

    return lista;
  }

  public List<Vehiculo> listarDisponibles() {
    List<Vehiculo> listaSinFiltrar = vehiculoDao.listar();
    List<Vehiculo> listaFiltrada = new ArrayList<>();

    if(listaSinFiltrar == null || listaSinFiltrar.isEmpty()){
      throw new IllegalArgumentException("No hay vehiculos disponibles");
    }

    for(Vehiculo v : listaSinFiltrar){
      if(v.getEstado()){
        listaFiltrada.add(v);
      }
    }

    if(listaFiltrada.isEmpty()){
      throw new IllegalArgumentException("No hay vehiculos disponibles");
    }

    return listaFiltrada;
  }

  public List<Vehiculo> listarSinConductor() {
    List<Vehiculo> listaSinFiltrar = vehiculoDao.listar();
    List<Vehiculo> listaFiltrada = new ArrayList<>();

    if(listaSinFiltrar == null || listaSinFiltrar.isEmpty()){
      throw new IllegalArgumentException("No existen vehiculos registrados");
    }

    for(Vehiculo v : listaSinFiltrar){
      if(v.getIdLicenciaConductor() == null){
        listaFiltrada.add(v);
      }
    }

    if(listaFiltrada.isEmpty()){
      throw new IllegalArgumentException("Todos los vehiculos tienen conductor asignado");
    }

    return listaFiltrada;
  }
  
  public void eliminar(String placa) {
    if(vehiculoDao.verificarPlaca(placa)){
      throw new IllegalArgumentException("No existe ningún vehiculo con la placa: " + placa);
    }

    vehiculoDao.eliminar(placa);
  }
  
  public String modificarEstado(String placa) {
    if(vehiculoDao.verificarPlaca(placa)){
      throw new IllegalArgumentException("No existe ningún vehiculo con la placa: " + placa);
    }

    String estAnt = vehiculoDao.verEstado(placa);
    vehiculoDao.modificarEstado(placa);
    String estPost = vehiculoDao.verEstado(placa);

    return estAnt + "-" + estPost;
  }

  public void modificarRuta(String placa, String ruta) {
    if(vehiculoDao.verificarPlaca(placa)){
      throw new IllegalArgumentException("No existe ningún vehiculo con placa: " + placa);
    }

    if(ruta == null || ruta.trim().isEmpty()){
      throw new IllegalArgumentException("La ruta no puede estar vacía");
    }

    vehiculoDao.modificarRuta(placa, ruta);
  }
  
  public void asignarConductor(String placa, String idLicConductor) {
    if(placa == null || placa.trim().isEmpty()){
      throw new IllegalArgumentException("La placa no puede estar vacía");
    }

    if(idLicConductor == null || idLicConductor.trim().isEmpty()){
      throw new IllegalArgumentException("El id de la licencia del conductor no puede estar vacío");
    }

    if(vehiculoDao.verificarPlaca(placa)){
      throw new IllegalArgumentException("No hay ningun vehicuo con la placa: " + placa);
    }

    if(!conductorDao.verificarIdLicConductor(idLicConductor)){
      throw new IllegalArgumentException("No hay ningun conductor con el id de licencia: " + idLicConductor);
    }
    
    Conductor c = conductorDao.buscar(idLicConductor);

    if(!c.getDisponible()){
      throw new IllegalArgumentException("Conductor no disponible");
    }

    vehiculoDao.asignarConductor(placa, idLicConductor);
  }
}
