package dicotomica.proyecto2;

import javax.swing.JOptionPane;

/**
 * Clase principal con la lógica de la clave dicotómica
 */
public class ClaveDicotomica {
    private Arbol arbol;
    private TablaDispersion tablaEspecies;

    public void cargarDesdeJSON(String ruta) {
        try {
            JsonKeyLoader loader = new JsonKeyLoader();
            loader.cargarArchivo(ruta);
            arbol = loader.getArbol();
            tablaEspecies = loader.getTablaEspecies();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar JSON: " + e.getMessage());
        }
    }

    public String identificarEspecie() {
        NodoClave actual = arbol.getRaiz();
        while (actual != null && !actual.esHoja()) {
            boolean respuesta = mostrarPregunta(actual.getTexto());
            actual = respuesta ? actual.getHijoSi() : actual.getHijoNo();
        }
        return (actual != null) ? actual.getTexto() : "Especie no identificada";
    }

    public String buscarPorHash(String nombre) {
        long inicio = System.nanoTime();
        NodoClave especie = tablaEspecies.get(nombre);
        long fin = System.nanoTime();
        
        return (especie != null) 
            ? "Especie: " + especie.getTexto() + " | Tiempo: " + (fin - inicio) + " ns"
            : "No encontrada | Tiempo: " + (fin - inicio) + " ns";
    }

    public String buscarEnArbol(String nombre) {
        long inicio = System.nanoTime();
        String camino = buscarRec(arbol.getRaiz(), nombre, new StringBuilder());
        long fin = System.nanoTime();
        
        return (camino != null) 
            ? camino + "Tiempo: " + (fin - inicio) + " ns"
            : "No encontrada | Tiempo: " + (fin - inicio) + " ns";
    }

    private String buscarRec(NodoClave nodo, String nombre, StringBuilder ruta) {
        if (nodo == null) return null;
        if (nodo.esHoja() && nodo.getTexto().equals(nombre)) return ruta.toString();
        
        if (!nodo.esHoja()) {
            StringBuilder rutaSi = new StringBuilder(ruta)
                .append("P: ").append(nodo.getTexto()).append(" → Sí\n");
            
            String resultado = buscarRec(nodo.getHijoSi(), nombre, rutaSi);
            if (resultado == null) {
                StringBuilder rutaNo = new StringBuilder(ruta)
                    .append("P: ").append(nodo.getTexto()).append(" → No\n");
                resultado = buscarRec(nodo.getHijoNo(), nombre, rutaNo);
            }
            return resultado;
        }
        return null;
    }

    private boolean mostrarPregunta(String pregunta) {
        int respuesta = JOptionPane.showConfirmDialog(
            null, pregunta, "Pregunta", JOptionPane.YES_NO_OPTION
        );
        return respuesta == JOptionPane.YES_OPTION;
    }
    public Arbol getArbol() {
        return arbol;
    }

    public TablaDispersion getTablaEspecies() {
        return tablaEspecies;
    }
}