package GrafoEtiquetado;

import EstructurasAux.lineales.Lista;

public class Grafo {
    
    // atributos
    private NodoVert inicio = null;
    public Grafo(){
        this.inicio = null;
    }        
    

    private NodoVert ubicarVertice(Object buscado){
        //busca que buscado no exista, si existe no se puede insertar
        NodoVert aux = this.inicio;
        while (aux != null && !aux.getElem().equals(buscado)) {
            aux = aux.getSigVertice();
        }
        return aux;
    }

    public boolean insertarVertice(Object nuevoVertice){
        //no hay vertices repetidos
        boolean exito = false;
        NodoVert aux = this.ubicarVertice(nuevoVertice);
        if (aux == null) {
            // si no esta repetido lo inserta
            this.inicio = new NodoVert(nuevoVertice, this.inicio, null); // no conozco como se unen asique null
            exito = true;
        }
        return exito;
    }
    
    private NodoAdy ubicarEtiqueta(Object etiqueta, NodoVert vertice){
        NodoAdy aux = null;;
        if (vertice != null) {
            aux = vertice.getPrimerAdy();
            while (aux.getSigAdyacente()!= null && aux.getEtiqueta().equals(etiqueta)) {
                aux = aux.getSigAdyacente();
            }
        }
        return aux;
    }

    public boolean insertarArco(Object origen, Object destino, Object etiqueta){
        boolean exito= false;
        NodoVert origenVertice = ubicarVertice(origen);         //busco el vertice origen
        NodoVert destinoVertice = ubicarVertice(destino);       //busco el vertice destino
        //busco que exista el vertice
        if (origenVertice != null && destinoVertice != null) {
            NodoAdy nuevoArco = new NodoAdy(destinoVertice, null, etiqueta); // creo el arco
            NodoAdy ultimo = ubicarEtiqueta(etiqueta, origenVertice); //busco el ultimo arco del origen
            ultimo.setSigAdyacente(nuevoArco);  //al ultimo arco lo enlazo con el nuevo arco
            exito = true;

        }
        return exito;
    }
    //eliminarVertice
    public boolean eliminarVertice(Object vertice ){
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
            //desenlaza aux de la lista de vertices
            if (anterior == null) {
                this.inicio = aux.getSigVertice();
            }else{
                anterior.setSigVertice(aux.getSigVertice());
            }
            exito = true;
        }

        return exito;
    }
    public boolean eliminarArco(Object origen, Object destino){
        boolean exito = false;
        NodoVert origenVertice = ubicarVertice(origen);   //busco el vertice origen
        NodoVert destinoVertice = ubicarVertice(destino);  //busco el vertice destino
        if (origenVertice != null && destinoVertice != null) {  //busco que existan los vertices
            NodoAdy aux = origenVertice.getPrimerAdy();  //busco el primer arco del vertice origen
            NodoAdy anterior = null;
            while (aux != null && !exito) {     //corta cuando encuentra el arco o cuando no hay mas arcos
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

    public boolean existeVertice(Object buscado){
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

    public boolean existeArco(Object origen, Object destino){
        boolean exito = false;
        NodoVert origenVertice = ubicarVertice(origen);   //busco el vertice origen
        NodoVert destinoVertice = ubicarVertice(destino);  //busco el vertice destino
        if (origenVertice != null && destinoVertice != null) {  //busco que existan los vertices
            NodoAdy aux = origenVertice.getPrimerAdy();  //busco el primer arco del vertice origen
            while (aux != null && !exito) {     //corta cuando encuentra el arco o cuando no hay mas arcos
                if (aux.getVertice().getElem().equals(destino)) {
                    exito = true;
                }
                aux = aux.getSigAdyacente();
            }
        }
        return exito;
    }

    
    public boolean existeCamino(Object origen, Object destino){
        boolean exito = false;
        NodoVert auxO = null;
        NodoVert auxD = null;
        NodoVert aux = this.inicio;
        while ((auxO == null || auxD == null)&& aux != null) {
            if (aux.getElem().equals(origen)) auxO = aux;
            if (aux.getElem().equals(destino)) auxD = aux;
            aux = aux.getSigVertice();
        }
        if (auxO != null && auxD != null) {
            Lista visitados = new Lista();
            exito = existeCaminoAux(auxO, destino, visitados);
        }

        return exito;
    }

    private boolean existeCaminoAux(NodoVert n, Object dest, Lista vis){
        boolean exito = false;
        if (n != null) {
            if (n.getElem().equals(dest)) {
                exito = true;
            }else{
                vis.insertar(n.getElem(), vis.longitud()+1);
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
    /*
    caminoMasCorto
    caminoMasLargo
    listarEnProfundidad
    listarEnAnchura
    esVacio                 X 
    clone
    */
   public boolean esVacio(){
        boolean exito= false;
        if (this.inicio == null) exito = true;
        return exito;
   }
}
