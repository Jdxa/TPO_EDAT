package Sistema;

import Estructuras.GrafoEtiquetado.Grafo;
import Estructuras.TablaAVL.ArbolAVL;
import Modelo.*;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class GestorArchivo {

    public static void cargarDatos(ArbolAVL unArbol, Grafo unGrafo, HashMap<String, Equipo> unHash) {
        String linea;
        try {
            BufferedReader bufferLectura = new BufferedReader(new FileReader("archivoTxt/setCargaEscapeRoom.txt"));

            while ((linea = bufferLectura.readLine()) != null) {

                switch (linea.charAt(0)) {
                    case 'P':
                        cargarPlano(linea, unGrafo);
                        break;
                    case 'H':
                        cargarHabitaciones(linea, unArbol,unGrafo);
                        break;
                    case 'D':
                        cargarDesafios(linea,unArbol);
                        break;
                    case 'E':
                        cargarEquipos(linea, unArbol, unHash);
                        break;
                }
            }
            
            bufferLectura.close();
            
        } catch(IOException error) {
            System.out.println("Error al leer el archivo: " + error.getMessage());
        }
    }
    
    public static void cargarDatoLinea(String linea, ArbolAVL unArbol, Grafo unGrafo, HashMap<String, Equipo> unHash) {
        switch (linea.charAt(0)) {
            case 'P':
                cargarPlano(linea, unGrafo);
                break;
            case 'H':
                cargarHabitaciones(linea, unArbol,unGrafo);
                break;
            case 'D':
                cargarDesafios(linea, unArbol);
                break;
            case 'E':
                cargarEquipos(linea, unArbol, unHash);
                break;
        }
    }

    private static void cargarDesafios(String linea, ArbolAVL unArbol) {
        int unPuntaje, unCodHab;
        String unNombre, unTipo;
        String[] partes = linea.split(";");
        Desafio carga;
        Habitacion habitacion;

        unPuntaje = Integer.parseInt(partes[1]);
        unCodHab = Integer.parseInt(partes[2]);
        unNombre = partes[3];
        unTipo = partes[4];

        carga = new Desafio(unPuntaje, unCodHab, unNombre, unTipo);

        habitacion = (Habitacion) unArbol.recuperar(unCodHab);
        habitacion.cargarDesafio(carga);
    }

    private static void cargarHabitaciones(String linea,ArbolAVL unArbol,Grafo unPlano) {
        int unCodigo,unaPlanta,unaMedida;
        String unNombre;
        boolean tieneSalida;
        Habitacion carga;
        String[] partes = linea.split(";");

        unCodigo = Integer.parseInt(partes[1]);
        unNombre = partes[2];
        unaPlanta = Integer.parseInt(partes[3]);
        unaMedida = Integer.parseInt(partes[4]);
        tieneSalida = (partes[5].equals("true"));
        carga = new Habitacion(unCodigo, unNombre, unaPlanta, unaMedida, tieneSalida);
        unPlano.insertarVertice(unCodigo);

        unArbol.insertar(unCodigo, carga);
    }


    private static void cargarEquipos(String linea,ArbolAVL habitaciones,HashMap<String, Equipo> hashEquipos){
        String unNombre;
        int unPuntajeExig, unPuntajeAcum, unPuntajeAct, unCodHab;
        Equipo nuevoEquipo;
        // separo la linea en segmentos
        String[] partes = linea.split(";");

        //obtengo los primeros valores
        unNombre=partes[1];
        unPuntajeExig= Integer.parseInt(partes[2]);
        unPuntajeAcum=Integer.parseInt(partes[3]);
        unCodHab=Integer.parseInt(partes[4]);
        unPuntajeAct=Integer.parseInt(partes[5]);

        nuevoEquipo=new Equipo(unNombre,unPuntajeExig,unPuntajeAcum,unCodHab,unPuntajeAct);
        //para la lista de desafios resueltos, de forma (codigoHabitacion, puntajeDesafio)
        
        for(int i = 6; i < partes.length; i++ ){
            String texto = partes[i];
            texto = texto.replace("(", "").replace(")", "");
            String[] separados = texto.split(",");
            Integer codigoHabitacion = Integer.parseInt(separados[0].trim());
            Integer puntajeDesafio = Integer.parseInt(separados[1].trim());

            //cargo el codigo de la habitacion y el puntaje del desafio
            nuevoEquipo.cargarDesafiosRealizados(codigoHabitacion,puntajeDesafio ); 
            
        }
        //guardo el equipo en el hash de equipos
        hashEquipos.put(unNombre, nuevoEquipo);
    }

    private static void cargarPlano(String linea,Grafo unGrafo){
        int unInicio,unDestino,unPuntaje;
        String[] partes = linea.split(";");

        unInicio= Integer.parseInt(partes[1]);
        unDestino=Integer.parseInt(partes[2]);
        unPuntaje=Integer.parseInt(partes[3]);
        unGrafo.insertarArco(unInicio, unDestino, unPuntaje);
    }
    
    public static void registrarLog(String mensaje){
        //uso el try-with-resource y cierra autimaticamente el archivo
        try(PrintWriter salida = new PrintWriter(new FileOutputStream("archivoTxt/log.txt",true))){
            salida.println(mensaje);
        }catch(IOException e){
            System.out.println("Ocurrio un error al escribir el log"+ e.getMessage());
        }
    }

    public static void iniciarLog(){
         try (PrintWriter salida = new PrintWriter(new FileOutputStream("archivoTxt/log.txt"))){
             LocalDateTime ahora = LocalDateTime.now();
             DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm:ss");
             String fechaHora = ahora.format(formato);
             salida.println("========== INICIO DEL PROGRAMA: "+fechaHora+" ==========");
         } catch (Exception e) {
            System.out.println("Ocurrio un error al escribir el log"+e.getMessage());
         }
    }

}
