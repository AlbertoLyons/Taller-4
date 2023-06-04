package services;

import models.*;
import ucn.ArchivoEntrada;
import ucn.Registro;
import ucn.StdOut;

import java.io.IOException;
import java.util.Scanner;

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

    public void despliegue(){

        pokedex.despliegue();
    }

}
