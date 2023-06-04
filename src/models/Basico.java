package models;
import java.lang.*;

public class Basico extends Pokemon{
    private String primerEvolucion;

    public Basico(String nombre, String id, String primerEvolucion, String primerTipo, String segundoTipo) {
        super(nombre, id, primerTipo, segundoTipo);
        this.primerEvolucion = primerEvolucion;
    }
}
