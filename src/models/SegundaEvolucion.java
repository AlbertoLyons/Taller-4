package models;

public class SegundaEvolucion extends Pokemon {

    private String evolucionPrevia;
    private String basico;

    public SegundaEvolucion(String id, String nombre, String primerTipo, String segundoTipo, String evolucionPrevia, String basico) {
        super(nombre, id, primerTipo, segundoTipo);
        this.basico = basico;
        this.evolucionPrevia = evolucionPrevia;
    }
}
