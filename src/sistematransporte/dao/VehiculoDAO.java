package sistematransporte.dao;

import sistematransporte.model.Vehiculo;
import sistematransporte.model.Bus;
import sistematransporte.model.Microbus;
import sistematransporte.model.Buseta;
import sistematransporte.model.Conductor;
import sistematransporte.dao.ConductorDao;
import sistematransporte.util.RutaArchivos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jssdv
 */

public class VehiculoDao {

    private ConductorDao conductorDao;

    public VehiculoDao(){
        this.conductorDao = new ConductorDao();
    }   
}
