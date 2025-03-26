import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CSV {
    private List<Map<String, String>> datos;
    private String[] encabezados;
    private char separador;

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
        
        try (BufferedReader bufferLectura = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea = bufferLectura.readLine();
            if (linea == null) {
                return datos; 
            }
            
            String[] encabezados = linea.split(String.valueOf(this.separador));
            System.out.println("Encabezados: " + Arrays.toString(encabezados));
            
            while ((linea = bufferLectura.readLine()) != null) {
                String[] valores = linea.split(String.valueOf(this.separador));
                Map<String, String> fila = new HashMap<>();
                
                for (int i = 0; i < encabezados.length && i < valores.length; i++) {
                    fila.put(encabezados[i], valores[i]);
                }
                
                datos.add(fila);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return datos;
    }

    public void escribirCSV(String rutaArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}