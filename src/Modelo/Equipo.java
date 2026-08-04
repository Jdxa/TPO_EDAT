package Modelo;
import java.util.HashMap;

import Estructuras.EstructurasAux.Lista;

public class Equipo {
    private String nombre;
    private int puntajeExigido;
    private int puntajeAcumulado;
    private int codigoHabitacionActual;
    private int puntajeActual;
    private HashMap<Integer, Lista> desafiosCompletados;


    public Equipo(String unNombre, int unPuntExigido, int unPuntAcumulado, int unaHabActual, int unPuntActual){
        this.nombre = unNombre;
        this.puntajeExigido = unPuntExigido;
        this.puntajeAcumulado = unPuntAcumulado;
        this.codigoHabitacionActual = unaHabActual;
        this.puntajeActual = unPuntActual;
        desafiosCompletados = new HashMap<Integer, Lista>();

    }
    //Nombre desafio
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String unNombre){
        this.nombre = unNombre;
    }
    //Puntaje Exigido
    public int getPuntajeExigido(){
        return this.puntajeExigido;
    }
    public void setPuntajeExigido(int unPuntExigido){
        this.puntajeExigido = unPuntExigido;
    }
    //Puntaje Acumulado
    public int getPuntajeAcumulado(){
        return this.puntajeAcumulado;
    }
    public void setPuntajeAcumulado(int unPuntAcumulado){
        this.puntajeAcumulado = unPuntAcumulado;
    }
    //Habitacion
     public int getCodigoHabitacionActual(){
        return this.codigoHabitacionActual;
    }
    public void setCodigoHabitacionActual(int unCodigo){
        this.codigoHabitacionActual = unCodigo;
    }
    //Puntaje Actual
     public int getPuntajeActual(){
        return this.puntajeActual;
    }
    public void setPuntajeActual(int unPuntajeActual){
        this.puntajeActual = unPuntajeActual;
    }

    public void cargarDesafiosRealizados(Integer codigo, Integer unDesafio){
       Lista l = this.desafiosCompletados.computeIfAbsent(codigo, k -> new Lista());
       l.insertar(unDesafio, 1);
    }
    public HashMap<Integer,Lista> getDesafiosCompletados(){
        return this.desafiosCompletados;
    }
    public String toString(){
        return "nombre: "+this.nombre+", puntajeExigido: "+this.puntajeExigido+", puntajeAcumulado: "+this.puntajeAcumulado+", codigo habitacion actual: "+this.codigoHabitacionActual+", puntajeActual: "+this.puntajeActual+", desafiosCompletados: "+this.desafiosCompletados.toString();
    }
}