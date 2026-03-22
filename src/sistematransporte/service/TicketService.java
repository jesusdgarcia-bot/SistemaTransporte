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

    public TicketService() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 

    public boolean venderTicket(String cedulaPasajero, String placaVehiculo,
                                String origen, String destino) {
 
        // Buscar pasajero
        Pasajero pasajero = pasajeroDao.buscarPorCedula(cedulaPasajero);
        if (pasajero == null) {
            System.out.println("Pasajero no encontrado: " + cedulaPasajero);
            return false;
        }
 
        
        Vehiculo vehiculo = vehiculoDao.buscar(placaVehiculo);
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
        
        vehiculo.venderTicket();
        vehiculoDao.actualizarVehiculo(vehiculo);
        ticketDao.guardarTicket(ticket);
        System.out.println("Ticket vendido! ID: " + nuevoId + " | Valor: $" + ticket.getValorFinal());
        return true;
        
}
    
    // read - listar todos los tickets
    public List<String[]> listarTickets() {
        return ticketDao.listarTodosRaw();
    }
 
    // read - buscar ticket por id
    public String[] buscarTicket(int id) {
        String[] t = ticketDao.buscarPorIdRaw(id);
        if (t == null) {
            System.out.println("No se encontro ticket con id: " + id);
        }
        return t;
    }
 
    // update - actualizar datos del ticket (origen, destino, fecha)
    public boolean actualizarTicket(int id, String nuevoOrigen, String nuevoDestino) {
        String[] raw = ticketDao.buscarPorIdRaw(id);
        if (raw == null) {
            System.out.println("No se encontro ticket con id: " + id);
            return false;
        }
 
        Pasajero pasajero = pasajeroDao.buscarPorCedula(raw[1]);
        if (pasajero == null) return false;
 
        Ticket actualizado = new Ticket(id, pasajero, null, raw[3], nuevoOrigen, nuevoDestino);
        actualizado.setValorFinal(Double.parseDouble(raw[6]));
 
        boolean ok = ticketDao.actualizarTicket(actualizado);
        if (ok) System.out.println("Ticket actualizado correctamente.");
        return ok;
    }
 
    // delete - cancelar ticket (y decrementar pasajerosActuales en el vehiculo)
    public boolean cancelarTicket(int id) {
        String[] raw = ticketDao.buscarPorIdRaw(id);
        if (raw == null) {
            System.out.println("No se encontro ticket con id: " + id);
            return false;
        }
 
        
        
        Vehiculo vehiculo = vehiculoDao.buscar(raw[2]);
        if (vehiculo != null) {
            int actuales = vehiculo.getPasajerosActuales();
            if (actuales > 0) vehiculo.setPasajerosActuales(actuales - 1);
            vehiculoDao.actualizarVehiculo(vehiculo);
        }
        
 
        boolean ok = ticketDao.eliminarTicket(id);
        if (ok) System.out.println("Ticket cancelado correctamente.");
        return ok;
    }
}