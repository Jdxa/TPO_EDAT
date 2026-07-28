package Estructuras.EstructurasAux;
public class Nodo {
        /************* Autores ***********
    Joaquin Aguilera, Legajo FAI-4550
    Lucas Peroni, Legajo FAI-5499
    Miguel Mudarra Sucre, Legajo FAI-5172
    Santiago Lencina, Legajo FAI-5789
*/
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
