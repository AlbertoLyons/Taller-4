package services;

import models.*;
import ucn.*;

import java.io.IOException;

public class SistemaImpl implements Sistema{

    private ListaNodoDoble pokedex;

    public SistemaImpl() {
        this.pokedex = new ListaNodoDoble();
    }

    public void inicio() throws IOException {

        ArchivoEntrada archEnt = new ArchivoEntrada("kanto.txt");
        Pokemon pokemonLectura = null;

        while (!archEnt.isEndFile()) {

            Registro regEnt = archEnt.getRegistro();
            String id = regEnt.getString();
            String pokemon = regEnt.getString();

            String etapa = regEnt.getString();
            switch (etapa) {
                case "Basico" -> {
                    String primerEvolucion;
                    String segundaEvolucion;
                    if (pokemon.equals("Eevee")){
                        String primerEvolucion1 = regEnt.getString();
                        String primerEvolucion2 = regEnt.getString();
                        String primerEvolucion3 = regEnt.getString();
                        primerEvolucion = primerEvolucion1 + ", " + primerEvolucion2 + ", " + primerEvolucion3;

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
                case "Segunda Evolucion" -> {
                    String primeraEvolucion = regEnt.getString();
                    String basico = regEnt.getString();
                    String primerTipo = regEnt.getString();
                    String segundoTipo = regEnt.getString();
                    pokemonLectura = new SegundaEvolucion(id, pokemon, primeraEvolucion, basico, primerTipo, segundoTipo);
                }

            }
            this.pokedex.agregar(pokemonLectura);
        }
        archEnt.close();
    }
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
            StdOut.print("Escoja una opcion: ");
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
    //FALTA VERIFICADOR DE INT
    public void desplegarPorRangosId() {
        StdOut.println("----- BUSQUEDA DE POKEMONS POR RANGOS ------------------------------------------------");
        StdOut.println("Escriba un rango de ID (Ejemplo: desde el 1 hasta el 151, o desde el 60 hasta el 70):");
        StdOut.println(" *EL PRIMER VALOR DEBE SER MENOR AL SEGUNDO*");
        StdOut.print("Desde: ");
        int desde = StdIn.readInt();
        StdOut.print("Hasta: ");
        int hasta = StdIn.readInt();

        if (desde > pokedex.getTamanio() || hasta > pokedex.getTamanio()){
            StdOut.println("Uno de los valores ingresados excede el tamanio de pokemons almacenados. Volviendo al menu inicial");
            return;
        }

        if (desde > hasta) {
            int aux = desde;
            desde = hasta;
            hasta = aux;
        }
        StdOut.println("Desplegando pokemons desde el ID: " + desde + " hasta el ID: " + hasta);
        StdOut.println("---------------------------------------------------------------------------------------");
        for (int i = desde-1; i < hasta+1; i++) {
            String id = Integer.toString(i);
            pokedex.desplegarID(id);
        }
    }
    @Override
    public void desplegarTodosLosPokemons() {

        for (int i = 0; i <= pokedex.getTamanio() ; i++) {
            String id = Integer.toString(i);
            pokedex.desplegarID(id);
        }
    }
    @Override
    public void desplegarPorTipo() {
        StdOut.print("Ingrese el tipo de pokemons que desea desplegar: ");
        String tipo = StdIn.readString();

        if (pokedex.contieneTipo(tipo)){
            StdOut.println("Desplegando pokemons de tipo " + tipo);
            StdOut.println("");
            pokedex.desplegarTipo(tipo);
        }else {
            StdOut.println("No fueron encontrados pokemons de tipo " + tipo);
        }
    }

    @Override
    public void desplegarPrimeraEvolucion() {

    }

    @Override
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

    public void buscarNombre() {
        StdOut.print("Ingrese nombre el pokemon a buscar: ");
        String nombre = StdIn.readString();

        Pokemon pokemon = null;
        if (pokedex.contieneNombre(nombre)) {
            StdOut.println("Pokemon con nombre " + nombre + " encontrado!");
            pokemon = pokedex.obtenerPokemonPorNombre(nombre);
            pokedex.desplieguePokemon(pokemon);
        }
        else {
            StdOut.println("No fue encontrado un pokemon con nombre: " + nombre);
        }
    }

    public void buscarID() {
        StdOut.print("Ingrese ID el pokemon a buscar: ");
        String id = StdIn.readString();

        Pokemon pokemon = null;
        if (pokedex.contieneID(id)) {
            StdOut.println("Pokemon con ID " + id + " encontrado!");
            pokemon = pokedex.obtenerPokemonPorId(id);
            pokedex.desplieguePokemon(pokemon);
        }
        else {
            StdOut.println("No fue encontrado un pokemon con el id: " + id);
        }
    }

}
