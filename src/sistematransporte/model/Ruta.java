package sistematransporte.model;

import sistematransporte.model.interfaces.Imprimible;

public class Ruta implements Imprimible {

    private String codigoRuta;
    private String ciudadOrigen;
    private String ciudadDestino;
    private double distanciaKm;
    private int tiempoMinutos;

    public Ruta() {
    }

    public Ruta(String codigoRuta, String ciudadOrigen, String ciudadDestino, double distanciaKm, int tiempoMinutos) {
        this.codigoRuta = codigoRuta;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.distanciaKm = distanciaKm;
        this.tiempoMinutos = tiempoMinutos;
    }

    public String getCodigoRuta() {
        return codigoRuta;
    }

    public void setCodigoRuta(String codigoRuta) {
        this.codigoRuta = codigoRuta;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }
    
    public String getOrigenDestino() {
      return getCiudadOrigen() + "-" + getCiudadDestino();
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public int getTiempoMinutos() {
        return tiempoMinutos;
    }

    public void setTiempoMinutos(int tiempoMinutos) {
        this.tiempoMinutos = tiempoMinutos;
    }

    @Override
    public String toString() {
        return codigoRuta + ";" + ciudadOrigen + ";" + ciudadDestino + ";" + distanciaKm + ";" +  tiempoMinutos;
    }

    public void fromString(String linea) {

        String[] datos = linea.split(";");

        this.codigoRuta = datos[0];
        this.ciudadOrigen = datos[1];
        this.ciudadDestino = datos[2];
        this.distanciaKm = Double.parseDouble(datos[3]);
        this.tiempoMinutos = Integer.parseInt(datos[4]);
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("| Código:           " + codigoRuta);
        System.out.println("| Origen:           " + ciudadOrigen);
        System.out.println("| Destino:          " + ciudadDestino);
        System.out.println("| Distancia (km):   " + distanciaKm);
        System.out.println("| Tiempo (min):     " + tiempoMinutos);
    }
}