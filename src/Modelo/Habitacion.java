package Modelo;

public class Habitacion {
    private int codigo;
    private String nombre;
    private int planta;
    private int metrosCuadrados;
    private boolean tieneSalida;

    public Habitacion(int unCodigo, String unNombre,int unaPlanta,int unaMedida,boolean unTieneSalida){
        this.codigo = unCodigo;
        this.nombre = unNombre;
        this.planta = unaPlanta;
        this.metrosCuadrados = unaMedida;
        this.tieneSalida = unTieneSalida;
    }
    public int getCodigo(){
        return this.codigo;
    }
}
