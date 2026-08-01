package Menu;
import java.util.HashMap;
import java.util.Scanner;

import Estructuras.EstructurasAux.Lista;
import Estructuras.GrafoEtiquetado.Grafo;
import Estructuras.TablaAVL.ArbolAVL;
import Modelo.*;

public class main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numMenu,numMenuHabitacion,numMenuDesafio,codigo;
        Grafo planoCasa= new Grafo();
        ArbolAVL habitaciones=new ArbolAVL();
        HashMap<String, Equipo> equipo = new HashMap<>();
        
        
        do {
            mostrarMenu();
            numMenu=scanner.nextInt();


            switch (numMenu) {
                case 1:
                    System.out.println("Seleccionaste: Carga inicial del sistema (desde archivo de texto).");
                    // cargarDatos();
                    break;
                    
                case 2:
                    System.out.println("Seleccionaste: Altas, Bajas y Modificaciones (ABM) de habitaciones, desafíos y equipos.");
                    
                    break;
                    
                case 3:
                    mostrarMenuHabitacion();
                    numMenuHabitacion = scanner.nextInt();
                    
                    switch (numMenuHabitacion) {
                        case 1:
                            System.out.println("-> Ejecutando: mostrarHabitación...");
                            System.out.println("Ingrese un numero de habitacion:");
                            codigo = scanner.nextInt();
                            //Llamo a metodo de AVL que me da un String de los datos de la habitacion encontrada
                            System.out.println(habitaciones.mostrarHabitacion(numMenuHabitacion));
                            break;
                        case 2:
                            System.out.println("-> Ejecutando: habitacionesContiguas...");
                            System.out.println("Ingrese un numero de habitacion:");
                            codigo = scanner.nextInt();
                            //llamo metodo de Grafo que me da un String de las habitaciones contiguas
                            System.out.println(planoCasa.habitacionesContiguas(codigo));
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
                    numMenuDesafio = scanner.nextInt();
                    
                    switch (numMenuDesafio) {
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
        public static void mostrarMenuHabitacion(){
            System.out.println("3. CONSULTA SOBRE HABITACIONES");
                    System.out.println("1. mostrarHabitación");
                    System.out.println("2. habitacionesContiguas");
                    System.out.println("3. esPosibleLlegar");
                    System.out.println("4. minimoPuntaje");
                    System.out.println("5. sinPasarPor");
                    System.out.print("Introduce un número para elegir una consulta: ");
        }
        public static void mostrarMenuDesafio(){
                    System.out.println("--- 4. CONSULTAS SOBRE DESAFÍOS ---");
                    System.out.println("1. mostrarDesafío");
                    System.out.println("2. mostrarDesafíosResueltos");
                    System.out.println("3. verificarDesafíoResuelto");
                    System.out.println("4. mostrarDesafíosTipo");
                    System.out.print("Introduce un número para elegir una consulta: ");
        }
    //punto 3
    
    

        
        //punto 4



        //punto 5 consultas sobre habitaciones

    
}




