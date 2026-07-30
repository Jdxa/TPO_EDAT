package Menu;
import java.util.Scanner;

public class main {
    //INTERFAZ DE USUARIO
   

public class MenuJuego {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcionPrincipal;

        do {
            // Mostrar menú principal
            System.out.println("\n========== MENÚ PRINCIPAL ==========");
            System.out.println("1. Carga inicial del sistema");
            System.out.println("2. Altas, Bajas y Modificaciones (ABM)");
            System.out.println("3. Consulta sobre habitaciones");
            System.out.println("4. Consultas sobre desafíos");
            System.out.println("0. Salir");
            System.out.print("Introduce un número para avanzar: ");
            
            opcionPrincipal = scanner.nextInt();

            switch (opcionPrincipal) {
                case 1:
                    System.out.println("\n--> Seleccionaste: Carga inicial del sistema (desde archivo de texto).");
                    // Acá irá tu lógica de carga inicial
                    break;
                    
                case 2:
                    System.out.println("\n--> Seleccionaste: Altas, Bajas y Modificaciones (ABM) de habitaciones, desafíos y equipos.");
                    // Acá irá tu lógica de ABM
                    break;
                    
                case 3:
                    System.out.println("\n--- 3. CONSULTA SOBRE HABITACIONES ---");
                    System.out.println("1. mostrarHabitación");
                    System.out.println("2. habitacionesContiguas");
                    System.out.println("3. esPosibleLlegar");
                    System.out.println("4. minimoPuntaje");
                    System.out.println("5. sinPasarPor");
                    System.out.print("Introduce un número para elegir una consulta: ");
                    
                    int opcionHabitaciones = scanner.nextInt();
                    
                    switch (opcionHabitaciones) {
                        case 1:
                            System.out.println("-> Ejecutando: mostrarHabitación...");
                            break;
                        case 2:
                            System.out.println("-> Ejecutando: habitacionesContiguas...");
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
                            System.out.println("Opción de habitación no válida.");
                            break;
                    }
                    break;
                    
                case 4:
                    System.out.println("\n--- 4. CONSULTAS SOBRE DESAFÍOS ---");
                    System.out.println("1. mostrarDesafío");
                    System.out.println("2. mostrarDesafíosResueltos");
                    System.out.println("3. verificarDesafíoResuelto");
                    System.out.println("4. mostrarDesafíosTipo");
                    System.out.print("Introduce un número para elegir una consulta: ");
                    
                    int opcionDesafios = scanner.nextInt();
                    
                    switch (opcionDesafios) {
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
                    System.out.println("\nSaliendo del sistema... ¡Nos vemos!");
                    break;
                    
                default:
                    System.out.println("\nNúmero no reconocido. Por favor, introduce una opción válida.");
                    break;
            }
        } while (opcionPrincipal != 0);

        scanner.close();
    }
}
}
