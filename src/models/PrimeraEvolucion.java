package models;

public class PrimeraEvolucion extends Pokemon{

    private String basico;
    private String segundaEvolucion;

    public PrimeraEvolucion(String id, String nombre, String primerTipo, String segundoTipo, String segundaEvolucion, String basico) {
        super(nombre, id, primerTipo, segundoTipo);
        this.basico = basico;
        this.segundaEvolucion = segundaEvolucion;
    }
}
