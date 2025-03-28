import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        List<Map<String, String>> datos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            String[] encabezados = null;

            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");

                if (encabezados == null) {
                    encabezados = valores;
                } else {
                    Map<String, String> fila = new LinkedHashMap<>();
                    for (int i = 0; i < encabezados.length; i++) {
                        fila.put(encabezados[i], valores[i]);
                    }
                    datos.add(fila);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return datos;
    }

    public void escribirCSV(String rutaArchivo, List<Map<String, String>> datos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            if (!datos.isEmpty()) {

                bw.write(String.join(",", datos.get(0).keySet()));
                bw.newLine();

                for (Map<String, String> fila : datos) {
                    bw.write(String.join(",", fila.values()));
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}