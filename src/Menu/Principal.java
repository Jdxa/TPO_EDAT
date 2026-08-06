package Menu;

import java.util.HashMap;
import java.util.Scanner;

import Estructuras.GrafoEtiquetado.Grafo;
import Estructuras.Lineales.Lista;
import Estructuras.TablaAVL.ArbolAVL;
import Modelo.Desafio;
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
                    System.out.println("Seleccionaste: Carga inicial del sistema.");
                    if (estaCargado) {
                        System.out.println("Los datos ya fueron cargados previamente. No se puede cargar nuevamente.");
                        break;
                    } else {
                        archivo.cargarDatos(habitaciones, planoCasa, equipo);
                    //  GestorArchivo.registrarLog("Se cargaron los datos");
                        estaCargado = true;
                    }

                    break;

                case 2:
                    System.out.println("Seleccionaste: Altas, Bajas y Modificaciones (ABM) de habitaciones, desafíos y equipos.");
                    gestionABM(sc, habitaciones, equipo,planoCasa);
                    break;

                case 3:
                    System.out.println("Seleccionaste: Consultas sobre habitaciones");
                    gestionConsultaHabitacion(sc,habitaciones,planoCasa);

                    break;

                case 4:
                    System.out.println("Seleccionaste: Consultas sobre desafíos");
                    gestionConsultaDesafios(sc,habitaciones,equipo);
                    break;
                case 5:
                    System.out.println("Seleccionaste: Consultas sobre equipos");
                    gestionConsultaEquipos(sc,equipo,habitaciones,planoCasa);
                    break;
                case 6:
                    System.out.println("Seleccionaste: Consulta general");
                    gestionConsultaGeneral(sc, habitaciones, planoCasa, equipo);
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

    public static void gestionABM(Scanner sc, ArbolAVL habitaciones,
        HashMap<String, Equipo> equipos,Grafo planoCasa) {
        mostrarMenuABM();
        int opcionABM = sc.nextInt();

        switch (opcionABM) {
            case 1:
                System.out.println("Seleccionaste: Menu ABM de habitaciones");
                habitacionesABM(sc, habitaciones,planoCasa);
            break;

            case 2:
                System.out.println("Seleccionaste: Menu ABM de desafios");
                desafiosABM(sc, habitaciones);
            break;

            case 3:
                System.out.println("Seleccionaste: Menu ABM de equipos");
                equiposABM(sc, equipos,planoCasa,habitaciones);
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
     public static void habitacionesABM(Scanner sc, ArbolAVL habitaciones,Grafo planoCasa) {
        int opcionABM;
        mostrarMenuABMhabitacion();
        opcionABM = sc.nextInt();

        switch (opcionABM) {
            case 1:
                System.out.println("Seleccionaste: crear de habitacion");
                System.out.println(agregarHabitacion(sc, habitaciones,planoCasa));
            break;

            case 2:
                System.out.println("Seleccionaste: modificar habitacion");
                System.out.println(modificarHabitacion(sc, habitaciones,planoCasa));
            break;

            case 3:
                System.out.println("Seleccionaste: baja de habitacion");
                System.out.println(eliminarHabitacion(sc, habitaciones,planoCasa));
            break;

            case 4:
                System.out.println("Saliendo al menu principal");
            break;

            default:
                System.out.println("Opción no válida. Saliendo al menu principal");
            break;
        }
    }

    public static String agregarHabitacion(Scanner sc, ArbolAVL habitaciones,Grafo planoCasa) {
        int codigo;
        String palabra, linea,res;
        GestorArchivo archivo= new GestorArchivo();
        System.out.println("Ingrese el código que le quiere dar a la habitación:");
        codigo = sc.nextInt();
        if (habitaciones.pertenece(codigo)) {
        linea = "H;" + codigo;
        System.out.println("Ingrese el nombre de la habitación:");
        sc.nextLine();
        palabra = sc.nextLine();
        linea += ";" + palabra;
        System.out.println("Ingrese la planta de la habitación:");
        codigo = sc.nextInt();
        linea += ";" + codigo;
        System.out.println("Ingrese la medida de la habitación:");
        codigo = sc.nextInt();
        linea += ";" + codigo + ";false";
        archivo.cargarDatoLinea(linea, habitaciones,planoCasa, null);
        GestorArchivo.registrarLog("Se agrego una habitacion con el nombre: "+palabra);

        res="Habitación creada exitosamente.";
        }else{
        res="El codigo de la habitacion ya esta en uso.";
        }
        return res;
    }

    public static String modificarHabitacion(Scanner sc, ArbolAVL habitaciones, Grafo planoCasa) {
        int codigo, numMenu;
        String res;
        System.out.println("Ingrese el código de la habitación a modificar:");
        codigo = sc.nextInt();
        if (habitaciones.pertenece(codigo)) {
            mostrarMenuModificarHabitacion();
            numMenu = sc.nextInt();
            switch (numMenu) {
                case 1:
                    sc.nextLine();
                    res=cambiarNombreHabitacion(sc, habitaciones, codigo);
                    break;

                case 2:
                    res=cambiarPlantaHabitacion(sc, habitaciones, codigo);
                    break;

                case 3:
                    res=cambiarMedidaHabitacion(sc, habitaciones, codigo);
                    break;

                case 4:
                    res=añadirCaminos(sc,planoCasa,codigo);
                    break;

                case 5:
                    res="Saliendo al menu principal";
                    break;

                default:
                    res="Opción no válida. Saliendo al menu principal";
                break;
            }
        } else {
            res="No se encontró la habitación con el código ingresado.";
        }
        return res;
    }

    public static String cambiarNombreHabitacion(Scanner sc, ArbolAVL habitaciones, int codigo) {
        String nombre;
        
        System.out.println("Ingrese el nuevo nombre de la habitación:");
        nombre = sc.nextLine();
        //opero desde AVL los atributos de habitacion
        habitaciones.modificarNombreHabitacion(codigo, nombre);
        GestorArchivo.registrarLog("Se modifico el nombre de la habitacion "+codigo+" por: "+nombre);
        return "Nombre de la habitación modificado correctamente.";
    }

    public static String cambiarPlantaHabitacion(Scanner sc, ArbolAVL habitaciones, int codigo) {
        int planta;
        System.out.println("Ingrese la nueva planta de la habitación:");
        planta = sc.nextInt();
        //opero desde AVL los atributos de habitacion
        habitaciones.modificarPlantaHabitacion(codigo, planta);
        GestorArchivo.registrarLog("Se modifico la plata de la habitacion "+codigo+" por: "+planta);
        return "Planta de la habitación modificada correctamente.";
    }

    private static String cambiarMedidaHabitacion(Scanner sc, ArbolAVL habitaciones, int codigo) {
        int medida;
        System.out.println("Ingrese la nueva medida de la habitación:");
        medida = sc.nextInt();
        //opero desde AVL los atributos de habitacion
        habitaciones.modificarMedidaHabitacion(codigo, medida);
        GestorArchivo.registrarLog("Se modifico la medida de la habitacion "+codigo+" por: "+medida);
        return "Medida de la habitación modificada correctamente.";
    }

    private static String eliminarHabitacion(Scanner sc, ArbolAVL habitaciones,Grafo planoCasa) {
        int codigo;
        String res;
        System.out.println("inserte el codigo de la habitacion a eliminar.");
        codigo = sc.nextInt();
        // Llamo a metodo AVL que elimina si existe habitacion y devuelve boolean
        if (habitaciones.eliminar(codigo)) {
            planoCasa.eliminarVertice(codigo);
            GestorArchivo.registrarLog("Se elimino la habitacion "+codigo);
            res="Habitación eliminada correctamente.";
        } else {
            res="No se encontró la habitación con el código "+codigo+".";
        }
        return res;
    }
    public static String añadirCaminos(Scanner sc, Grafo planoCasa, int codigo){
        int destino,puntajeRequerido;
        String linea,res;
        GestorArchivo archivo= new GestorArchivo();
        linea="P;"+codigo+";";
        System.out.println("inserte la habitacion destino.");
        destino = sc.nextInt();
        if(planoCasa.existeVertice(destino)){
        linea+=destino+";";
        System.out.println("inserte el requisito de puntos para usar la puerta.");
        puntajeRequerido = sc.nextInt();
        linea+=puntajeRequerido;
        archivo.cargarDatoLinea(linea, null, planoCasa, null);
        GestorArchivo.registrarLog("Se agrego la puerta con origen "+codigo+" y destino "+destino+" con un puntaje de "+puntajeRequerido);
        res="Se cargo exitosamente la nueva puerta";
        }else{
        res="no existe la habitacion destino";
        }
        return res;
    }

//------------------------------------------------------------------------------
//----------------Desafios ABM--------------------------------------------------
//------------------------------------------------------------------------------

    public static void desafiosABM(Scanner sc, ArbolAVL habitaciones) {
        int numMenu;
        mostrarMenuABMdesafio();
        numMenu = sc.nextInt();

        switch (numMenu) {
            case 1:
                System.out.println("Seleccionaste: crear desafio");
                System.out.println(agregarDesafio(sc, habitaciones));
            break;

            case 2:
                System.out.println("Seleccionaste: modificar desafio");
                System.out.println(modificarDesafio(sc, habitaciones));
            break;

            case 3:
                System.out.println("Seleccionaste: baja de desafio");
                System.out.println(eliminarDesafio(sc, habitaciones));
            break;

            case 4:
                System.out.println("Saliendo del menú de ABM...");
            break;

            default:
                System.out.println("Opción no válida.");
            break;
        }
    }

    public static String agregarDesafio(Scanner sc, ArbolAVL habitaciones) {
        int codigoHabitacion, puntaje;
        String linea, palabra,res;
        GestorArchivo archivo=new GestorArchivo();
        
        System.out.println("Ingrese el código de la habitación a la que pertenece el desafío:");
        codigoHabitacion = sc.nextInt();
        if (habitaciones.pertenece(codigoHabitacion)) {
        System.out.println("Ingrese el puntaje del desafío:");
        puntaje = sc.nextInt();
        if(habitaciones.perteneceDesafio(codigoHabitacion, puntaje)){
        linea = "D;" + puntaje + ";" + codigoHabitacion;
        System.out.println("Ingrese el nombre del desafio:");
        sc.nextLine();
        palabra = sc.nextLine();
        linea += ";" + palabra;
        System.out.println("Ingrese el tipo del desafio:");
        palabra = sc.next();
        linea += ";" + palabra;
        archivo.cargarDatoLinea(linea, habitaciones, null, null);
        GestorArchivo.registrarLog("Se creo un desafio con codigo "+puntaje+" en la habitacion "+codigoHabitacion);
        res="Desafío creado exitosamente.";
        }else{
        res="El puntaje ingresado ya le pertenece a otro desafio de la habitacion";
        }
        }else{
        res="No se encontró la habitación con el código ingresado.";
        }
        return res;
    }

    public static String modificarDesafio(Scanner sc, ArbolAVL habitaciones) {
        int codigo, numMenu, puntaje;
        String res;
        System.out.println("Ingrese el código de la habitación a la que pertenece el desafío:");
        codigo = sc.nextInt();
        if (habitaciones.pertenece(codigo)) {
            System.out.println("Ingrese el puntaje del desafio a modificar:");
            puntaje = sc.nextInt();
            if (habitaciones.perteneceDesafio(codigo, puntaje)) {
                mostrarMenuModificardesafio();
                numMenu = sc.nextInt();
                switch (numMenu) {
                    case 1:
                        sc.nextLine();
                        System.out.println("Seleccionaste: Modificar nombre del desafio");
                        res=cambiarNombreDesafio(sc, habitaciones, codigo, puntaje);
                    break;

                    case 2:
                        System.out.println("Seleccionaste: Modificar tipo del desafio");
                        res=cambiarTipoDesafio(sc, habitaciones, codigo, puntaje);
                    break;

                    case 3:
                        res="Saliendo al menu principal";
                    break;

                    default:
                        res="Opción no válida. Saliendo al menu principal";
                    break;
                }
        }else{
        res="No se encontró el desafio ingresado, en la habitacion ingresada.";
        }
        }else{
        res="No se encontró la habitación con el código ingresado.";
        }
        return res;
    }

    public static String cambiarNombreDesafio(Scanner sc, ArbolAVL habitaciones, int codigo, int puntaje) {
        String nombre;
        System.out.println("Ingrese el nuevo nombre del desafío:");
        nombre = sc.nextLine();
        // Llamo a método de AVL que modifica el nombre del desafío y me devuelve un
        // boolean
        habitaciones.modificarNombreDesafio(codigo, puntaje, nombre);
        GestorArchivo.registrarLog("Se cambio el nombre del desafio "+codigo+" por: "+nombre);
        return "Nombre del desafío modificado correctamente.";

    }

    public static String cambiarTipoDesafio(Scanner sc, ArbolAVL habitaciones, int codigo, int puntaje) {
        String tipo;
        System.out.println("Ingrese el nuevo tipo del desafío:");
        tipo = sc.next();
        // Llamo a método de AVL que modifica el tipo del desafío y me devuelve un
        // boolean
        habitaciones.modificarTipoDesafio(codigo, puntaje, tipo);
        GestorArchivo.registrarLog("Se cambio el tipo, al desafio "+codigo+" por: "+tipo);
        return "Tipo del desafío modificado correctamente.";

    }

    public static String eliminarDesafio(Scanner sc, ArbolAVL habitaciones) {
        int codigoHabitacion, puntaje;
        String res;

        System.out.println("--> Baja de desafío");
        System.out.println("Ingrese el código de la habitación del desafío a eliminar:");
        codigoHabitacion = sc.nextInt();
        if (habitaciones.pertenece(codigoHabitacion)) {
  
            System.out.println("Ingrese el puntaje del desafío a eliminar:");
            puntaje = sc.nextInt();
            if(habitaciones.eliminarDesafio(codigoHabitacion, puntaje)){
                GestorArchivo.registrarLog("Se elimino el desafio "+puntaje+" en la habitacion "+codigoHabitacion);
                res="Desafío eliminado correctamente.";
            } else {
                res="No se encontró el desafio ingresado, en la habitacion ingresada.";
            }
        }else{
            res="No se encontró la habitación con el código ingresado.";
        }
        return res;
    }

//------------------------------------------------------------------------------
//----------------Equipos ABM---------------------------------------------------
//------------------------------------------------------------------------------

public static void equiposABM(Scanner sc, HashMap<String, Equipo> equipos,
    Grafo planoCasa,ArbolAVL habitaciones) {
        int opcionABM;
        mostrarMenuABMequipo();
        opcionABM = sc.nextInt();
        switch (opcionABM) {
            case 1:
                sc.nextLine();
                System.out.println("Seleccionaste: crear equipo");
                System.out.println(agregarEquipo(sc, equipos, habitaciones));
                break;

            case 2:
                sc.nextLine();
                System.out.println("Seleccionaste: modificar equipo");
                System.out.println(modificarEquipo(sc, equipos));
                break;

            case 3:
                sc.nextLine();
                System.out.println("Seleccionaste: Baja de equipo");
                System.out.println(eliminarEquipo(sc, equipos));
                break;
                
            case 4:
                System.out.println("Saliendo del menú de ABM...");
            break;

            default:
                System.out.println("Opción no válida.");
            break;
        }
    }

    public static String agregarEquipo(Scanner sc, HashMap<String, Equipo> equipos,ArbolAVL habitaciones) {
        GestorArchivo archivo= new GestorArchivo();
        String nombre, linea,res;
        int puntajeExigido, codigo;
        System.out.println("--> Crear de equipo");
        System.out.println("Ingrese el nombre del equipo:");
        nombre = sc.nextLine();
        if (equipos.containsKey(nombre)) {
            res="El nombre ingresado ya existe. Por favor, ingrese un nombre diferente:";
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
            GestorArchivo.registrarLog("Se agrego el equipo "+nombre);
            res="Equipo creado exitosamente.";
            }else{
            res="No se pudo crear puesto que no existe una habitacion con dicho codigo.";
            }
        }
        return res;
    }
    public static String modificarEquipo(Scanner sc, HashMap<String, Equipo> equipos) {
        String nombre,res;
        int numMenu;
        System.out.println("Introduce el nombre del equipo a modificar:");
        nombre=sc.nextLine();
        if (equipos.containsKey(nombre)) {
            mostrarMenuModificarEquipo();
            numMenu = sc.nextInt();
            switch (numMenu) {
                case 1:
                    res=(cambiarPuntajeExigido(sc, equipos, nombre));
                    break;
                case 2:
                    res="Saliendo de la modificación de equipo...";
                    break;
                default:
                    res="Opción no válida. Saliendo de la modificación de equipo...";
                    break;
            }
        } else {
            res="El equipo no existe.";
        }
        return res;
    }
    
    public static String cambiarPuntajeExigido(Scanner sc, HashMap<String, Equipo> equipos, String nombre) {
        int puntajeExigido;
        System.out.println("Ingrese el nuevo puntaje exigido del equipo:");
        puntajeExigido = sc.nextInt();
        equipos.get(nombre).setPuntajeExigido(puntajeExigido);
        GestorArchivo.registrarLog("Se modifico el puntaje exigido del equipo "+nombre+" por: "+puntajeExigido);
        return "Puntaje exigido del equipo modificado correctamente.";
    }

    public static String eliminarEquipo(Scanner sc, HashMap<String, Equipo> equipos) {
        String nombre,res;
        System.out.println("Introduce el nombre del equipo a eliminar:");
        nombre=sc.nextLine();
        if (equipos.remove(nombre)!=null) {
            GestorArchivo.registrarLog("Se elimino el equipo "+nombre);
            res="Equipo eliminado";
        }else{
            res="El equipo no existe.";
        }
        return res;
    }

    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------
    //---------Punto 3, Consulta Habitacion--------------------------------------
    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------

    public static void gestionConsultaHabitacion(Scanner sc,ArbolAVL habitaciones,Grafo planoCasa){
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
                System.out.println(habitaciones.mostrarHabitacion(numMenu));
            break;

            case 2:
                System.out.println("Seleccionaste: mostrar habitaciones contiguas");
                System.out.println("Ingrese un numero de habitacion:");
                numMenu = sc.nextInt();
                // llamo metodo de Grafo que me da un String de las habitaciones contiguas
                System.out.println(planoCasa.listarAdyacentesString(numMenu));
            break;

            case 3:
                System.out.println("Seleccionaste: es posible llegar de habitacion A a habitacion B");
                
            break;

            case 4:
                System.out.println("Seleccionaste: cual es el minimo puntaje para ir de habitacion A a habitacion B");
                Lista mejorCamino = new Lista();
                System.out.println("Ingrese hab1: ");
                int hab1 = sc.nextInt();
                System.out.println("Ingrese hab2: ");
                int hab2 = sc.nextInt();
                int puntajeMin = planoCasa.minimoPuntaje(hab1, hab2, mejorCamino);
                System.out.println("El puntaje min es: "+puntajeMin);
                System.out.println("El mejor camino es: "+mejorCamino.toString());

            break;

            case 5:
                System.out.println("Seleccionaste: cuales son las formas de ir a de habitacion A a habitacion B sin pasar por la habitacion C");

            break;

            case 6:
                System.out.println("Saliendo al menu principal");
            break;
                        
            default:
                System.out.println("Opción no válida. Saliendo al menu principal");
            break;
        }
    }

    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------
    //---------Punto 4, Consulta Desafios----------------------------------------
    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------

public static void gestionConsultaDesafios(Scanner sc,ArbolAVL habitaciones,
    HashMap<String, Equipo> equipos ){
    int numMenu;
    mostrarMenuDesafio();
    numMenu = sc.nextInt();
    sc.nextLine();
    switch (numMenu) {
        case 1:
            System.out.println("Seleccionaste: mostrar desafio");
            mostrarDesafio(sc, habitaciones);
        break;

        case 2:
            System.out.println("Seleccionaste: mostrar desafios resueltos");
            mostrarDesafiosResueltos(sc, equipos, habitaciones);
        break;

        case 3:
            System.out.println("Seleccionaste: verificar desafio resuelto");
            verificarDesafioResuelto(sc, equipos);
        break;

        case 4:
            System.out.println("Seleccionaste: mostrar desafios tipo");
            mostrarDesafiosPorTipo(sc,habitaciones);
        break;
        
        case 5:
            System.out.println("Saliendo al menu principal");
        break;
                        
        default:
            System.out.println("Opción no válida. Saliendo al menu principal");
        break;
    }
    }
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
    public static void mostrarDesafiosPorTipo(Scanner sc, ArbolAVL habitaciones){
        String str = "";
        System.out.println("Ingrese la clave de la habitacion: ");
        int claveHabitacion = sc.nextInt();
        System.out.println("Ingrese el rango inferior [a]: ");
        int a = sc.nextInt(); //Rango inferior
        System.out.println("Ingrese el rango superior [b]: ");
        int b = sc.nextInt(); //Rango Superior
        System.out.println("Ingrese el tipo de desafio: ");
        sc.nextLine();
        String x = sc.nextLine();
        if (!habitaciones.esVacio()) {
            Lista l =habitaciones.filtrarPorRango(claveHabitacion, a, b);
            if (!l.esVacia()) {
                str+= "Los desafios del tipo "+x+ " en la habitacion "+claveHabitacion +" son los siguientes: \n";
                str += filtrarPorTipo(l, x);
            }else{
                str += "No hay desafios con esos filtros";
            }
        }
        System.out.println(str);
    }

    private static String filtrarPorTipo(Lista enRango, String x){
        String str = "";
        int i = 1;
        while (i <= enRango.longitud()) {
            Desafio desafioActual = (Desafio)enRango.recuperar(i);
            if (desafioActual.getTipo().equals(x)) {
                str += desafioActual.toString() + "\n";
            }
            i++;
        }
        return str;
    }

    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------
    //---------Punto 5, Consulta Equipos-----------------------------------------
    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------


    public static void gestionConsultaEquipos(Scanner sc,HashMap<String, Equipo> equipos,
        ArbolAVL habitaciones,Grafo planoCasa){
        int numMenu;
        mostrarMenuConsultaEquipos();
        numMenu=sc.nextInt();
        switch (numMenu) {
            case 1:
                System.out.println("Seleccionaste: Mostrar informacion de equipo");
                sc.nextLine();
                System.out.println(mostrarEquipo(sc,equipos));
            break;

            case 2:
                System.out.println("Seleccionaste: Mostrar posibles desafios");
                sc.nextLine();
                System.out.println(mostrarPosiblesDesafios(sc,equipos,habitaciones,planoCasa));

            break;

            case 3:
                System.out.println("Seleccionaste: Jugar desafio");
                sc.nextLine();
                System.out.println(jugarDesafio(sc,equipos, habitaciones));
            break;

            case 4:
                System.out.println("Seleccionaste: Cambiar de habitacion ");
                sc.nextLine();
                System.out.println(cambiarDeHabitacion(sc,equipos,habitaciones,planoCasa));
            break;

            case 5:
                System.out.println("Seleccionaste: Puede salir ");
                sc.nextLine();
                System.out.println(puedeSalirEquipo(sc,equipos,habitaciones));
            break;

            case 6:
                System.out.println("Saliendo al menu principal.");
            break;

            default:
                System.out.println("Opción no válida. Saliendo al menu principa.");
            break;
        }
    }

    public static String mostrarEquipo(Scanner sc,HashMap<String, Equipo> equipos){
        String nombre,res;
        System.out.println("Introduce el nombre del equipo a mostrar:");
        nombre=sc.nextLine();
        if (equipos.containsKey(nombre)) {
            res=(equipos.get(nombre).toString());
            }else{
                res="El equipo no existe.";
            }
        return res;
    }
    public static String mostrarPosiblesDesafios(Scanner sc,HashMap<String, Equipo> equipos,
        ArbolAVL habitaciones,Grafo planoCasa ){
        String res,nombre;
        int codActual,codigo,puntajeNecesario;

        System.out.println("Introduce el nombre del equipo que juega el desafio:");
        nombre=sc.nextLine();
        if (equipos.containsKey(nombre)) {
            Equipo equipo=equipos.get(nombre);
            codActual=equipo.getCodigoHabitacionActual();
            System.out.println("introduce el codigo de la habitacion a la que quieren avanzar");
            codigo=sc.nextInt();
            if(planoCasa.existeArco(codActual,codigo)){
               
                puntajeNecesario=planoCasa.minimoPuntaje(codActual, codigo, new Lista())-equipo.getPuntajeActual();
                //obtengo el puntaje del camino mas corto que seria la etiqueta entre adyacentes
                // y luego le resto el puntaje actual del equipo para obtener el valor que deberia
                //buscar que tengas los desafios como minimo para que solo resolviendo uno puedan
                //avanzar
                res="los desafios que completando uno solo pueden cambiar de habitacion son:\n";
                res+=habitaciones.listarMayoesoIgualesDesafios(codigo,puntajeNecesario);
            }else{
                res="la habitacion no es contigua";
            }
        }else{
            res="El equipo no existe.";
        }
        return res;
    }

    public static String jugarDesafio(Scanner sc,HashMap<String, Equipo> equipos,ArbolAVL habitaciones){
        String nombre,res;
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
                res="Se ha agregado el desafio como realizado para el equipo "+nombre+".";
                }else{
                    res="No existe un desafio con ese puntaje en esta habitacion.";
                }
            }else{
                res="La habitacion no existe.";
            }
        }else{
            res="El equipo no existe.";
        }
        return res;
    }

    public static String cambiarDeHabitacion(Scanner sc,HashMap<String, Equipo> equipos,
        ArbolAVL habitaciones,Grafo planoCasa){
        String nombre,res;
        int codigo;
        System.out.println("Introduce el nombre del equipo que juega el desafio:");
        nombre=sc.nextLine();
        if (equipos.containsKey(nombre)) {
            System.out.println("introduce el codigo de la habitacion a la que quieren avanzar");
            codigo=sc.nextInt();
            if(habitaciones.pertenece(codigo)){
                Equipo equipo=equipos.get(nombre);
                if(planoCasa.existeArco(equipo.getCodigoHabitacionActual(),codigo)){
                    if(planoCasa.esPosibleLlegar(equipo.getCodigoHabitacionActual(),codigo,equipo.getPuntajeActual())){
                            equipo.setCodigoHabitacionActual(codigo);
                            equipo.actualizarPuntajeActual();
                            res="El equipo cambio de habitacion";
                        }else{
                            res="El equipo no tiene el puntaje para cambiar de habitacion.";
                        }
                }else{
                    res="La habitacion no es contigua.";
                }
            }else{
                res="La habitacion no existe.";
            }

        }else{
            res="El equipo no existe.";
        }
        return res;
    }

    public static String puedeSalirEquipo(Scanner sc,HashMap<String, Equipo> equipos, ArbolAVL habitaciones){
        String nombre,res;
        System.out.println("Introduce el nombre del equipo que juega el desafio:");
        nombre=sc.nextLine();
        if (equipos.containsKey(nombre)) {
            Equipo equipo = equipos.get(nombre);
            if(habitaciones.tieneSalida(equipo.getCodigoHabitacionActual())){
                if(equipo.getPuntajeAcumulado()>=equipo.getPuntajeExigido()){
                    equipos.remove(nombre);
                    res="El equipo "+nombre+" logro escapar.";
                }else{
                    res="El equipo no tiene el puntaje para escapar.";
                }
            }else{
                res="La habitacion no tiene salida.";
            }
        }else{
            res="El equipo no existe.";
        }
        return res;
    }
    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------
    //---------Punto 6, Consulta General-----------------------------------------
    //---------------------------------------------------------------------------
    //---------------------------------------------------------------------------
    public static void gestionConsultaGeneral(Scanner sc, ArbolAVL habitaciones, Grafo planoCasa,
            HashMap<String, Equipo> equipos) {
        System.out.println("Consulta general de habitaciones, desafíos y equipos.");
        int numMenu;
        mostrarMenuConsultaGeneral();
        numMenu = sc.nextInt();
        switch (numMenu) {
            case 1:
                System.out.println("Seleccionaste: ver AVL");
                System.out.println(habitaciones.toString());
                break;
            case 2:
                System.out.println("Seleccionaste: ver Grafo");
                System.out.println(planoCasa.toString());
                break;
            case 3:
                System.out.println("Seleccionaste: ver Hash");
                System.out.println(equipos.toString());

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
        System.out.println("0. Salir");
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
