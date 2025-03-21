package dicotomica.proyecto2;
/**
 * Representa un nodo en el árbol dicotómico.
 */
public class NodoClave {
    private String texto;
    private NodoClave hijoSi;
    private NodoClave hijoNo;
    private boolean esHoja;

    public NodoClave(String texto) {
        this.texto = texto;
        this.esHoja = false;
    }

    public NodoClave(String nombreEspecie, boolean esHoja) {
        this.texto = nombreEspecie;
        this.esHoja = esHoja;
    }

    // Getters y setters
    public String getTexto() { return texto; }
    public NodoClave getHijoSi() { return hijoSi; }
    public NodoClave getHijoNo() { return hijoNo; }
    public boolean esHoja() { return esHoja; }
    public void setHijoSi(NodoClave nodo) { hijoSi = nodo; }
    public void setHijoNo(NodoClave nodo) { hijoNo = nodo; }
}