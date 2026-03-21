/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematransporte.service;

import sistematransporte.dao.PasajeroDao;
import sistematransporte.model.Pasajero;
import sistematransporte.model.PasajeroAdultoMayor;
import sistematransporte.model.PasajeroEstudiante;
import sistematransporte.model.PasajeroRegular;

import java.util.List;

/**
 *
 * @author Equipo
 */
public class PasajeroService {
     private PasajeroDao pasajeroDao;
 
    public PasajeroService( ) {
        this.pasajeroDao = new PasajeroDao();
    }
 
    // CREATE - registrar pasajero regular
    public boolean registrarRegular(String cedula, String nombre) {
        return registrar(new PasajeroRegular(cedula, nombre));
    }
 
    // CREATE - registrar pasajero estudiante
    public boolean registrarEstudiante(String cedula, String nombre) {
        return registrar(new PasajeroEstudiante(cedula, nombre));
    }
 
    // CREATE - registrar pasajero adulto mayor
    public boolean registrarAdultoMayor(String cedula, String nombre) {
        return registrar(new PasajeroAdultoMayor(cedula, nombre));
    }
 
    // Metodo privado que valida cedula unica antes de guardar
    private boolean registrar(Pasajero pasajero) {
        if (pasajeroDao.buscarPorCedula(pasajero.getCedula()) != null) {
            System.out.println("Ya existe un pasajero con cedula: " + pasajero.getCedula());
            return false;
        }
        pasajeroDao.guardarPasajero(pasajero);
        System.out.println("Pasajero registrado correctamente.");
        return true;
    }
 
    // READ - listar todos
    public List<Pasajero> listarPasajeros() {
        return pasajeroDao.listarTodos();
    }
 
    // READ - buscar por cedula
    public Pasajero buscarPasajero(String cedula) {
        Pasajero p = pasajeroDao.buscarPorCedula(cedula);
        if (p == null) {
            System.out.println("No se encontro pasajero con cedula: " + cedula);
        }
        return p;
    }
 
    // UPDATE - se reemplaza con un nuevo objeto del mismo tipo
    public boolean actualizarPasajero(String cedula, String nuevoNombre, String tipo) {
        Pasajero actualizado;
        switch (tipo.toUpperCase()) {
            case "ESTUDIANTE":   actualizado = new PasajeroEstudiante(cedula, nuevoNombre); break;
            case "ADULTO_MAYOR": actualizado = new PasajeroAdultoMayor(cedula, nuevoNombre); break;
            default:             actualizado = new PasajeroRegular(cedula, nuevoNombre); break;
        }
 
        boolean ok = pasajeroDao.actualizarPasajero(actualizado);
        if (ok) {
            System.out.println("Pasajero actualizado correctamente.");
        } else {
            System.out.println("No se encontro pasajero con cedula: " + cedula);
        }
        return ok;
    }
 
    // DELETE
    public boolean eliminarPasajero(String cedula) {
        boolean ok = pasajeroDao.eliminarPasajero(cedula);
        if (ok) {
            System.out.println("Pasajero eliminado correctamente.");
        } else {
            System.out.println("No se encontro pasajero con cedula: " + cedula);
        }
        return ok;
    }
}

