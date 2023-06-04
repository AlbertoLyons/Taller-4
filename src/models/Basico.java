package models;
import java.lang.*;

public class Basico extends Pokemon{
    private String primerEvolucion;

    public Basico(String nombre, String id, String primerTipo, String segundoTipo, String primerEvolucion) {
        super(nombre, id, primerTipo, segundoTipo);
        this.primerEvolucion = primerEvolucion;
    }
}
