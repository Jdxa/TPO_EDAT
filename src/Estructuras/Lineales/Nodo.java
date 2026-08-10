package Estructuras.Lineales;
public class Nodo {

    private Object dato;
    private Nodo enlace;

    public Nodo(Object dato, Nodo enlace) {
        this.dato = dato;
        this.enlace = enlace;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public Nodo getEnlace() {
        return enlace;
    }

    public void setEnlace(Nodo enlace) {
        this.enlace = enlace;
    }
}
