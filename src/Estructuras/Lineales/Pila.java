package Estructuras.Lineales;
public class Pila {
        /************* Autores ***********
    Joaquin Aguilera, Legajo FAI-4550
    Lucas Peroni, Legajo FAI-5499
   
*/
    private Nodo tope;

    public Pila(){
        this.tope = null;
    }
    public boolean apilar(Object elem){
        Nodo nuevo = new Nodo(elem, this.tope);
        this.tope = nuevo;
        return true;
    }
     public boolean desapilar(){
        boolean exito= false;
        if (this.tope != null) {
            this.tope = this.tope.getEnlace();
            exito = true;
        }
        return exito;   
    }

    public boolean vaciar(){
        this.tope = null;
        return true;
    }
    public boolean esVacia(){
        boolean exito = false;
        if (this.tope == null) {
            exito = true;
        }
        return exito;
    }
    public Object obtenerTope(){
        Object elem;
        if (this.tope != null) {
            elem = this.tope.getDato();
        }else{
            elem = null;
        }
        return elem;
    }
   public Pila clone() {
    Pila clon = new Pila();

    if (this.tope != null) {
        // Crear el primer nodo
        clon.tope = new Nodo(this.tope.getDato(), null);

        Nodo actualOriginal = this.tope.getEnlace();
        Nodo actualClon = clon.tope;

        // Recorrer y copiar
        while (actualOriginal != null) {
            Nodo nuevo = new Nodo(actualOriginal.getDato(), null);
            actualClon.setEnlace(nuevo);

            actualClon = nuevo;
            actualOriginal = actualOriginal.getEnlace();
        }
    }

    return clon;
}

    public String toString(){
        String s = "[";
        Nodo nodoActual = this.tope;
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
