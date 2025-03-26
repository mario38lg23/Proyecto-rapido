import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        gestionApp gestion = new gestionApp();

        do {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Seleccionar carpeta");
            System.out.println("2. Lectura de fichero");
            System.out.println("3. Conversión");
            System.out.println("0. Salir");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Introduce la ruta de la carpeta:");
                    String ruta = sc.nextLine();
                    gestion.comprobarFichero(ruta);
                    break;

                case 2:
                    System.out.println("Introduce el nombre del archivo a leer:");
                    String nombreArchivo = sc.nextLine();
                    gestion.leerArchivo(nombreArchivo);
                    break;

                case 3:
                    System.out.println("Introduce el nombre del archivo a convertir:");
                    String archivoOrigen = sc.nextLine();
                    System.out.println("Elige el formato de salida:");
                    System.out.println("1. CSV");
                    System.out.println("2. JSON");
                    System.out.println("3. XML");
                    int formatoSalida = sc.nextInt();
                    sc.nextLine();
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