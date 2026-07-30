package Modelo;
import Estructuras.TablaAVL.ArbolAVL;

public class Habitacion {
    private int codigo;
    private String nombre;
    private int planta;
    private int metrosCuadrados;
    private boolean tieneSalida;
    private ArbolAVL desafios;

    public Habitacion(int unCodigo,String unNombre,int unaPlanta,int unaMedida,boolean unTieneSalida){
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
    public void setDesafio(unDesafio desafio){
        this.desafios.listar()
    }
}
