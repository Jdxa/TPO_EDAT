package Estructuras.GrafoEtiquetado;

import Estructuras.EstructurasAux.Cola;
import Estructuras.EstructurasAux.Lista;

public class Grafo {

    // atributos
    private NodoVert inicio = null;

    public Grafo() {
        this.inicio = null;
    }

    private NodoVert ubicarVertice(Object buscado) {
        // busca que buscado no exista, si existe no se puede insertar
        NodoVert aux = this.inicio;
        while (aux != null && !aux.getElem().equals(buscado)) {
            aux = aux.getSigVertice();
        }
        return aux;
    }

    public boolean insertarVertice(Object nuevoVertice) {
        // no hay vertices repetidos
        boolean exito = false;
        NodoVert aux = this.ubicarVertice(nuevoVertice);
        if (aux == null) {
            // si no esta repetido lo inserta
            this.inicio = new NodoVert(nuevoVertice, this.inicio, null); // no conozco como se unen asique null
            exito = true;
        }
        return exito;
    }

    private NodoAdy ubicarEtiqueta(Object etiqueta, NodoVert vertice) {
        NodoAdy aux = null;
        ;
        if (vertice != null) {
            aux = vertice.getPrimerAdy();
            while (aux.getSigAdyacente() != null && aux.getEtiqueta().equals(etiqueta)) {
                aux = aux.getSigAdyacente();
            }
        }
        return aux;
    }

    public boolean insertarArco(Object origen, Object destino, Object etiqueta) {
        boolean exito = false;
        NodoVert origenVertice = ubicarVertice(origen); // busco el vertice origen
        NodoVert destinoVertice = ubicarVertice(destino); // busco el vertice destino
        // busco que exista el vertice
        if (origenVertice != null && destinoVertice != null) {
            NodoAdy nuevoArco = new NodoAdy(destinoVertice, null, etiqueta); // creo el arco
            NodoAdy ultimo = ubicarEtiqueta(etiqueta, origenVertice); // busco el ultimo arco del origen
            ultimo.setSigAdyacente(nuevoArco); // al ultimo arco lo enlazo con el nuevo arco
            exito = true;

        }
        return exito;
    }

    // eliminarVertice
    public boolean eliminarVertice(Object vertice) {
        boolean exito = false;
        NodoVert anterior = null;
        NodoVert aux = this.inicio;
        while (aux != null && !aux.getElem().equals(vertice)) {
            anterior = aux;
            aux = aux.getSigVertice();
        }
        if (aux != null) {
            // elimino arcos q vayan a aux
            NodoVert v = this.inicio;
            while (v != null) {
                if (v != aux) {
                    this.eliminarArco(v.getElem(), aux.getElem());
                }
                v = v.getSigVertice();
            }
            // desenlaza aux de la lista de vertices
            if (anterior == null) {
                this.inicio = aux.getSigVertice();
            } else {
                anterior.setSigVertice(aux.getSigVertice());
            }
            exito = true;
        }

        return exito;
    }

    public boolean eliminarArco(Object origen, Object destino) {
        boolean exito = false;
        NodoVert origenVertice = ubicarVertice(origen); // busco el vertice origen
        NodoVert destinoVertice = ubicarVertice(destino); // busco el vertice destino
        if (origenVertice != null && destinoVertice != null) { // busco que existan los vertices
            NodoAdy aux = origenVertice.getPrimerAdy(); // busco el primer arco del vertice origen
            NodoAdy anterior = null;
            while (aux != null && !exito) { // corta cuando encuentra el arco o cuando no hay mas arcos
                if (aux.getVertice().getElem().equals(destino)) {
                    if (anterior == null) {
                        origenVertice.setPrimerAdy(aux.getSigAdyacente());
                    } else {
                        anterior.setSigAdyacente(aux.getSigAdyacente());
                    }
                    exito = true;
                }
                anterior = aux;
                aux = aux.getSigAdyacente();
            }
        }
        return exito;
    }

    public boolean existeVertice(Object buscado) {
        boolean exito = false;
        NodoVert aux = this.inicio;
        while (aux != null && !exito) {
            if (aux.getElem().equals(buscado)) {
                exito = true;
            }
            aux = aux.getSigVertice();
        }
        return exito;
    }

    public boolean existeArco(Object origen, Object destino) {
        boolean exito = false;
        NodoVert origenVertice = ubicarVertice(origen); // busco el vertice origen
        NodoVert destinoVertice = ubicarVertice(destino); // busco el vertice destino
        if (origenVertice != null && destinoVertice != null) { // busco que existan los vertices
            NodoAdy aux = origenVertice.getPrimerAdy(); // busco el primer arco del vertice origen
            while (aux != null && !exito) { // corta cuando encuentra el arco o cuando no hay mas arcos
                if (aux.getVertice().getElem().equals(destino)) {
                    exito = true;
                }
                aux = aux.getSigAdyacente();
            }
        }
        return exito;
    }

    public boolean existeCamino(Object origen, Object destino) {
        boolean exito = false;
        NodoVert auxO = null;
        NodoVert auxD = null;
        NodoVert aux = this.inicio;
        while ((auxO == null || auxD == null) && aux != null) {
            if (aux.getElem().equals(origen))
                auxO = aux;
            if (aux.getElem().equals(destino))
                auxD = aux;
            aux = aux.getSigVertice();
        }
        if (auxO != null && auxD != null) {
            Lista visitados = new Lista();
            exito = existeCaminoAux(auxO, destino, visitados);
        }

        return exito;
    }

    private boolean existeCaminoAux(NodoVert n, Object dest, Lista vis) {
        boolean exito = false;
        if (n != null) {
            if (n.getElem().equals(dest)) {
                exito = true;
            } else {
                vis.insertar(n.getElem(), vis.longitud() + 1);
                NodoAdy ady = n.getPrimerAdy();
                while (!exito && ady != null) {
                    if (vis.localizar(ady.getVertice().getElem()) < 0) {
                        exito = existeCaminoAux(ady.getVertice(), dest, vis);
                    }
                    ady = ady.getSigAdyacente();
                }

            }
        }
        return exito;
    }

    public boolean esVacio() {
        boolean exito = false;
        if (this.inicio == null)
            exito = true;
        return exito;
    }

    public Lista caminoMasCorto(Object origen, Object destino) {
        Lista caminoCorto = new Lista();
        NodoVert nodoO = ubicarVertice(origen);
        NodoVert nodoD = ubicarVertice(destino);

        if (nodoO != null && nodoD != null) {
            Lista actual = new Lista();
            caminoCorto = caminoMasCortoAux(nodoO, destino, actual, caminoCorto);
        }
        return caminoCorto;
    }

    private Lista caminoMasCortoAux(NodoVert n, Object dest, Lista actual, Lista menor) {
        actual.insertar(n.getElem(), actual.longitud() + 1);

        if (n.getElem().equals(dest)) {
            if (menor.esVacia() || actual.longitud() < menor.longitud()) {
                menor = actual.clone();
            }
        } else {
            NodoAdy ady = n.getPrimerAdy();
            while (ady != null) {
                // Si el vértice no está en la lista actual (localizar retorna -1)
                if (actual.localizar(ady.getVertice().getElem()) < 0) {
                    // Poda: solo sigo buscando si el camino actual es menor que el más corto
                    // encontrado
                    if (menor.esVacia() || actual.longitud() < menor.longitud()) {
                        menor = caminoMasCortoAux(ady.getVertice(), dest, actual, menor);
                    }
                }
                ady = ady.getSigAdyacente();
            }
        }
        // Backtracking: saco el último nodo insertado
        actual.eliminar(actual.longitud());
        return menor;
    }

    public Lista caminoMasLargo(Object origen, Object destino) {
        Lista caminoLargo = new Lista();
        NodoVert nodoO = ubicarVertice(origen);
        NodoVert nodoD = ubicarVertice(destino);

        if (nodoO != null && nodoD != null) {
            Lista actual = new Lista();
            caminoLargo = caminoMasLargoAux(nodoO, destino, actual, caminoLargo);
        }
        return caminoLargo;
    }

    private Lista caminoMasLargoAux(NodoVert n, Object dest, Lista actual, Lista mayor) {
        actual.insertar(n.getElem(), actual.longitud() + 1);

        if (n.getElem().equals(dest)) {
            if (mayor.esVacia() || actual.longitud() > mayor.longitud()) {
                mayor = actual.clone();
            }
        } else {
            NodoAdy ady = n.getPrimerAdy();
            while (ady != null) {
                if (actual.localizar(ady.getVertice().getElem()) < 0) {
                    mayor = caminoMasLargoAux(ady.getVertice(), dest, actual, mayor);
                }
                ady = ady.getSigAdyacente();
            }
        }
        actual.eliminar(actual.longitud());
        return mayor;
    }

    public Lista listarEnProfundidad() {
        Lista visitados = new Lista();
        NodoVert aux = this.inicio;
        while (aux != null) {
            if (visitados.localizar(aux.getElem()) < 0) {
                listarEnProfundidadAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    private void listarEnProfundidadAux(NodoVert n, Lista visitados) {
        if (n != null) {
            visitados.insertar(n.getElem(), visitados.longitud() + 1);
            NodoAdy ady = n.getPrimerAdy();
            while (ady != null) {
                if (visitados.localizar(ady.getVertice().getElem()) < 0) {
                    listarEnProfundidadAux(ady.getVertice(), visitados);
                }
                ady = ady.getSigAdyacente();
            }
        }
    }

    public Lista listarEnAnchura() {
        Lista visitados = new Lista();
        NodoVert aux = this.inicio;
        while (aux != null) {
            if (visitados.localizar(aux.getElem()) < 0) {
                anchuraDesde(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    private void anchuraDesde(NodoVert n, Lista visitados) {
        Cola q = new Cola();
        visitados.insertar(n.getElem(), visitados.longitud() + 1);
        q.poner(n);

        while (!q.esVacia()) {
            NodoVert u = (NodoVert) q.obtenerFrente();
            q.sacar();
            NodoAdy ady = u.getPrimerAdy();
            while (ady != null) {
                if (visitados.localizar(ady.getVertice().getElem()) < 0) {
                    visitados.insertar(ady.getVertice().getElem(), visitados.longitud() + 1);
                    q.poner(ady.getVertice());
                }
                ady = ady.getSigAdyacente();
            }
        }
    }

    public Grafo clone() {
        Grafo clon = new Grafo();
        if (this.inicio != null) {
            // 1. Clonar todos los vértices primero manteniendo el orden
            clon.inicio = new NodoVert(this.inicio.getElem(), null, null);
            NodoVert aux = this.inicio.getSigVertice();
            NodoVert auxClon = clon.inicio;

            while (aux != null) {
                auxClon.setSigVertice(new NodoVert(aux.getElem(), null, null));
                auxClon = auxClon.getSigVertice();
                aux = aux.getSigVertice();
            }

            // 2. Clonar todos los arcos
            aux = this.inicio;
            auxClon = clon.inicio;
            while (aux != null) {
                NodoAdy ady = aux.getPrimerAdy();
                if (ady != null) {
                    NodoVert destinoClon = clon.ubicarVertice(ady.getVertice().getElem());
                    auxClon.setPrimerAdy(new NodoAdy(destinoClon, null, ady.getEtiqueta()));

                    NodoAdy adyClon = auxClon.getPrimerAdy();
                    ady = ady.getSigAdyacente();

                    while (ady != null) {
                        destinoClon = clon.ubicarVertice(ady.getVertice().getElem());
                        adyClon.setSigAdyacente(new NodoAdy(destinoClon, null, ady.getEtiqueta()));
                        adyClon = adyClon.getSigAdyacente();
                        ady = ady.getSigAdyacente();
                    }
                }
                aux = aux.getSigVertice();
                auxClon = auxClon.getSigVertice();
            }
        }
        return clon;
    }

    public String habitacionesContiguas(Object elem) {
        NodoVert nodoVert = ubicarVertice(elem);
        String str = "No existe";
        Lista adyacentes = new Lista();
        if (this.inicio != null) {
            if (nodoVert != null) {

                NodoAdy nodoAdy = nodoVert.getPrimerAdy();
                while (nodoAdy != null) {
                    adyacentes.insertar(nodoAdy.toString(), 1);
                    nodoAdy = nodoAdy.getSigAdyacente();
                }
            }
        }
        str = adyacentes.toString();
        return str;

    }

    public boolean esPosibleLlegar(Object origen, Object destino, int k) {
        boolean exito = false;
        NodoVert nodoO = ubicarVertice(origen);
        NodoVert nodoD = ubicarVertice(destino);
        // mientras existan esas habitaciones puedo averiguar el coste del camino
        if (nodoO != null && nodoD != null) {
            Lista visitados = new Lista();
            exito = esPosibleLlegarAux(nodoO, destino, k, 0, visitados);
        }

        return exito;
    }

    private boolean esPosibleLlegarAux(NodoVert n, Object dest, int k, int sumaActual, Lista visitados) {
        boolean exito = false;
        if (n != null) {
            if (n.getElem().equals(dest)) {
                // se llega aqui mientras k sea
                exito = true;
            } else {
                // marco nodo como visitado
                visitados.insertar(n.getElem(), visitados.longitud() + 1);
                NodoAdy ady = n.getPrimerAdy();
                while (!exito && ady != null) {
                    // comprueba q no este visitado el nodo a visitar
                    if (visitados.localizar(ady.getVertice().getElem()) < 0) {
                        int costo = (int) ady.getEtiqueta();
                        // entra si k me alcanza para llegar al nodo a visitar, en caso contrario corta
                        // el programa y no es posible llegar
                        if (sumaActual + costo <= k) {
                            exito = esPosibleLlegarAux(ady.getVertice(), dest, k, sumaActual + costo, visitados);
                        }

                    }
                    ady = ady.getSigAdyacente();
                }
                // backtracking
                visitados.eliminar(visitados.longitud());

            }
        }
        return exito;
    }

    public int minimoPuntaje(Object origen, Object destino) {
        NodoVert nodoO = ubicarVertice(origen);
        NodoVert nodoD = ubicarVertice(destino);
        int [] minCoste = {-1};
        if (nodoD != null && nodoO!= null) {
            Lista visitados = new Lista();
            minimoPuntajeAux(nodoO, destino, 0, visitados, minCoste);
        }
        return minCoste[0];
    }

    private void minimoPuntajeAux(NodoVert n, Object dest, int sumaActual, Lista visitados, int[] minCoste) {
        boolean flag = true;

        if (minCoste[0] != -1 && sumaActual >= minCoste[0]) {
            flag = false;
        }
        if (flag) {
            if (n.getElem().equals(dest)) {
                // llegue a destino
                if ((minCoste[0] == -1 || sumaActual < minCoste[0])) {
                    minCoste[0] = sumaActual;
                }
            } else {
                visitados.insertar(minCoste, visitados.longitud() + 1);
                NodoAdy ady = n.getPrimerAdy();

                while (ady != null) {
                    if (visitados.localizar(ady.getVertice().getElem()) < 0) {
                        int etiqueta = (int) ady.getEtiqueta();
                        minimoPuntajeAux(n.getSigVertice(), dest, sumaActual + etiqueta, visitados, minCoste);
                    }
                    ady = ady.getSigAdyacente();
                }
                visitados.eliminar(visitados.longitud());
            }
        }
    }
    public Lista sinPasarPor(Object hab1, Object hab2, Object hab3, int p) {
        Lista caminosExitosos = new Lista();
        NodoVert nodoO = ubicarVertice(hab1);
        NodoVert nodoD = ubicarVertice(hab2);
        // si hab3 es nula me va a dar todos los caminos posibles de hab1 a hab2
        if (nodoO != null && nodoD != null) {
            Lista caminoActual = new Lista();
            sinPasarPorAux(nodoO, hab2, hab3, p, 0, caminoActual, caminosExitosos);
        }
        return caminosExitosos;
    }

    private void sinPasarPorAux(NodoVert n, Object dest, Object hab3, int p, int sumaActual, Lista caminoActual,
            Lista caminosExitosos) {
        // si hab3 es nula nunca va a dar falso
        if (!n.getElem().equals(hab3)) {
            // marco nodo en caminoactual
            caminoActual.insertar(n.getElem(), caminoActual.longitud() + 1);

            if (n.getElem().equals(dest)) {
                // guardo el camino exitoso en la lista
                caminosExitosos.insertar(caminoActual.clone(), caminosExitosos.longitud() + 1);
            } else {
                // paso recursivo
                NodoAdy ady = n.getPrimerAdy();
                while (ady != null) {
                    // para evitar ciclos
                    if (caminoActual.localizar(ady.getVertice().getElem()) < 0) {
                        int costo = (int) ady.getEtiqueta();

                        if (sumaActual + costo <= p) {
                            sinPasarPorAux(ady.getVertice(), dest, hab3, p, sumaActual + costo, caminoActual,
                                    caminosExitosos);
                        }
                    }
                    ady = ady.getSigAdyacente();
                }
            }

        }
        // backtracking
        caminoActual.eliminar(caminoActual.longitud());
    }
}