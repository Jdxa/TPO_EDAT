package Sistema;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {

        Logica EscapeRoom=new Logica();
        Scanner sc = new Scanner(System.in);
        int numMenu;

        do {
            mostrarMenu();
            numMenu = sc.nextInt();

            switch (numMenu) {
                case 1:
                    System.out.println("Seleccionaste: Carga inicial del sistema.");
                    System.out.println(EscapeRoom.cargarScapeRoom());

                    break;

                case 2:
                    System.out.println("Seleccionaste: Altas, Bajas y Modificaciones (ABM) de habitaciones, desafíos y equipos.");
                    gestionABM(sc,EscapeRoom);
                    break;

                case 3:
                    System.out.println("Seleccionaste: Consultas sobre habitaciones");
                    gestionConsultaHabitacion(sc,EscapeRoom);

                    break;

                case 4:
                    System.out.println("Seleccionaste: Consultas sobre desafíos");
                    gestionConsultaDesafios(sc,EscapeRoom);
                    break;
                case 5:
                    System.out.println("Seleccionaste: Consultas sobre equipos");
                    gestionConsultaEquipos(sc,EscapeRoom);
                    break;
                case 6:
                    System.out.println("Seleccionaste: Consulta general");
                    gestionConsultaGeneral(sc, EscapeRoom);
                    break;

                case 7:
                    System.out.println("Saliendo del sistema... ¡Nos vemos!");
                    break;

                default:
                    System.out.println("Número no reconocido. Por favor, introduce una opción válida.");
                    break;
            }
        } while (numMenu != 7);
        sc.close();
    }
    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------
    //---------Punto 2, Gestion ABM----------------------------------------------
    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------

    public static void gestionABM(Scanner sc, Logica scapeRoom) {
        mostrarMenuABM();
        int opcionABM = sc.nextInt();

        switch (opcionABM) {
            case 1:
                System.out.println("Seleccionaste: Menu ABM de habitaciones");
                habitacionesABM(sc,scapeRoom);
            break;

            case 2:
                System.out.println("Seleccionaste: Menu ABM de desafios");
                desafiosABM(sc, scapeRoom);
            break;

            case 3:
                System.out.println("Seleccionaste: Menu ABM de equipos");
                equiposABM(sc, scapeRoom);
            break;

            case 4:
                System.out.println("Saliendo del menú de ABM...");
            break;

            default:
                System.out.println("Opción no válida. Saliendo al menu principal");
            break;
        }
    }

//------------------------------------------------------------------------------
//----------------Habitaciones ABM----------------------------------------------
//------------------------------------------------------------------------------
     public static void habitacionesABM(Scanner sc, Logica scapeRoom) {
        int opcionABM,codigo,planta,medida;
        String nombre;

        mostrarMenuABMhabitacion();
        opcionABM = sc.nextInt();

        switch (opcionABM) {
            case 1:
                System.out.println("Seleccionaste: crear de habitacion");
                System.out.println("Ingrese el código que le quiere dar a la habitación:");
                codigo = sc.nextInt();
                System.out.println("Ingrese el nombre de la habitación:");
                sc.nextLine();
                nombre = sc.nextLine();
                System.out.println("Ingrese la planta de la habitación:");
                planta = sc.nextInt();
                System.out.println("Ingrese la medida de la habitación:");
                medida = sc.nextInt();

                System.out.println(scapeRoom.agregarHabitacion(codigo,nombre,planta,medida));
            break;

            case 2:
                System.out.println("Seleccionaste: modificar habitacion");
                System.out.println(modificarHabitacion(sc, scapeRoom));
            break;

            case 3:
                System.out.println("Seleccionaste: baja de habitacion");
                System.out.println("inserte el codigo de la habitacion a eliminar.");
                codigo = sc.nextInt();
                System.out.println(scapeRoom.eliminarHabitacion(codigo));
            break;

            case 4:
                System.out.println("Saliendo al menu principal");
            break;

            default:
                System.out.println("Opción no válida. Saliendo al menu principal");
            break;
        }
    }



    public static String modificarHabitacion(Scanner sc, Logica scapeRoom) {
        int codigo, numMenu,medida,planta,codDestino,puntajeRequerido;
        String res,nombre;
        System.out.println("Ingrese el código de la habitación a modificar:");
        codigo = sc.nextInt();
            mostrarMenuModificarHabitacion();
            numMenu = sc.nextInt();
            switch (numMenu) {
                case 1:
                    sc.nextLine();
                    System.out.println("Ingrese el nuevo nombre de la habitación:");
                    nombre=sc.nextLine();
                    res=scapeRoom.cambiarNombreHabitacion(codigo,nombre);
                    break;

                case 2:
                    System.out.println("Ingrese la nueva planta de la habitación:");
                    planta= sc.nextInt();
                    res=scapeRoom.cambiarPlantaHabitacion(codigo,planta);
                    break;

                case 3:
                    System.out.println("Ingrese la nueva medida de la habitación:");
                    medida= sc.nextInt();
                    res=scapeRoom.cambiarMedidaHabitacion(codigo,medida);
                    break;

                case 4:
                    System.out.println("ingrese el codigo de la habitacion destino.");
                    codDestino = sc.nextInt();
                    System.out.println("inserte el requisito de puntos para usar la puerta.");
                    puntajeRequerido = sc.nextInt();
                    res=scapeRoom.añadirCaminos(codigo,codDestino,puntajeRequerido);
                    break;

                case 5:
                    res="Saliendo al menu principal";
                    break;

                default:
                    res="Opción no válida. Saliendo al menu principal";
                break;
            }
            return res;
        }

//------------------------------------------------------------------------------
//----------------Desafios ABM--------------------------------------------------
//------------------------------------------------------------------------------

    public static void desafiosABM(Scanner sc,Logica scapeRoom) {
        int numMenu,codigo, puntaje;
        String palabra,tipo;
        mostrarMenuABMdesafio();
        numMenu = sc.nextInt();

        switch (numMenu) {
            case 1:
                System.out.println("Seleccionaste: crear desafio");
                System.out.println("Ingrese el código de la habitación a la que le pertenecera el desafío:");
                codigo = sc.nextInt();
                System.out.println("Ingrese el puntaje del desafío:");
                puntaje = sc.nextInt();
                System.out.println("Ingrese el nombre del desafio:");
                sc.nextLine();
                palabra = sc.nextLine();
                System.out.println("Ingrese el tipo del desafio:");
                tipo = sc.next();

                System.out.println(scapeRoom.agregarDesafio(codigo,puntaje,palabra,tipo));
            break;

            case 2:
                System.out.println("Seleccionaste: modificar desafio");
                System.out.println(modificarDesafio(sc,scapeRoom));
            break;

            case 3:
                System.out.println("Seleccionaste: baja de desafio");
                System.out.println("Ingrese el código de la habitación del desafío a eliminar:");
                codigo = sc.nextInt();
                System.out.println("Ingrese el puntaje del desafío:");
                puntaje = sc.nextInt();
                System.out.println(scapeRoom.eliminarDesafio(codigo,puntaje));
            break;

            case 4:
                System.out.println("Saliendo del menú de ABM...");
            break;

            default:
                System.out.println("Opción no válida.");
            break;
        }
    }


    public static String modificarDesafio(Scanner sc, Logica scapeRoom) {
        int codigo, numMenu, puntaje;
        String res,nombre;
        System.out.println("Ingrese el código de la habitación a la que pertenece el desafío:");
        codigo = sc.nextInt();
            System.out.println("Ingrese el puntaje del desafio a modificar:");
            puntaje = sc.nextInt();
                mostrarMenuModificardesafio();
                numMenu = sc.nextInt();
                switch (numMenu) {
                    case 1:
                        System.out.println("Seleccionaste: Modificar el nombre desafio");
                        sc.nextLine();
                        System.out.println("Ingrese el nuevo nombre del desafío:");
                        nombre = sc.nextLine();
                        System.out.println("Seleccionaste: Modificar nombre del desafio");
                        res=scapeRoom.cambiarNombreDesafio(codigo, puntaje,nombre);
                    break;

                    case 2:
                        System.out.println("Seleccionaste: Modificar tipo del desafio");
                        System.out.println("Ingrese el nuevo tipo del desafío:");
                        sc.nextLine();
                        nombre = sc.nextLine();
                        res=scapeRoom.cambiarTipoDesafio(codigo, puntaje,nombre);
                    break;

                    case 3:
                        res="Saliendo al menu principal";
                    break;

                    default:
                        res="Opción no válida. Saliendo al menu principal";
                    break;
                }
    
        return res;
    }



//------------------------------------------------------------------------------
//----------------Equipos ABM---------------------------------------------------
//------------------------------------------------------------------------------

public static void equiposABM(Scanner sc,Logica scapeRoom) {
        int opcionABM,codigo,puntajeExigido;
        String nombre;
        mostrarMenuABMequipo();
        opcionABM = sc.nextInt();
        switch (opcionABM) {
            case 1:
                sc.nextLine();
                System.out.println("Seleccionaste: crear equipo");
                System.out.println("Ingrese el nombre del equipo:");
                nombre = sc.nextLine();
                System.out.println("Ingrese el puntaje exigido del equipo:");
                puntajeExigido = sc.nextInt();
                System.out.println("Ingrese el codigo de la habitacion actual del equipo:");
                codigo = sc.nextInt();
                System.out.println(scapeRoom.agregarEquipo(nombre,codigo,puntajeExigido));
                break;

            case 2:
                sc.nextLine();
                System.out.println("Seleccionaste: modificar equipo");
                System.out.println(modificarEquipo(sc, scapeRoom));
                break;

            case 3:
                
                sc.nextLine();
                System.out.println("Seleccionaste: Baja de equipo");
                System.out.println("Ingrese el nombre del equipo:");
                nombre = sc.nextLine();
                System.out.println(scapeRoom.eliminarEquipo(nombre));
                break;
                
            case 4:
                System.out.println("Saliendo del menú de ABM...");
            break;

            default:
                System.out.println("Opción no válida.");
            break;
        }
    }


    public static String modificarEquipo(Scanner sc, Logica scapeRoom) {
        String nombre,res;
        int numMenu,puntajeExigido;
        System.out.println("Introduce el nombre del equipo a modificar:");
        nombre=sc.nextLine();
            mostrarMenuModificarEquipo();
            numMenu = sc.nextInt();
            switch (numMenu) {
                case 1:
                    System.out.println("Seleccionaste: cambiar puntaje exijido");
                    System.out.println("Ingrese el nuevo puntaje exigido:");
                    puntajeExigido=sc.nextInt();
                    res=(scapeRoom.cambiarPuntajeExigido(nombre,puntajeExigido));
                    break;
                case 2:
                    res="Saliendo de la modificación de equipo...";
                    break;
                default:
                    res="Opción no válida. Saliendo de la modificación de equipo...";
                    break;
            }
        return res;
    }
    

    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------
    //---------Punto 3, Consulta Habitacion--------------------------------------
    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------

    public static void gestionConsultaHabitacion(Scanner sc,Logica scapeRoom){
        int numMenu;
        mostrarMenuHabitacion();
        numMenu = sc.nextInt();
        switch (numMenu) {
            case 1:
                
                System.out.println("Seleccionaste: mostrar habitacion");
                System.out.println("Ingrese un numero de habitacion:");
                numMenu = sc.nextInt();
                // Llamo a metodo de AVL que me da un String de los datos de la habitacion
                // encontrada
                System.out.println(scapeRoom.mostrarHabitacion(numMenu));
            break;

            case 2:
                System.out.println("Seleccionaste: mostrar habitaciones contiguas");
                System.out.println("Ingrese un numero de habitacion:");
                numMenu = sc.nextInt();
                // llamo metodo de Grafo que me da un String de las habitaciones contiguas
                System.out.println(scapeRoom.mostrarContiguas(numMenu));
            break;

            case 3:
                System.out.println("Seleccionaste: es posible llegar de habitacion A a habitacion B");
                gestionEsPosibleLlegar(sc, scapeRoom);
            break;

            case 4:
                System.out.println("Seleccionaste: cual es el minimo puntaje para ir de habitacion A a habitacion B");
                gestionMinimoPuntajeEntreHabs(sc,scapeRoom);
            break;

            case 5:
                System.out.println("Seleccionaste: cuales son las formas de ir a de habitacion A a habitacion B sin pasar por la habitacion C");
                gestionSinPasarPor(sc, scapeRoom);
            break;

            case 6:
                System.out.println("Saliendo al menu principal");
            break;
                        
            default:
                System.out.println("Opción no válida. Saliendo al menu principal");
            break;
        }
    }
    public static void gestionMinimoPuntajeEntreHabs(Scanner sc,Logica scapeRoom){
        int hab1,hab2;
        System.out.println("Ingrese hab1: ");
        hab1 = sc.nextInt();
        System.out.println("Ingrese hab2: ");
        hab2 = sc.nextInt();
        System.out.println(scapeRoom.minimoPuntaje(hab1,hab2));
    }
    public static void gestionEsPosibleLlegar(Scanner sc, Logica scapeRoom){
        int hab1, hab2, k;
        System.out.println("Ingrese hab1: ");
        hab1 = sc.nextInt();
        System.out.println("Ingrese hab2: ");
        hab2 = sc.nextInt();
        System.out.println("Ingrese valor k: ");
        k = sc.nextInt();
        System.out.println(scapeRoom.esPosibleLlegar(hab1, hab2, k));
    }
    public static void gestionSinPasarPor(Scanner sc, Logica scapeRoom){
        int hab1, hab2, hab3, p;
        System.out.println("Ingrese hab1: ");
        hab1 = sc.nextInt();
        System.out.println("Ingrese hab2: ");
        hab2 = sc.nextInt();
        System.out.println("Ingrese hab3: ");
        hab3 = sc.nextInt();
        System.out.println("Ingrese valor p: ");
        p = sc.nextInt();
        System.out.println(scapeRoom.sinPasarPor(hab1, hab2, hab3, p));
    }

    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------
    //---------Punto 4, Consulta Desafios----------------------------------------
    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------

public static void gestionConsultaDesafios(Scanner sc,Logica scapeRoom){
    int numMenu,codigo, puntaje,rangoInferior,rangoSuperior;
    String nombre;
    mostrarMenuDesafio();
    numMenu = sc.nextInt();
    sc.nextLine();
    switch (numMenu) {
        case 1:
            System.out.println("Seleccionaste: mostrar desafio");
            System.out.println("Ingrese el numero de habitacion");
            codigo= sc.nextInt();
            System.out.println("Ingrese el codigo del desafio ");
            puntaje= sc.nextInt();
            System.out.println(scapeRoom.mostrarDesafio(sc,codigo,puntaje));
        break;

        case 2:
            System.out.println("Seleccionaste: mostrar desafios resueltos");
            System.out.println("Ingrese el nombre del equipo: ");
            // sc.nextLine();
            nombre = sc.nextLine();
            System.out.println(scapeRoom.mostrarDesafiosResueltos(nombre));
        break;

        case 3:
            System.out.println("Seleccionaste: verificar desafio resuelto");
            System.out.println("Ingrese el nombre del equipo: ");
            sc.nextLine();
            nombre = sc.nextLine();
            System.out.println("Ingrese el numero de la habitacion: ");
            codigo = sc.nextInt();
            System.out.println("Ingrese el codigo del desafio: ");
            puntaje = sc.nextInt();
            System.out.println(scapeRoom.verificarDesafioResuelto(nombre,codigo,puntaje));
        break;

        case 4:
            System.out.println("Seleccionaste: mostrar desafios tipo");
            System.out.println("Ingrese la clave de la habitacion: ");
            codigo = sc.nextInt();
            System.out.println("Ingrese el rango inferior [a]: ");
            rangoInferior = sc.nextInt(); //Rango inferior
            System.out.println("Ingrese el rango superior [b]: ");
            rangoSuperior = sc.nextInt(); //Rango Superior
            System.out.println("Ingrese el tipo de desafio: ");
            sc.nextLine();
            nombre = sc.nextLine();
            System.out.println(scapeRoom.mostrarDesafiosPorTipo(codigo,rangoInferior,rangoSuperior,nombre));
        break;
        
        case 5:
            System.out.println("Saliendo al menu principal");
        break;
                        
        default:
            System.out.println("Opción no válida. Saliendo al menu principal");
        break;
    }
    }
    
    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------
    //---------Punto 5, Consulta Equipos-----------------------------------------
    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------


    public static void gestionConsultaEquipos(Scanner sc,Logica scapeRoom){
        int numMenu,codigo,puntaje;
        String nombre;
        mostrarMenuConsultaEquipos();
        numMenu=sc.nextInt();
        switch (numMenu) {
            case 1:
                System.out.println("Seleccionaste: Mostrar informacion de equipo");
                sc.nextLine();
                System.out.println("Introduce el nombre del equipo a mostrar:");
                nombre=sc.nextLine();
                System.out.println(scapeRoom.mostrarEquipo(nombre));
                
            break;

            case 2:
                System.out.println("Seleccionaste: Mostrar posibles desafios");
                sc.nextLine();
                System.out.println("Introduce el nombre del equipo que juega el desafio:");
                nombre=sc.nextLine();
                System.out.println("introduce el codigo de la habitacion a la que quieren avanzar");
                codigo=sc.nextInt();
                System.out.println(scapeRoom.mostrarPosiblesDesafios(nombre,codigo));

            break;

            case 3:
                System.out.println("Seleccionaste: Jugar desafio");
                sc.nextLine();
                System.out.println("Introduce el nombre del equipo que juega el desafio:");
                nombre=sc.nextLine();
                System.out.println("introduce el codigo de la habitacion a la que quieren avanzar");
                codigo=sc.nextInt();
                System.out.println("Introduce el puntaje del desafio que estan jugando:");
                puntaje=sc.nextInt();
                System.out.println(scapeRoom.jugarDesafio(nombre,codigo,puntaje));
            break;

            case 4:
                System.out.println("Seleccionaste: Cambiar de habitacion ");
                sc.nextLine();
                System.out.println("Introduce el nombre del equipo que juega el desafio:");
                nombre=sc.nextLine();
                System.out.println("introduce el codigo de la habitacion a la que quieren avanzar");
                codigo=sc.nextInt();
                System.out.println(scapeRoom.cambiarDeHabitacion(nombre,codigo));
            break;

            case 5:
                System.out.println("Seleccionaste: Puede salir ");
                sc.nextLine();
                System.out.println("Introduce el nombre del equipo que juega el desafio:");
        nombre=sc.nextLine();
                System.out.println(scapeRoom.puedeSalirEquipo(nombre));
            break;

            case 6:
                System.out.println("Saliendo al menu principal.");
            break;

            default:
                System.out.println("Opción no válida. Saliendo al menu principa.");
            break;
        }
    }
    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------
    //---------Punto 6, Consulta General-----------------------------------------
    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------
    public static void gestionConsultaGeneral(Scanner sc, Logica scapeRoom){
        System.out.println("Consulta general de habitaciones, desafíos y equipos.");
        int numMenu;
        mostrarMenuConsultaGeneral();
        numMenu = sc.nextInt();
        switch (numMenu) {
            case 1:
                System.out.println("Seleccionaste: ver AVL");
                System.out.println(scapeRoom.toStringAVL());
                break;
            case 2:
                System.out.println("Seleccionaste: ver Grafo");
                System.out.println(scapeRoom.toStringPlanoCasa());
                break;
            case 3:
                System.out.println("Seleccionaste: ver Hash");
                System.out.println(scapeRoom.toStringHashMap());

                //System.out.println(equipos.get("Misterio Inc").getDesafiosCompletados().toString());
                break;
            case 4:
                System.out.println("Saliendo al menu principal");
                break;

            default:
                System.out.println("Opción no válida. Saliendo al menu principal");
            break;
        }
    }

    public static void mostrarMenu() {
        System.out.println("====================================");
        System.out.println("========== MENU PRINCIPAL ==========");
        System.out.println("====================================");
        System.out.println("");
        System.out.println("1. Carga inicial del sistema");
        System.out.println("2. Altas, Bajas y Modificaciones (ABM)");
        System.out.println("3. Consulta sobre habitaciones");
        System.out.println("4. Consultas sobre desafíos");
        System.out.println("5. Consultas sobre equipos");
        System.out.println("6. Consulta general");
        System.out.println("7. Salir");
        System.out.print("Introduce un número para avanzar: ");
    }

    public static void mostrarMenuABM() {
        System.out.println("");
        System.out.println("1. ABM de habitacion");
        System.out.println("2. ABM de desafío");
        System.out.println("3. ABM de equipo");
        System.out.println("4. Salir");
        System.out.print("Introduce un numero para avanzar: ");
    }

    public static void mostrarMenuABMhabitacion() {
        System.out.println("");
        System.out.println("1. crear habitacion");
        System.out.println("2. modificar habitacion");
        System.out.println("3. baja de habitación");
        System.out.println("4. Salir");
        System.out.print("Introduce un numero para avanzar: ");
    }

    public static void mostrarMenuABMdesafio() {
        System.out.println("");
        System.out.println("1. crear desafío");
        System.out.println("2. modificar desafío");
        System.out.println("3. baja de desafío");
        System.out.println("4. Salir");
        System.out.print("Introduce un numero para avanzar: ");
    }

    public static void mostrarMenuABMequipo() {
        System.out.println("");
        System.out.println("1. crear equipo");
        System.out.println("2. modificar equipo");
        System.out.println("3. Baja de equipo");
        System.out.println("4. Salir");
        System.out.print("Introduce un numero para avanzar: ");
    }

    public static void mostrarMenuHabitacion() {
        System.out.println("");
        System.out.println("1. mostrar habitacion");
        System.out.println("2. mostrar habitaciones contiguas");
        System.out.println("3. es posible llegar de habitacion A a habitacion B");
        System.out.println("4. cual es el minimo puntaje para ir de habitacion A a habitacion B");
        System.out.println("5. cuales son las formas de ir a de habitacion A a habitacion B sin pasar por la habitacion C");
        System.out.println("6. Salir");
        System.out.print("Introduce un numero para elegir una consulta: ");
    }

    public static void mostrarMenuDesafio() {
        System.out.println("");
        System.out.println("1. mostrar desafio");
        System.out.println("2. mostrar desafios resueltos");
        System.out.println("3. verificar desafio resuelto");
        System.out.println("4. mostrar desafios tipo");
        System.out.println("5. Salir");
        System.out.print("Introduce un numero para elegir una consulta: ");
    }

    public static void mostrarMenuModificarHabitacion() {
        System.out.println("");
        System.out.println("1. Modificar nombre de la habitación");
        System.out.println("2. Modificar planta de la habitación");
        System.out.println("3. Modificar medida de la habitación");
        System.out.println("4. Salir");
        System.out.print("Introduce un numero para elegir una modificación: ");
    }

    public static void mostrarMenuModificardesafio() {
        System.out.println("");
        System.out.println("1. Modificar nombre del desafío");
        System.out.println("2. Modificar tipo del desafío");
        System.out.println("3. Salir");
        System.out.print("Introduce un numero para elegir: ");
    }
    public static void mostrarMenuModificarEquipo(){
        System.out.println("");
        System.out.println("1. Modificación de puntaje exigido del equipo");
        System.out.println("2. Salir");
        System.out.print("Introduce un numero para avanzar: ");
    }

        public static void mostrarMenuConsultaEquipos() {
        System.out.println("");
        System.out.println("1. Mostrar informacion de equipo");
        System.out.println("2. Mostrar posibles desafios");
        System.out.println("3. Jugar desafio");
        System.out.println("4. Cambiar de habitacion ");
        System.out.println("5. Puede salir ");
        System.out.println("6. Salir ");
        System.out.print("Introduce un numero para elegir: ");
    }
    public static void mostrarMenuConsultaGeneral() {
        System.out.println("");
        System.out.println("1. Ver AVL");
        System.out.println("2. Ver Grafo");
        System.out.println("3. Ver Hash");
        System.out.println("4. Salir");
        System.out.print("Introduce un numero para elegir: ");
    }
}
