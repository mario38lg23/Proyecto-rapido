import java.util.*;

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

    public List<Map<String, String>> leerXML(String rutaArchivo) {
        return null;
    }

    public void escribirXML(String rutaArchivo) {
    }
}