import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class gestionApp {
    
    public gestionApp(){

    }

    public void comprobarFichero(String ruta){
        File fichero = new File(ruta);

        if (fichero.exists()) {
            if (fichero.isDirectory()) {
                System.out.println("Es un directorio. Contenido:");
                String[] contenido = fichero.list();
                if (contenido != null) {
                    for (String item : contenido) {
                        System.out.println(item);
                    }
                } else {
                    System.out.println("El directorio está vacío o no se puede leer. ");
                }
            } else {
                System.out.println("La ruta seleccionada no es una carpeta. ");
            }
        } else {
            System.out.println("Esta ruta no existe. ");
        }
    }

    public void leerFichero(String rutaDos){
        File fichero = new File(rutaDos);
                
        if (fichero.exists()) {
            if (fichero.isDirectory()) {
                System.out.println("Es un directorio. Contenido:");
                String[] contenido = fichero.list();
                if (contenido != null) {
                    for (String item : contenido) {
                        System.out.println(item);
                    }
                } else {
                    System.out.println("El directorio está vacío o no se puede leer.");
                }
            } else {
                System.out.println("La ruta seleccionada no es una carpeta. Es un fichero");
                try (BufferedReader br = new BufferedReader(new FileReader(rutaDos))) {
                    System.out.println("Leyendo el fichero: ");
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        System.out.println(linea);
                    }
                } catch (IOException e) {
                    System.out.println("Error al leer el archivo: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Esta ruta no existe.");
        }
    }
}
