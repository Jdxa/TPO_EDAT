package GrafoEtiquetado;
public class Grafo {
    
    // atributos
    private NodoVert inicio = null;
    public Grafo(){

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

}
