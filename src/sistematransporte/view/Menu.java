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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
