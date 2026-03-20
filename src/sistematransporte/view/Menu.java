package sistematransporte.view;
import java.util.Scanner;
import java.util.List;
import sistematransporte.model.Vehiculo;

import sistematransporte.service.VehiculoService;
/**
 *
 * @author jssdv
 */
public class Menu {
    private Scanner scanner;
    private VehiculoService vehiculoService;
    
    public Menu(){
        this.scanner = new Scanner(System.in);
        this.vehiculoService = new VehiculoService();
    }
    
    public void iniciar(){
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
            
            switch (opcion){
                case 1: menuVehiculos(); break;
                case 2: menuConductores(); break;
                case 3: menuPasajeros(); break;
                case 4: menuTickets(); break;
                case 5: MenuEstadisticas(); break;
                case 0: System.out.println(" Saliendo del sistema... ");
                default: System.out.println(" Opcion no valida... ");
            }
        }while (opcion != 0);
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
                case 1: registrarVehiculo(); break;
                case 2: listarVehiculos(); break;
                case 3: buscarVehiculo(); break;
                case 4: eliminarVehiculo(); break;
                case 0: break;
                default: System.out.println("️ Opcion no valida...");
            }
        } while (opcion != 0);
    }

   
   private void registrarVehiculo(){
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

    
   private void listarVehiculos(){
          System.out.println("\n── Lista de Vehiculos ──");
        List<Vehiculo> lista = vehiculoService.listar();
        for (Vehiculo v : lista) {
            v.imprimirDetalle();
        }
   }
 
   private void buscarVehiculo (){
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
   
   private void eliminarVehiculo(){
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void menuPasajeros() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void menuTickets() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void MenuEstadisticas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
   
    
    
    
}
