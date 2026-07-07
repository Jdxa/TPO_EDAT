package GrafoEtiquetado;

public class NodoAdy {
    
    // atributos
    private NodoVert vertice;
    private NodoAdy sigAdyacente;

    public NodoAdy(NodoVert vert, NodoAdy ady){
        this.vertice = vert;
        this.sigAdyacente = ady;
    }

    public NodoVert getVertice(){
        return this.vertice;
    }

    public void setVertice(NodoVert vert){
        this.vertice = vert;
    }

    public NodoAdy getSigAdyacente(){
        return this.sigAdyacente;
    }

    public void setSigAdyacente(NodoAdy ady){
        this.sigAdyacente = ady;
    }
}
