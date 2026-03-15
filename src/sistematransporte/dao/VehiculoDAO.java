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
    
    private String obtenerArchivo(String tipo){
        switch(tipo){
            case "BUS":
                return RutaArchivos.BUS;
            case "MICROBUS":
                return RutaArchivos.MICROBUS;
            case "BUSETA":
                return RutaArchivos.BUSETA;
            default:
                throw new IllegalArgumentException("Tipo de vehículo inválido");
        }
    }
    
    public void registrar(Vehiculo vehiculo) {

        String archivo = obtenerArchivo(vehiculo.getTipo());

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(archivo, true))) {

            bw.write(vehiculo.toString());
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error al guardar vehículo: " + e.getMessage());
        }
    }
}
