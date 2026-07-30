// package Menu;
// import Modelo.*;
// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;

// import Estructuras.TablaAVL.ArbolAVL;

// public class cargarDatos {

//       public void cargarHabitaciones(ArbolAVL unArbol) {
//       String linea,unNombre;
//       int unCodigo,unaPlanta,unaMedida;
//       boolean unTieneSalida;
//       try{
//       BufferedReader bufferLectura = new BufferedReader(new FileReader(/*a definir */));
//           while ((linea = bufferLectura.readLine()) != null) {
//                 String[] partes = linea.split(";");
//                 unCodigo=Integer.parseInt(partes[0]);
//                 unNombre=partes[1];//Integer.parseInt(textoNumero)
//                 unaPlanta= Integer.parseInt(partes[2]);
//                 unaMedida=Integer.parseInt(partes[3]);
//                 unTieneSalida=(partes[4]=="true");
//                 Habitacion carga= new Habitacion(unCodigo,unNombre,unaPlanta,unaMedida,unTieneSalida);
//                 bufferLectura.close();        
//             }
//         }catch(IOException error) {
//             System.out.println("Error al leer el archivo: " + error.getMessage());
//         }
//     }

//     public void cargarDesafios(ArbolAVL unArbol) {
//       String linea,unNombre;
//       int unCodigo,unaPlanta,unaMedida;
//       boolean unTieneSalida;
//       Habitacion carga;
//       try{
//       BufferedReader bufferLectura = new BufferedReader(new FileReader(/*a definir */));
//           while ((linea = bufferLectura.readLine()) != null) {
//                 String[] partes = linea.split(";");
//                 unCodigo=Integer.parseInt(partes[0]);
//                 unNombre=partes[1];//Integer.parseInt(textoNumero)
//                 unaPlanta= Integer.parseInt(partes[2]);
//                 unaMedida=Integer.parseInt(partes[3]);
//                 unTieneSalida=(partes[4]=="true");
//                 carga= new Habitacion(unCodigo,unNombre,unaPlanta,unaMedida,unTieneSalida);
//                 bufferLectura.close();   
//             }
//         }catch(IOException error) {
//             System.out.println("Error al leer el archivo: " + error.getMessage());
//         }
//     }
// }
