package Estructuras.TablaAVL;


public class NodoArbol {
    private Object elem;
    private NodoArbol izquierdo;
    private NodoArbol derecho;

    public NodoArbol(Object nuevo, NodoArbol izq, NodoArbol der){
        this.elem = nuevo;
        this.izquierdo = izq;
        this.derecho = der;

    }

    public Object getElem(){
        return this.elem;
    }

    public NodoArbol getIzquierdo(){
        return this.izquierdo;
    }

    public NodoArbol getDerecho(){
        return this.derecho;
    }

    public void setElem(Object nuevo){
        this.elem = nuevo;
    }

    public void setIzquierdo(NodoArbol nuevo){
        this.izquierdo = nuevo;
    }

    public void setDerecho(NodoArbol nuevo){
        this.derecho = nuevo;
    }
}
