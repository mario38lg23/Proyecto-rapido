import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class JSON {
    private List<Map<String, String>> datos;
    private String[] encabezados;

    public JSON() {
        this.datos = new ArrayList<>();
    }
    
    public List<Map<String, String>> getDatos() {
        return datos;
    }

    public String[] getEncabezados() {
        return encabezados;
    }

    public List<Map<String, String>> leerJSON(String rutaArchivo) {
        datos.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                jsonBuilder.append(linea.trim());
            }

            String json = jsonBuilder.toString();
            if (json.startsWith("[") && json.endsWith("]")) {
                json = json.substring(1, json.length() - 1); 
                String[] elementos = json.split("\\},\\{"); 

                for (String elemento : elementos) {
                    elemento = elemento.replace("{", "").replace("}", ""); 
                    Map<String, String> mapa = new HashMap<>();
                    String[] pares = elemento.split(",");
                    for (String par : pares) {
                        String[] claveValor = par.split(":");
                        if (claveValor.length == 2) {
                            String clave = claveValor[0].trim().replace("\"", "");
                            String valor = claveValor[1].trim().replace("\"", "");
                            mapa.put(clave, valor);
                        }
                    }
                    datos.add(mapa);
                }

                if (!datos.isEmpty()) {
                    encabezados = datos.get(0).keySet().toArray(new String[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datos;
    }

    public void escribirJSON(String rutaArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            bw.write("[");
            for (int i = 0; i < datos.size(); i++) {
                Map<String, String> fila = datos.get(i);
                bw.write("{");
                int j = 0;
                for (Map.Entry<String, String> entrada : fila.entrySet()) {
                    bw.write("\"" + entrada.getKey() + "\":\"" + entrada.getValue() + "\"");
                    if (j < fila.size() - 1) {
                        bw.write(",");
                    }
                    j++;
                }
                bw.write("}");
                if (i < datos.size() - 1) {
                    bw.write(",");
                }
            }
            bw.write("]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}