/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistematransporte.service;

import sistematransporte.dao.TicketDao;
import sistematransporte.dao.PasajeroDao;
import sistematransporte.dao.VehiculoDao;
import sistematransporte.model.Pasajero;
import sistematransporte.model.Ticket;
import sistematransporte.model.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Equipo
 */
public class TicketService {
    private TicketDao ticketDao;
    private PasajeroDao pasajeroDao;
    private VehiculoDao vehiculoDao;
 
    public TicketService(TicketDao ticketDao, PasajeroDao pasajeroDao, VehiculoDao vehiculoDao) {
        this.ticketDao = ticketDao;
        this.pasajeroDao = pasajeroDao;
        this.vehiculoDao = vehiculoDao;
        
    }
 

    public boolean venderTicket(String cedulaPasajero, String placaVehiculo,
                                String origen, String destino) {
 
        // Buscar pasajero
        Pasajero pasajero = pasajeroDao.buscarPorCedula(cedulaPasajero);
        if (pasajero == null) {
            System.out.println("Pasajero no encontrado: " + cedulaPasajero);
            return false;
        }
 
        
        Vehiculo vehiculo = vehiculoDao.buscarPorPlaca(placaVehiculo);
        if (vehiculo == null) {
            System.out.println("Vehiculo no encontrado: " + placaVehiculo);
            return false;
        }
        if (!vehiculo.tieneCupos()) {
            System.out.println("El vehiculo " + placaVehiculo + " no tiene cupos disponibles.");
            return false;
        }
        
        int nuevoId = ticketDao.generarNuevoId();
        String fecha = LocalDate.now().toString();
        Ticket ticket = new Ticket(nuevoId, pasajero, vehiculo, fecha, origen, destino);
        
        vehiculo.registrarVenta();
        vehiculoDao.actualizarVehiculo(vehiculo);
        ticketDao.guardarTicket(ticket);
        System.out.println("Ticket vendido! ID: " + nuevoId + " | Valor: $" + ticket.getValorFinal());
        return true;
        
}
}
