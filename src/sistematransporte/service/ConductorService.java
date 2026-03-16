/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematransporte.service;

import sistematransporte.dao.ConductorDao;
import sistematransporte.model.Conductor;

import java.util.List;


/**
 *
 * @author Equipo
 */
public class ConductorService {
    
    private ConductorDao conductorDao;
 
    public ConductorService(ConductorDao conductorDao) {
        this.conductorDao = conductorDao;
    }
 
    // CREATE - registrar un nuevo conductor
    public boolean registrarConductor(String cedula, String nombre,
                                      String numeroLicencia, String categoria) {
        // Validar que no exista ya ese conductor
        if (conductorDao.buscarPorCedula(cedula) != null) {
            System.out.println("Ya existe un conductor con cedula: " + cedula);
            return false;
        }
 
        Conductor nuevo = new Conductor(cedula, nombre, numeroLicencia, categoria);
        conductorDao.guardarConductor(nuevo);
        System.out.println("Conductor registrado correctamente.");
        return true;
    }
 
    // READ - listar todos los conductores
    public List<Conductor> listarConductores() {
        return conductorDao.listarTodos();
    }
 
    // READ - buscar un conductor por cedula
    public Conductor buscarConductor(String cedula) {
        Conductor c = conductorDao.buscarPorCedula(cedula);
        if (c == null) {
            System.out.println("No se encontro conductor con cedula: " + cedula);
        }
        return c;
    }
 
    // UPDATE - actualizar datos del conductor
    public boolean actualizarConductor(String cedula, String nuevoNombre,
                                       String nuevaLicencia, String nuevaCategoria) {
        Conductor existente = conductorDao.buscarPorCedula(cedula);
        if (existente == null) {
            System.out.println("No se encontro conductor con cedula: " + cedula);
            return false;
        }
 
        existente.setNombre(nuevoNombre);
        existente.setNumeroLicencia(nuevaLicencia);
        existente.setCategoriaLicencia(nuevaCategoria);
 
        boolean ok = conductorDao.actualizarConductor(existente);
        if (ok) System.out.println("Conductor actualizado correctamente.");
        return ok;
    }
 
    // DELETE - eliminar conductor por cedula
    public boolean eliminarConductor(String cedula) {
        boolean ok = conductorDao.eliminarConductor(cedula);
        if (ok) {
            System.out.println("Conductor eliminado correctamente.");
        } else {
            System.out.println("No se encontro conductor con cedula: " + cedula);
        }
        return ok;
    }
}
