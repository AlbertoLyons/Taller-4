package models;

public class PrimeraEvolucion extends Pokemon{

    private String basico;
    private String segundaEvolucion;

    public PrimeraEvolucion(String id, String nombre, String segundaEvolucion, String basico, String primerTipo, String segundoTipo) {
        super(nombre, id, primerTipo, segundoTipo);
        this.basico = basico;
        this.segundaEvolucion = segundaEvolucion;
    }

    public String getBasico() {
        return basico;
    }

    public String getSegundaEvolucion() {
        return segundaEvolucion;
    }
}
