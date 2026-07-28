package GrafoEtiquetado;
public class NodoVert {
    
    // atributos
    private Object elem;
    private NodoVert sigVertice;
    private NodoAdy primerAdy;

    public NodoVert(Object dato, NodoVert vert, NodoAdy primer){
        this.elem = dato;
        this.sigVertice = vert;
        this.primerAdy = primer;
    }

    //getter y setter

    public Object getElem(){
        return this.elem;
    }

    public void setElem(Object dato){
        this.elem = dato;
    }

    public NodoVert getSigVertice(){
        return this.sigVertice;
    }

    public void setSigVertice(NodoVert sigVertice2) {
        this.sigVertice = sigVertice2;
    }

    public NodoAdy getPrimerAdy(){
        return this.primerAdy;
    }

    public void setPrimerAdy(NodoAdy primer){
        this.primerAdy = primer;
    }
}
