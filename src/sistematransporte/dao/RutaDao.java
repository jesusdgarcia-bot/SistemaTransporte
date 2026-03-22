package sistematransporte.dao;

import sistematransporte.model.Ruta;
import sistematransporte.model.Vehiculo;
import sistematransporte.dao.VehiculoDAO;
import sistematransporte.util.RutaArchivos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RutaDao {

    private List<Ruta> rutas;
    private VehiculoDAO vehiculoDao;

    public RutaDao() {
        this.rutas = cargar();
        this.vehiculoDao = new VehiculoDAO();
    }

    public void registrar(Ruta ruta) {
        rutas.add(ruta);
        guardar();
    }

    public Ruta buscar(String codigoRuta) {

        for (Ruta r : rutas) {
            if (r.getCodigoRuta().equalsIgnoreCase(codigoRuta)) {
                return r;
            }
        }

        return null;
    }

    public List<Ruta> listar() {
        return rutas;
    }

    public void eliminar(String codigoRuta) {
        rutas.removeIf(r -> r.getCodigoRuta().equalsIgnoreCase(codigoRuta));
        guardar();
        
        List<Vehiculo> vehiculos = vehiculoDao.listar();

        for (Vehiculo v : vehiculos) {
            if (v.getCodRuta() != null && v.getCodRuta().equalsIgnoreCase(codigoRuta)) {
                vehiculoDao.modificarRuta(v.getPlaca(), null);
            }
        }
    }

    public void modificar(String codigoRuta, Ruta nuevaRuta) {

        for (int i = 0; i < rutas.size(); i++) {

            if (rutas.get(i).getCodigoRuta().equalsIgnoreCase(codigoRuta)) {
                rutas.set(i, nuevaRuta);
                break;
            }
        }

        guardar();
    }

    public boolean verificarRuta(String codigoRuta) {
        return buscar(codigoRuta) == null;
    }

    private List<Ruta> cargar() {

        List<Ruta> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(RutaArchivos.RUTA))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.trim().isEmpty()) continue;

                Ruta ruta = new Ruta();
                ruta.fromString(linea);

                lista.add(ruta);
            }

        } catch (IOException e) {
            System.out.println("Error al cargar rutas: " + e.getMessage());
        }

        return lista;
    }

    private void guardar() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RutaArchivos.RUTA, false))) {

            for (Ruta r : rutas) {
                bw.write(r.toString());
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error al guardar rutas: " + e.getMessage());
        }
    }
}