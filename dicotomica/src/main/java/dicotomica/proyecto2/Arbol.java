package dicotomica.proyecto2;

/**
 * Implementación manual de un árbol binario para la clave dicotómica
 */
public class Arbol {
    private NodoClave raiz;

    public void insertarEspecie(String nombreEspecie, Pair[] preguntas) {
        if (raiz == null) {
            raiz = new NodoClave(preguntas[0].key);
        }
        insertarRec(raiz, preguntas, 0, nombreEspecie);
    }

    private void insertarRec(NodoClave actual, Pair[] preguntas, int idx, String especie) {
        if (idx == preguntas.length - 1) {
            NodoClave nodoEspecie = new NodoClave(especie, true);
            if (preguntas[idx].value) {
                actual.setHijoSi(nodoEspecie);
            } else {
                actual.setHijoNo(nodoEspecie);
            }
            return;
        }

        NodoClave siguiente;
        if (preguntas[idx].value) {
            siguiente = (actual.getHijoSi() != null) ? 
                actual.getHijoSi() : 
                new NodoClave(preguntas[idx + 1].key);
            actual.setHijoSi(siguiente);
        } else {
            siguiente = (actual.getHijoNo() != null) ? 
                actual.getHijoNo() : 
                new NodoClave(preguntas[idx + 1].key);
            actual.setHijoNo(siguiente);
        }
        insertarRec(siguiente, preguntas, idx + 1, especie);
    }

    public NodoClave getRaiz() { return raiz; }
}