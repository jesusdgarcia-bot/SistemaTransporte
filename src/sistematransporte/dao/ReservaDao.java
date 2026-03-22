package sistematransporte.dao;

import sistematransporte.model.Pasajero;
import sistematransporte.model.PasajeroEstudiante;
import sistematransporte.model.PasajeroRegular;
import sistematransporte.model.Reserva;
import sistematransporte.model.Vehiculo;
import sistematransporte.util.RutaArchivos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Maneja la persistencia de reservas en reservas.txt
 * Formato: codigo;cedulaPasajero;nombrePasajero;tipoPasajero;placaVehiculo;fechaCreacion;fechaViaje;estado
 */
public class ReservaDao {

    private List<Reserva> reservas;
    private PasajeroDao pasajeroDao;
    private VehiculoDAO vehiculoDao;

    public ReservaDao() {
        reservas    = new ArrayList<>();
        pasajeroDao = new PasajeroDao();
        vehiculoDao = new VehiculoDAO();
        cargarDesdeArchivo();
    }

    private void cargarDesdeArchivo() {
        File archivo = new File(RutaArchivos.RESERVAS);
        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    Reserva r = parsearLinea(linea);
                    if (r != null) reservas.add(r);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer reservas.txt: " + e.getMessage());
        }
    }

    // Formato: codigo;cedula;nombre;tipo;placa;fechaCreacion;fechaViaje;estado
    private Reserva parsearLinea(String linea) {
        String[] p = linea.split(";");
        if (p.length < 8) return null;

        int codigo = Integer.parseInt(p[0]);

        Pasajero pasajero = pasajeroDao.buscarPorCedula(p[1]);
        if (pasajero == null) return null;

        Vehiculo vehiculo = vehiculoDao.buscar(p[4]);
        if (vehiculo == null) return null;

        Reserva r = new Reserva(codigo, pasajero, vehiculo, p[5], p[6]);
        r.setEstado(p[7]);
        return r;
    }

    private void guardarEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RutaArchivos.RESERVAS))) {
            for (Reserva r : reservas) {
                bw.write(
                    r.getCodigo() + ";" +
                    r.getPasajero().getCedula() + ";" +
                    r.getPasajero().getNombre() + ";" +
                    r.getPasajero().getClass().getSimpleName() + ";" +
                    r.getVehiculo().getPlaca() + ";" +
                    r.getFechaCreacion() + ";" +
                    r.getFechaViaje() + ";" +
                    r.getEstado()
                );
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar reservas.txt: " + e.getMessage());
        }
    }

    // CREATE
    public void guardarReserva(Reserva reserva) {
        reservas.add(reserva);
        guardarEnArchivo();
    }

    // READ - listar todas
    public List<Reserva> listarTodas() {
        return new ArrayList<>(reservas);
    }

    // READ - listar solo las activas
    public List<Reserva> listarActivas() {
        List<Reserva> activas = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getEstado().equals(Reserva.ACTIVA)) activas.add(r);
        }
        return activas;
    }

    // READ - buscar por codigo
    public Reserva buscarPorCodigo(int codigo) {
        for (Reserva r : reservas) {
            if (r.getCodigo() == codigo) return r;
        }
        return null;
    }

    // READ - historial de reservas de un pasajero
    public List<Reserva> listarPorPasajero(String cedula) {
        List<Reserva> resultado = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getPasajero().getCedula().equalsIgnoreCase(cedula)) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    // UPDATE - actualizar objeto reserva completo
    public boolean actualizarReserva(Reserva actualizada) {
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getCodigo() == actualizada.getCodigo()) {
                reservas.set(i, actualizada);
                guardarEnArchivo();
                return true;
            }
        }
        return false;
    }

    // UPDATE - actualizar solo el estado de una reserva por codigo
    public boolean actualizarEstado(int codigo, String nuevoEstado) {
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getCodigo() == codigo) {
                reservas.get(i).setEstado(nuevoEstado);
                guardarEnArchivo();
                return true;
            }
        }
        return false;
    }

    // Genera el siguiente codigo disponible
    public int generarNuevoCodigo() {
        int maxCodigo = 0;
        for (Reserva r : reservas) {
            if (r.getCodigo() > maxCodigo) maxCodigo = r.getCodigo();
        }
        return maxCodigo + 1;
    }
}