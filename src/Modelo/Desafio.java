package Modelo;

public class Desafio {
    private int puntajeOtorga;
    private int codHabitacion;
    private String nombre;
    private String tipo;
    
    public Desafio(int unPuntaje, int unCodigoHab, String unNombre, String unTipo){
        this.puntajeOtorga = unPuntaje;
        this.codHabitacion = unCodigoHab;
        this.nombre = unNombre;
        this.tipo = unTipo;
    }

    public int getPuntaje(){
        return this.puntajeOtorga;
    }

    public void setPuntaje(int unPuntaje){
        this.puntajeOtorga = unPuntaje;
    }
    
    public int getCodigoHabitacion(){
        return this.codHabitacion;
    }

    public void setCodigoHabitacion(int unCodigo){
        this.codHabitacion = unCodigo;
    }
    
    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String unNombre){
        this.nombre = unNombre;
    }
    
    public String getTipo(){
        return this.tipo;
    }

    public void setTipo(String unTipo){
        this.tipo = unTipo;
    }
}
