package models;

public abstract class Pokemon {

    private String nombre;
    private String id;
    private String primerTipo;
    private String segundoTipo;

    public Pokemon(String nombre, String id, String primerTipo, String segundoTipo) {
        this.nombre = nombre;
        this.id = id;
        this.primerTipo = primerTipo;
        this.segundoTipo = segundoTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public String getPrimerTipo() {
        return primerTipo;
    }

    public String getSegundoTipo() {
        return segundoTipo;
    }
}
