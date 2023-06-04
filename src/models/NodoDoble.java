package models;

public class NodoDoble {

    private Pokemon pokemon;
    private NodoDoble anterior;
    private NodoDoble siguiente;

    public NodoDoble(Pokemon pokemon) {

        if (pokemon == null){
            throw new IllegalArgumentException("Pokemon nulo!");
        }
        this.pokemon = pokemon;
        this.anterior = null;
        this.siguiente = null;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public NodoDoble getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }

    public NodoDoble getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }

}
