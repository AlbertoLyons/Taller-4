package models;
import java.lang.*;

public class Basico extends Pokemon{
    private String primerEvolucion;
    private String segundaEvolucion;

    public Basico(String id,String nombre, String primerEvolucion, String segundaEvolucion, String primerTipo, String segundoTipo) {
        super(nombre, id, primerTipo, segundoTipo);
        this.primerEvolucion = primerEvolucion;
        this.segundaEvolucion = segundaEvolucion;
    }

    public String getPrimerEvolucion() {
        return primerEvolucion;
    }
    public String getSegundaEvolucion() {
        return segundaEvolucion;
    }
}
