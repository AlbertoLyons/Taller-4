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
            StdOut.println("---POKEDEX REGIONAL DE KANTO---\n" +
                    "Opciones a elegir:\n" +
                    "1. Buscar pokemons por rango de ID\n" +
                    "2. Desplegar todos los pokemon\n" +
                    "3. Desplegar pokemons dados un tipo\n" +
                    "4. Desplegar pokemons de primera evolucion\n" +
                    "5. Busqueda personalizada\n" +
                    "6. Salir");

            StdOut.print("Escoja una opcion: ");
            opcion =  StdIn.readString();
            switch (opcion){
                case "1" -> this.desplegarPorRangosId();
                case "2" -> this.desplegarTodosAlfabeticamente();
                case "3" -> this.desplegarCoincideTipoIdDecreciente();
                case "4" -> this.desplegarPrimeraEvolucion();
                case "5" -> this.busquedaPersonalizada();
                case "6" -> StdOut.println("Saliendo...");
                default -> StdOut.println("Opcion no valida, intente nuevamente\n");
            }
        }
    }
    public void buscarID() {
        StdOut.println("Ingrese ID el pokemon a buscar: ");
        String id = StdIn.readString();
        if (pokedex.contieneID(id)) {
            StdOut.println("Encontrado");
        }
        else {
            StdOut.println("No encontrado");
        }
    }
    public void buscarNombre() {
        StdOut.println("Ingrese nombre el pokemon a buscar: ");
        String nombre = StdIn.readString();
        if (pokedex.contieneNombre(nombre)) {
            StdOut.println("Encontrado");
        }
        else {
            StdOut.println("No encontrado");
        }

    }

    @Override
    //FALTA VERIFICADOR DE INT
    public void desplegarPorRangosId() {
        StdOut.println("---BUSQUEDA POR RANGOS---");

        StdOut.println("Escriba un rango de ID (Ejemplo: desde el 1 hasta el 151, o desde el 60 hasta el 70):");

        StdOut.println("Desde: ");
        int desde = StdIn.readInt();
        StdOut.println("Hasta: ");
        int hasta = StdIn.readInt();

        if (desde > hasta) {
            int aux = desde;
            desde = hasta;
            hasta = aux;
        }
        StdOut.println(desde);
        StdOut.println(hasta);
        for (int i = 0; i < hasta+1; i++) {
            String id = Integer.toString(i);
            pokedex.desplegarID(id);
        }


    }

    @Override
    public void desplegarTodosAlfabeticamente() {

    }

    @Override
    public void desplegarCoincideTipoIdDecreciente() {

    }

    @Override
    public void desplegarPrimeraEvolucion() {

    }

    @Override
    public void busquedaPersonalizada() {

    }
}
