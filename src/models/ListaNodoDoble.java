package models;

import ucn.StdOut;

public class ListaNodoDoble {
    /**
     *  The cabeza
     * @see NodoDoble
     */
    private NodoDoble cabeza;
    /**
     *  The cola
     * @see NodoDoble
     */
    private NodoDoble cola;
    /**
     * The tamanio
     */
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
     * @return tamanio.
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
        //Si es el primer dato, se inicializa la lista
        if (this.cabeza == null){
            this.cabeza = nuevoPokemon;
            this.cola = nuevoPokemon;
            tamanio++; //Aumenta el tamaño de la lista
            return;
        }
        //Se añade cuando la lista ya esta inicializada
        this.cabeza.setAnterior(nuevoPokemon);
        nuevoPokemon.setSiguiente(this.cabeza);
        this.cabeza = nuevoPokemon;
        tamanio++; //Aumenta el tamaño de la lista
    }
    /**
     * Despliega la informacion de un pokemon dado un pokemon.
     * @param pokemon
     */
    public void desplieguePokemon(Pokemon pokemon){
        StdOut.println("ID: " + pokemon.getId());
        StdOut.println("NOMBRE: " + pokemon.getNombre());

        //Busca si el pokemon dado por el parametro es de tipo Basico
        if (pokemon instanceof Basico){
            //Verifica si el pokemon tiene primera evolucion. Si es asi lo despliega
            if (((Basico) pokemon).getPrimerEvolucion() != null) {
                StdOut.println("PRIMER EVOLUCION: " + ((Basico) pokemon).getPrimerEvolucion());
            }
            //Verifica si el pokemon tiene segunda evolucion. Si es asi lo despliega
            if (((Basico) pokemon).getSegundaEvolucion() != null) {
                StdOut.println("SEGUNDA EVOLUCION: " + ((Basico) pokemon).getSegundaEvolucion());
            }
            //Verifica si el pokemon es de monotipo
            if (pokemon.getPrimerTipo().equals(pokemon.getSegundoTipo())){
                StdOut.println("TIPO: " + pokemon.getPrimerTipo());
            //Si no es asi, despliega sus 2 tipos
            }else {
                StdOut.println("PRIMER TIPO: " + pokemon.getPrimerTipo());
                StdOut.println("SEGUNDO TIPO: " + pokemon.getSegundoTipo());
            }
        //Pasada la verificacion si era de tipo Basico, se verifica si es de PrimeraEvolucion
        } else if (pokemon instanceof PrimeraEvolucion) {
            //Verifica si el pokemon tiene segunda evolucion. Si es asi lo despliega
            if (((PrimeraEvolucion) pokemon).getSegundaEvolucion() != null) {
                StdOut.println("SEGUNDA EVOLUCION: " + ((PrimeraEvolucion) pokemon).getSegundaEvolucion());
            }
            //Despliega el pokemon basico que es previo a la primer evolucion
            StdOut.println("POKEMON BASICO: " + ((PrimeraEvolucion) pokemon).getBasico());

            //Verifica si el pokemon es de monotipo
            if (pokemon.getPrimerTipo().equals(pokemon.getSegundoTipo())){
                StdOut.println("TIPO: " + pokemon.getPrimerTipo());
            //Si no es asi, despliega sus 2 tipos
            }else {
                StdOut.println("PRIMER TIPO: " + pokemon.getPrimerTipo());
                StdOut.println("SEGUNDO TIPO: " + pokemon.getSegundoTipo());
            }
        //Pasada la verificacion si era de tipo PrimeraEvolucion, se verifica si es de SegundaEvolucion
        } else if (pokemon instanceof SegundaEvolucion) {
            //Despliega su primera evolucion y el pokemon basico
            StdOut.println("PRIMERA EVOLUCION: " + ((SegundaEvolucion) pokemon).getPrimeraEvolucion());
            StdOut.println("POKEMON BASICO: " + ((SegundaEvolucion) pokemon).getBasico());

            //Verifica si es de monotipo
            if (pokemon.getPrimerTipo().equals(pokemon.getSegundoTipo())){
                StdOut.println("TIPO: " + pokemon.getPrimerTipo());
            //Si no es asi, despliega los 2 tipos
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
        //Se empieza desde la cola de la lista
        NodoDoble actual = this.cola;
        //Recorre toda la lista para buscar si coincide con el parametro dado. Si es asi, se despliega.
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
        //Se empieza desde la cola de la lista
        NodoDoble actual = this.cola;
        //Se recorre toda la lista para buscar unicamente si coincide con el id del parametro
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
     * @return the pokemon or null.
     */
    public Pokemon obtenerPokemonPorId(String id){
        //Se empieza desde la cola
        NodoDoble actual = this.cola;
        //Se recorre toda la lista para buscar si coincide con el parametro dado. Retorna con la informacion del pokemon dado
        while (actual != null){
            if (actual.getPokemon().getId().equals(id)){
                return actual.getPokemon();
            }
            actual = actual.getAnterior();
        }
        //Si no encuentra nada, retorna null
        return null;
    }

    /**
     * Busca si existe un pokemon dado un nombre.
     * @param nombre
     * @return true or false.
     */
    public boolean contieneNombre(String nombre) {
        //Se empieza desde la cola
        NodoDoble actual = this.cola;
        //Se recorre toda la lista para verificar si coincide unicamente con el parametro dado
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
        //Se empieza desde la cola
        NodoDoble actual = this.cola;
        //Se recorre la lista para verificar si coincide con el parametro dado. Si es asi retorna con la informacion del pokemon dado
        while (actual != null){
            if (actual.getPokemon().getNombre().equals(nombre)){
                return actual.getPokemon();
            }
            actual = actual.getAnterior();
        }
        //En caso de no encontrarlo, se retorna null
        return null;
    }

    /**
     * Busca si existe un pokemon dado un tipo.
     * @param tipo
     * @return true or false
     */
    public boolean contieneTipo(String tipo){
        //Se empieza desde la cola
        NodoDoble actual = this.cola;
        //Se recorre la lista para verificar si el parametro coincide con el primer o segundo tipo
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
        //Se empieza desde la cola
        NodoDoble actual = this.cola;
        //Recorre toda la lista para desplegar el tipo dado con el parametro en caso de coincidir. Se necesita que coincida con el primer o segundo tipo.
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

        // Validacion si la lista esta vacia, en este caso retorna.
        if (this.cabeza == null){
            return;
        }

        // Si hay un solo elemento no tiene sentido ordenar.
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
        //Se empieza desde la cabeza
        NodoDoble aux = this.cabeza;
        //Recorre la lista desde la cabeza
        while(aux !=null){
            desplieguePokemon(aux.getPokemon());
            aux = aux.getSiguiente();
        }
    }

    /**
     * Recorre la lista y con un condicional nos funciona para el cuarto requerimiento.
     */
    public void recorrerAtrasCuartoRequerimiento(){
        //Se empieza desde la cola
        NodoDoble aux = this.cola;
        //Recorre la lista desde la cola
        while(aux!=null) {
            Pokemon pokemonAux = aux.getPokemon();
            //Verifica si el pokemon aux es de PrimeraEvolucion y lo despliega
            if (pokemonAux instanceof PrimeraEvolucion){
                desplieguePokemon(aux.getPokemon());
            }
            aux = aux.getAnterior();
        }
    }

    /**
     * Ordena la lista nodo de manera creciente.
     */
    public void ordenar(){

        // Validacion si la lista esta vacia, en este caso retorna.
        if (this.cabeza == null){
            return;
        }

        boolean cambio = true;
        while (cambio){

            int iteracion = 0;
            cambio = false;
            for (NodoDoble i = this.cabeza; iteracion < this.tamanio; i = i.getSiguiente()){
                // Validacion para comprobar si la posicion actual es mayor que la siguiente y en caso de cumplirse realizamos el cambio
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

    /**
     * Metodo de despliegue para el quinto requerimiento, el de busqueda personalizada.
     * @param pokemon
     */
    public void desplieguePokemonBusquedaPersonalizada(Pokemon pokemon){
        StdOut.println("ID: " + pokemon.getId());
        StdOut.println("NOMBRE: " + pokemon.getNombre());
        if (pokemon.getPrimerTipo().equals(pokemon.getSegundoTipo())) {
            StdOut.println("TIPO: " + pokemon.getPrimerTipo());
        }else{
            StdOut.println("PRIMER TIPO: " + pokemon.getPrimerTipo());
            StdOut.println("SEGUNDO TIPO: " + pokemon.getSegundoTipo());
        }
    }
}
