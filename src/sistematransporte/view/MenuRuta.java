package sistematransporte.view;

import sistematransporte.service.RutaService;
import sistematransporte.model.Ruta;

import java.util.List;
import java.util.Scanner;

public class MenuRuta {

    private RutaService rutaService;
    private Scanner scanner;

    public MenuRuta() {
        this.rutaService = new RutaService();
        this.scanner = new Scanner(System.in);
    }

    public void menuPrincipal() {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1: registrar(); break;
                case 2: listar(); break;
                case 3: buscar(); break;
                case 4: eliminar(); break;
                case 5: modificar(); break;
                case 0: System.out.println("Saliendo..."); break;
                default: System.out.println("Opción inválida");
            }

        } while (opcion != 0);
    }

    private void registrar() {
        System.out.println("+--------------------------------------------------+");
        System.out.println("|               REGISTRAR RUTA                    |");
        System.out.println("+--------------------------------------------------+");

        String codigo = leerCadena("| Código: ");
        String origen = leerCadena("| Origen: ");
        String destino = leerCadena("| Destino: ");
        double distancia = leerDouble("| Distancia (km): ");
        int tiempo = leerEntero("| Tiempo (min): ");

        try {
            rutaService.registrar(codigo, origen, destino, distancia, tiempo);
            System.out.println("| Ruta registrada exitosamente");
        } catch (IllegalArgumentException e) {
            System.out.println("| Error: " + e.getMessage());
        }

        pausar();
    }

    private void listar() {
        System.out.println("+--------------------------------------------------+");
        System.out.println("|                LISTA DE RUTAS                    |");
        System.out.println("+--------------------------------------------------+");

        try {
            List<Ruta> lista = rutaService.listar();

            for (Ruta r : lista) {
                r.imprimirDetalle();
                System.out.println("+--------------------------------------------------+");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("| Error: " + e.getMessage());
        }

        pausar();
    }

    private void buscar() {
        System.out.println("+--------------------------------------------------+");
        System.out.println("|                 BUSCAR RUTA                      |");
        System.out.println("+--------------------------------------------------+");

        String codigo = leerCadena("| Código: ");

        try {
            Ruta r = rutaService.buscar(codigo);
            r.imprimirDetalle();
        } catch (IllegalArgumentException e) {
            System.out.println("| Error: " + e.getMessage());
        }

        pausar();
    }

    private void eliminar() {
        System.out.println("+--------------------------------------------------+");
        System.out.println("|                ELIMINAR RUTA                     |");
        System.out.println("+--------------------------------------------------+");

        String codigo = leerCadena("| Código: ");

        try {
            rutaService.eliminar(codigo);
            System.out.println("| Ruta eliminada exitosamente");
        } catch (IllegalArgumentException e) {
            System.out.println("| Error: " + e.getMessage());
        }

        pausar();
    }

    private void modificar() {
        System.out.println("+--------------------------------------------------+");
        System.out.println("|               MODIFICAR RUTA                     |");
        System.out.println("+--------------------------------------------------+");

        String codigo = leerCadena("| Código: ");
        String origen = leerCadena("| Nuevo origen: ");
        String destino = leerCadena("| Nuevo destino: ");
        double distancia = leerDouble("| Nueva distancia (km): ");
        int tiempo = leerEntero("| Nuevo tiempo (min): ");

        try {
            rutaService.modificar(codigo, origen, destino, distancia, tiempo);
            System.out.println("| Ruta modificada exitosamente");
        } catch (IllegalArgumentException e) {
            System.out.println("| Error: " + e.getMessage());
        }

        pausar();
    }

    private void mostrarMenu() {
        System.out.println("+--------------------------------------------------+");
        System.out.println("|                GESTION DE RUTAS                  |");
        System.out.println("+--------------------------------------------------+");
        System.out.println("| 1. Registrar Ruta                              |");
        System.out.println("| 2. Listar Rutas                                |");
        System.out.println("| 3. Buscar Ruta                                 |");
        System.out.println("| 4. Eliminar Ruta                               |");
        System.out.println("| 5. Modificar Ruta                              |");
        System.out.println("| 0. Salir                                       |");
        System.out.println("+--------------------------------------------------+");
    }

    private String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private double leerDouble(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextDouble()) {
            System.out.print("Ingrese un número válido: ");
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }

    private void pausar() {
        System.out.println("Presione enter para continuar...");
        scanner.nextLine();
    }
}