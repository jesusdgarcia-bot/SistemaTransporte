/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematransporte.dao;

import sistematransporte.model.Ticket;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Equipo
 */
public class TicketDao {
    private static final String ARCHIVO = "tickets.txt";
 
    // Guarda los tickets como lineas de texto plano en memoria
    private List<String[]> ticketsRaw;
 
    public TicketDao() {
        ticketsRaw = new ArrayList<>();
        cargarDesdeArchivo();
    }
 
    private void cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return;
 
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    ticketsRaw.add(linea.split(";"));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer tickets.txt: " + e.getMessage());
        }
    }
 
    private void guardarEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (String[] t : ticketsRaw) {
                bw.write(String.join(";", t));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar tickets.txt: " + e.getMessage());
        }
    }
 
    // CREATE - guarda un ticket ya calculado
    public void guardarTicket(Ticket ticket) {
        String cedulaPasajero = ticket.getPasajero().getCedula();
        String placaVehiculo = ticket.getVehiculo() != null ? ticket.getVehiculo().toString() : "SIN_PLACA";
 
        String[] linea = {
            String.valueOf(ticket.getId()),
            cedulaPasajero,
            placaVehiculo,
            ticket.getFechaCompra(),
            ticket.getOrigen(),
            ticket.getDestino(),
            String.valueOf(ticket.getValorFinal())
        };
        ticketsRaw.add(linea);
        guardarEnArchivo();
    }
 
    // READ - retorna todos los tickets como arreglos de Strings
    // [id, cedulaPasajero, placaVehiculo, fechaCompra, origen, destino, valorFinal]
    public List<String[]> listarTodosRaw() {
        return new ArrayList<>(ticketsRaw);
    }
 
    // READ - buscar por id
    public String[] buscarPorIdRaw(int id) {
        for (String[] t : ticketsRaw) {
            if (Integer.parseInt(t[0]) == id) {
                return t;
            }
        }
        return null;
    }
 
    // UPDATE - reemplaza el ticket con el mismo id
    public boolean actualizarTicket(Ticket actualizado) {
        for (int i = 0; i < ticketsRaw.size(); i++) {
            if (Integer.parseInt(ticketsRaw.get(i)[0]) == actualizado.getId()) {
                String cedulaPasajero = actualizado.getPasajero().getCedula();
                String placaVehiculo = actualizado.getVehiculo() != null
                        ? actualizado.getVehiculo().toString() : "SIN_PLACA";
 
                ticketsRaw.set(i, new String[]{
                    String.valueOf(actualizado.getId()),
                    cedulaPasajero,
                    placaVehiculo,
                    actualizado.getFechaCompra(),
                    actualizado.getOrigen(),
                    actualizado.getDestino(),
                    String.valueOf(actualizado.getValorFinal())
                });
                guardarEnArchivo();
                return true;
            }
        }
        return false;
    }
 
    // DELETE - elimina por id
    public boolean eliminarTicket(int id) {
        boolean eliminado = ticketsRaw.removeIf(t -> Integer.parseInt(t[0]) == id);
        if (eliminado) {
            guardarEnArchivo();
        }
        return eliminado;
    }
 
    // Genera el siguiente id disponible
    public int generarNuevoId() {
        int maxId = 0;
        for (String[] t : ticketsRaw) {
            int idActual = Integer.parseInt(t[0]);
            if (idActual > maxId) maxId = idActual;
        }
        return maxId + 1;
    }
}

