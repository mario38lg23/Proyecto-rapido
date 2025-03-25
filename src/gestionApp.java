import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class gestionApp {
    ArrayList<CSV> ArrayListCSV = new ArrayList<>();
    ArrayList<XML> ArrayListXML = new ArrayList<>();
    ArrayList<JSON> ArrayListJSON = new ArrayList<>();

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

    public void leerFicheroCSV(String rutaDos){
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
    public void leerFicheroXML(String rutaDos){
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
            System.out.println("La ruta seleccionada es un archivo XML.");

            try (BufferedReader br = new BufferedReader(new FileReader(rutaDos))) {
                System.out.println("Leyendo el fichero y extrayendo datos:");
    
                // Leer todo el contenido del archivo XML
                StringBuilder contenidoXML = new StringBuilder();
                String linea;
                while ((linea = br.readLine()) != null) {
                    contenidoXML.append(linea.trim()); // Elimina espacios extra
                }
    
                // Convertir a String
                String xml = contenidoXML.toString();
                Map<String, String> xmlMap = new HashMap<>();
    
                // Expresión regular mejorada para extraer solo etiquetas de datos
                String[] partes = xml.split("</?([^>]+)>");
    
                // Recorrer y guardar en el mapa
                for (int i = 1; i < partes.length - 1; i += 2) {
                    String clave = partes[i].trim();
                    String valor = partes[i + 1].trim();
    
                    // Filtrar etiquetas no deseadas
                    if (!clave.equalsIgnoreCase("coches") && !clave.equalsIgnoreCase("coche")) {
                        xmlMap.put(clave, valor);
                    }
                }
    
                // Imprimir el mapa resultante
                System.out.println(xmlMap);

                } catch (IOException e) {
                    System.out.println("Error al leer el archivo: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Esta ruta no existe.");
        }
    }
    public void leerFicheroJSON(String rutaDos){
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
