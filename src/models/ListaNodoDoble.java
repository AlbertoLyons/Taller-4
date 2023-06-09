package models;

import ucn.StdOut;

public class ListaNodoDoble {

    private NodoDoble cabeza;
    private NodoDoble cola;
    private int tamanio;

    /**
     * The constructor.
     */
    public ListaNodoDoble() {
        this.cabeza = null;
        this.cola = null;
        this.tamanio = 0;
    }
    /**
     * @return the tamanio.
     */
    public int getTamanio() {
        return tamanio;
    }

    /**
     * Agrega un pokemon a la lista.
     * @param pokemon
     */
    public void agregar(Pokemon pokemon){
        NodoDoble nuevoPokemon = new NodoDoble(pokemon);

        if (this.cabeza == null){
            this.cabeza = nuevoPokemon;
            this.cola = nuevoPokemon;
            tamanio++;
            return;
        }
        this.cabeza.setAnterior(nuevoPokemon);
        nuevoPokemon.setSiguiente(this.cabeza);
        this.cabeza = nuevoPokemon;
        tamanio++;
    }
    /**
     * Despliega la informacion de un pokemon dado un pokemon.
     * @param pokemon
     */
    public void desplieguePokemon(Pokemon pokemon){
        StdOut.println("ID: " + pokemon.getId());
        StdOut.println("NOMBRE: " + pokemon.getNombre());

        if (pokemon instanceof Basico){
            if (((Basico) pokemon).getPrimerEvolucion() != null) {
                StdOut.println("PRIMER EVOLUCION: " + ((Basico) pokemon).getPrimerEvolucion());
            }
            if (((Basico) pokemon).getSegundaEvolucion() != null) {
                StdOut.println("SEGUNDA EVOLUCION: " + ((Basico) pokemon).getSegundaEvolucion());
            }
            if (pokemon.getPrimerTipo().equals(pokemon.getSegundoTipo())){
                StdOut.println("TIPO: " + pokemon.getPrimerTipo());
            }else {
                StdOut.println("PRIMER TIPO: " + pokemon.getPrimerTipo());
                StdOut.println("SEGUNDO TIPO: " + pokemon.getSegundoTipo());
            }
        } else if (pokemon instanceof PrimeraEvolucion) {
            if (((PrimeraEvolucion) pokemon).getSegundaEvolucion() != null) {
                StdOut.println("SEGUNDA EVOLUCION: " + ((PrimeraEvolucion) pokemon).getSegundaEvolucion());
            }

            StdOut.println("POKEMON BASICO: " + ((PrimeraEvolucion) pokemon).getBasico());

            if (pokemon.getPrimerTipo().equals(pokemon.getSegundoTipo())){
                StdOut.println("TIPO: " + pokemon.getPrimerTipo());
            }else {
                StdOut.println("PRIMER TIPO: " + pokemon.getPrimerTipo());
                StdOut.println("SEGUNDO TIPO: " + pokemon.getSegundoTipo());
            }

        } else if (pokemon instanceof SegundaEvolucion) {
            StdOut.println("PRIMERA EVOLUCION: " + ((SegundaEvolucion) pokemon).getPrimeraEvolucion());
            StdOut.println("POKEMON BASICO: " + ((SegundaEvolucion) pokemon).getBasico());

            if (pokemon.getPrimerTipo().equals(pokemon.getSegundoTipo())){
                StdOut.println("TIPO: " + pokemon.getPrimerTipo());
            }else {
                StdOut.println("PRIMER TIPO: " + pokemon.getPrimerTipo());
                StdOut.println("SEGUNDO TIPO: " + pokemon.getSegundoTipo());
            }
        }
        StdOut.println("");
    }

    /**
     * Despliegue de la informacion de un pokemon dado su id.
     * @param id
     */
    public void desplegarID(String id) {
        NodoDoble actual = this.cola;
        while (actual != null) {
            if (actual.getPokemon().getId().equalsIgnoreCase(id)) {
                desplieguePokemon(actual.getPokemon());
            }
            actual = actual.getAnterior();
        }
    }

    /**
     * Busca si existe un pokemon dado un ID.
     * @param id
     * @return true or false.
     */
    public boolean contieneID(String id) {
        NodoDoble actual = this.cola;

        while(actual != null){
            if (actual.getPokemon().getId().equals(id)){
                return true;
            }
            actual = actual.getAnterior();
        }
        return false;

    }

    /**
     * Busca un pokemon dado el ID y lo retorna en caso de ser encontrado.
     * @param id
     * @return the pokemon.
     */
    public Pokemon obtenerPokemonPorId(String id){
        NodoDoble actual = this.cola;
        while (actual != null){
            if (actual.getPokemon().getId().equals(id)){
                return actual.getPokemon();
            }
            actual = actual.getAnterior();
        }
        return null;
    }

    /**
     * Busca si existe un pokemon dado un nombre.
     * @param nombre
     * @return true or false.
     */
    public boolean contieneNombre(String nombre) {
        NodoDoble actual = this.cola;

        while(actual != null){
            if (actual.getPokemon().getNombre().equals(nombre)){
                return true;
            }
            actual = actual.getAnterior();
        }
        return false;
    }

    /**
     * Busca un pokemon dado el nombre y lo retorna en caso de ser encontrado.
     * @param nombre
     * @return the pokemon.
     */
    public Pokemon obtenerPokemonPorNombre(String nombre){

        NodoDoble actual = this.cola;
        while (actual != null){
            if (actual.getPokemon().getNombre().equals(nombre)){
                return actual.getPokemon();
            }
            actual = actual.getAnterior();
        }
        return null;
    }

    /**
     * Busca si existe un pokemon dado un tipo.
     * @param tipo
     * @return true or false
     */
    public boolean contieneTipo(String tipo){
        NodoDoble actual = this.cola;

        while(actual != null){
            if (actual.getPokemon().getPrimerTipo().equals(tipo) || actual.getPokemon().getSegundoTipo().equals(tipo)){
                return true;
            }
            actual = actual.getAnterior();
        }
        return false;
    }

    /**
     * Despliega un pokemon dado un tipo.
     * @param tipo
     */
    public void desplegarTipo(String tipo){
        NodoDoble actual = this.cola;
        while (actual != null) {
            if (actual.getPokemon().getPrimerTipo().equals(tipo) || actual.getPokemon().getSegundoTipo().equals(tipo)) {
                desplieguePokemon(actual.getPokemon());
            }
            actual = actual.getAnterior();
        }
    }

    /**
     * Ordena alfabeticamente la lista de nodo, intercambiado los pokemons de lugar.
     */
    public void ordenarAlfabeticamente(){

        if (this.cabeza == null){
            return;
        }

        if (this.cabeza.getSiguiente() == this.cabeza){
            return;
        }

        boolean cambio = true;

        while (cambio){
            int iteracion = 0;
            cambio = false;
            for (NodoDoble i = this.cabeza; iteracion < this.tamanio; i=i.getSiguiente()){
                if (i.getSiguiente() != null && (i.getPokemon().getNombre().compareTo(i.getSiguiente().getPokemon().getNombre())) > 0){
                    Pokemon pokemon = i.getPokemon();
                    i.setPokemon(i.getSiguiente().getPokemon());
                    i.getSiguiente().setPokemon(pokemon);
                    cambio = true;
                }
                iteracion++;
            }
        }
    }

    /**
     * Recorre la lista hacia adelante, esta modificado para el requerimiento 2.
     */
    public void recorrerAdelante(){
        NodoDoble aux = this.cabeza;
        while(aux !=null){
            desplieguePokemon(aux.getPokemon());
            aux = aux.getSiguiente();
        }
        return;
    }

    /**
     * Recorre la lista y con un condificional nos funciona para el cuarto requerimiento.
     */
    public void recorrerAtrasCuartoRequerimiento(){
        NodoDoble aux = this.cola;
        while(aux!=null) {
            Pokemon pokemonAux = aux.getPokemon();
            if (pokemonAux instanceof PrimeraEvolucion){
                desplieguePokemon(aux.getPokemon());
            }
            aux = aux.getAnterior();
        }
        return;
    }

    /**
     * Ordena la lista nodo.
     */
    public void ordenar(){

        if (this.cabeza == null){
            return;
        }

        boolean cambio = true;

        while (cambio){

            int iteracion = 0;
            cambio = false;
            for (NodoDoble i = this.cabeza; iteracion < this.tamanio; i = i.getSiguiente()){
                if (i.getSiguiente() != null && (Integer.parseInt(i.getPokemon().getId()) > Integer.parseInt(i.getSiguiente().getPokemon().getId()))){
                    Pokemon pokemon = i.getPokemon();
                    i.setPokemon(i.getSiguiente().getPokemon());
                    i.getSiguiente().setPokemon(pokemon);
                    cambio = true;
                }
                iteracion++;
            }
        }
    }

    public void despliegueEvoluciones(){



    }
}
