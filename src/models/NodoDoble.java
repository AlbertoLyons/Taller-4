package models;

public class NodoDoble {
    /**
     * The pokemon
     * @see Pokemon
     */
    private Pokemon pokemon;
    /**
     * The anterior
     * @see NodoDoble
     */
    private NodoDoble anterior;
    /**
     * The siguiente
     * @see NodoDoble
     */
    private NodoDoble siguiente;

    /**
     * The constructor
     * @param pokemon
     */
    public NodoDoble(Pokemon pokemon) {

        if (pokemon == null){
            throw new IllegalArgumentException("Pokemon nulo!");
        }
        this.pokemon = pokemon;
        this.anterior = null;
        this.siguiente = null;
    }

    /**
     * @return pokemon
     */
    public Pokemon getPokemon() {
        return pokemon;
    }

    /**
     * @param pokemon
     */
    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    /**
     * @return anterior
     */
    public NodoDoble getAnterior() {
        return anterior;
    }

    /**
     * @param anterior
     */
    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }

    /**
     * @return siguiente
     */
    public NodoDoble getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente
     */
    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }

}
