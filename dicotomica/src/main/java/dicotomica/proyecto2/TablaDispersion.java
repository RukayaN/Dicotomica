package dicotomica.proyecto2;

/**
 * Tabla de dispersión manual con listas enlazadas
 */
public class TablaDispersion {
    private static final int CAPACIDAD = 100;
    private EntradaHash[] tabla;

    public TablaDispersion() {
        tabla = new EntradaHash[CAPACIDAD];
    }

    private int hash(String clave) {
        return Math.abs(clave.hashCode()) % CAPACIDAD;
    }

    public void put(String clave, NodoClave valor) {
        int indice = hash(clave);
        EntradaHash nuevaEntrada = new EntradaHash(clave, valor);
        
        if (tabla[indice] == null) {
            tabla[indice] = nuevaEntrada;
        } else {
            EntradaHash actual = tabla[indice];
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevaEntrada;
        }
    }

    public NodoClave get(String clave) {
        int indice = hash(clave);
        EntradaHash actual = tabla[indice];
        
        while (actual != null) {
            if (actual.clave.equals(clave)) {
                return actual.valor;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    private static class EntradaHash {
        String clave;
        NodoClave valor;
        EntradaHash siguiente;

        EntradaHash(String clave, NodoClave valor) {
            this.clave = clave;
            this.valor = valor;
        }
    }
}