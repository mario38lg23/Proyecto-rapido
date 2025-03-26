import java.io.*;
import java.util.*;

public class CSV {
    private List<Map<String, String>> datos;
    private String[] encabezados;

    public CSV() {
        this.datos = new ArrayList<>();
    }
    
    public List<Map<String, String>> getDatos() {
        return datos;
    }

    public String[] getEncabezados() {
        return encabezados;
    }
    
    public List<Map<String, String>> leerCSV(String rutaArchivo) {
        datos.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean primeraLinea = true;
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");
                if (primeraLinea) {
                    encabezados = valores;
                    primeraLinea = false;
                } else {
                    Map<String, String> fila = new HashMap<>();
                    for (int i = 0; i < encabezados.length; i++) {
                        fila.put(encabezados[i], i < valores.length ? valores[i] : "");
                    }
                    datos.add(fila);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datos;
    }

    public void convertirCSV(String rutaArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {

            bw.write(String.join(",", encabezados));
            bw.newLine();

            for (Map<String, String> fila : datos) {
                List<String> valores = new ArrayList<>();
                for (String encabezado : encabezados) {
                    valores.add(fila.getOrDefault(encabezado, ""));
                }
                bw.write(String.join(",", valores));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}