package sistematransporte.service;

import sistematransporte.dao.PasajeroDao;
import sistematransporte.dao.ReservaDao;
import sistematransporte.dao.TicketDao;
import sistematransporte.dao.VehiculoDAO;
import sistematransporte.model.Pasajero;
import sistematransporte.model.Reserva;
import sistematransporte.model.Ticket;
import sistematransporte.model.Vehiculo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class ReservaService {

    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final List<String> FESTIVOS = Arrays.asList(
        "01-01", "01-06", "03-24", "04-17", "04-18",
        "05-01", "06-02", "06-23", "07-20", "08-07",
        "10-13", "11-03", "12-25"
    );

    private ReservaDao reservaDao;
    private PasajeroDao pasajeroDao;
    private VehiculoDAO vehiculoDao;
    private TicketDao ticketDao;

    public ReservaService() {
        this.reservaDao  = new ReservaDao();
        this.pasajeroDao = new PasajeroDao();
        this.vehiculoDao = new VehiculoDAO();
        this.ticketDao   = new TicketDao();
    }

    private boolean esFestivo(LocalDate fecha) {
        String mesDia = String.format("%02d-%02d", fecha.getMonthValue(), fecha.getDayOfMonth());
        return FESTIVOS.contains(mesDia);
    }

    /**
     * Crea una nueva reserva aplicando todas las reglas de negocio.
     */
    public boolean crearReserva(String cedulaPasajero, String placaVehiculo, String fechaViaje) {

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

        // REGLA 3 - no puede haber dos reservas activas del mismo pasajero,
        // mismo vehiculo y misma fecha de viaje
        List<Reserva> activas = reservaDao.listarActivas();
        for (int i = 0; i < activas.size(); i++) {
            Reserva r = activas.get(i);
            if (r.getPasajero().getCedula().equalsIgnoreCase(cedulaPasajero)
                    && r.getVehiculo().getPlaca().equalsIgnoreCase(placaVehiculo)
                    && r.getFechaViaje().equals(fechaViaje)) {
                System.out.println("El pasajero ya tiene una reserva activa para ese vehiculo en esa fecha.");
                return false;
            }
        }

        // REGLA 1 - tickets vendidos + reservas activas no supera la capacidad
        int reservasActivas = contarReservasActivasPorVehiculo(placaVehiculo);
        int ocupacion = vehiculo.getPasajerosActuales() + reservasActivas;
        if (ocupacion >= vehiculo.getCapacidadMaxima()) {
            System.out.println("El vehiculo " + placaVehiculo + " no tiene cupos disponibles para reservar.");
            return false;
        }

        int nuevoCodigo = reservaDao.generarNuevoCodigo();
        String fechaCreacion = LocalDateTime.now().format(FORMATO_FECHA);
        Reserva reserva = new Reserva(nuevoCodigo, pasajero, vehiculo, fechaCreacion, fechaViaje);
        reservaDao.guardarReserva(reserva);

        System.out.println("Reserva creada exitosamente! Codigo: " + nuevoCodigo);
        return true;
    }

    /**
     * Cancela una reserva por su codigo.
     */
    public boolean cancelarReserva(int codigo) {
        Reserva reserva = reservaDao.buscarPorCodigo(codigo);
        if (reserva == null) {
            System.out.println("No se encontro reserva con codigo: " + codigo);
            return false;
        }
        if (!reserva.getEstado().equals(Reserva.ACTIVA)) {
            System.out.println("La reserva no esta activa, no se puede cancelar.");
            return false;
        }
        reserva.setEstado(Reserva.CANCELADA);
        reservaDao.actualizarEstado(codigo, Reserva.CANCELADA);
        System.out.println("Reserva cancelada correctamente.");
        return true;
    }

    /**
     * Lista todas las reservas con estado ACTIVA.
     */
    public List<Reserva> listarReservasActivas() {
        return reservaDao.listarActivas();
    }

    /**
     * Lista el historial completo de reservas de un pasajero.
     */
    public List<Reserva> historialPasajero(String cedulaPasajero) {
        return reservaDao.listarPorPasajero(cedulaPasajero);
    }

    /**
     * Convierte una reserva activa en ticket aplicando descuentos y recargo festivo.
     */
    public boolean convertirEnTicket(int codigo, String origen, String destino) {
        Reserva reserva = reservaDao.buscarPorCodigo(codigo);
        if (reserva == null) {
            System.out.println("No se encontro reserva con codigo: " + codigo);
            return false;
        }
        if (!reserva.getEstado().equals(Reserva.ACTIVA)) {
            System.out.println("La reserva no esta activa.");
            return false;
        }

        // REGLA 2 - verificar vencimiento (mas de 24 horas)
        if (haVencido(reserva)) {
            reservaDao.actualizarEstado(codigo, Reserva.CANCELADA);
            System.out.println("La reserva ha vencido (mas de 24 horas). Se cancelo automaticamente.");
            return false;
        }

        Pasajero pasajero = reserva.getPasajero();
        Vehiculo vehiculo = reserva.getVehiculo();

        // Verificar limite de 3 tickets por dia
        LocalDate hoy = LocalDate.now();
        String fechaHoy = hoy.toString();
        int ticketsHoy = ticketDao.contarPorPasajeroYFecha(pasajero.getCedula(), fechaHoy);
        if (ticketsHoy >= 3) {
            System.out.println("Venta rechazada: el pasajero ya tiene 3 tickets para hoy.");
            return false;
        }

        // Recargo festivo si aplica
        boolean festivo = esFestivo(hoy);
        if (festivo) System.out.println("Aviso: hoy es festivo, se aplica recargo del 20%.");

        // Crear el ticket
        int nuevoId = ticketDao.generarNuevoId();
        Ticket ticket = new Ticket(nuevoId, pasajero, vehiculo, fechaHoy, origen, destino, festivo);

        // Registrar la venta en el vehiculo
        vehiculo.venderTicket();
        ticketDao.guardarTicket(ticket);

        // Cambiar estado de la reserva a CONVERTIDA
        reservaDao.actualizarEstado(codigo, Reserva.CONVERTIDA);

        System.out.println("Reserva convertida en ticket exitosamente!");
        System.out.println("ID Ticket: " + nuevoId + " | Valor: $" + String.format("%.0f", ticket.getValorFinal()));
        return true;
    }

    /**
     * Verifica todas las reservas activas y cancela las vencidas (mas de 24h).
     * Informa cuantas fueron canceladas.
     */
    public int verificarReservasVencidas() {
        int canceladas = 0;
        List<Reserva> activas = reservaDao.listarActivas();
        for (int i = 0; i < activas.size(); i++) {
            Reserva r = activas.get(i);
            if (haVencido(r)) {
                reservaDao.actualizarEstado(r.getCodigo(), Reserva.CANCELADA);
                canceladas++;
            }
        }
        System.out.println("Verificacion completada. Reservas canceladas por vencimiento: " + canceladas);
        return canceladas;
    }

    // Verifica si una reserva supero las 24 horas desde su creacion
    private boolean haVencido(Reserva r) {
        try {
            LocalDateTime creacion = LocalDateTime.parse(r.getFechaCreacion(), FORMATO_FECHA);
            return LocalDateTime.now().isAfter(creacion.plusHours(24));
        } catch (Exception e) {
            return false;
        }
    }

    // Cuenta cuantas reservas activas tiene un vehiculo
    private int contarReservasActivasPorVehiculo(String placa) {
        int contador = 0;
        List<Reserva> activas = reservaDao.listarActivas();
        for (int i = 0; i < activas.size(); i++) {
            if (activas.get(i).getVehiculo().getPlaca().equalsIgnoreCase(placa)) {
                contador++;
            }
        }
        return contador;
    }
}
