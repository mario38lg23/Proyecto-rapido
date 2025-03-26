import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class gestionApp {
    ArrayList<CSV> ArrayListCSV = new ArrayList<>();
    ArrayList<XML> ArrayListXML = new ArrayList<>();
    ArrayList<JSON> ArrayListJSON = new ArrayList<>();

    public gestionApp() {

    }

    public void comprobarFichero(String ruta) {
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

    public void leerFicheroCSV(String rutaDos) {
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

    public void leerFicheroXML(String rutaDos) {
        File fichero = new File(rutaDos);

        if (!fichero.exists()) {
            System.out.println("Esta ruta no existe.");
            return;
        }

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
            return;
        }

        System.out.println("La ruta seleccionada es un archivo XML.");

        try (BufferedReader br = new BufferedReader(new FileReader(rutaDos))) {
            StringBuilder contenidoXML = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                contenidoXML.append(linea.trim());
            }

            String xml = contenidoXML.toString();
            List<List<Map<String, String>>> listaCoches = new ArrayList<>();

            // Expresión regular para extraer coches completos
            Pattern cochePattern = Pattern.compile("<coche>(.*?)</coche>");
            Matcher cocheMatcher = cochePattern.matcher(xml);

            while (cocheMatcher.find()) {
                String cocheXML = cocheMatcher.group(1);
                List<Map<String, String>> cocheListaMapas = new ArrayList<>();

                // Expresión regular para extraer cada etiqueta dentro de <coche>
                Pattern etiquetaPattern = Pattern.compile("<([^/][^>]*)>(.*?)</\\1>");
                Matcher etiquetaMatcher = etiquetaPattern.matcher(cocheXML);

                while (etiquetaMatcher.find()) {
                    String clave = etiquetaMatcher.group(1).trim();
                    String valor = etiquetaMatcher.group(2).trim();

                    // Crear un nuevo mapa para cada etiqueta y agregarlo a la lista del coche
                    Map<String, String> mapaEtiqueta = new HashMap<>();
                    mapaEtiqueta.put(clave, valor);
                    cocheListaMapas.add(mapaEtiqueta);
                }

                // Agregar la lista de mapas de este coche a la lista de coches
                listaCoches.add(cocheListaMapas);
            }

            // Imprimir la estructura
            for (int i = 0; i < listaCoches.size(); i++) {
                System.out.println("Coche " + (i + 1) + ": ");
                for (Map<String, String> mapa : listaCoches.get(i)) {
                    System.out.println("  " + mapa);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public void leerFicheroJSON(String rutaDos) {
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
