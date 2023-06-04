package models;

public class ListaNodoDoble {

    private NodoDoble cabeza;
    private NodoDoble cola;

    public ListaNodoDoble() {
        this.cabeza = null;
        this.cola = null;
    }

    public boolean agregar(Pokemon pokemon){

        NodoDoble nuevoPokemon = new NodoDoble(pokemon);

        if (this.cabeza == null){
            this.cabeza = nuevoPokemon;
            this.cola = nuevoPokemon;
            return true;
        }

        this.cabeza.setAnterior(nuevoPokemon);
        nuevoPokemon.setSiguiente(this.cabeza);
        this.cabeza = nuevoPokemon;

        return true;
    }

    public String desplieguePrueba(){

        StringBuilder sb = new StringBuilder();
        for (NodoDoble aux = this.cabeza; aux != null; aux = aux.getSiguiente()){
            sb.append(aux.getPokemon().toString()).append(",");
        }
        return sb.toString();
    }

}
