package Estructuras.TablaAVL;

import java.util.HashMap;
import java.util.Map.Entry;

import Estructuras.EstructurasAux.*;
import Modelo.Desafio;
import Modelo.Equipo;
import Modelo.Habitacion;

public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    // --- MÉTODOS PÚBLICOS DEL TDA ---

    public boolean insertar(Comparable clave, Object dato) {
        boolean[] exito = { false };
        this.raiz = insertarPrivado(this.raiz, clave, dato, exito);
        return exito[0];
    }

    public boolean eliminar(Comparable clave) {
        boolean[] exito = { false };
        this.raiz = eliminarPrivado(this.raiz, clave, exito);
        return exito[0];
    }

    public boolean pertenece(Comparable elem) {
        boolean exito = false;
        if (this.raiz != null) {
            exito = perteneceaux(this.raiz, elem);
        }
        return exito;
    }

    public Object recuperar(Comparable clave) {
        return recuperarPrivado(this.raiz, clave);
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public Object minimoElem() {
        Object elemento = null;
        if (this.raiz != null) {
            NodoAVL actual = this.raiz;
            while (actual.getIzquierdo() != null) {
                actual = actual.getIzquierdo();
            }
            elemento = actual.getElem();
        }
        return elemento;
    }

    public Object maximoElem() {
        Object elemento = null;
        if (this.raiz != null) {
            NodoAVL actual = this.raiz;
            while (actual.getDerecho() != null) {
                actual = actual.getDerecho();
            }
            elemento = actual.getElem();
        }
        return elemento;
    }

    public Lista listar() {
        Lista listaResultado = new Lista();
        listarPrivado(this.raiz, listaResultado);
        return listaResultado;
    }

    public Lista listarRango(Comparable min, Comparable max) {
        Lista listaResultado = new Lista();
        listarRangoPrivado(this.raiz, min, max, listaResultado);
        return listaResultado;
    }

    public String toString() {
        String cadena = "Árbol AVL Vacío";
        if (this.raiz != null) {
            cadena = toStringPrivado(this.raiz);
        }
        return cadena;
    }

    public String toStringDesafio() {

        String cadena = "Sin Desafios";
        if (this.raiz != null) {
            cadena = toStringDesafioPrivado(this.raiz);
            cadena = cadena.substring(0, cadena.length() - 2);
        }
        return cadena;
    }

    // --- MÉTODOS PRIVADOS RECURSIVOS ---

    private NodoAVL insertarPrivado(NodoAVL nodoActual, Comparable clave, Object dato, boolean[] exito) {
        NodoAVL resultado = nodoActual;

        if (nodoActual == null) {
            resultado = new NodoAVL(clave, dato, null, null);
            exito[0] = true;
        } else {
            int comparacion = clave.compareTo(nodoActual.getClave());

            if (comparacion < 0) {
                nodoActual.setIzquierdo(insertarPrivado(nodoActual.getIzquierdo(), clave, dato, exito));
            } else if (comparacion > 0) {
                nodoActual.setDerecho(insertarPrivado(nodoActual.getDerecho(), clave, dato, exito));
            } else {
                exito[0] = false;
            }

            if (exito[0]) {
                actualizarAltura(nodoActual);
                resultado = balancear(nodoActual);
            }
        }
        return resultado;
    }

    private NodoAVL eliminarPrivado(NodoAVL nodoActual, Comparable clave, boolean[] exito) {
        NodoAVL resultado = nodoActual;

        if (nodoActual != null) {
            int comparacion = clave.compareTo(nodoActual.getClave());

            if (comparacion < 0) {
                nodoActual.setIzquierdo(eliminarPrivado(nodoActual.getIzquierdo(), clave, exito));
            } else if (comparacion > 0) {
                nodoActual.setDerecho(eliminarPrivado(nodoActual.getDerecho(), clave, exito));
            } else {
                exito[0] = true;

                if (nodoActual.getIzquierdo() == null || nodoActual.getDerecho() == null) {
                    resultado = (nodoActual.getIzquierdo() != null) ? nodoActual.getIzquierdo()
                            : nodoActual.getDerecho();
                } else {
                    NodoAVL sucesor = buscarNodoMinimo(nodoActual.getDerecho());
                    nodoActual.setClave(sucesor.getClave());
                    nodoActual.setElem(sucesor.getElem());

                    nodoActual.setDerecho(eliminarPrivado(nodoActual.getDerecho(), sucesor.getClave(), exito));
                    resultado = nodoActual;
                }
            }

            if (resultado != null) {
                actualizarAltura(resultado);
                resultado = balancear(resultado);
            }
        }
        return resultado;
    }

    private boolean perteneceaux(NodoAVL nodo, Comparable elem) {
        boolean esta = false;
        int comp = elem.compareTo(nodo.getClave());
        if (comp == 0) {
            esta = true;
        } else if (comp < 0) {
            // comparo si voy por izquierda o derecha
            if (nodo.getIzquierdo() != null) {
                esta = perteneceaux(nodo.getIzquierdo(), elem);
            }
        } else if (comp > 0) {
            // voy por rama derecha
            if (nodo.getDerecho() != null) {
                esta = perteneceaux(nodo.getDerecho(), elem);
            }
        }
        return esta;
    }

    private Object recuperarPrivado(NodoAVL nodoActual, Comparable clave) {
        Object encontrado = null;
        if (nodoActual != null) {
            int comparacion = clave.compareTo(nodoActual.getClave());
            if (comparacion == 0) {
                encontrado = nodoActual.getElem();
            } else if (comparacion < 0) {
                encontrado = recuperarPrivado(nodoActual.getIzquierdo(), clave);
            } else {
                encontrado = recuperarPrivado(nodoActual.getDerecho(), clave);
            }
        }
        return encontrado;
    }

    private void listarPrivado(NodoAVL nodoActual, Lista lista) {
        if (nodoActual != null) {
            listarPrivado(nodoActual.getIzquierdo(), lista);
            lista.insertar(nodoActual.getElem(), lista.longitud() + 1);
            listarPrivado(nodoActual.getDerecho(), lista);
        }
    }

    private void listarRangoPrivado(NodoAVL nodoActual, Comparable min, Comparable max, Lista lista) {
        if (nodoActual != null) {
            Comparable clave = nodoActual.getClave();

            if (clave.compareTo(min) > 0) {
                listarRangoPrivado(nodoActual.getIzquierdo(), min, max, lista);
            }

            if (clave.compareTo(min) >= 0 && clave.compareTo(max) <= 0) {
                lista.insertar(nodoActual.getElem(), lista.longitud() + 1);
            }

            if (clave.compareTo(max) < 0) {
                listarRangoPrivado(nodoActual.getDerecho(), min, max, lista);
            }
        }
    }

    private String toStringPrivado(NodoAVL nodoActual) {
        String cadena = "";
        if (nodoActual != null) {
            cadena += nodoActual.getClave().toString() + " (Alt: " + nodoActual.getAltura() + ")";
            Object hijoIzq = (nodoActual.getIzquierdo() != null) ? nodoActual.getIzquierdo().getClave() : "-";
            Object hijoDer = (nodoActual.getDerecho() != null) ? nodoActual.getDerecho().getClave() : "-";

            cadena += " HI: " + hijoIzq + " HD: " + hijoDer + "\n";
            cadena += toStringPrivado(nodoActual.getIzquierdo());
            cadena += toStringPrivado(nodoActual.getDerecho());
        }
        return cadena;
    }

    private String toStringDesafioPrivado(NodoAVL nodo) {
        String cadena = "";

        if (nodo != null) {
            cadena += toStringDesafioPrivado(nodo.getIzquierdo());

            cadena += ((Desafio) nodo.getElem()).toStringHabitacion() + ", ";

            cadena += toStringDesafioPrivado(nodo.getDerecho());
        }

        return cadena; // Devolvemos la cadena acumulada en este paso
    }

    // --- MÉTODOS DE BALANCEO Y ALTURA ---

    private NodoAVL balancear(NodoAVL nodoActual) {
        int balance = obtenerBalance(nodoActual);
        NodoAVL nuevaRaiz = nodoActual;

        // Desbalanceado a la Izquierda
        if (balance > 1) {
            if (obtenerBalance(nodoActual.getIzquierdo()) < 0) {
                nodoActual.setIzquierdo(rotarIzquierda(nodoActual.getIzquierdo()));
            }
            nuevaRaiz = rotarDerecha(nodoActual);
        }
        // Desbalanceado a la Derecha
        else if (balance < -1) {
            if (obtenerBalance(nodoActual.getDerecho()) > 0) {
                nodoActual.setDerecho(rotarDerecha(nodoActual.getDerecho()));
            }
            nuevaRaiz = rotarIzquierda(nodoActual);
        }
        return nuevaRaiz;
    }

    private NodoAVL rotarDerecha(NodoAVL nodoY) {
        NodoAVL nodoX = nodoY.getIzquierdo();
        NodoAVL hijoDerechoDeX = nodoX.getDerecho();

        nodoX.setDerecho(nodoY);
        nodoY.setIzquierdo(hijoDerechoDeX);

        actualizarAltura(nodoY);
        actualizarAltura(nodoX);

        return nodoX;
    }

    private NodoAVL rotarIzquierda(NodoAVL nodoX) {
        NodoAVL nodoY = nodoX.getDerecho();
        NodoAVL hijoIzquierdoDeY = nodoY.getIzquierdo();

        nodoY.setIzquierdo(nodoX);
        nodoX.setDerecho(hijoIzquierdoDeY);

        actualizarAltura(nodoX);
        actualizarAltura(nodoY);

        return nodoY;
    }

    private void actualizarAltura(NodoAVL nodoActual) {
        if (nodoActual != null) {
            nodoActual.setAltura(1 + Math.max(getAlt(nodoActual.getIzquierdo()), getAlt(nodoActual.getDerecho())));
        }
    }

    private int getAlt(NodoAVL nodoActual) {
        return (nodoActual != null) ? nodoActual.getAltura() : -1;
    }

    private int obtenerBalance(NodoAVL nodoActual) {
        return (nodoActual != null) ? getAlt(nodoActual.getIzquierdo()) - getAlt(nodoActual.getDerecho()) : 0;
    }

    private NodoAVL buscarNodoMinimo(NodoAVL nodoActual) {
        NodoAVL resultado = nodoActual;
        while (resultado.getIzquierdo() != null) {
            resultado = resultado.getIzquierdo();
        }
        return resultado;
    }

    //
    public String mostrarHabitacion(int codigo) {
        String str = "No existe";
        if (this.raiz != null) {
            Object dato = this.recuperar(codigo);
            if (dato != null) {
                str = dato.toString();
            }
        }
        return str;
    }

    public boolean modificarNombreDesafio(int[] datos, String nuevoNombre) {
        boolean exito = false;
        if (this.raiz != null) {
            Habitacion habitacion = (Habitacion) this.recuperar(datos[0]);
            if (habitacion != null) {
                Desafio desafio = (Desafio) habitacion.getDesafios().recuperar(datos[1]);
                if (desafio != null) {
                    desafio.setNombre(nuevoNombre);
                    exito = true;
                }
            }
        }
        return exito;
    }

    public boolean modificarTipoDesafio(int[] datos, String nuevoTipo) {
        boolean exito = false;
        if (this.raiz != null) {
            Habitacion habitacion = (Habitacion) this.recuperar(datos[0]);
            if (habitacion != null) {
                Desafio desafio = (Desafio) habitacion.getDesafios().recuperar(datos[1]);
                if (desafio != null) {
                    desafio.setTipo(nuevoTipo);
                    exito = true;
                }
            }
        }
        return exito;
    }

    public boolean modificarNombreHabitacion(int codigo, String nuevoNombre) {
        boolean exito = false;
        if (this.raiz != null) {
            Habitacion habitacion = (Habitacion) this.recuperar(codigo);
            if (habitacion != null) {
                habitacion.setNombre(nuevoNombre);
                exito = true;
            }
        }
        return exito;
    }

    public boolean modificarPlantaHabitacion(int codigo, int planta) {
        boolean exito = false;
        if (this.raiz != null) {
            Habitacion habitacion = (Habitacion) this.recuperar(codigo);
            if (habitacion != null) {
                habitacion.setPlanta(planta);
                exito = true;
            }
        }
        return exito;
    }

    public boolean modificarMedidaHabitacion(int codigo, int medida) {
        boolean exito = false;
        if (this.raiz != null) {
            Habitacion habitacion = (Habitacion) this.recuperar(codigo);
            if (habitacion != null) {
                habitacion.setMedida(medida);
                exito = true;
            }
        }
        return exito;
    }

    public boolean eliminarDesafio(int codigo, int puntaje) {
        boolean exito = false;
        if (this.raiz != null) {
            Habitacion habitacion = (Habitacion) this.recuperar(codigo);
            if (habitacion != null) {
                exito = habitacion.eliminarDesafio(puntaje);
            }
        }
        return exito;
    }

    public boolean perteneceDesafio(int codigo, int puntaje) {
        boolean exito = false;
        if (this.raiz != null) {
            Habitacion habitacion = (Habitacion) this.recuperar(codigo);
            if (habitacion != null) {
                exito = habitacion.getDesafios().pertenece(puntaje);
            }
        }
        return exito;
    }

    public boolean modificarNombreDesafio(int codigo, int puntaje, String nuevoNombre) {
        boolean exito = false;
        if (this.raiz != null) {
            Habitacion habitacion = (Habitacion) this.recuperar(codigo);
            if (habitacion != null) {
                Desafio desafio = (Desafio) habitacion.getDesafios().recuperar(puntaje);
                if (desafio != null) {
                    desafio.setNombre(nuevoNombre);
                    exito = true;
                }
            }
        }
        return exito;
    }

    public boolean modificarTipoDesafio(int codigo, int puntaje, String nuevoTipo) {
        boolean exito = false;
        if (this.raiz != null) {
            Habitacion habitacion = (Habitacion) this.recuperar(codigo);
            if (habitacion != null) {
                Desafio desafio = (Desafio) habitacion.getDesafios().recuperar(puntaje);
                if (desafio != null) {
                    desafio.setTipo(nuevoTipo);
                    exito = true;
                }
            }
        }
        return exito;
    }

    // metodos de mostrarDesafiosResueltos
    public String listarDesafiosHabitacion(Equipo eq) {
        String str = "{";
        if (eq != null) {

            HashMap<Integer, Lista> desafiosCompletados = eq.getDesafiosCompletados(); // mapa de desafios
            for (Entry<Integer, Lista> par : desafiosCompletados.entrySet()) {
                // recorro el hash, obtengo ambos valores
                int codigoHabitacion = par.getKey();
                Lista desafios = par.getValue();
                //clono la lista para usarla como recorrido
                Lista aux = desafios.clone();
                Habitacion hab = (Habitacion) recuperar(codigoHabitacion); // busco la habitacion
                ArbolAVL desafiosHab = hab.getDesafios(); // agarro el arbol de desafios de esa habitacion
                Lista desafiosComp = buscarDesafio(desafiosHab, aux);
                //concateno cada string resultante de buscarDesafio
                str = str + " Desafio: " + desafiosComp.toString();
            }
        }
        str = str + "}";
        return str;
    }

    private Lista buscarDesafio(ArbolAVL desafiosHab, Lista clavesDesafio) {
        Lista listaDesafiosCompletados = new Lista();
        while (!clavesDesafio.esVacia()) {
            int claveDesafio = (int) clavesDesafio.recuperar(1); // recupero la clave
            Desafio des = (Desafio) desafiosHab.recuperar(claveDesafio); //busco un desafio con esa clave en el AVL
            if (des != null) {
                listaDesafiosCompletados.insertar(des, 1);
            } else {
                listaDesafiosCompletados.insertar("no existe", 1); //esto es para testeo
            }
            clavesDesafio.eliminar(1); // borro la clave q ya use
        }
        return listaDesafiosCompletados;
    }

    public String mostrarDesafioAux(Comparable codigoDes, Comparable numHab){
        String str= "";
        boolean existe = this.pertenece(numHab);
        if (existe) {
            Habitacion hab = (Habitacion)this.recuperar(numHab);
            ArbolAVL desafiosHab = hab.getDesafios();
            if (!desafiosHab.esVacio()) {
                existe = desafiosHab.pertenece(codigoDes);
                if (existe) {
                    Desafio des = (Desafio )desafiosHab.recuperar(codigoDes);
                    str = des.toString();
                }else{
                    str = "No existe ese desafio";
                }
            }else{
                str = "La habitaciones no tiene desafios";
            }
        }else{
            str = "No existe esa habitacion";
        }
        
        return str;
    }

}