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
        return null;
    }

    public void escribirJSON(String rutaArchivo) {
    }

}