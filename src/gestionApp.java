import java.io.*;
import java.util.*;

public class gestionApp {
    private CSV csv;
    private JSON json;
    private XML xml;
    boolean salir = false;

    public gestionApp() {
        csv = new CSV();
        json = new JSON();
        xml = new XML();
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
                    System.out.println("El directorio está vacío o no se puede leer.");
                }
            } else {
                System.out.println("La ruta seleccionada no es una carpeta.");
            }
        } else {
            System.out.println("Esta ruta no existe.");
        }
    }

    
    public void leerArchivo(String nombreArchivo) {
        String rutaCompleta = nombreArchivo;
        System.out.println(rutaCompleta);
        File archivo = new File(rutaCompleta);

        if (!archivo.exists()) {
            System.out.println("El archivo no existe.");
            return;
        }

        String extension = obtenerExtension(nombreArchivo);

        List<Map<String, String>> datos = new ArrayList<Map<String, String>>();
        if (extension.equalsIgnoreCase("csv")) {
            datos = csv.leerCSV(rutaCompleta);
            salir = true;
        } else if (extension.equalsIgnoreCase("json")) {
            datos = json.leerJSON(rutaCompleta);
            salir = true;
        } else if (extension.equalsIgnoreCase("xml")) {
            xml.leerXML(rutaCompleta);
            datos = xml.getElementos();
            salir = true;
        }

        if (salir) {
            mostrarDatos(datos);
        } else {
            System.out.println("Formato de archivo no soportado.");
        }
    }

    private void mostrarDatos(List<Map<String, String>> datos) {
        for (Map<String, String> fila : datos) {
            System.out.println(fila);
        }
    }

    private String obtenerExtension(String nombreArchivo) {
        int puntoIndex = nombreArchivo.lastIndexOf('.');
        return puntoIndex > 0 ? nombreArchivo.substring(puntoIndex + 1) : "";
    }

    public void escribirArchivo(String nombreArchivo, List<Map<String, String>> datos) {
        String rutaCompleta = nombreArchivo;
        String extension = obtenerExtension(nombreArchivo);
    
        if (extension.equalsIgnoreCase("csv")) {
            csv.escribirCSV(rutaCompleta, datos);
            salir = true;
        } else if (extension.equalsIgnoreCase("json")) {
            json.escribirJSON(rutaCompleta, datos);
            salir = true;
        } else if (extension.equalsIgnoreCase("xml")) {
            xml.setNombreRaiz("root");
            xml.escribirXML(rutaCompleta, datos);
            salir = true;
        }
    
        if (!salir) {
            System.out.println("Formato de archivo no soportado para escritura.");
        }
    }

    public void convertirArchivo(String archivoOrigen, int formatoSalida, String nombreSalida) {
        String rutaOrigen = archivoOrigen;
        List<Map<String, String>> datos = null;

        String extensionOrigen = obtenerExtension(archivoOrigen);

        if (extensionOrigen.equalsIgnoreCase("csv")) {
            datos = csv.leerCSV(rutaOrigen);
            salir = true;
        } else if (extensionOrigen.equalsIgnoreCase("json")) {
            datos = json.leerJSON(rutaOrigen);
            salir = true;
        } else if (extensionOrigen.equalsIgnoreCase("xml")) {
            xml.leerXML(rutaOrigen);
            datos = xml.getElementos();
            salir = true;
        }

        if (!salir) {
            System.out.println("Formato de archivo de origen no soportado.");
        }

        if (datos == null || datos.isEmpty()) {
            System.out.println("No se pudieron leer datos del archivo de origen.");
        }
        String extension = null;

        switch (formatoSalida) {
            case 1:
                extension = "csv";
                break;
            case 2:
                extension = "json";
                break;
            case 3:
                extension = "xml";
                break;
            default:
                System.out.println("Formato de salida no válido.");
                return;
        }

        if (!salir) {
            return;
        }

        String nombreArchivoCompleto = nombreSalida + "." + extension;
        escribirArchivo(nombreArchivoCompleto, datos);
        System.out.println("Archivo convertido y guardado como: " + nombreArchivoCompleto);
    }
}