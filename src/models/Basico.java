package models;
import java.lang.*;

public class Basico extends Pokemon{ //Hereda la clase abstracta Pokemon
    /**
     * The primerEvolucion
     */
    private String primerEvolucion;
    /**
     * The segundaEvolucion
     */
    private String segundaEvolucion;

    /**
     * The constructor
     * @param id
     * @param nombre
     * @param primerEvolucion
     * @param segundaEvolucion
     * @param primerTipo
     * @param segundoTipo
     */

    public Basico(String id,String nombre, String primerEvolucion, String segundaEvolucion, String primerTipo, String segundoTipo) {
        super(nombre, id, primerTipo, segundoTipo);
        this.primerEvolucion = primerEvolucion;
        this.segundaEvolucion = segundaEvolucion;
    }

    /**
     * @return primerEvolucion
     */
    public String getPrimerEvolucion() {
        return primerEvolucion;
    }

    /**
     * @return segundaEvolucion
     */
    public String getSegundaEvolucion() {
        return segundaEvolucion;
    }
}
