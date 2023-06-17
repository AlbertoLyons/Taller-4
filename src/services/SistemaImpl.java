package services;

import models.*;
import ucn.*;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

public class SistemaImpl implements Sistema{ //Hereda la clase interface Sistema
    /**
     * The pokedex
     * @see ListaNodoDoble
     */
    private ListaNodoDoble pokedex;

    /**
     * The constructor.
     */
    public SistemaImpl() {
        this.pokedex = new ListaNodoDoble();
    }

    /**
     * Lee el archivo txt y agrega pokemons a la lista.
     * @throws IOException
     */
    public void inicio() throws IOException {

        this.ordenarArchivo();

        ArchivoEntrada archEnt = new ArchivoEntrada("kanto.txt");
        Pokemon pokemonLectura = null;

        while (!archEnt.isEndFile()) {

            Registro regEnt = archEnt.getRegistro();
            String id = regEnt.getString();
            String pokemon = regEnt.getString();

            String etapa = regEnt.getString();
            switch (etapa) {
                // Ordena segun si el pokemon del archivo en la linea es de tipo Basico.
                case "Basico" -> {
                    String primerEvolucion;
                    String segundaEvolucion;
                    // Validacion si el pokemon leido es eevee.
                    if (pokemon.equals("Eevee")){
                        // Con este algoritmo le asignamos aleatoriamente la primera evolucion, tomando en cuenta las consideraciones del taller.
                        String[] primerasEvoluciones = new String[3];
                        primerasEvoluciones[0] = regEnt.getString();
                        primerasEvoluciones[1] = regEnt.getString();
                        primerasEvoluciones[2] = regEnt.getString();
                        Random random = new Random();
                        int randomPosicion = random.nextInt(primerasEvoluciones.length);
                        primerEvolucion = primerasEvoluciones[randomPosicion];

                    }else {
                        primerEvolucion = regEnt.getString();
                    }

                    segundaEvolucion = regEnt.getString();
                    String primerTipo = regEnt.getString();
                    String segundoTipo = regEnt.getString();

                    if (segundoTipo == null) {
                        segundoTipo = primerTipo;
                        primerTipo = segundaEvolucion;
                        segundaEvolucion = null;
                    }

                    pokemonLectura = new Basico(id, pokemon, primerEvolucion, segundaEvolucion, primerTipo, segundoTipo);
                }
                // Ordena segun si el pokemon del archivo en la linea es de tipo Primera Evolucion.
                case "Primera Evolucion" -> {
                    String segundaEvolucion = regEnt.getString();
                    String basico = regEnt.getString();
                    String primerTipo = regEnt.getString();
                    String segundoTipo = regEnt.getString();
                    if (segundoTipo == null) {
                        segundoTipo = primerTipo;
                        primerTipo = basico;
                        basico = segundaEvolucion;
                        segundaEvolucion = null;

                    }
                    pokemonLectura = new PrimeraEvolucion(id, pokemon, segundaEvolucion, basico, primerTipo, segundoTipo);
                }
                // Ordena segun si el pokemon del archivo en la linea es de tipo Segunda Evolucion.
                case "Segunda Evolucion" -> {
                    String primeraEvolucion = regEnt.getString();
                    String basico = regEnt.getString();
                    String primerTipo = regEnt.getString();
                    String segundoTipo = regEnt.getString();
                    pokemonLectura = new SegundaEvolucion(id, pokemon, primeraEvolucion, basico, primerTipo, segundoTipo);
                }

            }
            // Lo agrega a la lista al final.
            this.pokedex.agregar(pokemonLectura);
        }
        archEnt.close();
    }

    /**
     * Algoritmo encargado de ordenar eliminar los espacios dentro del txt original y las lineas vacias.
     */
    public void ordenarArchivo(){

        String nombreArchivoEntrada = "kantoDesordenado.txt";
        String nombreArchivoSalida = "kanto.txt";

        try {
            FileReader fr = new FileReader(nombreArchivoEntrada);
            BufferedReader br = new BufferedReader(fr);

            FileWriter fw = new FileWriter(nombreArchivoSalida);
            BufferedWriter bw = new BufferedWriter(fw);

            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.replaceAll("\\s*,\\s*", ","); // Eliminar espacios antes y después de las comas.
                linea = linea.trim(); // Eliminar espacios en blanco al principio y al final de la línea.

                if (!linea.isEmpty()) { // Ignorar líneas vacías.
                    bw.write(linea);
                    bw.newLine(); // Agregar nueva línea al archivo de salida.
                }
            }

            br.close();
            fr.close();
            bw.close();
            fw.close();

        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }

    }

    /**
     * Despliegue de menu principal.
     */
    public void menuPrincipal() {
        String opcion = "";
        while (!opcion.equals("6")){

            StdOut.print("""
                  ----- ＰＯＫＥＤＥＸ ＲＥＧＩＯＮＡＬ ＤＥ ＫＡＮＴＯ --------------
                  -      [1] Buscar pokemons por rango de ID                  -  
                  -      [2] Desplegar todos los pokemons almacenados         - 
                  -      [3] Desplegar todos los pokemons dado un tipo        -
                  -      [4] Desplegar todos los pokemons de primer evolucion -
                  -      [5] Busqueda personalizada de un pokemon             -  
                  -      [6] Salir                                            -
                  -------------------------------------------------------------
                    """);
            StdOut.print("Escoja un opcion: ");
            opcion =  StdIn.readString();
            StdOut.println("");
            switch (opcion){
                case "1" -> this.desplegarPorRangosId();
                case "2" -> this.desplegarTodosLosPokemons();
                case "3" -> this.desplegarPorTipo();
                case "4" -> this.desplegarPrimeraEvolucion();
                case "5" -> this.busquedaPersonalizada();
                case "6" -> StdOut.println("Saliendo...");
                default -> StdOut.println("Opcion no valida, intente nuevamente\n");
            }
        }
    }

    @Override
    /**
     * Despliega los pokemons dado un rango ingresado desde teclado.
     */
    public void desplegarPorRangosId() {
        StdOut.println("----- BUSQUEDA DE POKEMONS POR RANGOS ------------------------------------------------");
        StdOut.println("Escriba un rango de ID (Ejemplo: desde el 1 hasta el 151, o desde el 60 hasta el 70):");
        StdOut.println(" *EL PRIMER VALOR DEBE SER MENOR AL SEGUNDO* ");
        StdOut.print("Desde: ");
        int desde = StdIn.readInt();
        StdOut.print("Hasta: ");
        int hasta = StdIn.readInt();
        // Verifica que alguno de los 2 parametros no sobrepase la cantidad almacenada en la pokedex.
        if (desde > pokedex.getTamanio() || hasta > pokedex.getTamanio()){
            StdOut.println("Uno de los valores ingresados excede el tamanio de pokemons almacenados, el tamanio de la pokedex es de "+ pokedex.getTamanio() + " pokemons. Volviendo al menu inicial");
            StdOut.println("");
            return;
        }
        // Verifica que alguno de los parametros sea distinto de 0.
        if (desde == 0 || hasta == 0){
            StdOut.println("Uno de los valores ingresados fue cero, no hay pokemon con ese ID. Volviendo al menu principal...");
            StdOut.println("");
            return;
        }
        // Verifica que los parametros no se ingresen de orden alternado.
        if (desde > hasta) {
            StdOut.println("Ingreso de manera incorrecta los valores. Volviendo al menu principal....");
            StdOut.println("");
            return;
        }
        StdOut.println("Desplegando pokemons desde el ID: " + desde + " hasta el ID: " + hasta);
        StdOut.println("---------------------------------------------------------------------------------------");
        // Se crea una linkdelist asociado al NodoDoble.
        LinkedList<NodoDoble> lista = pokedex.LinkedList();
        // Se crea el iterador desde el parametro desde-1 para empezar desde la posicion 0.
        ListIterator<NodoDoble> iterador = lista.listIterator(desde-1);
        // Se ordena la lista de manera creciente.
        pokedex.ordenar("Cola");
        // Se empieza a iterar con la lista iterador, por el rango de id que dio el usuario.
        while (iterador.hasNext() && iterador.nextIndex() < hasta) {
            String id = iterador.next().getPokemon().getId();
            pokedex.desplegarID(id);

        }
        StdOut.println("Volviendo al menu principal....");
        StdOut.println("");
    }
    @Override
    /**
     * Despliega todos los pokemons almacenados en la lista.
     */
    public void desplegarTodosLosPokemons() {
        // Se crea el array lista ya ordenada alfabeticamente con el metodo de la listaNodoDoble.
        ArrayList<String> lista = pokedex.ordenarAlfabeticamenteArray();
        for (int i = 0; i < lista.size(); i++) {
            Pokemon pokemon = pokedex.obtenerPokemonPorNombre(lista.get(i));
            // Se despliegan los pokemon ordenados.
            pokedex.desplieguePokemon(pokemon);
        }
    }
    @Override
    /**
     * Despliega todos los pokemons dado un tipo desde teclado.
     */
    public void desplegarPorTipo() {
        StdOut.print("Ingrese el tipo de pokemons que desea desplegar: ");
        String tipo = StdIn.readString();
        // Se busca al tipo dado por el usuario.
        if (pokedex.contieneTipo(tipo)){
            StdOut.println("Desplegando pokemons de tipo " + tipo);
            StdOut.println("");
            pokedex.desplegarTipo(tipo);
            StdOut.println("Volviendo al menu principal....");
            StdOut.println("");
        // Si no encuentra, se le avisa al usuario.
        }else {
            StdOut.println("No fueron encontrados pokemons de tipo " + tipo);
            StdOut.println("Volviendo al menu principal....");
            StdOut.println("");
        }
    }
    @Override
    /**
     * Despliega todos que esten etapa primera evolucion.
     */
    public void desplegarPrimeraEvolucion() {
        // Ordenamos la lista de nodo.
        pokedex.ordenar("Cabeza");
        StdOut.println("Desplegamos los pokemons etapa primer evolucion...");
        StdOut.println("");
        // la recorremos hacia atras de esta manera se recorrera en forma decreciente.
        pokedex.recorrerAtrasCuartoRequerimiento();
        StdOut.println("Volviendo al menu principal..");
        StdOut.println("");
    }
    @Override
    /**
     * Menu de busqueda personalizada de pokemon, llama a otros subprogramas para luego desplegarlo.
     */
    public void busquedaPersonalizada() {

        StdOut.print("""           
                ----- BUSQUEDA PERSONALIZADA DE UN POKEMON -----
               [1] Busqueda por nombre
               [2] Busqueda por ID
                ------------------------------------------------
                """);
        StdOut.print("Ingrese la opcion por la que desea buscar al pokemon: ");
        String opcion = StdIn.readString();

        switch (opcion){
            case "1" -> buscarNombre();
            case "2" -> buscarID();
            default -> StdOut.println("La opcion ingresada no es valida, volviendo al menu inicial.");
        }
    }

    /**
     * Metodo que solicita un nombre para luego buscarlo y desplegarlo segun las condiciones.
     */
    public void buscarNombre() {
        StdOut.print("Ingrese nombre el pokemon a buscar: ");
        String nombre = StdIn.readString();

        Pokemon pokemon = null;
        if (pokedex.contieneNombre(nombre)) {
            StdOut.println("Pokemon con nombre " + nombre + " encontrado!");
            StdOut.println("");
            pokemon = pokedex.obtenerPokemonPorNombre(nombre);
            pokedex.desplieguePokemonBusquedaPersonalizada(pokemon);
            // Valida si el pokemon es de tipo basico.
            if (pokemon instanceof Basico){
                despliegueEvolucionesBasico((Basico) pokemon);
                // Valida si el pokemon es de tipo primera evolucion.
            } else if (pokemon instanceof PrimeraEvolucion) {
                despliegueEvolucionesPrimeraEvolucion((PrimeraEvolucion) pokemon);
            // Valida si el pokemon es de tipo segunda evolucion.
            } else if (pokemon instanceof SegundaEvolucion) {
                despliegueEvolucionesSegundaEvolucion((SegundaEvolucion) pokemon);
            }
        }else {
            StdOut.println("No fue encontrado un pokemon con nombre: " + nombre);
            StdOut.println("Volviendo al menu principal");
            StdOut.println("");
        }
    }

    /**
     * Metodo que solicita un id para luego buscarlo y desplegarlo segun las condiciones.
     */
    public void buscarID() {
        StdOut.print("Ingrese ID el pokemon a buscar: ");
        String id = StdIn.readString();

        Pokemon pokemon = null;
        if (pokedex.contieneID(id)) {
            StdOut.println("Pokemon con id " + id + " encontrado!");
            StdOut.println("");
            pokemon = pokedex.obtenerPokemonPorId(id);
            pokedex.desplieguePokemonBusquedaPersonalizada(pokemon);
            // Valida si el pokemon es de tipo basico.
            if (pokemon instanceof Basico){
                // Verifica si el basico tiene primer evolucion.
                despliegueEvolucionesBasico((Basico) pokemon);
                // Valida si el pokemon es de tipo primera evolucion.
            } else if (pokemon instanceof PrimeraEvolucion) {
                // Verifica si tiene segunda evolucion.
                despliegueEvolucionesPrimeraEvolucion((PrimeraEvolucion) pokemon);
                // Valida si el pokemon es de tipo segunda evolucion.
            } else if (pokemon instanceof SegundaEvolucion) {
                despliegueEvolucionesSegundaEvolucion((SegundaEvolucion) pokemon);
            }
        }else {
            StdOut.println("No fue encontrado un pokemon con id: " + id);
            StdOut.println("Volviendo al menu principal");
            StdOut.println("");
        }
    }

    /**
     * Metodo que despliega en base al requerimiento 5, la busqueda personalizada.
     * @param pokemon
     */
    public void despliegueEvolucionesBasico(Basico pokemon){
        StdOut.println("*ESTE POKEMON SE ENCUENTRA EN ETAPA BASICA*");
        while (true){
            StdOut.println("""   
                            ------ OPCIONES -----
                            [1] Desplegar primera evolucion
                            [2] Desplegar segunda evolucion
                            [3] Salir
                            ---------------------
                                                      """);
            StdOut.print("Ingrese la opcion deseada: ");
            String opcion = StdIn.readString();

            switch (opcion){
                case "1" -> {
                    if (pokemon.getPrimerEvolucion() == null){
                        StdOut.println("El pokemon " + pokemon.getNombre() + " no posee primer evolucion.");
                        StdOut.println("");
                    }else {
                        StdOut.println("Informacion de la primera evolucion del pokemon " + pokemon.getNombre());
                        StdOut.println("");
                         Pokemon pokemonPrimeraEvolucion = pokedex.obtenerPokemonPorNombre(pokemon.getPrimerEvolucion());
                        pokedex.desplieguePokemon(pokemonPrimeraEvolucion);
                        despliegueEvolucionesPrimeraEvolucion((PrimeraEvolucion) pokemonPrimeraEvolucion);
                        // La funcion de este return es no tener que devolverse al menu anterior sucesivamente (en caso de haber navegado hartas veces este se devolvia menu por menu).
                        return;
                    }
                }
                case "2" -> {
                    if (pokemon.getSegundaEvolucion() == null){
                        StdOut.println("El pokemon " + pokemon.getNombre() + " no posee segunda evolucion.");
                        StdOut.println("");
                    }else {
                        StdOut.println("Informacion de la segunda evolucion del pokemon " + pokemon.getNombre());
                        StdOut.println("");
                        Pokemon pokemonSegundaEvolucion = pokedex.obtenerPokemonPorNombre(pokemon.getSegundaEvolucion());
                        pokedex.desplieguePokemon(pokemonSegundaEvolucion);
                        despliegueEvolucionesSegundaEvolucion((SegundaEvolucion) pokemonSegundaEvolucion);
                        // La funcion de este return es no tener que devolverse al menu anterior sucesivamente (en caso de haber navegado hartas veces este se devolvia menu por menu).
                        return;
                    }
                }
                case "3" -> {
                    StdOut.println("Saliendo...");
                    StdOut.println("");
                    return;
                }
                default -> {
                    StdOut.println("Intentelo denuevo.");
                    StdOut.println("");
                }
            }
        }
    }

    /**
     * Metodo para despliegue de pokemones tipo primera evolucion para el requerimiento 5.
     * @param pokemon
     */
    public void despliegueEvolucionesPrimeraEvolucion(PrimeraEvolucion pokemon){
        StdOut.println("*ESTE POKEMON SE ENCUENTRA EN ETAPA PRIMERA EVOLUCION*");
        while (true){
            StdOut.println("""   
                            ------ OPCIONES -----
                            [1] Desplegar etapa basica
                            [2] Desplegar segunda evolucion
                            [3] Salir
                            ---------------------
                                                      """);
            StdOut.print("Ingrese la opcion deseada: ");
            String opcion = StdIn.readString();

            switch (opcion){
                case "1" ->{
                    StdOut.println("Informacion de la etapa basica del pokemon " + pokemon.getNombre());
                    StdOut.println("");
                    Pokemon pokemonBasico = pokedex.obtenerPokemonPorNombre(pokemon.getBasico());
                    pokedex.desplieguePokemon(pokemonBasico);
                    despliegueEvolucionesBasico((Basico) pokemonBasico);
                    // La funcion de este return es no tener que devolverse al menu anterior sucesivamente (en caso de haber navegado hartas veces este se devolvia menu por menu).
                    return;
                }
                case "2" ->{
                    if (pokemon.getSegundaEvolucion() == null){
                        StdOut.println("El pokemon " + pokemon.getNombre() + " no posee segunda evolucion.");
                        StdOut.println("");
                    }else {
                        StdOut.println("Informacion de la segunda evolucion del pokemon " + pokemon.getNombre());
                        StdOut.println("");
                        Pokemon pokemonSegundaEvolucion = pokedex.obtenerPokemonPorNombre(pokemon.getSegundaEvolucion());
                        pokedex.desplieguePokemon(pokemonSegundaEvolucion);
                        despliegueEvolucionesSegundaEvolucion((SegundaEvolucion) pokemonSegundaEvolucion);
                        // La funcion de este return es no tener que devolverse al menu anterior sucesivamente (en caso de haber navegado hartas veces este se devolvia menu por menu).
                        return;
                    }
                }
                case "3" -> {
                    StdOut.println("Saliendo...");
                    StdOut.println("");
                    return;
                }
                default -> {
                    StdOut.println("Intentelo denuevo.");
                    StdOut.println("");
                }
            }
        }
    }

    /**
     * Metodo para despliegue de pokemones tipo segunda evolucion para el requerimiento 5.
     * @param pokemon
     */
    public void despliegueEvolucionesSegundaEvolucion(SegundaEvolucion pokemon){
        StdOut.println("*ESTE POKEMON SE ENCUENTRA EN ETAPA SEGUNDA EVOLUCION*");
        while (true) {
            StdOut.println("""   
                    ------ OPCIONES -----
                    [1] Desplegar etapa basica
                    [2] Desplegar primera evolucion
                    [3] Salir
                    ---------------------
                                              """);
            StdOut.print("Ingrese la opcion deseada: ");
            String opcion = StdIn.readString();

            switch (opcion){
                case "1" ->{
                    StdOut.println("Informacion de la etapa basica del pokemon " + pokemon.getNombre());
                    StdOut.println("");
                    Pokemon pokemonBasico = pokedex.obtenerPokemonPorNombre(pokemon.getBasico());
                    pokedex.desplieguePokemon(pokemonBasico);
                    despliegueEvolucionesBasico((Basico) pokemonBasico);
                    // La funcion de este return es no tener que devolverse al menu anterior sucesivamente (en caso de haber navegado hartas veces este se devolvia menu por menu).
                    return;
                }
                case "2" -> {
                    StdOut.println("Informacion de la primera evolucion del pokemon " + pokemon.getNombre());
                    StdOut.println("");
                    Pokemon pokemonPrimeraEvolucion = pokedex.obtenerPokemonPorNombre(pokemon.getPrimeraEvolucion());
                    pokedex.desplieguePokemon(pokemonPrimeraEvolucion);
                    despliegueEvolucionesBasico((Basico) pokemonPrimeraEvolucion);
                    // La funcion de este return es no tener que devolverse al menu anterior sucesivamente (en caso de haber navegado hartas veces este se devolvia menu por menu).
                    return;
                }
                case "3" ->{
                    StdOut.println("Saliendo...");
                    StdOut.println("");
                    // La funcion de este return es no tener que devolverse al menu anterior sucesivamente (en caso de haber navegado hartas veces este se devolvia menu por menu).
                    return;
                }
                default -> {
                    StdOut.println("Intentelo denuevo.");
                    StdOut.println("");
                }
            }
        }
    }
}