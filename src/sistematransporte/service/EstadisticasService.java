
package sistematransporte.service;

import java.util.HashMap;
import sistematransporte.dao.PasajeroDao;
import sistematransporte.dao.TicketDao;
import sistematransporte.model.interfaces.Calculable;
import sistematransporte.model.Pasajero;
import sistematransporte.model.PasajeroAdultoMayor;
import sistematransporte.model.PasajeroEstudiante;
import sistematransporte.model.PasajeroRegular;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Equipo
 */
public class EstadisticasService {
    
private TicketDao ticketDao;
private PasajeroDao pasajeroDao;
 
    public EstadisticasService() {
        this.ticketDao = new TicketDao();
        this.pasajeroDao = new PasajeroDao();
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

    public Map<String, Integer> contarPasajerosPorTipo() {
        Map<String, Integer> conteo = new HashMap<>();
        conteo.put("REGULAR", 0);
        conteo.put("ESTUDIANTE", 0);
        conteo.put("ADULTO_MAYOR", 0);
 
        for (Pasajero p : pasajeroDao.listarTodos()) {
            if (p instanceof PasajeroEstudiante) {
                conteo.put("ESTUDIANTE", conteo.get("ESTUDIANTE") + 1);
            } else if (p instanceof PasajeroAdultoMayor) {
                conteo.put("ADULTO_MAYOR", conteo.get("ADULTO_MAYOR") + 1);
            } else {
                conteo.put("REGULAR", conteo.get("REGULAR") + 1);
            }
        }
        return conteo;
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
 
        double totalRecaudado = calcularTotal();
        System.out.printf("Total recaudado:        $%.0f%n", totalRecaudado);
 
        int totalTickets = ticketDao.listarTodosRaw().size();
        System.out.println("Total tickets vendidos: " + totalTickets);
 
        System.out.println("\nPasajeros por tipo:");
        Map<String, Integer> porTipo = contarPasajerosPorTipo();
        System.out.println("  Regular:      " + porTipo.get("REGULAR"));
        System.out.println("  Estudiante:   " + porTipo.get("ESTUDIANTE"));
        System.out.println("  Adulto Mayor: " + porTipo.get("ADULTO_MAYOR"));
 
        System.out.println("\nVehiculo con mas ventas: " + vehiculoConMasTickets());
        System.out.println("========================================");
  }
}
