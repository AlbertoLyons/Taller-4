package models;

public class SegundaEvolucion extends Pokemon { //Hereda la clase abstracta Pokemon
    /**
     * The primeraEvolucion
     */
    private String primeraEvolucion;
    /**
     * The basico
     */
    private String basico;

    /**
     * The constructor
     * @param id
     * @param nombre
     * @param primeraEvolucion
     * @param basico
     * @param primerTipo
     * @param segundoTipo
     */

    public SegundaEvolucion(String id, String nombre, String primeraEvolucion, String basico, String primerTipo, String segundoTipo) {
        super(nombre, id, primerTipo, segundoTipo);
        this.basico = basico;
        this.primeraEvolucion = primeraEvolucion;
    }

    /**
     * @return primeraEvolucion
     */
    public String getPrimeraEvolucion() {
        return primeraEvolucion;
    }

    /**
     * @return basico
     */
    public String getBasico() {
        return basico;
    }
}
