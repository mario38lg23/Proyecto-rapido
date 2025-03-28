/**
 * 
 * @author Miguel Gonzalez y Mario Lopez
 * @version 2.0
 */

 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.*;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 
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
        datos.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            StringBuilder contenidoXML = new StringBuilder();
            String linea;

            while ((linea = br.readLine()) != null) {
                contenidoXML.append(linea.trim());
            }

            String xml = contenidoXML.toString();
            if (xml.isEmpty()) {
                throw new IOException("El archivo XML está vacío.");
            }

            Pattern patternRaiz = Pattern.compile("<(\\w+)\\s*>.*</\\1>");
            Matcher matcherRaiz = patternRaiz.matcher(xml);
            if (matcherRaiz.find()) {
                this.etiquetaRaiz = matcherRaiz.group(1);
            } else {
                throw new IOException("No se pudo detectar una etiqueta raíz válida en el archivo XML.");
            }

            int inicioRaiz = xml.indexOf("<" + etiquetaRaiz + ">");
            int finRaiz = xml.lastIndexOf("</" + etiquetaRaiz + ">");
            String contenidoRaiz = xml.substring(inicioRaiz + etiquetaRaiz.length() + 2, finRaiz);

            Pattern patternElemento = Pattern.compile("<(\\w+)>(.*?)</\\1>");
            Matcher matcherElemento = patternElemento.matcher(contenidoRaiz);

            while (matcherElemento.find()) {
                Map<String, String> elemento = new LinkedHashMap<>();
                String contenidoElemento = matcherElemento.group(2);
                Matcher matcherSubElemento = patternElemento.matcher(contenidoElemento);

                while (matcherSubElemento.find()) {
                    String clave = matcherSubElemento.group(1);
                    String valor = matcherSubElemento.group(2);
                    elemento.put(clave, valor);
                }

                if (!elemento.isEmpty()) {
                    datos.add(elemento);
                }
            }
        }

        if (datos.isEmpty()) {
            throw new IOException("El archivo XML no contiene datos válidos.");
        }

        return datos;
    }
 
    public void escribirXML(String rutaArchivo, List<Map<String, String>> datos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            bw.newLine();
            bw.write("<" + (etiquetaRaiz != null ? etiquetaRaiz : "datos") + ">");
            bw.newLine();

            for (Map<String, String> fila : datos) {
                bw.write("  <elemento>");
                bw.newLine();
                for (Map.Entry<String, String> entry : fila.entrySet()) {
                    bw.write("    <" + entry.getKey() + ">" + escapeXml(entry.getValue()) + "</" + entry.getKey() + ">");
                    bw.newLine();
                }
                bw.write("  </elemento>");
                bw.newLine();
            }

            bw.write("</" + (etiquetaRaiz != null ? etiquetaRaiz : "datos") + ">");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String escapeXml(String input) {
        return input.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&apos;");
    }
 }