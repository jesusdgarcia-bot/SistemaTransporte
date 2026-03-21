package sistematransporte.view;

import java.util.Scanner;
import java.util.List;
import sistematransporte.model.Conductor;
import sistematransporte.model.Pasajero;
import sistematransporte.model.Vehiculo;
import sistematransporte.service.ConductorService;
import sistematransporte.service.PasajeroService;
import sistematransporte.service.VehiculoService;

/**
 *
 * @author jssdv
 */
public class Menu {

    private Scanner scanner;
    private VehiculoService vehiculoService;
    private ConductorService conductorService;
    private PasajeroService pasajeroService;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.vehiculoService = new VehiculoService();
        this.conductorService = new ConductorService();
        this.pasajeroService = new PasajeroService();
    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n+===============================+");
            System.out.println("|     SISTEMA DE TICKETS");
            System.out.println("+===============================+");
            System.out.println("| 1) Gestion de vehiculos");
            System.out.println("| 2) Gestion de conductores");
            System.out.println("| 3) Gestion de pasajeros");
            System.out.println("| 4) Gestion de tickets");
            System.out.println("| 5) Gestion de Estadisticas");
            System.out.println("+===============================+");
            System.out.println("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    menuVehiculos();
                    break;
                case 2:
                    menuConductores();
                    break;
                case 3:
                    menuPasajeros();
                    break;
                case 4:
                    menuTickets();
                    break;
                case 5:
                    MenuEstadisticas();
                    break;
                case 0:
                    System.out.println(" Saliendo del sistema... ");
                default:
                    System.out.println(" Opcion no valida... ");
            }
        } while (opcion != 0);
    }

    private void menuVehiculos() {
        int opcion;
        do {
            System.out.println("\n+===============================+");
            System.out.println("|   GESTION DE VEHICULOS");
            System.out.println("+===============================+");
            System.out.println("|  1. Registrar vehiculo");
            System.out.println("|  2. Listar vehiculos");
            System.out.println("|  3. Buscar vehiculo por placa");
            System.out.println("|  4. Eliminar vehiculo");
            System.out.println("|  0. Volver");
            System.out.println("+===============================+");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarVehiculo();
                    break;
                case 2:
                    listarVehiculos();
                    break;
                case 3:
                    buscarVehiculo();
                    break;
                case 4:
                    eliminarVehiculo();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("️ Opcion no valida...");
            }
        } while (opcion != 0);
    }

    private void registrarVehiculo() {
        System.out.println("\n REGISTRAR VEHICULO");
        System.out.println("Numero de placa: ");
        String placa = scanner.nextLine();
        System.out.println("Ruta: ");
        String ruta = scanner.nextLine();
        System.out.println("Tarifa: ");
        String tipo = scanner.nextLine();
        try {
            vehiculoService.registrar(placa, ruta, tipo);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarVehiculos() {
        System.out.println("\n── Lista de Vehiculos ──");
        List<Vehiculo> lista = vehiculoService.listar();
        for (Vehiculo v : lista) {
            v.imprimirDetalle();
        }
    }

    private void buscarVehiculo() {
        System.out.println("\n── Buscar Vehiculo ──");
        System.out.print("Ingrese la placa: ");
        String placa = scanner.nextLine();
        try {
            Vehiculo e = vehiculoService.buscar(placa);
            e.imprimirDetalle();
        } catch (IllegalArgumentException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    private void eliminarVehiculo() {
        System.out.println("\n── Eliminar Vehiculo ──");
        System.out.print("Ingrese la placa: ");
        String placa = scanner.nextLine();
        try {
            vehiculoService.eliminar(placa);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void menuConductores() {
        int opcion;
        do {
            System.out.println("\n+===============================+");
            System.out.println("|   GESTION DE CONDUCTORES  ");
            System.out.println("+===============================+");
            System.out.println("|  1. Registrar conductor");
            System.out.println("|  2. Listar conductores");
            System.out.println("|  3. Buscar conductor");
            System.out.println("|  4. Eliminar conductor");
            System.out.println("|  0. Volver");
            System.out.println("+===============================+");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarConductor();
                    break;
                case 2:
                    listarConductores();
                    break;
                case 3:
                    buscarConductor();
                    break;
                case 4:
                    eliminarConductor();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("️ Opcion no valida...");
            }
        } while (opcion != 0);
    }

    private void registrarConductor() {
        System.out.println("\n REGISTRAR CONDUCTOR");
        System.out.println("Numero de cedula: ");
        String cedula = scanner.nextLine();
        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Numero de licencia: ");
        String numeroLicencia = scanner.nextLine();
        System.out.println("categoria de licencia: ");
        String categoriaLicencia = scanner.nextLine();
        try {
            conductorService.registrarConductor(cedula, nombre, numeroLicencia, categoriaLicencia);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarConductores() {
        System.out.println("\n── Lista de Conductores ──");
        List<Conductor> lista = conductorService.listarConductores();
        for (Conductor v : lista) {
            v.imprimirDetalle();
        }
    }

    private void buscarConductor() {
        System.out.println("\n── Buscar Conductor ──");
        System.out.print("Ingrese el numero de licencia: ");
        String numeroLicencia = scanner.nextLine();
        try {
            Conductor e = conductorService.buscarConductor(numeroLicencia);
            e.imprimirDetalle();
        } catch (IllegalArgumentException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    private void eliminarConductor() {
        System.out.println("\n── Eliminar Conductor ──");
        System.out.print("Ingrese el numero de licencia: ");
        String numeroLicencia = scanner.nextLine();
        try {
            conductorService.eliminarConductor(numeroLicencia);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void menuPasajeros() {
        int opcion;
        do {
            System.out.println("\n+===============================+");
            System.out.println("|   GESTION DE PASAJEROS  ");
            System.out.println("+===============================+");
            System.out.println("|  1. Registrar pasajero");
            System.out.println("|  2. Listar pasajeros");
            System.out.println("|  3. Buscar pasajero");
            System.out.println("|  4. Eliminar pasajero");
            System.out.println("|  0. Volver");
            System.out.println("+===============================+");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarPasajero();
                    break;
                case 2:
                    listarPasajeros();
                    break;
                case 3:
                    buscarPasajero();
                    break;
                case 4:
                    eliminarPasajero();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("️ Opcion no valida...");
            }
        } while (opcion != 0);
    }

    private void registrarPasajero() {
        int opcion;
        System.out.println("\n REGISTRAR PASAJERO");
        System.out.println("Numero de cedula: ");
        String cedula = scanner.nextLine();
        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Seleccione el tipo de pasajero: ");
        System.out.println("1. Adulto mayor");
        System.out.println("2. Estudiante");
        System.out.println("3. Regular");
        opcion = scanner.nextInt();
            scanner.nextLine();
            
        switch (opcion){
            case 1:  try {
            pasajeroService.registrarAdultoMayor(cedula, nombre);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
            case 2:   try {
            pasajeroService.registrarEstudiante(cedula, nombre);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } 
            case 3: try {
            pasajeroService.registrarRegular(cedula, nombre);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
                
           
        }
            
    }

    private void listarPasajeros() {
        System.out.println("\n── Lista de Pasajeros ──");
        List<Pasajero> lista = pasajeroService.listarPasajeros();
        for (Pasajero v : lista) {
            v.imprimirDetalle();
        }
    }

    private void buscarPasajero() {
        System.out.println("\n── Buscar Pasajero ──");
        System.out.print("Ingrese el numero de cedula: ");
        String cedula = scanner.nextLine();
        try {
            Pasajero e = pasajeroService.buscarPasajero(cedula);
            e.imprimirDetalle();
        } catch (IllegalArgumentException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    private void eliminarPasajero() {
        System.out.println("\n── Eliminar Pasajero ──");
        System.out.print("Ingrese el numero de cedula: ");
        String cedula = scanner.nextLine();
        try {
            pasajeroService.eliminarPasajero(cedula);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void menuTickets() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void MenuEstadisticas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
