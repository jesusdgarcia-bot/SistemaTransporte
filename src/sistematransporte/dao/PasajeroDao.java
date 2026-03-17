/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematransporte.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import sistematransporte.model.Pasajero;
import sistematransporte.model.PasajeroRegular;
import sistematransporte.model.PasajeroEstudiante;
import sistematransporte.model.PasajeroAdultoMayor;

/**
 *
 * @author Equipo
 */


public class PasajeroDao {
    private static final String ARCHIVO = "pasajeros.txt";
    private List<Pasajero> pasajeros;
 
    public PasajeroDao() {
        pasajeros = new ArrayList<>();
        cargarDesdeArchivo();
    }
 
    private void cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return;
        }
 
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    Pasajero p = parsearLinea(linea);
                    if (p != null) {
                        pasajeros.add(p);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer pasajeros.txt: " + e.getMessage());
        }
    }
 
    // Formato: cedula;nombre;tipo
    // tipo puede ser: REGULAR | ESTUDIANTE | ADULTO_MAYOR
    private Pasajero parsearLinea(String linea) {
        String[] partes = linea.split(";");
        if (partes.length < 3) return null;
 
        String cedula = partes[0];
        String nombre = partes[1];
        String tipo   = partes[2];
 
        switch (tipo.toUpperCase()) {
            case "ESTUDIANTE":   return new PasajeroEstudiante(cedula, nombre);
            case "ADULTO_MAYOR": return new PasajeroAdultoMayor(cedula, nombre);
            default:             return new PasajeroRegular(cedula, nombre);
        }
    }
 
    // Determina el tipo como String para guardarlo en el archivo
    private String obtenerTipo(Pasajero p) {
        if (p instanceof PasajeroEstudiante)  return "ESTUDIANTE";
        if (p instanceof PasajeroAdultoMayor) return "ADULTO_MAYOR";
        return "REGULAR";
    }
 
    private void guardarEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Pasajero p : pasajeros) {
                bw.write(p.getCedula() + ";" + p.getNombre() + ";" + obtenerTipo(p));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar pasajeros.txt: " + e.getMessage());
        }
    }
 
    // CREATE
    public void guardarPasajero(Pasajero pasajero) {
        pasajeros.add(pasajero);
        guardarEnArchivo();
    }
 
    // READ - listar todos
    public List<Pasajero> listarTodos() {
        return new ArrayList<>(pasajeros);
    }
 
    // READ - buscar por cedula
    public Pasajero buscarPorCedula(String cedula) {
        for (Pasajero p : pasajeros) {
            if (p.getCedula().equalsIgnoreCase(cedula)) {
                return p;
            }
        }
        return null;
    }
 
    // UPDATE
    public boolean actualizarPasajero(Pasajero actualizado) {
        for (int i = 0; i < pasajeros.size(); i++) {
            if (pasajeros.get(i).getCedula().equalsIgnoreCase(actualizado.getCedula())) {
                pasajeros.set(i, actualizado);
                guardarEnArchivo();
                return true;
            }
        }
        return false;
    }
 
    // DELETE
    public boolean eliminarPasajero(String cedula) {
        boolean eliminado = pasajeros.removeIf(p -> p.getCedula().equalsIgnoreCase(cedula));
        if (eliminado) {
            guardarEnArchivo();
        }
        return eliminado;
    }
}

