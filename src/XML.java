import java.io.BufferedReader;
import java.io.FileReader;
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
        System.out.println("La ruta seleccionada es un archivo XML.");

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            StringBuilder contenidoXML = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                contenidoXML.append(linea.trim());
            }

            String xml = contenidoXML.toString();

            
            Pattern etiquetaPattern = Pattern.compile("<([^/][^>]*)>(.*?)</\\1>");
            Matcher etiquetaMatcher = etiquetaPattern.matcher(xml);

            while (etiquetaMatcher.find()) {
                String clave = etiquetaMatcher.group(1).trim();
                String valor = etiquetaMatcher.group(2).trim();

                
                Map<String, String> mapaEtiqueta = new HashMap<>();
                mapaEtiqueta.put(clave, valor);

                
                elementos.add(mapaEtiqueta);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public void escribirXML(String rutaArchivo) {

    }
}