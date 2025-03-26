import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

    Scanner sc = new Scanner(System.in);
    boolean salir = false;
    gestionApp gestion = new gestionApp();

    do {
        System.out.println("Seleccione una opcion ");
        System.out.println("1. Seleccionar carpeta ");
        System.out.println("2. Lectura de fichero ");
        System.out.println("3. Conversion ");
        System.out.println("0. Para salir ");
        int opcion = sc.nextInt();
        sc.nextLine();
        switch (opcion) {
            case 1:
                System.out.println("Dime la ruta que deseas");
                String ruta = sc.nextLine();
                gestion.comprobarFichero(ruta);
                break;

            case 2:
                System.out.println("Dime la ruta que deseas");
                String rutaDos = sc.nextLine();
                gestion.leerFicheroXML(rutaDos);
                break;

                case 3:
                    System.out.println("Elige el formato");
                    System.out.println("1.CSV");
                    System.out.println("2.JSON");
                    System.out.println("3.XML");
                    break;
                case 0:
                    salir = true;
                    break;

                default:
                    System.out.println("Opcion no valida");
                    break;
            }

        } while (!salir);
        sc.close();
    }
}