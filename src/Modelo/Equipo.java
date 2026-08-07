package Modelo;
import Estructuras.Lineales.Lista;
import java.util.HashMap;

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
        desafiosCompletados = new HashMap<Integer,Lista>(); 

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
    public void actualizarPuntajes(){
        actualizarPuntajeAcumulado();
        actualizarPuntajeActual();

    }
    private void actualizarPuntajeAcumulado(){
        int acu;
        acu=0;
        for (Lista l : this.desafiosCompletados.values()) {
            if (l != null) {
                // Recorremos la lista desde la posición 1 hasta su longitud total
                int longitud = l.longitud();
                for (int i = 1; i <= longitud; i++) {
                    acu += (Integer) l.recuperar(i);
                }
            }
        }
        this.puntajeAcumulado = acu;
    }
    public void actualizarPuntajeActual(){
        int num=0;
        if(this.desafiosCompletados.get(this.codigoHabitacionActual)!=null){
        Lista l=this.desafiosCompletados.get(this.codigoHabitacionActual);

        int longitud = l.longitud();
        for (int i = 1; i <= longitud; i++) {
        num += (Integer) l.recuperar(i);
        }
    }
        this.puntajeActual=num;
    }

    @Override
    public String toString(){
        return "nombre: "+this.nombre+", puntajeExigido: "+this.puntajeExigido+", puntajeAcumulado: "+this.puntajeAcumulado+", codigo habitacion actual: "+this.codigoHabitacionActual+", puntajeActual: "+this.puntajeActual+", desafiosCompletados: "+this.desafiosCompletados.toString();
    }
    
}