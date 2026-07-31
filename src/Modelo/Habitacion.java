package Modelo;
import Estructuras.TablaAVL.ArbolAVL;

public class Habitacion {
    private int codigo;
    private String nombre;
    private int planta;
    private int metrosCuadrados;
    private boolean tieneSalida;
    private ArbolAVL desafios;

    public Habitacion(int unCodigo, String unNombre,int unaPlanta,int unaMedida,boolean unTieneSalida){
        this.codigo = unCodigo;
        this.nombre = unNombre;
        this.planta = unaPlanta;
        this.metrosCuadrados = unaMedida;
        this.tieneSalida = unTieneSalida;
        this.desafios= new ArbolAVL();

    }
    public int getCodigo(){
        return this.codigo;
    }

    public void setCodigo(int unCod){
        this.codigo = unCod;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String unNom){
        this.nombre = unNom;
    }

    public int getPlanta(){
        return this.planta;
    }

    public void setPlanta(int unaPlanta){
        this.planta = unaPlanta;
    }

    public int getMedida(){
        return this.metrosCuadrados;
    }
    public void setMedida(int unaMedida){
        this.metrosCuadrados = unaMedida;
    }

    public boolean getTieneSalida(){
        return this.tieneSalida;
    }

    public void setTieneSalida(boolean unaSalida){
        this.tieneSalida = unaSalida;
    }

    public boolean cargarDesafio(Desafio unDesafio){
        return desafios.insertar(unDesafio.getPuntaje(),unDesafio);
    }

    public String toString(){
        String str= "";
        str = "codigo: "+this.codigo+", nombre: "+this.nombre+", planta: "+this.planta+", metrosCuadrados: "+this.metrosCuadrados+", tieneSalida: "+this.tieneSalida+", desafios: "+this.desafios.toString();
        return str;
    }
}
