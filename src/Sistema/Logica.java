package Sistema;

import Estructuras.GrafoEtiquetado.Grafo;
import Estructuras.Lineales.Lista;
import Estructuras.TablaAVL.ArbolAVL;
import Modelo.*;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Logica {

    private final Grafo planoCasa;
    private final ArbolAVL habitaciones;
    private final HashMap<String, Equipo> equipos;
    private boolean estaCargado;

    public Logica(){
        this.planoCasa = new Grafo();
        this.habitaciones = new ArbolAVL();
        this.equipos = new HashMap<>();
        this.estaCargado=false;
    }

    public String cargarScapeRoom(){
        String res="los datos fueron cargados";
        if (this.estaCargado) {
            res="Los datos ya fueron cargados previamente. No se puede cargar nuevamente.";
            } else {
            GestorArchivo.cargarDatos(this.habitaciones, this.planoCasa, this.equipos);
            this.estaCargado = true;
            }
            return res;
    }

    public String agregarHabitacion(int codigo, String nombre, int planta, int medida){
        String linea,res="El codigo de la habitacion ya esta en uso.";

        if (!this.habitaciones.pertenece(codigo)) {
        linea = "H;" + codigo+";" + nombre+";" + planta+ ";" + medida + ";false";
        GestorArchivo.cargarDatoLinea(linea, this.habitaciones,this.planoCasa, null);
        GestorArchivo.registrarLog("Se agrego la habitacion "+codigo+" con el nombre: "+nombre);
        res="Habitación creada exitosamente.";
        }
        return res;
    }

    public String cambiarNombreHabitacion(int codigo,String nombre) {
        String res;
        if(this.habitaciones.pertenece(codigo)){
            Habitacion unaHab= (Habitacion) this.habitaciones.recuperar(codigo);
            unaHab.setNombre(nombre);
            res="se cambio el nombre de la habitacion";
        }else{
            res="la habitacion no existe";
        }
        return res;
    }

    public String cambiarPlantaHabitacion(int codigo,int planta) {
        String res;
        if(this.habitaciones.pertenece(codigo)){
            Habitacion unaHab= (Habitacion) this.habitaciones.recuperar(codigo);
            unaHab.setPlanta(planta);
            res="se cambio la planta de la habitacion";
        }else{
            res="la habitacion no existe";
        }
        return res;
    }

    public  String cambiarMedidaHabitacion(int codigo, int medida) {
        String res;
        if(this.habitaciones.pertenece(codigo)){
            Habitacion unaHab= (Habitacion) this.habitaciones.recuperar(codigo);
            unaHab.setPlanta(medida);
            res="se cambio la medida de la habitacion";
        }else{
            res="la habitacion no existe";
        }
        return res;
    }

    public String eliminarHabitacion(int codigo) {
        String res;

        // Llamo a metodo AVL que elimina si existe habitacion y devuelve boolean
        if (this.habitaciones.eliminar(codigo)) {
            this.planoCasa.eliminarVertice(codigo);
            GestorArchivo.registrarLog("Se elimino la habitacion "+codigo);
            res="Habitación eliminada correctamente.";
        } else {
            res="No se encontró la habitación con el código "+codigo+".";
        }
        return res;
    }

    public  String añadirCaminos(int codigo,int destino, int puntajeRequerido){

        String linea,res="no existe la habitacion de partida";
        if(this.planoCasa.existeVertice(codigo)){
        if(this.planoCasa.existeVertice(destino)){
        linea="P;"+codigo+";"+destino+";"+puntajeRequerido;

        GestorArchivo.cargarDatoLinea(linea, null, this.planoCasa, null);
        GestorArchivo.registrarLog("Se agrego la puerta con origen "+codigo+" y destino "+destino+" con un puntaje de "+puntajeRequerido);
        res="Se cargo exitosamente la nueva puerta";
        }else{
            res="no existe la habitacion destino";
        }
    }
        return res;
    }
    //-------------------------------------------------
    public String agregarDesafio(int codigo,int puntaje,String nombre,String tipo) {
        String linea,res;

        if (this.habitaciones.pertenece(codigo)) {
            Habitacion unaHab=(Habitacion) this.habitaciones.recuperar(codigo);
            if(!unaHab.getDesafios().pertenece(puntaje)){
            linea = "D;" + puntaje + ";" + codigo+";"+nombre+";"+tipo;
            GestorArchivo.cargarDatoLinea(linea, this.habitaciones, null, null);
            GestorArchivo.registrarLog("Se creo un desafio con codigo "+puntaje+" en la habitacion "+codigo);
            res="Desafío creado exitosamente.";
        }else{
        res="El puntaje ingresado ya le pertenece a otro desafio de la habitacion";
        }
        }else{
        res="No se encontró la habitación con el código ingresado.";
        }
        return res;
    }

        public String cambiarNombreDesafio(int codigo, int puntaje,String nombre) {
        String res;
        if(this.habitaciones.pertenece(codigo)){
            Habitacion unaHab=(Habitacion) this.habitaciones.recuperar(codigo);
            if(unaHab.getDesafios().pertenece(puntaje)){
                Desafio unDes=(Desafio)unaHab.getDesafios().recuperar(puntaje);
                unDes.setNombre(nombre);
                GestorArchivo.registrarLog("Se cambio el nombre del desafio "+codigo+" por: "+nombre);
                res="nombre del desafío modificado correctamente.";
            }else{
            res="No se encontró el desafio ingresado, en la habitacion ingresada.";
            }
        }else{
        res="No se encontró la habitación con el código ingresado.";
        }
        return res;
    }

    public String cambiarTipoDesafio(int codigo, int puntaje,String tipo) {
        String res;
        if(this.habitaciones.pertenece(codigo)){
            Habitacion unaHab=(Habitacion) this.habitaciones.recuperar(codigo);
            if(unaHab.getDesafios().pertenece(puntaje)){
                Desafio unDes=(Desafio)unaHab.getDesafios().recuperar(puntaje);
                unDes.setTipo(tipo);
                GestorArchivo.registrarLog("Se cambio el tipo, al desafio "+codigo+" por: "+tipo);
                res="tipo del desafío modificado correctamente.";
            }else{
            res="No se encontró el desafio ingresado, en la habitacion ingresada.";
            }
        }else{
        res="No se encontró la habitación con el código ingresado.";
        }
        return res;
    }

    public String eliminarDesafio(int codigo, int puntaje) {
        String res;

        if (this.habitaciones.pertenece(codigo)) {
            Habitacion unaHab=(Habitacion) this.habitaciones.recuperar(codigo);
            if(unaHab.getDesafios().eliminar(puntaje)){
                GestorArchivo.registrarLog("Se elimino el desafio de la habitacion "+codigo+" puntaje "+puntaje);
                res="Desafio eliminado correctamente.";
            }else{
            res="No se encontró el desafio ingresado, en la habitacion ingresada.";
            }
        }else{
        res="No se encontró la habitación con el código ingresado.";
        }
        return res;
    }
//-------------------------------------------------------------------

    public String agregarEquipo(String nombre,int codigo,int puntajeExigido) {

        String linea,res;
        if (this.equipos.containsKey(nombre)) {
            res="El nombre ingresado ya esta usado.";
        } else {
            if(this.habitaciones.pertenece(codigo)){
            linea = "E;" + nombre+";" + puntajeExigido + ";0;" + codigo + ";0";
            GestorArchivo.cargarDatoLinea(linea, null, null, this.equipos);
            GestorArchivo.registrarLog("Se agrego el equipo "+nombre);
            res="Equipo creado exitosamente.";
            }else{
            res="No se pudo crear puesto que no existe una habitacion con dicho codigo.";
            }
        }
        return res;
    }

    public String cambiarPuntajeExigido(String nombre,int puntajeExigido) {
        String res="el equipo no existe";
        if(this.equipos.containsKey(nombre)){
            this.equipos.get(nombre).setPuntajeExigido(puntajeExigido);
            GestorArchivo.registrarLog("Se modifico el puntaje exigido del equipo "+nombre+" por: "+puntajeExigido);
            res= "Puntaje exigido del equipo modificado correctamente.";
        }
        return res;
    }

    public String eliminarEquipo(String nombre) {
        String res="El equipo no existe.";
        if (this.equipos.remove(nombre)!=null) {
            GestorArchivo.registrarLog("Se elimino el equipo "+nombre);
            res="Equipo eliminado";
        }
        return res;
    }

    //--------------------------------------------------------------------------------------
    public String mostrarHabitacion(int codigo) {
        String str = "No existe";
        if (this.habitaciones.pertenece(codigo)) {
            str=this.habitaciones.recuperar(codigo).toString();
        }
        return str;
    }
    public String mostrarContiguas(int codigo){
        Lista listaContiguas = this.planoCasa.listarAdyacentes(codigo);
        String str;
        if (!listaContiguas.esVacia()){
            
            str = "Habitaciones contiguas: "+listaContiguas.toString();
        }else{
            str = "No tiene contiguas";
        }
        return str;
    }

    public String minimoPuntaje(int hab1, int hab2){
        String res="no existe habitacion partida";
        Lista mejorCamino = new Lista();
        int puntajeMin = this.planoCasa.caminoMenorCosto(hab1, hab2, mejorCamino);
        if(this.planoCasa.existeVertice(hab1)){
            if(this.planoCasa.existeVertice(hab2)){
                res="El puntaje min es: "+puntajeMin+"\n El mejor camino es: "+mejorCamino.toString();

            }else{
                res="no existe habitacion destino";
            }
        }
        return res;
    }
    public String esPosibleLlegar(int hab1, int hab2, int k){
        String str= "No es posible";
        boolean exito = this.planoCasa.esPosibleLlegar(hab1, hab2, k);
        if (exito){
            str = "Es posible";
        }
        return str;
    }
    public String sinPasarPor(int hab1, int hab2, int hab3, int p){
        String str; 
        Lista l = planoCasa.sinPasarPor(hab1, hab2, hab3, p);
        if(!l.esVacia()){ 
            System.out.println("l no es vacia");
            str =l.toString();
        }else{
            str = "No existen tales caminos";
        }
        return str;
    }
        


    //------------------------------------------------------------------------------------------
    public String mostrarDesafio(Scanner sc, int codigo, int puntaje) {

        String res="la habitacion no existe";
        if(this.habitaciones.pertenece(codigo)){
            Habitacion unaHab=(Habitacion) this.habitaciones.recuperar(codigo);
            if(unaHab.getDesafios().pertenece(puntaje)){
                Desafio des=(Desafio) unaHab.getDesafios().recuperar(puntaje);
                res=des.toString();
            }
        }
        return res;
    }

    public String mostrarDesafiosResueltos(String nombre) {
        String res="no existe el equipo";
        if(this.equipos.containsKey(nombre)){
            Equipo equipo= this.equipos.get(nombre);
            equipo.getDesafiosCompletados();
            res = "El equipo " + nombre + " resolvio estos desafios: \n" +listarDesafiosAUX(equipo);
        }
        return res;
    }

    private String listarDesafiosAUX(Equipo eq) {
        String str = "";
        if (eq != null) {

            HashMap<Integer, Lista> desafiosCompletados = eq.getDesafiosCompletados(); // mapa de desafios
            for (Entry<Integer, Lista> par : desafiosCompletados.entrySet()) {
                // recorro el hash, obtengo ambos valores
                int codigoHabitacion = par.getKey();
                Lista desafios = par.getValue();
                //clono la lista para usarla como recorrido
                Lista aux = desafios.clonar();
                Habitacion hab = (Habitacion) habitaciones.recuperar(codigoHabitacion); // busco la habitacion
                ArbolAVL desafiosHab = hab.getDesafios(); // agarro el arbol de desafios de esa habitacion
                Lista desafiosComp = buscarDesafio(desafiosHab, aux);
                //concateno cada string resultante de buscarDesafio
                str = str + " Desafio: " + desafiosComp.toString()+ "\n";
            }
        }
        return str;
    }

    private Lista buscarDesafio(ArbolAVL desafiosHab, Lista clavesDesafio) {
        Lista listaDesafiosCompletados = new Lista();
        while (!clavesDesafio.esVacia()) {
            int claveDesafio = (int) clavesDesafio.recuperar(1); // recupero la clave
            Desafio des = (Desafio) desafiosHab.recuperar(claveDesafio); //busco un desafio con esa clave en el AVL
            if (des != null) {
                listaDesafiosCompletados.insertar(des, 1);
            } else {
                listaDesafiosCompletados.insertar("no existe", 1); //esto es para testeo
            }
            clavesDesafio.eliminar(1); // borro la clave q ya use
        }
        return listaDesafiosCompletados;
    }

    
    public String verificarDesafioResuelto(String nombre,int codigo, int puntaje) {
        String res="el equipo no existe";
        if(this.equipos.containsKey(nombre)){
            Equipo equipo=this.equipos.get(nombre);
            if(equipo.getDesafiosCompletados().containsKey(codigo)){
                Lista l=equipo.getDesafiosCompletados().get(codigo);
                if(l.localizar(puntaje)>=0){
                    res="el equipo completo el desafio";
                }else{
                    res="el equipo no completo el desafio";
                }
            }else{
                res="el equipo no completo desafios en esa habitacion";
            }
        }
    return res;
    }
    
    public String mostrarDesafiosPorTipo(int codigo,int a, int b,String x){
        //me devuelve los desafios de la habitacion con codigo, que esten entre a y b y sean del tipo x
        String res = "no existe la habitacion";
        if (this.habitaciones.pertenece(codigo)) {
            Habitacion unaHab= (Habitacion) this.habitaciones.recuperar(codigo);
            Lista l= unaHab.getDesafios().listarRango(a, b);
            if (!l.esVacia()) {
                res = "Los desafios del tipo "+x+ " en la habitacion "+codigo +" son los siguientes: \n";
                res += filtrarPorTipo(l, x);
            }else{
                res += "No hay desafios con esos filtros";
            }
        }
        return res;
    }

    private String filtrarPorTipo(Lista enRango, String x){
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
    public String mostrarEquipo(String nombre){
        String res="el equipo no existe";
        if (this.equipos.containsKey(nombre)) {
            res=(this.equipos.get(nombre).toString());
            }
        return res;
    }

    public String mostrarPosiblesDesafios(String nombre,int codigo){
        String res;
        int codActual,puntajeNecesario;
        if (this.equipos.containsKey(nombre)) {
            Equipo equipo=equipos.get(nombre);
            codActual=equipo.getCodigoHabitacionActual();
            if(this.planoCasa.existeArco(codActual,codigo)){
                puntajeNecesario=this.planoCasa.caminoMenorCosto(codActual, codigo, new Lista()); 
                //agrego una lista y no null asi no me rompe el metodo, aunque solo queremos el return
                //obtengo el puntaje del camino mas corto que seria la etiqueta entre adyacentes
                // y luego le resto el puntaje actual del equipo para obtener el valor que deberia
                //buscar que tengas los desafios como minimo para que solo resolviendo uno puedan
                //avanzar
                Habitacion unaHab=(Habitacion) this.habitaciones.recuperar(codActual);
                Lista l=unaHab.getDesafios().listarMayores(puntajeNecesario);
                res="los desafios que completando uno solo pueden cambiar de habitacion son:\n";
                res+=l.toString();
            }else{
                res="la habitacion no es contigua";
            }
        }else{
            res="El equipo no existe.";
        }
        return res;
    }

    public String jugarDesafio(String nombre,int codigo, int puntaje){
        String res="el equipo no existe";
  
        if (this.equipos.containsKey(nombre)) {
            if(habitaciones.pertenece(codigo)){
                Equipo equipo= equipos.get(nombre);
                Habitacion unaHab=(Habitacion) this.habitaciones.recuperar(codigo);
                if(unaHab.getDesafios().pertenece(puntaje)){
                equipo.cargarDesafiosRealizados(codigo,puntaje);
                equipo.actualizarPuntajes();
                res="Se ha agregado el desafio como realizado para el equipo "+nombre+".";
                }else{
                    res="No existe un desafio con ese puntaje en esta habitacion.";
                }
            }else{
                res="La habitacion no existe.";
            }
        }
        return res;
    }
    //en este se penso en no pasar la habitacion dado que seria raro que 
    //se completara un desafio desde otra habitacion que no sea la actual
    //sin embargo se termino pasando el codigo de la habitacion por parametro
    //por un supuesto donde se completan desafios desde otras habitaciones.

    public String cambiarDeHabitacion(String nombre, int codigo){
        String res;

        if (this.equipos.containsKey(nombre)) {

            if(this.habitaciones.pertenece(codigo)){
                Equipo equipo=this.equipos.get(nombre);
                if(this.planoCasa.existeArco(equipo.getCodigoHabitacionActual(),codigo)){
                    if(planoCasa.esPosibleLlegar(equipo.getCodigoHabitacionActual(),codigo,equipo.getPuntajeActual())){
                            equipo.setCodigoHabitacionActual(codigo);
                            equipo.actualizarPuntajeActual();
                            res="El equipo cambio de habitacion";
                        }else{
                            res="El equipo no tiene el puntaje actual para cambiar de habitacion.";
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

    public String puedeSalirEquipo(String nombre){
        String res;
        
        if (equipos.containsKey(nombre)) {
            Equipo equipo = equipos.get(nombre);
            Habitacion unaHab=(Habitacion) this.habitaciones.recuperar(equipo.getCodigoHabitacionActual());

            if(unaHab.getTieneSalida()){
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
    public String toStringAVL(){
        return this.habitaciones.toString();
    }
    public String toStringPlanoCasa(){
        return this.planoCasa.toString();
    }
    public String toStringHashMap(){
        return this.equipos.toString();
    }
    public static void iniciarLog(){
        GestorArchivo.iniciarLog();
    }
}
