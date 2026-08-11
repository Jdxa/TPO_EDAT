package Estructuras.Lineales;

public class Cola {

    private Nodo frente;
    private Nodo fin;
    
    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    public boolean poner(Object dato){
        Nodo nuevo = new Nodo(dato, null);
        if (this.fin == null) {             //Si la cola esta vacia, el nuevo nodo es el frente y el fin
            this.frente = nuevo;
            this.fin = nuevo;
        } else {                            //Si no esta vacia, el nuevo nodo se enlaza al fin y el nuevo nodo es el fin
            this.fin.setEnlace(nuevo);         //Enlazo el nuevo nodo al fin
            this.fin = nuevo;                    //El nuevo nodo es el fin
        }        
        return true;
    }
    public boolean sacar(){
        boolean exito = true;
        if (this.frente == null) {
            exito = false;
        } else {
            this.frente= this.frente.getEnlace();   //El nuevo frente es el nodo siguiente al frente actual
            if (this.frente== null) {
                this.fin = null;                    //Si el nuevo frente es null, la cola queda vacia, por lo que el fin tambien es null
            }                                   
        }

        return exito;
    }
    public Object obtenerFrente(){
        Object dato = null;
        if (this.frente != null) {
            dato = this.frente.getDato();
        }
        return dato;
    }
    public boolean esVacia(){
        boolean exito = false;
        if (this.frente == null) { //Si el frente es null, la cola esta vacia
            exito = true;
        }
        return exito;
    }
    public void vaciar(){
        this.frente = null;
        this.fin = null;
    }
    
    
    public Cola clonar(){
        Cola clon = new Cola();
            Nodo nodoActual = this.frente;
            while (nodoActual != null) {
                Nodo nuevo = new Nodo(nodoActual.getDato(), null);
                if (clon.fin == null){
                    clon.frente = nuevo;
                    clon.fin = nuevo;
                } else {
                    clon.fin.setEnlace(nuevo);
                    clon.fin = nuevo;
                }
                
                //iteracion
                nodoActual = nodoActual.getEnlace();
            }
     
        
        return clon;
    }
    @Override
    public String toString(){
        String s = "[";
        Nodo nodoActual = this.frente;
        while (nodoActual != null) {
            if (nodoActual.getEnlace() != null) {
                s += nodoActual.getDato() + ",";
            }else{
                s += nodoActual.getDato();
            }
            
            nodoActual = nodoActual.getEnlace();
        }
        s += "]";
        return s;
    }
    
}
