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
            System.out.println("========== MENU DE ABM ==========");
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
        
    //punto 3
    
    

        
        //punto 4



        //punto 5 consultas sobre habitaciones

    
}








