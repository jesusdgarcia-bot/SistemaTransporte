package sistematransporte.dao;

import sistematransporte.model.Vehiculo;
import sistematransporte.model.Bus;
import sistematransporte.model.Microbus;
import sistematransporte.model.Buseta;
import sistematransporte.util.RutaArchivos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jssdv
 */

public class VehiculoDao {
    public VehiculoDao(){    }   
    
    private String obtenerArchivo(String tipo){
        switch(tipo){
            case "BUS":
                return RutaArchivos.BUS;
            case "MICROBUS":
                return RutaArchivos.MICROBUS;
            case "BUSETA":
                return RutaArchivos.BUSETA;
            default:
                throw new IllegalArgumentException("Tipo de vehículo inválido");
        }
    }
    
    public void registrar(Vehiculo vehiculo) {

        String archivo = obtenerArchivo(vehiculo.getTipo());

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(archivo, true))) {

            bw.write(vehiculo.toString());
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error al guardar vehículo: " + e.getMessage());
        }
    }
    
    public Vehiculo buscar(String placa) {

        String[] archivos = {
            RutaArchivos.BUS,
            RutaArchivos.MICROBUS,
            RutaArchivos.BUSETA
        };

        for(String archivo : archivos){

            try (BufferedReader br = new BufferedReader(
                    new FileReader(archivo))) {

                String linea;

                while ((linea = br.readLine()) != null) {

                    if (linea.trim().isEmpty()) continue;

                    String[] datos = linea.split(";");

                    if (datos[0].equalsIgnoreCase(placa)) {

                        Vehiculo v = crearVehiculo(datos);
                        v.fromString(linea);
                        return v;
                    }
                }

            } catch (IOException e) {
                System.out.println("Error al buscar vehículo: " + e.getMessage());
            }
        }

        return null;
    }
    
    public List<Vehiculo> listar() {

        List<Vehiculo> lista = new ArrayList<>();

        String[] archivos = {
            RutaArchivos.BUS,
            RutaArchivos.MICROBUS,
            RutaArchivos.BUSETA
        };

        for(String archivo : archivos){

            try (BufferedReader br = new BufferedReader(
                    new FileReader(archivo))) {

                String linea;

                while ((linea = br.readLine()) != null) {

                    if (linea.trim().isEmpty()) continue;

                    String[] datos = linea.split(";");

                    Vehiculo v = crearVehiculo(datos);
                    v.fromString(linea);

                    lista.add(v);
                }

            } catch (IOException e) {
                System.out.println("Error al listar vehículos: " + e.getMessage());
            }
        }

        return lista;
    }
    
    public void eliminar(String placa) {

        String[] archivos = {
            RutaArchivos.BUS,
            RutaArchivos.MICROBUS,
            RutaArchivos.BUSETA
        };

        for(String archivo : archivos){

            List<Vehiculo> lista = listarArchivo(archivo);

            try (BufferedWriter bw = new BufferedWriter(
                    new FileWriter(archivo, false))) {

                for (Vehiculo v : lista) {

                    if (!v.getPlaca().equalsIgnoreCase(placa)) {
                        bw.write(v.toString());
                        bw.newLine();
                    }
                }

            } catch (IOException e) {
                System.out.println("Error al eliminar vehículo: " + e.getMessage());
            }
        }
    }
    
    public void modificarRuta(String placa, String ruta) {

        String[] archivos = {
            RutaArchivos.BUS,
            RutaArchivos.MICROBUS,
            RutaArchivos.BUSETA
        };

        for(String archivo : archivos){

            List<Vehiculo> lista = listarArchivo(archivo);

            try (BufferedWriter bw = new BufferedWriter(
                    new FileWriter(archivo, false))) {

                for (Vehiculo v : lista) {

                    if (v.getPlaca().equalsIgnoreCase(placa)) {
                        v.setRuta(ruta);
                    }

                    bw.write(v.toString());
                    bw.newLine();
                }

            } catch (IOException e) {
                System.out.println("Error al modificar ruta: " + e.getMessage());
            }
        }
    }
    
    public void modificarEstado(String placa) {

        String[] archivos = {
            RutaArchivos.BUS,
            RutaArchivos.MICROBUS,
            RutaArchivos.BUSETA
        };

        for(String archivo : archivos){

            List<Vehiculo> lista = listarArchivo(archivo);

            try (BufferedWriter bw = new BufferedWriter(
                    new FileWriter(archivo, false))) {

                for (Vehiculo v : lista) {

                    if (v.getPlaca().equalsIgnoreCase(placa)) {
                        v.setEstado(!v.getEstado());
                    }

                    bw.write(v.toString());
                    bw.newLine();
                }

            } catch (IOException e) {
                System.out.println("Error al modificar estado: " + e.getMessage());
            }
        }
    }
    
    public void asignarConductor(String placa, String idLicenciaConductor) {
        String[] archivos = {
            RutaArchivos.BUS,
            RutaArchivos.MICROBUS,
            RutaArchivos.BUSETA
        };

        for(String archivo : archivos){

            List<Vehiculo> lista = listarArchivo(archivo);

            try (BufferedWriter bw = new BufferedWriter(
                    new FileWriter(archivo, false))) {

                for (Vehiculo v : lista) {

                    if (v.getPlaca().equalsIgnoreCase(placa)) {
                        v.setIdLicenciaConductor(idLicenciaConductor);
                    }

                    bw.write(v.toString());
                    bw.newLine();
                }

            } catch (IOException e) {
                System.out.println("Error al asignar conductor: " + e.getMessage());
            }
        }
    }
    
    public boolean verificarPlaca(String placa) {
        return buscar(placa) == null;
    }
    
    private List<Vehiculo> listarArchivo(String archivo){

        List<Vehiculo> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split(";");

                Vehiculo v = crearVehiculo(datos);
                v.fromString(linea);

                lista.add(v);
            }

        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }

        return lista;
    }
    
    private Vehiculo crearVehiculo(String[] datos){

        int capMax = Integer.parseInt(datos[3]);

        switch (capMax) {
            case 45:
                return new Bus();
            case 25:
                return new Microbus();
            case 19:
                return new Buseta();
            default:
                throw new IllegalArgumentException("Tipo de vehículo inválido");
        }
    }
    
    
  
    public String verEstado(String placa) {
      Vehiculo v = buscar(placa);
      return (v.getEstado() ? "Disponible" : "No Disponible");
    }
}
