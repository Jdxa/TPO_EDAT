package Estructuras.Lineales;

// import static org.junit.Assert.*;

public class Lista {
    private Nodo cabecera;

    public Lista() {
        this.cabecera = null;
    }

    public int longitud() {
        int longitud = 0;
        Nodo nodoActual = this.cabecera;
        while (nodoActual != null) {
            longitud = longitud + 1;
            nodoActual = nodoActual.getEnlace();
        }
        return longitud;
    }

    ///////////////////////////////////////////////////////
    public boolean insertar(Object dato, int pos) {
        boolean exito = false;
        int longitud = this.longitud();
        Nodo nuevoNodo = new Nodo(dato, null); // Nodo a insertar
        Nodo nodoActual = this.cabecera; // Nodo de recorrido
        if (pos >= 1 && pos <= longitud + 1) { // Veririca dentro de limites
            if (pos == 1) {
                // se inserta pos 1
                nuevoNodo.setEnlace(cabecera); // le pongo al nuevo nodo de enlace cabeecera
                this.cabecera = nuevoNodo; // ahora cabecera es nuevo nodo
                exito = true;

            } else if (pos == longitud + 1) {
                // caso añadir al final
                for (int i = 1; i < pos - 1; i++) { // itero hasta nodo pos-1
                    nodoActual = nodoActual.getEnlace();
                }
                nodoActual.setEnlace(nuevoNodo); // a pos-1 le pongo de enlace nuevonodo
                exito = true;
            } else {
                // caso insertar en medio
                Nodo temp = new Nodo(null, null); // nodo auxiliar
                for (int i = 1; i < pos; i++) { // itero hasta que llegue a pos-1
                    temp.setEnlace(nodoActual); // enlazo el actual (pos-1) a temp
                    nodoActual = nodoActual.getEnlace(); // muevo el actual a pos para luego enlazarlo al nuevo nodo

                }

                nuevoNodo.setEnlace(nodoActual); // al nuevo nodo le pongo de enlace actual
                temp.getEnlace().setEnlace(nuevoNodo); // al enlace de temp le coloco de enlace el nuevo nodo
                exito = true; // mueve los nodos a la derecha para inserta en pos el nodo nuevo
            }
        }
        return exito;
    }

    ///////////////////////////////////////////////////////////
    public boolean eliminar(int pos) {
        boolean exito = false;
        int longitud = this.longitud();

        Nodo nodoActual = this.cabecera;
        if (pos >= 1 && pos <= longitud) { // si pos esta dentro de los limites
            if (pos == 1) {
                // si eliminamos primer elemento
                nodoActual = nodoActual.getEnlace(); // me voy a pos+1
                cabecera = nodoActual; // a pos+1 le seteo la cabecera, se desenlaza la antigua cabecera
                exito = true;

            } else if (pos == longitud) {
                // si eliminamos ultimo elemento
                for (int i = 1; i < pos - 1; i++) {
                    nodoActual = nodoActual.getEnlace(); // itero hasta pos-1 y su enlace es pos
                }
                nodoActual.setEnlace(null); // al enlace que posee que es pos lo hago nulo borrando el nodo en pos
                exito = true;
            } else {
                // caso comun, posiciones entre medias
                for (int i = 1; i < pos - 1; i++) {
                    nodoActual = nodoActual.getEnlace(); // itero hasta pos-1
                }
                Nodo temp = nodoActual.getEnlace().getEnlace(); // guardo el nodo pos+1
                nodoActual.setEnlace(temp); // lo enlazo con actual que es pos-1,
                                            // eliminando el del medio que es pos

                exito = true;
            }
        }
        return exito;
    }

    /////////////////////////////////////////////////////////////////
    public void vaciar() {
        this.cabecera = null; // poner null la cabecera pone null toda la lista
    }

    ////////////////////////////////////////////////////////////
    public Object recuperar(int pos) {
        Object res; // copia a retornar
        Nodo actual = this.cabecera; // nodo de recorrido
        for (int i = 1; i < pos; i++) {
            actual = actual.getEnlace(); // itero hasta pos asi actual es su enlace que es pos
        }
        res = actual.getDato(); // guardo el dato en res y retorno el objeto
        return res;
    }

    ////////////////////////////////////////////////////////////////////////
    public int localizar(Object unObjeto) {
        int pos = -1; // si no existe el objeto retona -1
        int i = 1; // la listas empiezan en 1
        Nodo actual = this.cabecera;
        Boolean flag = false;
        while (!flag && actual != null) { // mientras no lo encontro y la cabecera es vacia
            if (actual.getDato() == unObjeto) { // si el dato es igual al objeto
                flag = true; // corta el while
                pos = i; // me guarda la posicion que posee
            } else { // sino
                i++; // incrementa el iterador
                actual = actual.getEnlace(); // y se mueve por la lista y repite...
            }
        }
        return pos;
    }

    ///////////////////////////////////////////////////////////////
    public boolean esVacia() {
        boolean exito = false;
        if (this.cabecera == null) { // si la cabecera es null la lista esta vacia
            exito = true;
        }
        return exito;
    }

    /////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        String res = "[";
        Nodo actual = this.cabecera;
        while (actual != null) {
            res = res + actual.getDato();
            if (actual.getEnlace() != null) {
                res = res + ",";
            }
            actual = actual.getEnlace();
        }
        res = res + "]";
        return res;
    }

    ////////////////////////////////////////////////////////////
   public Lista clonar() {
    Lista clon = new Lista();
    
    if (this.cabecera != null) {
        // Clonamos la cabecera
        Nodo actual = this.cabecera; 
        Nodo nuevoNodo = new Nodo(actual.getDato(), null); 
        clon.cabecera = nuevoNodo; 
        
        Nodo anteriorNodo = nuevoNodo; 

        // Clonamos el resto
        actual = actual.getEnlace(); // Avanzamos al segundo nodo original (si existe)
        
        while (actual != null) {
            
            nuevoNodo = new Nodo(actual.getDato(), null); 
            
            anteriorNodo.setEnlace(nuevoNodo); // Linkeamos el anterior al nuevo
            anteriorNodo = nuevoNodo;          // Ahora el anterior es el que acabamos de crear
            
            actual = actual.getEnlace();       // Avanzamos en la lista original
        }
    }
    return clon;
}


public void agregarElem(Object nuevo, int x) {

    // inserta el nuevo elemento al inicio de la lista
    this.cabecera = new Nodo(nuevo, this.cabecera);

    // comienza desde el primer nodo original (no el que acabamos de insertar)
    Nodo actual = this.cabecera.getEnlace();

    int contador = 1; // cuenta posiciones de nodos originales

    // recorre toda la lista hasta el final
    while (actual != null) {

        // cada x nodos originales, inserta un nuevo nodo
        if (contador % x == 0) {

            // crea un nodo nuevo que apunta al siguiente del actual
            Nodo nuevoNodo = new Nodo(nuevo, actual.getEnlace());

            // enlaza el nodo actual con el nuevo nodo
            actual.setEnlace(nuevoNodo);

            // avanza al siguiente nodo original (salteando el insertado)
            actual = nuevoNodo.getEnlace();

            // incrementa el contador (porque pasamos al siguiente original)
            contador++;

        } else {
            // si no toca insertar, simplemente avanza al siguiente nodo
            actual = actual.getEnlace();

            // incrementa el contador
            contador++;
        }
    }
    }

}
