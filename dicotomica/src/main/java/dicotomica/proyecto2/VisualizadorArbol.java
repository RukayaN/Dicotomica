package dicotomica.proyecto2;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

public class VisualizadorArbol {
    static {
        System.setProperty("org.graphstream.ui", "swing");
    }

    public static void mostrarArbol(NodoClave raiz) {
        Graph grafo = new SingleGraph("Clave Dicotómica");
        grafo.setAttribute("ui.stylesheet", 
            "node { size: 20px; text-size: 14; fill-color: #A3C1AD; }" +
            "node.especie { fill-color: #FF6B6B; }"
        );
        
        agregarNodos(grafo, raiz, null);
        
        Viewer viewer = grafo.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);
    }

    private static void agregarNodos(Graph grafo, NodoClave nodo, String idPadre) {
        if (nodo == null) return;
        
        String id = nodo.getTexto() + System.identityHashCode(nodo);
        
        // 1. Crear el nodo
        grafo.addNode(id);
        
        // 2. Obtener el nodo y asignar atributos
        Node nodoGrafo = grafo.getNode(id);
        nodoGrafo.setAttribute("ui.label", nodo.getTexto());
        nodoGrafo.setAttribute("ui.class", nodo.esHoja() ? "especie" : "");

        // 3. Conectar con el padre
        if (idPadre != null) {
            grafo.addEdge(idPadre + "-" + id, idPadre, id);
        }
        
        // 4. Agregar hijos recursivamente
        if (!nodo.esHoja()) {
            agregarNodos(grafo, nodo.getHijoSi(), id);
            agregarNodos(grafo, nodo.getHijoNo(), id);
        }
    }
}