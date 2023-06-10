package models;

public abstract class Pokemon { //Hereda la clase abstracta Pokemon
    /**
     * The nombre
     */
    private String nombre;
    /**
     * The id
     */
    private String id;
    /**
     * The primerTipo
     */
    private String primerTipo;
    /**
     * The segundoTipo
     */
    private String segundoTipo;

    /**
     * The constructor
     * @param nombre
     * @param id
     * @param primerTipo
     * @param segundoTipo
     */
    public Pokemon(String nombre, String id, String primerTipo, String segundoTipo) {
        this.nombre = nombre;
        this.id = id;
        this.primerTipo = primerTipo;
        this.segundoTipo = segundoTipo;
    }

    /**
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @return primerTipo
     */
    public String getPrimerTipo() {
        return primerTipo;
    }

    /**
     * @return segundoTipo
     */
    public String getSegundoTipo() {
        return segundoTipo;
    }
}
