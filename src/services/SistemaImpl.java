package services;

import models.*;
import ucn.ArchivoEntrada;
import ucn.Registro;

import java.io.IOException;
import java.util.Scanner;

public class SistemaImpl implements Sistema{

    private ListaNodoDoble pokedex;

    public void inicio() throws IOException {

        ArchivoEntrada archEnt = new ArchivoEntrada("kanto.txt");
        Pokemon pokemonLectura;

        while (!archEnt.isEndFile()) {

            Registro regEnt = archEnt.getRegistro();
            String id = regEnt.getString();
            String pokemon = regEnt.getString();

            String etapa = regEnt.getString();
            switch (etapa) {
                case "Basico" -> {
                    String primerEvolucion = regEnt.getString();
                    String primerTipo = regEnt.getString();
                    String segundoTipo = regEnt.getString();
                    pokemonLectura = new Basico(id, pokemon, primerEvolucion, primerTipo, segundoTipo);
                    this.pokedex.agregar(pokemonLectura);

                }
                case "Primera Evolucion" -> {
                    String segundaEvolucion = regEnt.getString();
                    String basico = regEnt.getString();
                    String primerTipo = regEnt.getString();
                    String segundoTipo = regEnt.getString();
                    pokemonLectura = new PrimeraEvolucion(id, pokemon, primerTipo, segundoTipo, segundaEvolucion, basico);
                }
                case "Segunda Evolucion" -> {
                    String primeraEvolucion = regEnt.getString();
                    String basico = regEnt.getString();
                    String primerTipo = regEnt.getString();
                    String segundoTipo = regEnt.getString();
                    pokemonLectura = new SegundaEvolucion(id, pokemon, primerTipo, segundoTipo, primeraEvolucion, basico);
                }

            }
            archEnt.close();

        }
    }
}
