package models;

public class PrimeraEvolucion extends Pokemon{ //Hereda la clase abstracta Pokemon
    /**
     * The basico
     */
    private String basico;
    /**
     * The segundaEvolucion
     */
    private String segundaEvolucion;

    /**
     * The constructor
     * @param id
     * @param nombre
     * @param segundaEvolucion
     * @param basico
     * @param primerTipo
     * @param segundoTipo
     */
    public PrimeraEvolucion(String id, String nombre, String segundaEvolucion, String basico, String primerTipo, String segundoTipo) {
        super(nombre, id, primerTipo, segundoTipo);
        this.basico = basico;
        this.segundaEvolucion = segundaEvolucion;
    }

    /**
     * @return basico
     */
    public String getBasico() {
        return basico;
    }

    /**
     * @return segundaEvolucion
     */
    public String getSegundaEvolucion() {
        return segundaEvolucion;
    }
}
