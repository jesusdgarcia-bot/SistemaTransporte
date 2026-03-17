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
import sistematransporte.model.Conductor;
/**
 *
 * @author almen
 */
public class ConductorDao {
    
    public Conductor buscar(String idLicConductor){
        return new Conductor();
    }
    
    public boolean verificarIdLicConductor(String idLicConductor){
        return buscar(idLicConductor) == null;
    }
    
       private static final String ARCHIVO = "conductores.txt";
    private List<Conductor> conductores;
 
    public ConductorDao() {
        conductores = new ArrayList<>();
        cargarDesdeArchivo();
    }
 
    // Carga los conductores del archivo al iniciar
    private void cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return; // Si no existe el archivo, lista vacia
        }
 
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    Conductor c = parsearLinea(linea);
                    if (c != null) {
                        conductores.add(c);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer conductores.txt: " + e.getMessage());
        }
    }
 
    // Convierte una linea del archivo en un objeto Conductor
    // Formato: cedula;nombre;numeroLicencia;categoriaLicencia
    private Conductor parsearLinea(String linea) {
        String[] partes = linea.split(";");
        if (partes.length < 4) return null;
        return new Conductor(partes[0], partes[1], partes[2], partes[3]);
    }
 
    // Guarda toda la lista en el archivo (reescribe completo)
    private void guardarEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Conductor c : conductores) {
                bw.write(c.getCedula() + ";" + c.getNombre() + ";"
                        + c.getNumeroLicencia() + ";" + c.getCategoriaLicencia());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar conductores.txt: " + e.getMessage());
        }
    }
 
    // CREATE
    public void guardarConductor(Conductor conductor) {
        conductores.add(conductor);
        guardarEnArchivo();
    }
 
    // READ - listar todos
    public List<Conductor> listarTodos() {
        return new ArrayList<>(conductores);
    }
 
    // READ - buscar por cedula
    public Conductor buscarPorCedula(String cedula) {
        for (Conductor c : conductores) {
            if (c.getCedula().equalsIgnoreCase(cedula)) {
                return c;
            }
        }
        return null;
    }
 
    // UPDATE
    public boolean actualizarConductor(Conductor actualizado) {
        for (int i = 0; i < conductores.size(); i++) {
            if (conductores.get(i).getCedula().equalsIgnoreCase(actualizado.getCedula())) {
                conductores.set(i, actualizado);
                guardarEnArchivo();
                return true;
            }
        }
        return false;
    }
 
    // DELETE
    public boolean eliminarConductor(String cedula) {
        boolean eliminado = conductores.removeIf(c -> c.getCedula().equalsIgnoreCase(cedula));
        if (eliminado) {
            guardarEnArchivo();
        }
        return eliminado;
    }
}
