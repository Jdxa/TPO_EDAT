package Menu;

import java.util.HashMap;
import java.util.Scanner;
import Estructuras.EstructurasAux.Lista;
import Estructuras.GrafoEtiquetado.Grafo;
import Estructuras.TablaAVL.ArbolAVL;
import Modelo.Equipo;

public class Principal {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int numMenu;
        GestorArchivo archivo = new GestorArchivo();
        Grafo planoCasa = new Grafo();
        ArbolAVL habitaciones = new ArbolAVL();
        HashMap<String, Equipo> equipo = new HashMap<>();

        Boolean estaCargado = false;

        do {
            mostrarMenu();
            numMenu = sc.nextInt();

            switch (numMenu) {
                case 1:
                    System.out.println("Seleccionaste: Carga inicial del sistema (desde archivo de texto).");
                    if (estaCargado) {
                        System.out.println("Los datos ya fueron cargados previamente. No se puede cargar nuevamente.");
                        break;
                    } else {
                        archivo.cargarDatos(habitaciones, planoCasa, equipo);
                        estaCargado = true;
                    }

                    break;

                case 2:

                    gestionABM(sc, habitaciones, equipo,planoCasa);
                    break;

                case 3:
                    mostrarMenuHabitacion();
                    numMenu = sc.nextInt();
                    switch (numMenu) {
                        case 1:
                            System.out.println("-> Ejecutando: mostrarHabitación...");
                            System.out.println("Ingrese un numero de habitacion:");
                            numMenu = sc.nextInt();
                            // Llamo a metodo de AVL que me da un String de los datos de la habitacion
                            // encontrada
                            System.out.println(habitaciones.mostrarHabitacion(numMenu));
                            break;
                        case 2:
                            System.out.println("-> Ejecutando: habitacionesContiguas...");
                            System.out.println("Ingrese un numero de habitacion:");
                            numMenu = sc.nextInt();
                            // llamo metodo de Grafo que me da un String de las habitaciones contiguas
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
                            System.out.println("Opción de habitación no válida.");
                            break;
                    }
                    break;

                case 4:
                    mostrarMenuDesafio();
                    numMenu = sc.nextInt();
                    sc.nextLine();
                    switch (numMenu) {
                        case 1:
                            System.out.println("-> Ejecutando: mostrarDesafío...");
                            mostrarDesafio(sc, habitaciones);
                            break;
                        case 2:
                            System.out.println("-> Ejecutando: mostrarDesafíosResueltos...");
                            mostrarDesafiosResueltos(sc, equipo, habitaciones);
                            break;
                        case 3:
                            System.out.println("-> Ejecutando: verificarDesafíoResuelto...");
                            verificarDesafioResuelto(sc, equipo);

                            break;
                        case 4:
                            System.out.println("-> Ejecutando: mostrarDesafíosTipo...");
                            break;
                        default:
                            System.out.println("Opción de desafío no válida.");
                            break;
                    }
                    break;
                case 5:
                    System.out.println("Consultas sobre equipos");
                    gestionConsultaEquipos(sc,equipo,habitaciones,planoCasa);
                    break;
                case 6:
                    System.out.println("Consulta general");
                    gestionConsultaGeneral(sc, habitaciones, planoCasa, equipo);
                    break;

                case 0:
                    System.out.println("Saliendo del sistema... ¡Nos vemos!");
                    break;

                default:
                    System.out.println("Número no reconocido. Por favor, introduce una opción válida.");
                    break;
            }
        } while (numMenu != 0);

        sc.close();
    }

    public static void gestionABM(Scanner sc, ArbolAVL habitaciones,
            HashMap<String, Equipo> equipos,Grafo planoCasa) {
        System.out.println("Seleccionaste: Altas, Bajas y Modificaciones (ABM) de habitaciones, desafíos y equipos.");
        mostrarMenuABM();
        int opcionABM = sc.nextInt();

        switch (opcionABM) {
            case 1:
                habitacionesABM(sc, habitaciones,planoCasa);
                break;
            case 2:
                desafiosABM(sc, habitaciones);
                break;
            case 3:
                equiposABM(sc, equipos,planoCasa,habitaciones);
                break;
            case 4:
                System.out.println("Saliendo del menú de ABM...");
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    public static void habitacionesABM(Scanner sc, ArbolAVL habitaciones,Grafo planoCasa) {
        int opcionABM;
        mostrarMenuABMhabitacion();
        opcionABM = sc.nextInt();

        switch (opcionABM) {
            case 1:
                agregarHabitacion(sc, habitaciones,planoCasa);
                break;
            case 2:
                modificarHabitacion(sc, habitaciones,planoCasa);
                break;
            case 3:
                eliminarHabitacion(sc, habitaciones);
                break;
            case 4:
                System.out.println("Saliendo del menú de ABM...");
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    public static void desafiosABM(Scanner sc, ArbolAVL habitaciones) {
        int opcionABM;
        mostrarMenuABMdesafio();
        opcionABM = sc.nextInt();

        switch (opcionABM) {
            case 1:
                agregarDesafio(sc, habitaciones);
                break;
            case 2:
                modificarDesafio(sc, habitaciones);
                break;
            case 3:
                eliminarDesafio(sc, habitaciones);
                break;
            case 4:
                System.out.println("Saliendo del menú de ABM...");
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    public static void equiposABM(Scanner sc, HashMap<String, Equipo> equipos,Grafo planoCasa,ArbolAVL habitaciones) {
        int opcionABM;
        mostrarMenuABMequipo();
        opcionABM = sc.nextInt();
        System.out.println("Seleccionaste: Altas, Bajas y Modificaciones de equipos.");
        switch (opcionABM) {
            case 1:
                sc.nextLine();
                agregarEquipo(sc, equipos, habitaciones);
                break;
            case 2:
                sc.nextLine();
                modificarEquipo(sc, equipos);
                break;
            case 3:
                sc.nextLine();
                eliminarEquipo(sc, equipos);
                break;
            case 4:
                System.out.println("Saliendo del menú de ABM...");
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    public static void agregarHabitacion(Scanner sc, ArbolAVL habitaciones,Grafo planoCasa) {
        int numero;
        String palabra, linea;
        GestorArchivo archivo= new GestorArchivo();
        System.out.println("--> crear de habitacion");
        System.out.println("Ingrese el código que le quiere dar a la habitación:");
        numero = sc.nextInt();
        while (habitaciones.pertenece(numero)) {
            System.out.println("El código ingresado ya existe. Por favor, ingrese un código diferente:");
            numero = sc.nextInt();
        }

        linea = "H;" + numero;
        System.out.println("Ingrese el nombre de la habitación:");
        sc.nextLine();
        palabra = sc.nextLine();
        linea += ";" + palabra;
        System.out.println("Ingrese la planta de la habitación:");
        numero = sc.nextInt();
        linea += ";" + numero;
        System.out.println("Ingrese la medida de la habitación:");
        numero = sc.nextInt();
        linea += ";" + numero + ";false";
        archivo.cargarDatoLinea(linea, habitaciones,planoCasa, null);

        System.out.println("Habitación creada exitosamente.");
    }

    public static void modificarHabitacion(Scanner sc, ArbolAVL habitaciones, Grafo planoCasa) {
        int codigo, numMenu;
        System.out.println("--> Modificación de habitación");
        System.out.println("Ingrese el código de la habitación a modificar:");
        codigo = sc.nextInt();
        if (habitaciones.pertenece(codigo)) {
            mostrarMenuModificarHabitacion();
            numMenu = sc.nextInt();
            switch (numMenu) {
                case 1:
                    sc.nextLine();
                    cambiarNombreHabitacion(sc, habitaciones, codigo);
                    break;
                case 2:
                    cambiarPlantaHabitacion(sc, habitaciones, codigo);
                    break;
                case 3:
                    cambiarMedidaHabitacion(sc, habitaciones, codigo);
                    break;
                case 4:
                    añadirCaminos(sc,planoCasa,codigo);
                    break;
                case 5:
                    System.out.println("Saliendo de la modificación de habitación...");
                    break;
                default:
                    System.out.println("Opción no válida. Saliendo de la modificación de habitación...");
                    break;
            }
        } else {
            System.out.println("No se encontró la habitación con el código ingresado.");
        }
    }

    public static void cambiarNombreHabitacion(Scanner sc, ArbolAVL habitaciones, int codigo) {
        String nombre;
        System.out.println("Ingrese el nuevo nombre de la habitación:");
        nombre = sc.nextLine();
        habitaciones.modificarNombreHabitacion(codigo, nombre);
        System.out.println("Nombre de la habitación modificado correctamente.");
    }

    public static void cambiarPlantaHabitacion(Scanner sc, ArbolAVL habitaciones, int codigo) {
        int planta;
        System.out.println("Ingrese la nueva planta de la habitación:");
        planta = sc.nextInt();
        habitaciones.modificarPlantaHabitacion(codigo, planta);
        System.out.println("Planta de la habitación modificada correctamente.");

    }

    private static void cambiarMedidaHabitacion(Scanner sc, ArbolAVL habitaciones, int codigo) {
        int medida;
        System.out.println("Ingrese la nueva medida de la habitación:");
        medida = sc.nextInt();
        habitaciones.modificarMedidaHabitacion(codigo, medida);
        System.out.println("Medida de la habitación modificada correctamente.");
    }

    private static void eliminarHabitacion(Scanner sc, ArbolAVL habitaciones) {
        int codigo;
        System.out.println("inserte el codigo de la habitacion a eliminar.");
        codigo = sc.nextInt();
        // Llamo a metodo AVL que elimina si existe habitacion y devuelve boolean
        if (habitaciones.eliminar(codigo)) {
            System.out.println("Habitación eliminada correctamente.");
        } else {
            System.out.println("No se encontró la habitación con el código ingresado.");
        }
    }
    public static void añadirCaminos(Scanner sc, Grafo planoCasa, int codigo){
        int destino,puntajeRequerido;
        String linea;
        GestorArchivo archivo= new GestorArchivo();
        linea="P;codigo;";
        System.out.println("inserte la habitacion destino.");
        destino = sc.nextInt();
        linea+=destino+";";
        System.out.println("inserte el requisito de puntos para usar la puerta.");
        puntajeRequerido = sc.nextInt();
        linea+=puntajeRequerido;
        archivo.cargarDatoLinea(linea, null, planoCasa, null);
    }

    public static void agregarDesafio(Scanner sc, ArbolAVL habitaciones) {
        int codigoHabitacion, puntaje;
        String linea, palabra;
        GestorArchivo archivo=new GestorArchivo();
        
        System.out.println("--> Crear de desafío");
        System.out.println("Ingrese el código de la habitación a la que pertenece el desafío:");
        codigoHabitacion = sc.nextInt();
        if (!habitaciones.pertenece(codigoHabitacion)) {
            System.out.println("No se encontró la habitación con el código ingresado. Por favor, ingrese un código válido.");
        } else {
        }
        System.out.println("Ingrese el puntaje del desafío:");
        puntaje = sc.nextInt();
        while (habitaciones.perteneceDesafio(codigoHabitacion, puntaje)) {
            System.out.println("El puntaje ingresado ya existe. Por favor, ingrese un puntaje diferente:");
            puntaje = sc.nextInt();
        }
        linea = "D;" + puntaje + ";" + codigoHabitacion;
        System.out.println("Ingrese el nombre del desafio:");
        sc.nextLine();
        palabra = sc.nextLine();
        linea += ";" + palabra;
        System.out.println("Ingrese el tipo del desafio:");
        palabra = sc.next();
        linea += ";" + palabra;
        archivo.cargarDatoLinea(linea, habitaciones, null, null);
        System.out.println("Desafío creado exitosamente.");
    }

    public static void modificarDesafio(Scanner sc, ArbolAVL habitaciones) {
        int codigo, numMenu, puntaje;
        System.out.println("--> Modificación de habitación");
        System.out.println("Ingrese el código de la habitación a modificar:");
        codigo = sc.nextInt();
        if (!habitaciones.pertenece(codigo)) {
            System.out.println("No se encontró la habitación con el código ingresado.");
        } else {
            System.out.println("Ingrese el puntaje del desafio a modificar:");
            puntaje = sc.nextInt();
            if (!habitaciones.perteneceDesafio(codigo, puntaje)) {
                System.out.println("No se encontró el desafío con el puntaje ingresado.");
            } else {
                mostrarMenuModificardesafio();
                numMenu = sc.nextInt();
                switch (numMenu) {
                    case 1:
                        sc.nextLine();
                        cambiarNombreDesafio(sc, habitaciones, codigo, puntaje);
                        break;
                    case 2:
                        cambiarTipoDesafio(sc, habitaciones, codigo, puntaje);
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

    public static void cambiarNombreDesafio(Scanner sc, ArbolAVL habitaciones, int codigo, int puntaje) {
        String nombre;
        System.out.println("Ingrese el nuevo nombre del desafío:");
        nombre = sc.nextLine();
        // Llamo a método de AVL que modifica el nombre del desafío y me devuelve un
        // boolean
        habitaciones.modificarNombreDesafio(codigo, puntaje, nombre);
        System.out.println("Nombre del desafío modificado correctamente.");

    }

    public static void cambiarTipoDesafio(Scanner sc, ArbolAVL habitaciones, int codigo, int puntaje) {
        String tipo;
        System.out.println("Ingrese el nuevo tipo del desafío:");
        tipo = sc.next();
        // Llamo a método de AVL que modifica el tipo del desafío y me devuelve un
        // boolean
        habitaciones.modificarTipoDesafio(codigo, puntaje, tipo);
        System.out.println("Tipo del desafío modificado correctamente.");

    }

    public static void eliminarDesafio(Scanner sc, ArbolAVL habitaciones) {
        int codigoHabitacion, puntaje;

        System.out.println("--> Baja de desafío");
        System.out.println("Ingrese el código de la habitación del desafío a eliminar:");
        codigoHabitacion = sc.nextInt();
        if (!habitaciones.pertenece(codigoHabitacion)) {
            System.out.println(
                    "No se encontró la habitación con el código ingresado. Por favor, ingrese un código válido.");
        } else {
            System.out.println("Ingrese el puntaje del desafío a eliminar:");
            puntaje = sc.nextInt();

            if (habitaciones.eliminarDesafio(codigoHabitacion, puntaje)) {
                System.out.println("Desafío eliminado correctamente.");
            } else {
                System.out.println("No se encontró el desafío con los datos ingresados.");
            }
        }
    }

    public static void agregarEquipo(Scanner sc, HashMap<String, Equipo> equipos,ArbolAVL habitaciones) {
        GestorArchivo archivo= new GestorArchivo();
        String nombre, linea;
        int puntajeExigido, codigo;
        System.out.println("--> Crear de equipo");
        System.out.println("Ingrese el nombre del equipo:");
        nombre = sc.nextLine();
        if (equipos.containsKey(nombre)) {
            System.out.println("El nombre ingresado ya existe. Por favor, ingrese un nombre diferente:");
        } else {
            linea = "E;" + nombre;
            System.out.println("Ingrese el puntaje exigido del equipo:");
            puntajeExigido = sc.nextInt();
            linea += ";" + puntajeExigido + ";0";
            System.out.println("Ingrese el codigo de la habitacion actual del equipo:");
            codigo = sc.nextInt();
            if(habitaciones.pertenece(codigo)){
            linea += ";" + codigo + ";0";
            archivo.cargarDatoLinea(linea, null, null, equipos);
            System.out.println("Equipo creado exitosamente.");
            }else{

            }
            System.out.println("No se pudo crear puesto que no existe una habitacion con dicho codigo.");
        }
    }
    public static void modificarEquipo(Scanner sc, HashMap<String, Equipo> equipos) {
        String nombre;
        int numMenu;
        System.out.println("Introduce el nombre del equipo a modificar:");
        nombre=sc.nextLine();
        if (equipos.containsKey(nombre)) {
            mostrarMenuModificarEquipo();
            numMenu = sc.nextInt();
            switch (numMenu) {
                case 1:
                    cambiarPuntajeExigido(sc, equipos, nombre);
                    break;
                case 2:
                    System.out.println("Saliendo de la modificación de equipo...");
                    break;
                default:
                    System.out.println("Opción no válida. Saliendo de la modificación de equipo...");
                    break;
            }
        } else {
            System.out.println("El equipo no existe.");
        }
    }
    public static void eliminarEquipo(Scanner sc, HashMap<String, Equipo> equipos) {
        String nombre;
        System.out.println("Introduce el nombre del equipo a eliminar:");
        nombre=sc.nextLine();
        if (equipos.containsKey(nombre)) {
            equipos.remove(nombre);
            System.out.println("Equipo eliminado");
        }else{
            System.out.println("El equipo no existe.");
        }
    }



    public static void cambiarPuntajeExigido(Scanner sc, HashMap<String, Equipo> equipos, String nombre) {
        int puntajeExigido;
        System.out.println("Ingrese el nuevo puntaje exigido del equipo:");
        puntajeExigido = sc.nextInt();
        equipos.get(nombre).setPuntajeExigido(puntajeExigido);
        System.out.println("Puntaje exigido del equipo modificado correctamente.");
    }

    public static void cambiarCodigoHabitacionActual(Scanner sc, HashMap<String, Equipo> equipos, String nombre,Grafo planoCasa) {
        int codigo;
        System.out.println("Ingrese el nuevo código de la habitación actual del equipo:");
        codigo = sc.nextInt();
        if(planoCasa.existeArco(codigo,equipos.get(nombre).getCodigoHabitacionActual())){

        System.out.println("Código de la habitación actual del equipo modificado correctamente.");
        }else{
        System.out.println("No se pueden mover a esa habitacion.");
    }
}
    public static void gestionConsultaEquipos(Scanner sc,HashMap<String, Equipo> equipos,ArbolAVL habitaciones,Grafo planoCasa){
        int numMenu;
        mostrarMenuConsultaEquipos();
        numMenu=sc.nextInt();
        switch (numMenu) {
            case 1:
                System.out.println("-->Mostrar informacion de equipo");
                sc.nextLine();
                mostrarEquipo(sc,equipos);
                break;
            case 2:
                System.out.println("--> Mostrar posbiles desafios");

                break;
            case 3:
                System.out.println("-->Jugar desafio");
                sc.nextLine();
                jugarDesafio(sc,equipos, habitaciones);
                break;
            case 4:
                System.out.println("--> Cambiar de habitacion ");
                sc.nextLine();
                cambiarDeHabitacion(sc,equipos,habitaciones,planoCasa);
                break;
            case 5:
                System.out.println("--> Puede salir ");
                sc.nextLine();
                puedeSalirEquipo(sc,equipos,habitaciones);
                break;
            case 6:
                System.out.println("Saliendo de consulta de equipos... ");
                break;
            default:
                break;
        }
    }
    public static void mostrarEquipo(Scanner sc,HashMap<String, Equipo> equipos){
        String nombre;

        System.out.println("Introduce el nombre del equipo a modificar:");
        nombre=sc.nextLine();
        if (equipos.containsKey(nombre)) {
            Equipo equipo= equipos.get(nombre);
            equipo.toString();
            }else{
                System.out.println("El equipo no existe.");
            }
    }
    public static void jugarDesafio(Scanner sc,HashMap<String, Equipo> equipos,ArbolAVL habitaciones){
        String nombre;
        int codigo,puntaje;

        System.out.println("Introduce el nombre del equipo que juega el desafio:");
        nombre=sc.nextLine();

        if (equipos.containsKey(nombre)) {
            System.out.println("Introduce el codigo de la habitacion:");
            codigo=sc.nextInt();
            if(habitaciones.pertenece(codigo)){
                Equipo equipo= equipos.get(nombre);
                System.out.println("Introduce el puntaje del desafio que estan jugando:");
                puntaje=sc.nextInt();
                if(habitaciones.perteneceDesafio(codigo,puntaje)){
                equipo.cargarDesafiosRealizados(codigo,puntaje);
                equipo.actualizarPuntajes();
                System.out.println("Se ha agregado el desafio como realizado para el equipo "+nombre+".");
                }else{
                    System.out.println("El desafio no existe.");
                }
            }else{
                System.out.println("La habitacion no existe.");
            }
        }else{
            System.out.println("El equipo no existe.");
        }
    }
    public static void cambiarDeHabitacion(Scanner sc,HashMap<String, Equipo> equipos,ArbolAVL habitaciones,Grafo planoCasa){
        String nombre;
        int codigo;
        System.out.println("Introduce el nombre del equipo que juega el desafio:");
        nombre=sc.nextLine();
        if (equipos.containsKey(nombre)) {
            System.out.println("introduce el codigo de la habitacion a la que quieren avanzar");
            codigo=sc.nextInt();
            if(habitaciones.pertenece(codigo)){
                Equipo equipo=equipos.get(nombre);
                if(planoCasa.existeArco(equipo.getCodigoHabitacionActual(),codigo)){
                
                    if(planoCasa.esPosibleLlegar(equipo.getCodigoHabitacionActual(),codigo,equipo.getPuntajeAcumulado())){
                            equipo.setCodigoHabitacionActual(codigo);
                            equipo.actualizarPuntajeActual();
                        }else{
                            System.err.println("El equipo no tiene el puntaje para cambiar de habitacion.");
                        }
                }else{
                    System.out.println("La habitacion no es contigua.");
                }
            }else{
                System.out.println("La habitacion no existe.");
            }

        }else{
            System.out.println("El equipo no existe.");
        }
    }
    public static void puedeSalirEquipo(Scanner sc,HashMap<String, Equipo> equipos, ArbolAVL habitaciones){
        String nombre;
        System.out.println("Introduce el nombre del equipo que juega el desafio:");
        nombre=sc.nextLine();
        if (equipos.containsKey(nombre)) {
            Equipo equipo = equipos.get(nombre);
            if(habitaciones.tieneSalida(equipo.getCodigoHabitacionActual())){
                if(equipo.getPuntajeAcumulado()>=equipo.getPuntajeExigido()){
                    equipos.remove(nombre);
                    System.out.println("El equipo "+nombre+" logro escapar.");
                }else{
                    System.out.println("El equipo no tiene el puntaje para escapar.");
                }
            }else{
                System.out.println("La habitacion no tiene salida.");
            }
        }else{
            System.out.println("El equipo no existe.");
        }
    }

    public static void gestionConsultaGeneral(Scanner sc, ArbolAVL habitaciones, Grafo planoCasa,
            HashMap<String, Equipo> equipos) {
        System.out.println("Consulta general de habitaciones, desafíos y equipos.");
        int numMenu;
        mostrarMenuConsultaGeneral();
        numMenu = sc.nextInt();
        switch (numMenu) {
            case 1:
                System.out.println("Imprimiendo AVL de habitaciones.");
                System.out.println(habitaciones.toString());
                break;
            case 2:
                System.out.println("Imprimiendo Grafo de habitaciones.");
                System.out.println(planoCasa.toString());
                break;
            case 3:
                System.out.println("Imprimiendo Hash de equipos.");
                System.out.println(equipos.toString());
                System.out.println("");
                System.out.println("");
                System.out.println("");
                //System.out.println(equipos.get("Misterio Inc").getDesafiosCompletados().toString());
                break;
            case 4:
                System.out.println("Saliendo de la consulta general...");
                break;

            default:
                System.out.println("Número no reconocido. Volviendo al menu Principal.");
                break;
        }
    }

    public static void mostrarMenu() {
        System.out.println("========== MENU PRINCIPAL ==========");
        System.out.println("1. Carga inicial del sistema");
        System.out.println("2. Altas, Bajas y Modificaciones (ABM)");
        System.out.println("3. Consulta sobre habitaciones");
        System.out.println("4. Consultas sobre desafíos");
        System.out.println("5. Consultas sobre equipos");
        System.out.println("6. Consulta general");
        System.out.println("0. Salir");
        System.out.print("Introduce un número para avanzar: ");
    }

    public static void mostrarMenuABM() {
        System.out.println("2. MENU DE ABM");
        System.out.println("1. ABM de habitacion");
        System.out.println("2. ABM de desafío");
        System.out.println("3. ABM de equipo");
        System.out.println("10. Salir");
        System.out.print("Introduce un numero para avanzar: ");
    }

    public static void mostrarMenuABMhabitacion() {
        System.out.println("2. MENU DE ABM Habitacion");
        System.out.println("1. crear de habitacion");
        System.out.println("2. modificacion de habitacion");
        System.out.println("3. baja de habitación");
        System.out.println("4. Salir");
        System.out.print("Introduce un numero para avanzar: ");
    }

    public static void mostrarMenuABMdesafio() {
        System.out.println("2. MENU DE ABM desafio");
        System.out.println("1. crear de desafío");
        System.out.println("2. Modificación de desafío");
        System.out.println("3. Baja de desafío");
        System.out.println("4. Salir");
        System.out.print("Introduce un numero para avanzar: ");
    }

    public static void mostrarMenuABMequipo() {
        System.out.println("2. MENU DE ABM equipo");
        System.out.println("1. crear de equipo");
        System.out.println("2. Modificación de equipo");
        System.out.println("3. Baja de equipo");
        System.out.println("4. Salir");
        System.out.print("Introduce un numero para avanzar: ");
    }
    public static void mostrarMenuModificarEquipo(){
        System.out.println("2. MENU DE ABM modificacion");
        System.out.println("1. Modificación de puntaje exigido del equipo");
        System.out.println("3. Modificación de código de habitación actual del equipo");
        System.out.println("4. Agregar desafio resuelto");
        System.out.println("5. Salir");
        System.out.print("Introduce un numero para avanzar: ");
    }

    public static void mostrarMenuHabitacion() {
        System.out.println("3. CONSULTA SOBRE HABITACIONES");
        System.out.println("1. mostrar habitacion");
        System.out.println("2. mostrar habitaciones contiguas");
        System.out.println("3. es posible llegar de habitacion A a habitacion B");
        System.out.println("4. cual es el minimo puntaje para ir de habitacion A a habitacion B");
        System.out.println("5. cuales son las formas de ir a de habitacion A a habitacion B sin pasar por la habitacion C");
        System.out.println("6. Salir");
        System.out.print("Introduce un numero para elegir una consulta: ");
    }

    public static void mostrarMenuDesafio() {
        System.out.println("--- 4. CONSULTAS SOBRE DESAFÍOS ---");
        System.out.println("1. mostrarDesafio");
        System.out.println("2. mostrarDesafiosResueltos");
        System.out.println("3. verificarDesafioResuelto");
        System.out.println("4. mostrarDesafiosTipo");
        System.out.println("5. Salir");
        System.out.print("Introduce un numero para elegir una consulta: ");
    }

    public static void mostrarMenuModificarHabitacion() {
        System.out.println("Seleccionaste: Altas, Bajas y Modificaciones de habitaciones.");
        System.out.println(". MODIFICACION DE HABITACIÓN");
        System.out.println("1. Modificar nombre de la habitación");
        System.out.println("2. Modificar planta de la habitación");
        System.out.println("3. Modificar medida de la habitación");
        System.out.println("4. Salir");
        System.out.print("Introduce un numero para elegir una modificación: ");
    }

    public static void mostrarMenuModificardesafio() {
        System.out.println("Seleccionaste: Altas, Bajas y Modificaciones de desafíos.");
        System.out.println(". MODIFICACION DE DESAFÍO");
        System.out.println("1. Modificar nombre del desafío");
        System.out.println("2. Modificar tipo del desafío");
        System.out.println("3. Salir");
        System.out.print("Introduce un numero para elegir una modificación: ");
    }

        public static void mostrarMenuConsultaEquipos() {
        System.out.println("Seleccionaste Menu consulta equipos");
        System.out.println("1. Mostrar informacion de equipo");
        System.out.println("2. Mostrar posibles desafios");
        System.out.println("3. Jugar desafio");
        System.out.println("4. Cambiar de habitacion ");
        System.out.println("5. Puede salir ");
        System.out.println("6. Salir ");
    }
    public static void mostrarMenuConsultaGeneral() {
        System.out.println("Seleccionaste Menu consulta general");
        System.out.println("1. Ver AVL");
        System.out.println("2. Ver Grafo");
        System.out.println("3. Ver Hash");
        System.out.println("4. Salir");
    }

    // punto 3

    // punto 4
    private static void mostrarDesafio(Scanner sc, ArbolAVL habitaciones) {
        System.out.println("Ingrese el codigo del desafio ");
        Comparable codigoDes = sc.nextInt();
        System.out.println("Ingrese el numero de habitacion");
        Comparable numHab = sc.nextInt();
        String str = habitaciones.mostrarDesafioAux(codigoDes, numHab);
        System.out.println(str);
    }

    private static void mostrarDesafiosResueltos(Scanner sc, HashMap<String, Equipo> listaEquipos,
            ArbolAVL habitaciones) {
        String str = "";
        System.out.println("Ingrese el nombre del equipo: ");
        String nombreEq = sc.nextLine();
        Equipo eq = buscarEquipo(nombreEq, listaEquipos);
        if (eq != null) {
            str = "El equipo " + nombreEq + " resolvio estos desafios: \n" + habitaciones.listarDesafiosHabitacion(eq);
        } else {
            str = "No existe el equipo " + nombreEq;
        }
        System.out.println(str);
    }

    private static Equipo buscarEquipo(String nombreEq, HashMap<String, Equipo> mapaEq) {
        Equipo unEq = mapaEq.get(nombreEq);
        return unEq;
    }

    private static void verificarDesafioResuelto(Scanner sc, HashMap<String, Equipo> mapaEq) {
        System.out.println("Ingrese el nombre del equipo: ");
        String nombreEq = sc.nextLine();
        Equipo eq = buscarEquipo(nombreEq, mapaEq);
        System.out.println("Ingrese el codigo del desafio: ");
        int des = sc.nextInt();
        System.out.println("Ingrese el numero de la habitacion: ");
        int hab = sc.nextInt();
        boolean exito = verificarDesafioResueltoAux(eq, des, hab);
        if (exito) {
            System.out.println("El equipo lo resolvio");
        } else {
            System.out.println("El equipo no lo resolvio o no existe");
        }
    }

    private static boolean verificarDesafioResueltoAux(Equipo eq, Integer des, Integer hab) {
        boolean exito = false;
        if (eq != null) {
            HashMap <Integer,Lista> desafiosResueltos = eq.getDesafiosCompletados();
            if (desafiosResueltos != null) {
                Lista desafiosHab = desafiosResueltos.get(hab);
                if (!desafiosHab.esVacia()) {
                    int aux = desafiosHab.localizar(des);
                    if (aux > -1)
                        exito = true;
                }

            }
        }
        return exito;
    }
    // punto 5 consultas sobre habitaciones

    //agregar a modificacion de habitacion carga de conexiones
    //agregar a modificacion de equipos cambio de habitacion si hay puertas y puntos totales
    //y actualizar puntaje actual.
}
