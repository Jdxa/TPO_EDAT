package Estructuras.GrafoEtiquetado;

public class NodoAdy {
    
    // atributos
    private NodoVert vertice;
    private NodoAdy sigAdyacente;
    private Object etiqueta;

    public NodoAdy(NodoVert vert, NodoAdy ady, Object etiq){
        this.vertice = vert;
        this.sigAdyacente = ady;
        this.etiqueta = etiq;
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

    public Object getEtiqueta(){
        return this.etiqueta;
    }

    public void setEtiqueta(Object nuevaEtiqueta){
        this.etiqueta = nuevaEtiqueta;
    }

    public String toString(){
        return "["+ this.getVertice()+","+this.etiqueta+"]";
    }
}