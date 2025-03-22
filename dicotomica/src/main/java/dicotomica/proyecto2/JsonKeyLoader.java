package dicotomica.proyecto2;

import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Cargador de claves desde JSON usando Jackson
 */
public class JsonKeyLoader {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Arbol arbol;
    private TablaDispersion tablaEspecies;

    public void cargarArchivo(String ruta) throws Exception {
        JsonNode rootNode = objectMapper.readTree(new File(ruta));
        String categoria = rootNode.fieldNames().next();
        JsonNode especiesArray = rootNode.get(categoria);

        arbol = new Arbol();
        tablaEspecies = new TablaDispersion();

        java.util.Iterator<JsonNode> it = especiesArray.iterator();
        while (it.hasNext()) {
            JsonNode especieNode = it.next();
            String nombreEspecie = especieNode.fieldNames().next();
            JsonNode preguntasNode = especieNode.get(nombreEspecie);
            
            Pair[] preguntas = new Pair[preguntasNode.size()];
            java.util.Iterator<JsonNode> preguntasIt = preguntasNode.iterator();
            
            int i = 0;
            while (preguntasIt.hasNext()) {
                JsonNode preguntaNode = preguntasIt.next();
                String pregunta = preguntaNode.fieldNames().next();
                boolean respuesta = preguntaNode.get(pregunta).asBoolean();
                preguntas[i++] = new Pair(pregunta, respuesta);
            }
            
            arbol.insertarEspecie(nombreEspecie, preguntas);
            tablaEspecies.put(nombreEspecie, new NodoClave(nombreEspecie, true));
        }
    }

    public Arbol getArbol() { return arbol; }
    public TablaDispersion getTablaEspecies() { return tablaEspecies; }
}