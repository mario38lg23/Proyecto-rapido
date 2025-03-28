
/**
 * 
 * @author Miguel Gonzalez y Mario Lopez
 * @version 2.0
 * @since 28/03/2025
 * 
 */

import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String ruta = "";
        boolean salir = false;
        gestionApp gestion = new gestionApp();

        System.out.println("----- MENU -----");
        System.out.println("La ruta de la carpeta es : " + ruta);
        gestion.comprobarFichero(ruta);
        System.out.println("---------------");

        do {
            System.out.println();
            System.out.println("Seleccione una opción:");
            System.out.println("1. Seleccionar carpeta");
            System.out.println("2. Lectura de fichero");
            System.out.println("3. Conversión");
            System.out.println("0. Salir");
            int opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {

                case 1:
                    System.out.println("Introduce la ruta de la carpeta:");
                    ruta = sc.nextLine();
                    System.out.println("----- MENU -----");
                    System.out.println("La ruta de la carpeta es : " + ruta);
                    gestion.comprobarFichero(ruta);
                    System.out.println("---------------");
                    break;

                case 2:
                    System.out.println("Introduce el nombre del archivo a leer:");
                    String nombreArchivo = sc.nextLine();
                    System.out.println("----- MENU -----");
                    System.out.println("La ruta completa es : " + nombreArchivo);
                    gestion.leerArchivo(nombreArchivo);
                    System.out.println("---------------");
                    break;

                case 3:
                    System.out.println("Introduce el nombre del archivo a convertir:");
                    String archivoOrigen = sc.nextLine();
                    System.out.println("Elige el formato de salida:");
                    System.out.println("1. CSV");
                    System.out.println("2. JSON");
                    System.out.println("3. XML");
                    int formatoSalida = Integer.parseInt(sc.nextLine());
                    System.out.println("Introduce el nombre del archivo de salida (sin extensión):");
                    String nombreSalida = sc.nextLine();
                    gestion.convertirArchivo(archivoOrigen, formatoSalida, nombreSalida);
                    break;

                case 0:
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (!salir);

        sc.close();
    }
}