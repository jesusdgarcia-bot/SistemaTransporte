package sistematransporte.service;
 
import sistematransporte.dao.PasajeroDao;
import sistematransporte.model.Pasajero;
import sistematransporte.model.PasajeroAdultoMayor;
import sistematransporte.model.PasajeroEstudiante;
import sistematransporte.model.PasajeroRegular;
 
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
 
/**
 *
 * @author Equipo
 */
public class PasajeroService {
    private PasajeroDao pasajeroDao;
 
    public PasajeroService() {
        this.pasajeroDao = new PasajeroDao();
    }
 
    // CREATE - registrar pasajero regular
    // Si la fecha de nacimiento corresponde a 60 años o más, se registra como Adulto Mayor
    public boolean registrarRegular(String cedula, String nombre, String fechaNacimiento) {
        Pasajero p;
        if (esAdultoMayor(fechaNacimiento)) {
            p = new PasajeroAdultoMayor(cedula, nombre);
        } else {
            p = new PasajeroRegular(cedula, nombre);
        }
        p.setFechaNacimiento(fechaNacimiento);
        return registrar(p);
    }
 
    // CREATE - registrar pasajero estudiante
    // Si la fecha de nacimiento corresponde a 60 años o más, se registra como Adulto Mayor
    public boolean registrarEstudiante(String cedula, String nombre, String fechaNacimiento) {
        Pasajero p;
        if (esAdultoMayor(fechaNacimiento)) {
            p = new PasajeroAdultoMayor(cedula, nombre);
        } else {
            p = new PasajeroEstudiante(cedula, nombre);
        }
        p.setFechaNacimiento(fechaNacimiento);
        return registrar(p);
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
 
    // Retorna true si la fecha de nacimiento corresponde a una persona de 60 años o más
    private boolean esAdultoMayor(String fechaNacimiento) {
        LocalDate fechaNac = LocalDate.parse(fechaNacimiento,
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return Period.between(fechaNac, LocalDate.now()).getYears() >= 60;
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