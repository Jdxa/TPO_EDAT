package Menu;
import java.util.HashMap;
import java.util.Scanner;

import Estructuras.GrafoEtiquetado.Grafo;
import Estructuras.TablaAVL.ArbolAVL;
import Modelo.Equipo;

public class main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numMenu;
        int[] datosMenu = new int[4];
        String palabraMenu,linea;
        CargarDatos archivo = new CargarDatos();
        Grafo planoCasa= new Grafo();
        ArbolAVL habitaciones=new ArbolAVL();
        HashMap<String, Equipo> equipo = new HashMap<>();
        
        Boolean estaCargado = false;
        
        do {
            mostrarMenu();
            numMenu=scanner.nextInt();


            switch (numMenu) {
                case 1:
                    System.out.println("Seleccionaste: Carga inicial del sistema (desde archivo de texto).");
                    if(estaCargado){
                        System.out.println("Los datos ya fueron cargados previamente. No se puede cargar nuevamente.");
                        break;
                    }else{
                        archivo.cargarDato(habitaciones,planoCasa,equipo);
                        System.out.println(habitaciones.toString());
                        estaCargado = true;
                    }
                    
                    break;
                    
                case 2:
                    System.out.println("Seleccionaste: Altas, Bajas y Modificaciones (ABM) de habitaciones, desafíos y equipos.");
                    mostrarMenu2();
                    numMenu=scanner.nextInt();
                    switch (numMenu) {
                        case 1:
                            System.out.println("--> crear de habitacion");
                            System.out.println("Ingrese el código que le quiere dar a la habitación:");
                            numMenu = scanner.nextInt();
                            while(habitaciones.pertenece(numMenu)){
                                System.out.println("El código ingresado ya existe. Por favor, ingrese un código diferente:");
                                numMenu = scanner.nextInt();
                            }
                            linea = "H;" + numMenu;
                            System.out.println("Ingrese el nombre de la habitación:");
                            palabraMenu = scanner.next();
                            linea += ";" + palabraMenu;
                            System.out.println("Ingrese la planta de la habitación:");
                            numMenu = scanner.nextInt();
                            linea += ";" + numMenu;
                            System.out.println("Ingrese la medida de la habitación:");
                            numMenu= scanner.nextInt();
                            linea += ";" + numMenu+"false";
                            archivo.cargarHabitaciones(linea, habitaciones);


                            break;
                        case 2:
                            System.out.println("--> Baja de habitacion");
                            System.out.println("Ingrese el código de la habitación a eliminar:");
                            numMenu = scanner.nextInt();
                            //Llamo a metodo de AVL que elimina la habitacion y me devuelve un boolean
                            if(habitaciones.eliminar(numMenu)){
                                System.out.println("Habitación eliminada correctamente.");
                            } else {
                                System.out.println("No se encontró la habitación con el código ingresado.");
                            }

                            break;
                        case 3:
                            System.out.println("--> Modificación de habitación");
                            System.out.println("Ingrese el código de la habitación a modificar:");
                            datosMenu[0] = scanner.nextInt();
                            if(!habitaciones.pertenece(datosMenu[0])){
                                System.out.println("No se encontró la habitación con el código ingresado. Por favor, ingrese un código válido:");
                            }else{
                            mostrarMenu2_3();
                            System.out.print("Introduce un numero para elegir una modificación: ");
                            numMenu = scanner.nextInt();
                            switch (numMenu) {
                                case 1:
                                    System.out.println("Ingrese el nuevo nombre de la habitación:");
                                    palabraMenu = scanner.next();
                                    //Llamo a metodo de AVL que modifica el nombre de la habitacion y me devuelve un boolean
                                    if(habitaciones.modificarNombreHabitacion(datosMenu[0], palabraMenu)){
                                        System.out.println("Nombre de la habitación modificado correctamente.");
                                    }
                                    break;
                                case 2:
                                    System.out.println("Ingrese la nueva planta de la habitación:");
                                    datosMenu[1] = scanner.nextInt();
                                    //Llamo a metodo de AVL que modifica la planta de la habitacion y me devuelve un boolean
                                    if(habitaciones.modificarPlantaHabitacion(datosMenu)){
                                        System.out.println("Planta de la habitación modificada correctamente.");
                                    } else {
                                        System.out.println("No se encontró la habitación con el código ingresado.");
                                    }
                                    break;
                                case 3:
                                    System.out.println("Ingrese la nueva medida de la habitación:");
                                    datosMenu[1] = scanner.nextInt();
                                    //Llamo a metodo de AVL que modifica la medida de la habitacion y me devuelve un boolean
                                    if(habitaciones.modificarMedidaHabitacion(datosMenu)){
                                        System.out.println("Medida de la habitación modificada correctamente.");
                                    } else {
                                        System.out.println("No se encontró la habitación con el código ingresado.");
                                    }
                                    break;
                                
                                case 4:
                                    System.out.println("Saliendo de la modificación de habitación...");
                                    break;
                            
                                default:
                                    System.out.println("Opción no válida. Saliendo de la modificación de habitación...");
                                    break;
                            }
                            }
                            break;
                        case 4:
                            System.out.println("--> Crear de desafío");

                            break;
                        case 5:
                            System.out.println("--> Baja de desafío");
                            System.out.println("Ingrese el código del la habitacion del desafio aeliminar:");
                            datosMenu[0] = scanner.nextInt();
                            System.out.println("Ingrese el puntaje del desafio a eliminar:");
                            datosMenu[1] = scanner.nextInt();

                            if(habitaciones.eliminarDesafio(datosMenu)){
                                System.out.println("Desafío eliminado correctamente.");
                            } else {
                                System.out.println("No se encontró el desafío con los datos ingresados.");
                            }
                            break;
                        case 6:
                            System.out.println("--> Modificacion de desafío");
                            System.out.println("Ingrese el código del la habitacion del desafio a modificar:");
                            datosDesafio[0] = scanner.nextInt();
                            System.out.println("Ingrese el puntaje del desafio a modificar:");
                            datosDesafio[1] = scanner.nextInt();

                            mostrarMenu2_6();
                            numMenu = scanner.nextInt();
                            switch (numMenu) {
                                case 1:
                                    System.out.println("Ingrese el nuevo nombre del desafío:");
                                    String nuevoNombre = scanner.next();
                                    //Llamo a metodo de AVL que modifica el nombre del desafio y me devuelve un boolean
                                    if(habitaciones.modificarNombreDesafio(datosDesafio, nuevoNombre)){
                                        System.out.println("Nombre del desafío modificado correctamente.");
                                    } else {
                                        System.out.println("No se encontró el desafío con los datos ingresados.");
                                    }
                                    break;
                                case 2:
                                    System.out.println("Ingrese el nuevo tipo del desafío:");
                                    String nuevoTipo = scanner.next();
                                    //Llamo a metodo de AVL que modifica el tipo del desafio y me devuelve un boolean
                                    if(habitaciones.modificarTipoDesafio(datosDesafio, nuevoTipo)){
                                        System.out.println("Tipo del desafío modificado correctamente.");
                                    } else {
                                        System.out.println("No se encontró el desafío con los datos ingresados.");
                                    }
                                    break;
                                case 3:
                                    System.out.println("Saliendo de la modificación de desafío...");
                                    break;
                                default:
                                    System.out.println("Opción no válida. Saliendo de la modificación de desafío...");
                            }
                            break;
                        case 7:
                            System.out.println("--> Crear de equipo");

                            break;
                        case 8:
                            System.out.println("--> Baja de equipo");

                            break;
                        case 9:
                            System.out.println("--> Modificacion de equipo");

                            break;
                    break;
                    
                case 3:
                    mostrarMenuHabitacion();
                    numMenu = scanner.nextInt();
                    
                    switch (numMenu) {
                        case 1:
                            System.out.println("-> Ejecutando: mostrarHabitación...");
                            System.out.println("Ingrese un numero de habitacion:");
                            numMenu = scanner.nextInt();
                            //Llamo a metodo de AVL que me da un String de los datos de la habitacion encontrada
                            System.out.println(habitaciones.mostrarHabitacion(numMenu));
                            break;
                        case 2:
                            System.out.println("-> Ejecutando: habitacionesContiguas...");
                            System.out.println("Ingrese un numero de habitacion:");
                            numMenu = scanner.nextInt();
                            //llamo metodo de Grafo que me da un String de las habitaciones contiguas
                            System.out.println(planoCasa.habitacionesContiguas(numMenu));
                            break;
                        case 3:
                            System.out.println("-> Ejecutando: esPosibleLlegar...");
                            break;
                        case 4:
                            System.out.println("-> Ejecutando: minimoPuntaje...");
                            break;
                        case 5:
                            System.out.println("-> Ejecutando: sinPasarPor...");
                            break;
                        default:
                            System.out.println("Opción de habitación no válida.");break;
                    }
                    break;
                    
                case 4:
                    mostrarMenuDesafio();
                    numMenu = scanner.nextInt();
                    
                    switch (numMenu) {
                        case 1:
                            System.out.println("-> Ejecutando: mostrarDesafío...");
                            break;
                        case 2:
                            System.out.println("-> Ejecutando: mostrarDesafíosResueltos...");
                            break;
                        case 3:
                            System.out.println("-> Ejecutando: verificarDesafíoResuelto...");
                            break;
                        case 4:
                            System.out.println("-> Ejecutando: mostrarDesafíosTipo...");
                            break;
                        default:
                            System.out.println("Opción de desafío no válida.");
                            break;
                    }
                    break;
                    
                case 0:
                    System.out.println("Saliendo del sistema... ¡Nos vemos!");
                    break;
                    
                default:
                    System.out.println("Número no reconocido. Por favor, introduce una opción válida.");
                    break;
            }
        } while (numMenu != 0);

        scanner.close();

    }
        public static void mostrarMenu(){
            System.out.println("========== MENU PRINCIPAL ==========");
            System.out.println("1. Carga inicial del sistema");
            System.out.println("2. Altas, Bajas y Modificaciones (ABM)");
            System.out.println("3. Consulta sobre habitaciones");
            System.out.println("4. Consultas sobre desafíos");
            System.out.println("0. Salir");
            System.out.print("Introduce un número para avanzar: ");
        }
        public static void mostrarMenu2(){
            System.out.println("2. MENU DE ABM");
            System.out.println("1. crear de habitacion");
            System.out.println("2. Baja de habitacion");
            System.out.println("3. Modificación de habitación");
            System.out.println("4. Alta de desafío");
            System.out.println("5. Baja de desafío");
            System.out.println("6. Modificacion de desafío");
            System.out.println("7. Alta de equipo");
            System.out.println("8. Baja de equipo");
            System.out.println("9. Modificacion de equipo");
            System.out.println("10. Salir");
            System.out.print("Introduce un numero para avanzar: ");
        }
        public static void mostrarMenuHabitacion(){
            System.out.println("3. CONSULTA SOBRE HABITACIONES");
                    System.out.println("1. mostrar habitacion");
                    System.out.println("2. mostrar habitaciones contiguas");
                    System.out.println("3. es posible llegar de habitacion A a habitacion B");
                    System.out.println("4. cual es el minimo puntaje para ir de habitacion A a habitacion B");
                    System.out.println("5. cuales son las formas de ir a de habitacion A a habitacion B sin pasar por la habitacion C");
                    System.out.println("6. Salir");
                    System.out.print("Introduce un numero para elegir una consulta: ");
        }
        public static void mostrarMenuDesafio(){
                    System.out.println("--- 4. CONSULTAS SOBRE DESAFÍOS ---");
                    System.out.println("1. mostrarDesafio");
                    System.out.println("2. mostrarDesafiosResueltos");
                    System.out.println("3. verificarDesafioResuelto");
                    System.out.println("4. mostrarDesafiosTipo");
                    System.out.println("5. Salir");
                    System.out.print("Introduce un numero para elegir una consulta: ");
        }
        public static void mostrarMenu2_3(){
            System.out.println("2_3. MODIFICACION DE HABITACIÓN");
            System.out.println("1. Modificar nombre de la habitación");
            System.out.println("2. Modificar planta de la habitación");
            System.out.println("3. Modificar medida de la habitación");
            System.out.println("4. Salir");
            System.out.print("Introduce un numero para elegir una modificación: ");
        }
        public static void mostrarMenu2_6(){
            System.out.println("2_6. MODIFICACION DE DESAFÍO");
            System.out.println("1. Modificar nombre del desafío");
            System.out.println("2. Modificar tipo del desafío");
            System.out.println("3. Salir");
            System.out.print("Introduce un numero para elegir una modificación: ");
        }
    
    //punto 3
    
    

        
        //punto 4



        //punto 5 consultas sobre habitaciones

    
}








