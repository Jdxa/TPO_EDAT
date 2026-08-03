package Menu;
import java.util.HashMap;
import java.util.Scanner;

import Estructuras.GrafoEtiquetado.Grafo;
import Estructuras.TablaAVL.ArbolAVL;
import Modelo.Equipo;

public class Principal{
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numMenu;
        GestorArchivo archivo = new GestorArchivo();
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
                        archivo.cargarDatos(habitaciones,planoCasa,equipo);
                        System.out.println(habitaciones.toString());
                        estaCargado = true;
                    }
                    
                    break;
                    
                case 2:

                gestionABM(scanner, habitaciones, archivo, equipo);
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
                    case 6:
                            System.out.println("Consulta general");
                            gestionConsultaGeneral(scanner, habitaciones, planoCasa, equipo);
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
    public static void gestionABM(Scanner scanner,ArbolAVL habitaciones, GestorArchivo archivo, HashMap<String, Equipo> equipos) {
        System.out.println("Seleccionaste: Altas, Bajas y Modificaciones (ABM) de habitaciones, desafíos y equipos.");
        mostrarMenuABM();
        int opcionABM = scanner.nextInt();
        
        switch (opcionABM) {
            case 1:
                habitacionesABM(scanner, habitaciones, archivo);
                break;
            case 2:
                desafiosABM(scanner, habitaciones, archivo);
                break;
            case 3:
                equiposABM(scanner, equipos, archivo);
                break;
            case 4:
                System.out.println("Saliendo del menú de ABM...");
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
    public static void habitacionesABM(Scanner scanner,ArbolAVL habitaciones, GestorArchivo archivo){
        int opcionABM;
        mostrarMenuABMhabitacion();
        opcionABM = scanner.nextInt();
        
        switch (opcionABM) {
            case 1:
                agregarHabitacion(scanner, habitaciones, archivo);
                break;
            case 2:
                modificarHabitacion(scanner, habitaciones);
                break;
            case 3:
                eliminarHabitacion(scanner, habitaciones);
                break;
            case 4:
                System.out.println("Saliendo del menú de ABM...");
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
    public static void desafiosABM(Scanner scanner,ArbolAVL habitaciones, GestorArchivo archivo){
        int opcionABM;
        mostrarMenuABMdesafio();
        opcionABM = scanner.nextInt();
        
        switch (opcionABM) {
            case 1:
                agregarDesafio(scanner, habitaciones, archivo);
                break;
            case 2:
                modificarDesafio(scanner, habitaciones);
                break;
            case 3:
                eliminarDesafio(scanner, habitaciones);
                break;
            case 4:
                System.out.println("Saliendo del menú de ABM...");
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
     public static void equiposABM(Scanner scanner,HashMap <String, Equipo> equipos, GestorArchivo archivo){
        int opcionABM;
        mostrarMenuABMequipo();
        opcionABM = scanner.nextInt();
        System.out.println("Seleccionaste: Altas, Bajas y Modificaciones de equipos.");
        switch (opcionABM) {
            case 1:
                scanner.nextLine();
                agregarEquipo(scanner, equipos, archivo);
                break;
            case 2:
                //modificarEquipo(scanner, habitaciones);
                break;
            case 3:
                //eliminarEquipo(scanner, habitaciones);
                break;
            case 4:
                System.out.println("Saliendo del menú de ABM...");
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
    public static void agregarHabitacion(Scanner scanner, ArbolAVL habitaciones, GestorArchivo archivo) {
        int numero;
        String palabra,linea;
        System.out.println("--> crear de habitacion");
        System.out.println("Ingrese el código que le quiere dar a la habitación:");
        numero = scanner.nextInt();
        while(habitaciones.pertenece(numero)){
        System.out.println("El código ingresado ya existe. Por favor, ingrese un código diferente:");
        numero = scanner.nextInt();
        }

        linea = "H;" + numero;
        System.out.println("Ingrese el nombre de la habitación:");
        scanner.nextLine();
        palabra = scanner.nextLine();
        linea += ";" + palabra;
        System.out.println("Ingrese la planta de la habitación:");
        numero = scanner.nextInt();
        linea += ";" + numero;
        System.out.println("Ingrese la medida de la habitación:");
        numero= scanner.nextInt();
        linea += ";" + numero+";false";
        archivo.cargarDatoLinea(linea, habitaciones, null, null);

        System.out.println("Habitación creada exitosamente.");
    }

    public static void modificarHabitacion(Scanner scanner, ArbolAVL habitaciones) {
        int codigo,numMenu;
        System.out.println("--> Modificación de habitación");
        System.out.println("Ingrese el código de la habitación a modificar:");
        codigo = scanner.nextInt();
        if(habitaciones.pertenece(codigo)){
        mostrarMenuModificarHabitacion();
        numMenu=scanner.nextInt();
        switch (numMenu) {
            case 1:
                scanner.nextLine();
                cambiarNombreHabitacion(scanner, habitaciones, codigo);
                break;
            case 2:
                cambiarPlantaHabitacion(scanner, habitaciones, codigo);
                break;
            case 3:
                cambiarMedidaHabitacion(scanner, habitaciones, codigo);
                break;
            case 4:
                System.out.println("Saliendo de la modificación de habitación...");
            break;
            default:
            System.out.println("Opción no válida. Saliendo de la modificación de habitación...");
            break;
        }
            }else{
                System.out.println("No se encontró la habitación con el código ingresado.");
            }
        }

    public static void cambiarNombreHabitacion(Scanner scanner, ArbolAVL habitaciones, int codigo) {
        String nombre;
        System.out.println("Ingrese el nuevo nombre de la habitación:");
        nombre = scanner.nextLine();
        habitaciones.modificarNombreHabitacion(codigo, nombre);
        System.out.println("Nombre de la habitación modificado correctamente.");
    }

    public static void cambiarPlantaHabitacion(Scanner scanner, ArbolAVL habitaciones, int codigo) {
        int planta;
        System.out.println("Ingrese la nueva planta de la habitación:");
        planta = scanner.nextInt();
        habitaciones.modificarPlantaHabitacion(codigo, planta);
        System.out.println("Planta de la habitación modificada correctamente.");

    }

    private static void cambiarMedidaHabitacion(Scanner scanner, ArbolAVL habitaciones, int codigo) {
        int medida;
        System.out.println("Ingrese la nueva medida de la habitación:");
        medida = scanner.nextInt();
        habitaciones.modificarMedidaHabitacion(codigo, medida);
        System.out.println("Medida de la habitación modificada correctamente.");
    }

    private static void eliminarHabitacion(Scanner scanner, ArbolAVL habitaciones) {
        int codigo;
        System.out.println("inserte el codigo de la habitacion a eliminar.");
        codigo = scanner.nextInt();
        // Llamo a metodo AVL que elimina si existe habitacion y devuelve boolean
        if (habitaciones.eliminar(codigo)) {
            System.out.println("Habitación eliminada correctamente.");
        } else {
            System.out.println("No se encontró la habitación con el código ingresado.");
        }
    }

    public static void agregarDesafio(Scanner scanner, ArbolAVL habitaciones,GestorArchivo archivo) {
        int codigoHabitacion, puntaje;
        String linea,palabra;
        System.out.println("--> Crear de desafío");
        System.out.println("Ingrese el código de la habitación a la que pertenece el desafío:");
        codigoHabitacion = scanner.nextInt();
        if (!habitaciones.pertenece(codigoHabitacion)) {
            System.out.println("No se encontró la habitación con el código ingresado. Por favor, ingrese un código válido.");
        }else{}
        System.out.println("Ingrese el puntaje del desafío:");
        puntaje = scanner.nextInt();
        while(habitaciones.perteneceDesafio(codigoHabitacion, puntaje)){
        System.out.println("El puntaje ingresado ya existe. Por favor, ingrese un puntaje diferente:");
        puntaje = scanner.nextInt();
        }
        linea = "D;" + puntaje+";"+codigoHabitacion;
        System.out.println("Ingrese el nombre del desafio:");
        scanner.nextLine();
        palabra = scanner.nextLine();
        linea += ";" + palabra;
        System.out.println("Ingrese el tipo del desafio:");
        palabra = scanner.next();
        linea += ";" + palabra;
        archivo.cargarDatoLinea(linea, habitaciones, null, null);
        System.out.println("Desafío creado exitosamente.");
    }

    public static void modificarDesafio(Scanner scanner, ArbolAVL habitaciones) {
        int codigo,numMenu,puntaje;
        System.out.println("--> Modificación de habitación");
        System.out.println("Ingrese el código de la habitación a modificar:");
        codigo = scanner.nextInt();
        if(!habitaciones.pertenece(codigo)){
        System.out.println("No se encontró la habitación con el código ingresado.");
        }else{
        System.out.println("Ingrese el puntaje del desafio a modificar:");
        puntaje = scanner.nextInt();
        if(!habitaciones.perteneceDesafio(codigo,puntaje)){
            System.out.println("No se encontró el desafío con el puntaje ingresado.");
        }else{
        mostrarMenuModificardesafio();
        numMenu=scanner.nextInt();
        switch (numMenu) {
            case 1:
                scanner.nextLine();
                cambiarNombreDesafio(scanner, habitaciones, codigo,puntaje);
                break;
            case 2:
                cambiarTipoDesafio(scanner, habitaciones, codigo,puntaje);
                break;
            case 4:
                System.out.println("Saliendo de la modificación de habitación...");
            break;
            default:
            System.out.println("Opción no válida. Saliendo de la modificación de habitación...");
            break;
            }
            }
        }
    }
    public static void cambiarNombreDesafio(Scanner scanner, ArbolAVL habitaciones, int codigo, int puntaje) {
        String nombre;
        System.out.println("Ingrese el nuevo nombre del desafío:");
        nombre = scanner.nextLine();
        // Llamo a método de AVL que modifica el nombre del desafío y me devuelve un boolean
        if (habitaciones.modificarNombreDesafio(codigo, puntaje, nombre)) {
            System.out.println("Nombre del desafío modificado correctamente.");
        } else {
            System.out.println("No se encontró el desafío con el código ingresado.");
        }
    }
    public static void cambiarTipoDesafio(Scanner scanner, ArbolAVL habitaciones, int codigo,int puntaje) {
        String tipo;
        System.out.println("Ingrese el nuevo tipo del desafío:");
        tipo = scanner.next();
        // Llamo a método de AVL que modifica el tipo del desafío y me devuelve un boolean
        if (habitaciones.modificarTipoDesafio(codigo, puntaje, tipo)) {
            System.out.println("Tipo del desafío modificado correctamente.");
        } else {
            System.out.println("No se encontró el desafío con el código ingresado.");
        }
    }
    public static void eliminarDesafio(Scanner scanner, ArbolAVL habitaciones) {
        int codigoHabitacion, puntaje;

        System.out.println("--> Baja de desafío");
        System.out.println("Ingrese el código de la habitación del desafío a eliminar:");
        codigoHabitacion = scanner.nextInt();
        if (!habitaciones.pertenece(codigoHabitacion)) {
            System.out.println("No se encontró la habitación con el código ingresado. Por favor, ingrese un código válido.");
        }else{
        System.out.println("Ingrese el puntaje del desafío a eliminar:");
        puntaje = scanner.nextInt();

        if(habitaciones.eliminarDesafio(codigoHabitacion, puntaje)){
            System.out.println("Desafío eliminado correctamente.");
        } else {
            System.out.println("No se encontró el desafío con los datos ingresados.");
        }
    }
    }

    public static void agregarEquipo(Scanner scanner, HashMap<String, Equipo> equipos,GestorArchivo archivo){
    
        String nombre,linea;
        int puntajeExigido, puntajeAcumulado, puntajeActual;
        System.out.println("--> Crear de equipo");
        System.out.println("Ingrese el nombre del equipo:");
        nombre = scanner.nextLine();
        if(equipos.containsKey(nombre)){
            System.out.println("El nombre ingresado ya existe. Por favor, ingrese un nombre diferente:");
        }else{
        linea="E;"+nombre;
        System.out.println("Ingrese el puntaje exigido del equipo:");
        puntajeExigido = scanner.nextInt();
        linea += ";"+puntajeExigido;
        System.out.println("Ingrese el puntaje acumulado del equipo:");
        puntajeAcumulado = scanner.nextInt();
        linea +=";"+puntajeAcumulado;
        System.out.println("Ingrese el puntaje actual del equipo:");
        puntajeActual = scanner.nextInt();
        linea +=";"+puntajeActual;
        
        archivo.cargarDatoLinea(linea, null, null, equipos);
        System.out.println("Equipo creado exitosamente.");
        }
    }
    public static void gestionConsultaGeneral(Scanner scanner,ArbolAVL habitaciones, Grafo planoCasa, HashMap<String, Equipo> equipos) {
        System.out.println("Consulta general de habitaciones, desafíos y equipos.");
        int numMenu;
        mostrarMenuConsultaGeneral();
        numMenu=scanner.nextInt();
        switch (numMenu) {
            case 1:
                System.out.println("Imprimiendo AVL de habitaciones.");
                break;
            case 2:
                System.out.println("Imprimiendo Grafo de habitaciones.");
                break;
            case 3:
                System.out.println("Imprimiendo Hash de equipos.");
                break;
            case 4:
                System.out.println("Saliendo de la consulta general...");
                break;

            default:
                System.out.println("Número no reconocido. Volviendo al menu Principal.");
                break;
        }
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
        public static void mostrarMenuABM(){
            System.out.println("2. MENU DE ABM");
            System.out.println("1. ABM de habitacion");
            System.out.println("2. ABM de desafío");
            System.out.println("3. ABM de equipo");
            System.out.println("10. Salir");
            System.out.print("Introduce un numero para avanzar: ");
        }
        public static void mostrarMenuABMhabitacion(){
            System.out.println("2. MENU DE ABM Habitacion");
            System.out.println("1. crear de habitacion");
            System.out.println("2. modificacion de habitacion");
            System.out.println("3. baja de habitación");
            System.out.println("4. Salir");
            System.out.print("Introduce un numero para avanzar: ");
        }
        public static void mostrarMenuABMdesafio(){
            System.out.println("2. MENU DE ABM desafio");
            System.out.println("1. crear de desafío");
            System.out.println("2. Modificación de desafío");
            System.out.println("3. Baja de desafío");
            System.out.println("4. Salir");
            System.out.print("Introduce un numero para avanzar: ");
        }
        public static void mostrarMenuABMequipo(){
            System.out.println("2. MENU DE ABM equipo");
            System.out.println("1. crear de equipo");
            System.out.println("2. Modificación de equipo");
            System.out.println("3. Baja de equipo");
            System.out.println("4. Salir");
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
        public static void mostrarMenuModificarHabitacion(){
            System.out.println("Seleccionaste: Altas, Bajas y Modificaciones de habitaciones.");
            System.out.println(". MODIFICACION DE HABITACIÓN");
            System.out.println("1. Modificar nombre de la habitación");
            System.out.println("2. Modificar planta de la habitación");
            System.out.println("3. Modificar medida de la habitación");
            System.out.println("4. Salir");
            System.out.print("Introduce un numero para elegir una modificación: ");
        }
        public static void mostrarMenuModificardesafio(){
            System.out.println("Seleccionaste: Altas, Bajas y Modificaciones de desafíos.");
            System.out.println(". MODIFICACION DE DESAFÍO");
            System.out.println("1. Modificar nombre del desafío");
            System.out.println("2. Modificar tipo del desafío");
            System.out.println("3. Salir");
            System.out.print("Introduce un numero para elegir una modificación: ");
        }
        public static void mostrarMenuConsultaGeneral(){
            System.out.println("Seleccionaste Menu consulta general");
            System.out.println("1. Ver AVL");
            System.out.println("2. Ver Grafo");
            System.out.println("3. Ver Hash");
            System.out.println("4. Salir");
        }
    
    //punto 3
    
    

        
        //punto 4



        //punto 5 consultas sobre habitaciones

    
}








