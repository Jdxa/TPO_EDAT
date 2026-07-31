package Menu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
//---------------------------
import Estructuras.GrafoEtiquetado.Grafo;
import Estructuras.TablaAVL.ArbolAVL;
//---------------------------
import Modelo.Equipo;
import Modelo.Habitacion;
import Modelo.Desafio;

public class CargarDatos {

    public void cargarDato(ArbolAVL unArbol, Grafo unGrafo, HashMap<String, Equipo> unHash) {
        String linea;
        try {
            BufferedReader bufferLectura = new BufferedReader(new FileReader("archivos\\"));
            
            while ((linea = bufferLectura.readLine()) != null) {

                switch (linea.charAt(0)) {
                    case 'P':
                        cargarPlano(linea,unGrafo);
                        break;
                    case 'H':
                        cargarHabitaciones(linea,unArbol);
                        break;
                    case 'D':
                        cargarDesafios(linea,unArbol);
                        break;
                    case 'E':
                        cargarEquipos(linea,unArbol,unHash);
                        break;
                }
            }
            
            bufferLectura.close();
            
        } catch(IOException error) {
            System.out.println("Error al leer el archivo: " + error.getMessage());
        }
    }

    private void cargarHabitaciones(String linea,ArbolAVL unArbol) {
        int unCodigo,unaPlanta,unaMedida;
        String unNombre;
        boolean tieneSalida;
        Habitacion carga;
        String[] partes = linea.split(";");
        
        unCodigo=Integer.parseInt(partes[1]);
        unNombre= partes[2];
        unaPlanta=Integer.parseInt(partes[3]);
        unaMedida=Integer.parseInt(partes[4]);
        tieneSalida=(partes[5].equals("true")); 
        carga= new Habitacion(unCodigo,unNombre,unaPlanta,unaMedida,tieneSalida);

        unArbol.insertar(unCodigo, carga);
    }

    private void cargarDesafios(String linea,ArbolAVL unArbol){
        int unPuntaje,unCodHab;
        String unNombre,unTipo;
        String[] partes = linea.split(";");
        Desafio carga;
        Habitacion habitacion;

        unPuntaje=Integer.parseInt(partes[1]);
        unCodHab= Integer.parseInt(partes[2]);
        unNombre=partes[3];
        unTipo=partes[4];

        carga = new Desafio(unPuntaje,unCodHab,unNombre,unTipo);
        
        habitacion=(Habitacion)unArbol.recuperar(unCodHab);
        habitacion.setDesafio(carga);
    }

    private void cargarEquipos(String Linea,ArbolAVL unArbol,HashMap<String, Equipo> unHash){
        String unNombre;
        int unPuntajeExig,unPuntajeAcum,unPuntajeAct,unCodHab;
        Habitacion unaHab;
        String[] partes = linea.split(";");
        Equipo carga;

        unNombre=partes[1];
        unPuntajeExig= Integer.parseInt(partes[2]);
        unPuntajeAcum=Integer.parseInt(partes[3]);
        unCodHab=Integer.parseInt(partes[4]);
        unPuntajeAct=Integer.parseInt(partes[5]);

        unaHab=(Habitacion)unArbol.recuperar(unCodHab);

        carga=new Equipo(unNombre,unPuntajeExig,unPuntajeAcum,unaHab,unPuntajeAct);
        
        unHash.put(unNombre, carga);
    }

    private void cargarPlano(String linea,Grafo unGrafo){
    int unInicio,unDestino,unPuntaje;
    String[] partes = linea.split(";");

    unInicio= Integer.parseInt(partes[1]);
    unDestino=Integer.parseInt(partes[2]);
    unPuntaje=Integer.parseInt(partes[3]);

    unGrafo.insertarArco(unInicio, unDestino, unPuntaje);
    }
}