import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSON {
    private List<Map<String, String>> datos;

    public JSON() {
        this.datos = new ArrayList<>();
    }


    public List<Map<String, String>> getDatos() {
        return datos;
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datos;
    }

    public void escribirJSON(String rutaArchivo, List<Map<String, String>> datos) {
    public void escribirJSON(String rutaArchivo, List<Map<String, String>> datos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            bw.write("[\n");
            
            for (int i = 0; i < datos.size(); i++) {
                Map<String, String> fila = datos.get(i);
                bw.write("  {\n");
                
                int j = 0;
                for (Map.Entry<String, String> entry : fila.entrySet()) {
                    bw.write("    \"" + entry.getKey() + "\": ");
                    
                    try {
                        Double.parseDouble(entry.getValue());
                        bw.write(entry.getValue()); 
                    } catch (NumberFormatException e) {
                        bw.write("\"" + entry.getValue() + "\""); 
                    }
                    
                    if (j < fila.size() - 1) bw.write(","); 
                    bw.write("\n");
                    j++;
                }
                
                bw.write("  }");
                if (i < datos.size() - 1) bw.write(","); 
                bw.write("\n");
            }
            
            bw.write("]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}