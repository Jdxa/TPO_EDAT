package Estructuras.TablaAVL;

// import static org.junit.Assert.fail;

import Estructuras.EstructurasAux.*;

public class ArbolAVL {
    // *Autores
    // Aguilera Joaquin
    // el fixy */

    private NodoArbol raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre, char lugar) {
        boolean exito = true;
        if (this.raiz == null) {
            // si es es nulo el arbol
            this.raiz = new NodoArbol(elemNuevo, null, null);

        } else {
            // como el arbol no esta vacio busco el padre
            NodoArbol nPadre = obtenerNodo(this.raiz, elemPadre);
            if (nPadre != null) { // si existe ese nodo padre
                if (lugar == 'I' && nPadre.getIzquierdo() == null) {
                    // si inserto por izquierda y veo que este vacio ese lugar
                    nPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else if (lugar == 'D' && nPadre.getDerecho() == null) {
                    // si inserto por derecha y veo que este vacio ese lugar
                    nPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                } else {
                    // el padre ya tiene hijos
                    exito = false;
                }
            } else {
                // no existe el padre
                exito = false;
            }
        }

        return exito;
    }

    private NodoArbol obtenerNodo(NodoArbol n, Object buscado) {
        NodoArbol res = null;
        if (n != null) {
            // si n tiene a buscando lo retornado
            if (n.getElem().equals(buscado)) {
                res = n;
            } else {
                // busca por izquierda
                res = obtenerNodo(n.getIzquierdo(), buscado);
                // si no lo encontro por izq va por derecha
                if (res == null) {
                    res = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }

        return res;
    }

    private NodoArbol obtenerNodoPosicion(NodoArbol n, int posBusc, int[] posActual) {
        NodoArbol res = null;
        if (n != null) {
            // Incrementa la pos actual si hay nodo en n(nodo actual)
            posActual[0] = posActual[0] + 1;
            if (posBusc == posActual[0]) {
                // si llego a la posBusc retorna el nodo en esa pos
                res = n;
            } else {
                // visita por izquierda
                res = obtenerNodoPosicion(n.getIzquierdo(), posBusc, posActual);
                if (res == null) {
                    // si ya visito toda la izquierda va por la derecha del padre
                    res = obtenerNodoPosicion(n.getDerecho(), posBusc, posActual);
                }
            }
        }
        return res;
    }

    public boolean insertarPorPosicion(Object nuevo, int posPadre, char posHijo) {
        boolean exito = false;
        NodoArbol nNodo = new NodoArbol(nuevo, null, null);
        // solo inserta si existe un nodo en PosPadre
        if (this.raiz != null) {
            // arbol no vacio
            int[] posActual = { 0 };
            NodoArbol nPadre = obtenerNodoPosicion(this.raiz, posPadre, posActual);
            if (nPadre != null) {
                // lo encontro
                if (posHijo == 'I' && nPadre.getIzquierdo() == null) {
                    // si quiero insertar en I y no esta ocupado
                    nPadre.setIzquierdo(nNodo);
                    exito = true;
                } else if (posHijo == 'D' && nPadre.getDerecho() == null) {
                    // si quiero insertar por D y no esta ocupado
                    nPadre.setDerecho(nNodo);
                    exito = true;
                }
            }
        }
        return exito;
    }

    public Lista listarPreorden() {
        Lista lis = new Lista();
        listarPreordenAux(this.raiz, lis);
        return lis;
    }

    private void listarPreordenAux(NodoArbol nodo, Lista lis) {

        if (nodo != null) {
            lis.insertar(nodo.getElem(), lis.longitud() + 1);
            listarPreordenAux(nodo.getIzquierdo(), lis);
            listarPreordenAux(nodo.getDerecho(), lis);
        }
    }

    public Lista listarInorden() {
        Lista lis = new Lista();
        listarInordenAux(this.raiz, lis);
        return lis;

    }

    private void listarInordenAux(NodoArbol nodo, Lista lis) {
        if (nodo != null) {
            listarInordenAux(nodo.getIzquierdo(), lis);
            lis.insertar(nodo.getElem(), lis.longitud() + 1);
            listarInordenAux(nodo.getDerecho(), lis);
        }
    }

    public Lista listarPosorden() {
        Lista lis = new Lista();
        listarPosordenAux(this.raiz, lis);
        return lis;

    }

    private void listarPosordenAux(NodoArbol nodo, Lista lis) {
        if (nodo != null) {
            listarPosordenAux(nodo.getIzquierdo(), lis);
            listarPosordenAux(nodo.getDerecho(), lis);
            lis.insertar(nodo.getElem(), lis.longitud() + 1);
        }
    }

    public Cola listarPorNiveles() {
        Cola c = new Cola(); // Cola a retornar
        if (this.raiz != null) { // mientras no este vacio el arbol
            Cola aux = new Cola(); // creo una auxiliar para el recorrido
            aux.poner(this.raiz); // le coloco la raiz del arbol
            while (!aux.esVacia()) { // hasta que no recorra todo el arbol no corta
                NodoArbol nodoActual = (NodoArbol) aux.obtenerFrente(); // creo un nodo arbol que sera
                c.poner(nodoActual.getElem()); // coloco el elemento del nodo actual en la cola a retornar
                if (nodoActual.getIzquierdo() != null) { // si el nodo actual tiene hijo por izquierda lo coloco en la
                                                         // cola auxiliar
                    aux.poner(nodoActual.getIzquierdo()); //
                }
                if (nodoActual.getDerecho() != null) { // si el nodo actual tiene hijo por derecha lo coloco en la cola
                                                       // auxiliar
                    aux.poner(nodoActual.getDerecho());
                }
                aux.sacar(); // saco el nodo actual para seguir con el siguiente
            }
        }
        return c;
    }

    private int nivelaux(NodoArbol nodo, Object elemento, int nivel) {
        int n = -1;
        if (nodo != null) {
            if (nodo.getElem().equals(elemento)) {
                n = nivel;
            } else {
                // busco x izquierda
                n = nivelaux(nodo.getIzquierdo(), elemento, nivel + 1);
                // si no encontre busca x derecha
                if (n == -1) {
                    n = nivelaux(nodo.getDerecho(), elemento, nivel + 1);
                }
            }
        }
        return n;
    }

    public int nivel(Object elemento) {
        int n = nivelaux(this.raiz, elemento, 0);
        return n;
    }

    public int altura() {
        int altura = -1;
        if (this.raiz != null) {
            altura = alturaAux(this.raiz, 0);
        }
        return altura;
    }

    private int alturaAux(NodoArbol nodo, int i) {
        int res = 0;
        int izq, der;
        if (nodo != null) {
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                res = i;
            } else {
                izq = alturaAux(nodo.getIzquierdo(), i + 1);
                der = alturaAux(nodo.getDerecho(), i + 1);
                if (izq < der) {
                    res = der;
                } else {
                    res = izq;
                }
            }
        }
        return res;
    }

    public ArbolAVL clone() {
        ArbolAVL clon = new ArbolAVL();
        if (this.raiz != null) {
            NodoArbol nuevo = new NodoArbol(this.raiz.getElem(), null, null);
            clon.raiz = nuevo;
            cloneaux(this.raiz, clon.raiz);
        }
        return clon;
    }

    private void cloneaux(NodoArbol actual, NodoArbol clonRaiz) {
        if (actual != null) {
            // Por izquierda
            if (actual.getIzquierdo() != null) {
                NodoArbol nuevo = new NodoArbol(actual.getIzquierdo().getElem(), null, null);
                clonRaiz.setIzquierdo(nuevo);
                cloneaux(actual.getIzquierdo(), clonRaiz.getIzquierdo());
            }
            // Por derecha
            if (actual.getDerecho() != null) {
                NodoArbol nuevo2 = new NodoArbol(actual.getDerecho().getElem(), null, null);
                clonRaiz.setDerecho(nuevo2);
                cloneaux(actual.getDerecho(), clonRaiz.getDerecho());
            }
        }
    }

    // metodos propios
    private boolean verificarAux(NodoArbol nodo, Lista patron, int i) {
        boolean aux = false;
        if (nodo != null) {
            // caso de corte: ultimo elemento de la lista y nodo hoja
            if (i == patron.longitud() && nodo.getIzquierdo() == null && nodo.getDerecho() == null
                    && nodo.getElem().equals(patron.recuperar(i))) {
                aux = true;
            } else if (i < patron.longitud()) {
                // caso recursivo: evaluo nodos
                if (nodo.getElem().equals(patron.recuperar(i))) {
                    // por izquierda
                    if (verificarAux(nodo.getIzquierdo(), patron, i + 1)) {
                        aux = true;
                    } else {
                        // por derecha
                        aux = verificarAux(nodo.getDerecho(), patron, i + 1);
                    }
                }
            }
        }
        return aux;
    }

    public boolean verificarPatron(Lista lista) {
        boolean res = false;
        if (lista.esVacia() && this.raiz == null) {
            // ambas vacias patron [] existe en todo arbol
            res = true;
        } else if (lista.esVacia() && this.raiz != null) {
            // lista vacia
            res = false;
        } else if (!lista.esVacia() && this.raiz == null) {
            // arbol vacio
            res = false;
        } else if (!lista.esVacia() && this.raiz != null) {
            // ninguna vacia
            res = verificarAux(raiz, lista, 1);
        }

        return res;
    }

    public Lista frontera() {
        Lista l = new Lista();
        if (this.raiz != null) {
            int[] pos = { 1 };
            cargarLista(this.raiz, l, pos);
        }
        return l;
    }

    private void cargarLista(NodoArbol nodo, Lista l, int[] pos) {
        if (nodo != null) {
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                // es hoja
                l.insertar(nodo.getElem(), pos[0]);
                pos[0]++;
            } else {
                // voy por la izquierda y se cargan si son hojas
                cargarLista(nodo.getIzquierdo(), l, pos);
                // una vez revise la izquierda voy por derecha
                cargarLista(nodo.getDerecho(), l, pos);
            }
        }
    }

    public ArbolAVL clonarInvertido() {
        ArbolAVL clone = new ArbolAVL();
        if (this.raiz != null) {
            NodoArbol nuevo = new NodoArbol(this.raiz.getElem(), null, null);
            clone.raiz = nuevo;
            clonarinvertidoaux(this.raiz, clone.raiz);
        }
        return clone;
    }

    private void clonarinvertidoaux(NodoArbol actual, NodoArbol cloneRaiz) {
        if (actual != null) {
            // Por izquierda
            if (actual.getIzquierdo() != null) {
                NodoArbol nuevo = new NodoArbol(actual.getIzquierdo().getElem(), null, null);
                cloneRaiz.setDerecho(nuevo);
                clonarinvertidoaux(actual.getIzquierdo(), cloneRaiz.getDerecho());
            }
            // Por derecha
            if (actual.getDerecho() != null) {
                NodoArbol nuevo2 = new NodoArbol(actual.getDerecho().getElem(), null, null);
                cloneRaiz.setIzquierdo(nuevo2);
                clonarinvertidoaux(actual.getDerecho(), cloneRaiz.getIzquierdo());
            }
        }

    }
    public boolean equals(ArbolAVL otro){
        boolean res = equalsaux(this.raiz, otro.raiz);
        return res;
    }
    private boolean equalsaux(NodoArbol actual, NodoArbol otro){
        boolean res= false;
        if (actual == null && otro == null){
            //caso base1: comprobo todo o ambos son vacios
            res =true;
        }else if (actual== null || otro == null) {
            //caso base2: un nodo es nulo y el otro no
            res= false;
        }else{
            //caso recursivo: comprueba cada nodo por izquierda y por dercha

            if (actual.getElem().equals(otro.getElem())) {
                //elementos iguales, verifica x izquierda y derecha
                res = equalsaux(actual.getIzquierdo(), otro.getIzquierdo()) && equalsaux(actual.getDerecho(), otro.getDerecho());
            }else{
                //son distintos es falso
                res =false;
            }
        }
        return res;
    }
}
