package GrafoEtiquetado;
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
            this.inicio = new NodoVert(nuevoVertice, aux, null); // no conozco como se unen asique null
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

        }
        return exito;
    }

}
