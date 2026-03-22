package sistematransporte.service;

import sistematransporte.dao.RutaDao;
import sistematransporte.model.Ruta;

import java.util.List;

public class RutaService {

    private RutaDao rutaDao;

    public RutaService() {
        this.rutaDao = new RutaDao();
    }

    public void registrar(String codigoRuta, String origen, String destino, double distanciaKm, int tiempoMinutos) {

        if (codigoRuta == null || codigoRuta.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de la ruta no puede estar vacío");
        }

        if (origen == null || origen.trim().isEmpty()) {
            throw new IllegalArgumentException("La ciudad de origen no puede estar vacía");
        }

        if (destino == null || destino.trim().isEmpty()) {
            throw new IllegalArgumentException("La ciudad de destino no puede estar vacía");
        }

        if (distanciaKm <= 0) {
            throw new IllegalArgumentException("La distancia debe ser mayor a 0");
        }

        if (tiempoMinutos <= 0) {
            throw new IllegalArgumentException("El tiempo debe ser mayor a 0");
        }

        if (!rutaDao.verificarRuta(codigoRuta)) {
            throw new IllegalArgumentException("Ya existe una ruta con el código: " + codigoRuta);
        }

        Ruta ruta = new Ruta(codigoRuta, origen, destino, distanciaKm, tiempoMinutos);
        rutaDao.registrar(ruta);
    }

    public Ruta buscar(String codigoRuta) {
        if (codigoRuta == null || codigoRuta.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de la ruta no puede estar vacío");
        }

        Ruta ruta = rutaDao.buscar(codigoRuta);

        if (ruta == null) {
            throw new IllegalArgumentException("Ruta con código " + codigoRuta + " no encontrada");
        }

        return ruta;
    }

    public List<Ruta> listar() {

        List<Ruta> lista = rutaDao.listar();

        if (lista == null || lista.isEmpty()) {
            throw new IllegalArgumentException("No existen rutas registradas");
        }

        return lista;
    }

    public void eliminar(String codigoRuta) {

        if (rutaDao.verificarRuta(codigoRuta)) {
            throw new IllegalArgumentException("No existe ninguna ruta con código: " + codigoRuta);
        }

        rutaDao.eliminar(codigoRuta);
    }

    public void modificar(String codigoRuta, String origen, String destino, double distanciaKm, int tiempoMinutos) {
        if (rutaDao.verificarRuta(codigoRuta)) {
            throw new IllegalArgumentException("No existe ninguna ruta con código: " + codigoRuta);
        }

        if (origen == null || origen.trim().isEmpty()) {
            throw new IllegalArgumentException("La ciudad de origen no puede estar vacía");
        }

        if (destino == null || destino.trim().isEmpty()) {
            throw new IllegalArgumentException("La ciudad de destino no puede estar vacía");
        }

        if (distanciaKm <= 0) {
            throw new IllegalArgumentException("La distancia debe ser mayor a 0");
        }

        if (tiempoMinutos <= 0) {
            throw new IllegalArgumentException("El tiempo debe ser mayor a 0");
        }

        Ruta nuevaRuta = new Ruta(codigoRuta, origen, destino, distanciaKm, tiempoMinutos);
        rutaDao.modificar(codigoRuta, nuevaRuta);
    }
}