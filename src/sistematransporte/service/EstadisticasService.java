
package sistematransporte.service;

import sistematransporte.dao.PasajeroDao;
import sistematransporte.dao.TicketDao;
import sistematransporte.model.interfaces.Calculable;
import sistematransporte.model.Pasajero;
import sistematransporte.model.PasajeroAdultoMayor;
import sistematransporte.model.PasajeroEstudiante;
import sistematransporte.model.PasajeroRegular;

import java.util.List;

/**
 *
 * @author Equipo
 */
public class EstadisticasService {
    
private TicketDao ticketDao;
private PasajeroDao pasajeroDao;
 
    public EstadisticasService(TicketDao ticketDao, PasajeroDao pasajeroDao) {
        this.ticketDao = ticketDao;
        this.pasajeroDao = pasajeroDao;
    }
 
    // Suma el valorFinal de todos los tickets
    public double calcularTotal() {
        double total = 0;
        for (String[] t : ticketDao.listarTodosRaw()) {
            try {
                total += Double.parseDouble(t[6]);
            } catch (NumberFormatException e) {
            }
        }
        return total;
    }

    // Retorna el total de tickets vendidos
    public int contarTotalTickets() {
        return ticketDao.listarTodosRaw().size();
    }

    // Cuenta pasajeros de un tipo especifico: REGULAR, ESTUDIANTE, ADULTO_MAYOR
    public int contarPorTipo(String tipo) {
        int contador = 0;
        for (Pasajero p : pasajeroDao.listarTodos()) {
            switch (tipo.toUpperCase()) {
                case "ESTUDIANTE":
                    if (p instanceof PasajeroEstudiante) contador++;
                    break;
                case "ADULTO_MAYOR":
                    if (p instanceof PasajeroAdultoMayor) contador++;
                    break;
                case "REGULAR":
                    if (p instanceof PasajeroRegular) contador++;
                    break;
            }
        }
        return contador;
    }
 
    // Retorna la placa del vehiculo con mas tickets vendidos
    public String vehiculoConMasTickets() {
        List<String[]> lista = ticketDao.listarTodosRaw();

        if (lista.isEmpty()) return "Sin datos";

        String mejorPlaca = "Sin datos";
        int maxConteo = 0;

        for (String[] ticket : lista) {
            String placaActual = ticket[2];
            int conteo = 0;
            for (String[] t : lista) {
                if (t[2].equalsIgnoreCase(placaActual)) conteo++;
            }
            if (conteo > maxConteo) {
                maxConteo = conteo;
                mejorPlaca = placaActual;
            }
        }
        return mejorPlaca;
    }
 
    public void mostrarResumenCompleto() {
        System.out.println("========================================");
        System.out.println("       RESUMEN ESTADISTICAS TRANSCESAR  ");
        System.out.println("========================================");
        System.out.printf("Total recaudado:        $%.0f%n", calcularTotal());
        System.out.println("Total tickets vendidos: " + contarTotalTickets());
        System.out.println("\nPasajeros por tipo:");
        System.out.println("  Regular:      " + contarPorTipo("REGULAR"));
        System.out.println("  Estudiante:   " + contarPorTipo("ESTUDIANTE"));
        System.out.println("  Adulto Mayor: " + contarPorTipo("ADULTO_MAYOR"));
        System.out.println("\nVehiculo con mas ventas: " + vehiculoConMasTickets());
        System.out.println("========================================");
    }
}
