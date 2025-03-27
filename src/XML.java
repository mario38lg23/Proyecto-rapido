import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XML {
    private List<Map<String, String>> elementos;
    private String nombreRaiz;

    public XML() {
        this.elementos = new ArrayList<>();
    }

    public void setNombreRaiz(String nombreRaiz) {
        this.nombreRaiz = nombreRaiz;
    }

    public void agregarElemento(Map<String, String> elemento) {
        elementos.add(elemento);
    }

    public List<Map<String, String>> getElementos() {
        return elementos;
    }

    public String getNombreRaiz() {
        return nombreRaiz;
    }

    public void limpiarDatos() {
        elementos.clear();
        nombreRaiz = null;
    }

    public void leerArchivoXML(String ruta) {
        System.out.println("El archivo seleccionado es un XML.");
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            StringBuilder contenidoXML = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                contenidoXML.append(linea.trim());
            }

            String xml = contenidoXML.toString();

            Pattern cochePattern = Pattern.compile("<coche>(.*?)</coche>");
            Matcher cocheMatcher = cochePattern.matcher(xml);

            while (cocheMatcher.find()) {
                String cocheXML = cocheMatcher.group(1);
                Map<String, String> mapaCoche = new HashMap<>();
                Pattern etiquetaPattern = Pattern.compile("<(\\w+)>(.*?)</\\1>");
                Matcher etiquetaMatcher = etiquetaPattern.matcher(cocheXML);

                while (etiquetaMatcher.find()) {
                    mapaCoche.put(etiquetaMatcher.group(1), etiquetaMatcher.group(2));
                }

                elementos.add(mapaCoche);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

    }

    public void escribirXML(String rutaArchivo, List<Map<String, String>> datos) {

        File file = new File(rutaArchivo);

        String path = file.getAbsolutePath();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {

            bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            bw.newLine();
            bw.write("<coches>");
            bw.newLine();

            for (Map<String, String> fila : datos) {
                bw.write("  <coche>");
                bw.newLine();
                for (Map.Entry<String, String> entry : fila.entrySet()) {
                    String clave = entry.getKey();
                    String valor = entry.getValue();
                    bw.write("    <" + clave + ">" + valor + "</" + clave + ">");
                    bw.newLine();
                }
                bw.write("  </coche>");
                bw.newLine();
            }
            bw.write("</coches>");
            System.out.println("XML creado exitosamente en: " + path);
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        }

    }

}