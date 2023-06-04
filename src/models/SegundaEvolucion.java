package models;

public class SegundaEvolucion extends Pokemon {

    private String primeraEvolucion;
    private String basico;

    public SegundaEvolucion(String id, String nombre, String primeraEvolucion, String basico, String primerTipo, String segundoTipo) {
        super(nombre, id, primerTipo, segundoTipo);
        this.basico = basico;
        this.primeraEvolucion = primeraEvolucion;
    }

    public String getPrimeraEvolucion() {
        return primeraEvolucion;
    }

    public String getBasico() {
        return basico;
    }
}
