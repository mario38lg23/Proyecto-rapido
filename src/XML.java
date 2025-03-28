/**
 * 
 * @author Miguel Gonzalez y Mario Lopez
 * @version 2.3
 * @since 28/03/2025
 * 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class XML {
    private List<Map<String, String>> datos;
    private String etiquetaRaiz;

    public XML() {
        this.datos = new ArrayList<>();
    }

    public List<Map<String, String>> getDatos() {
        return datos;
    }

    public String getEtiquetaRaiz() {
        return etiquetaRaiz;
    }

    public List<Map<String, String>> leerXML(String rutaArchivo) throws IOException {
        List<Map<String, String>> datos = new ArrayList<>();
        Map<String, String> elementoActual = null;
        boolean dentroObjeto = false;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();

                if (etiquetaRaiz == null && linea.startsWith("<") && !linea.startsWith("</") && !linea.startsWith("<?") && !linea.startsWith("<!")) {
                    etiquetaRaiz = extraerEtiqueta(linea);
                }

                if (!dentroObjeto && linea.startsWith("<") && !linea.startsWith("</")) {
                    dentroObjeto = true;
                    elementoActual = new HashMap<>();
                }

                if (linea.startsWith("</") && dentroObjeto) {
                    datos.add(elementoActual);
                    elementoActual = null;
                    dentroObjeto = false;
                }

                if (dentroObjeto && linea.startsWith("<") && linea.contains("</")) {
                    String clave = extraerEtiqueta(linea);
                    String valor = extraerValor(linea);
                    if (clave != null && valor != null) {
                        elementoActual.put(clave, valor);
                    }
                }
            }
        }
        if (datos.isEmpty()) {
            throw new IOException("El archivo XML no contiene datos válidos.");
        }
        return datos;
    }

    private String extraerEtiqueta(String linea) {
        int inicio = linea.indexOf("<") + 1;
        int fin = linea.indexOf(">");
        return (inicio >= 0 && fin > inicio) ? linea.substring(inicio, fin).trim() : null;
    }

    private String extraerValor(String linea) {
        int inicio = linea.indexOf(">") + 1;
        int fin = linea.indexOf("</");
        return (inicio >= 0 && fin > inicio) ? linea.substring(inicio, fin).trim() : null;
    }

    public void escribirXML(String rutaArchivo, List<Map<String, String>> datos) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            String etiqueta;

            if (etiquetaRaiz != null) {
                etiqueta = etiquetaRaiz;
            } else {
                etiqueta = "datos";
            }
            
            bw.write("<" + etiqueta + ">");
            bw.newLine();

            for (Map<String, String> elemento : datos) {
                bw.write("  <elemento>");
                bw.newLine();
                for (Map.Entry<String, String> entry : elemento.entrySet()) {
                    bw.write("    <" + entry.getKey() + ">" + entry.getValue() + "</" + entry.getKey() + ">");
                    bw.newLine();
                }
                bw.write("  </elemento>");
                bw.newLine();
            }

            if (etiquetaRaiz == null) {
                bw.write("</datos>");
            } else {
                bw.write("</" + etiquetaRaiz + ">");
            }
        }
    }
}